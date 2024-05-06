package com.kozich.email.service.api;

import com.kozich.email.dao.entity.MessageEntity;
import com.kozich.email.service.api.dto.MessageDto;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<MessageDto> get(Long id);
    List<MessageDto> get(Integer page, Integer size);
    MessageDto create(MessageDto message);
}
