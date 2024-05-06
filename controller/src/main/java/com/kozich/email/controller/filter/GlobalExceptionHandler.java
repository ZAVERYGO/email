package com.kozich.email.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GlobalExceptionHandler {

    private final static Logger logger = LogManager.getLogger();

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleIllegal(IllegalArgumentException e){
        logger.log(Level.WARN, "Пользователь сделал что-то не так", e);

        Map<String, Object> errorObj = new HashMap<>();
        errorObj.put("error", e.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleIllegal(Exception e){
        logger.log(Level.ERROR, "Ошибка на стороне сервера", e);

        Map<String, Object> errorObj = new HashMap<>();
        errorObj.put("error", e.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}