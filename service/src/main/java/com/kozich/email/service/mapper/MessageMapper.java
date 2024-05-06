package com.kozich.email.service.mapper;

import com.kozich.email.dao.entity.MessageEntity;
import com.kozich.email.service.api.dto.MessageDto;

import java.util.ArrayList;
import java.util.List;

public class MessageMapper {

    public MessageEntity messageDtoToMessageEntity(MessageDto messageDto){
        return new MessageEntity()
                .setRecipientEmail(messageDto.getRecipientEmail())
                .setTopic(messageDto.getTopic())
                .setText(messageDto.getText());
    }

    public MessageDto messageEntityToMessageDto(MessageEntity messageEntity){
        return new MessageDto()
                .setRecipientEmail(messageEntity.getRecipientEmail())
                .setTopic(messageEntity.getTopic())
                .setText(messageEntity.getText());
    }

    public List<MessageEntity> messageDtoListToMessageEntityList(List<MessageDto> messageDtoList){
        List<MessageEntity> messageEntityList = new ArrayList<>();

        for (MessageDto messageDto : messageDtoList) {
            messageEntityList.add(messageDtoToMessageEntity(messageDto));
        }
        return messageEntityList;
    }

    public List<MessageDto> messageEntityListToMessageDtoList(List<MessageEntity> messageEntityList){
        List<MessageDto> messageDtoList = new ArrayList<>();

        for (MessageEntity messageEntity : messageEntityList) {
            messageDtoList.add(messageEntityToMessageDto(messageEntity));
        }
        return messageDtoList;
    }
}
