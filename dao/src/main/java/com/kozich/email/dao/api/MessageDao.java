package com.kozich.email.dao.api;

import com.kozich.email.dao.entity.MessageEntity;

import java.util.List;
import java.util.Optional;

public interface MessageDao {

    Optional<MessageEntity> get(Long id);
    List<MessageEntity> get(Integer page, Integer size);
    MessageEntity create(MessageEntity messageEntity);
}
