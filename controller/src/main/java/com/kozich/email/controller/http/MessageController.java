package com.kozich.email.controller.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.email.controller.factory.AppFactory;
import com.kozich.email.controller.util.EmailUtil;
import com.kozich.email.dao.entity.MessageEntity;
import com.kozich.email.service.api.MessageService;
import com.kozich.email.service.api.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final ObjectMapper mapper = AppFactory.getMapper();
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    public String get(@RequestParam(value = "id", required = false) Long id,
                      @RequestParam(value = "page", required = false) Integer page,
                      @RequestParam(value = "size", required = false) Integer size) throws IOException {
        if (id != null) {
            return mapper.writeValueAsString(messageService.get(id));
        } else if(page != null && size != null){
            List<MessageDto> data = messageService.get(page, size);
            return mapper.writeValueAsString(data);
        }
        else{
            throw new IllegalArgumentException("не указаны параметры");
        }
    }

    @PostMapping(value = "/send-message-to-email")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody MessageDto message) {
        messageService.create(message);
        try {
            EmailUtil.sendMessage(message);
            return ResponseEntity.ok("сообщение отправлено на адрес" + message.getRecipient());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка отправки сообщения на email: " + e.getMessage());
        }
    }
}

