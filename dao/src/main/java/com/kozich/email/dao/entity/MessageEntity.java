package com.kozich.email.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "message", schema = "app")
public class MessageEntity {

    @Id
    @Column(name = "message_id", nullable = false, unique = true)
    private Long messageId;
    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;
    @Column(name = "topic", nullable = false)
    private String topic;
    @Column(name = "text", nullable = false)
    private String text;

    public Long getMessageId() {
        return messageId;
    }

    public MessageEntity setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public MessageEntity setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public MessageEntity setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getText() {
        return text;
    }

    public MessageEntity setText(String text) {
        this.text = text;
        return this;
    }
}
