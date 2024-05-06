package com.kozich.email.service.impl;

import com.kozich.email.dao.api.MessageDao;
import com.kozich.email.dao.entity.MessageEntity;
import com.kozich.email.service.api.MessageService;
import com.kozich.email.service.api.dto.MessageDto;
import com.kozich.email.service.mapper.MessageMapper;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageDao messageDao, MessageMapper messageMapper) {
        this.messageDao = messageDao;
        this.messageMapper = messageMapper;
    }

    @Override
    public Optional<MessageDto> get(Long id) {
        return Optional.ofNullable(messageMapper.messageEntityToMessageDto(messageDao.get(id).get())) ;
    }

    @Override
    public List<MessageDto> get(Integer page, Integer size) {
        return messageMapper.messageEntityListToMessageDtoList(messageDao.get(page, size));
    }

    @Override
    public MessageDto create(MessageDto message) {
        return messageMapper.messageEntityToMessageDto
                (
                messageDao.create(messageMapper.messageDtoToMessageEntity(message))
                );
    }
}
