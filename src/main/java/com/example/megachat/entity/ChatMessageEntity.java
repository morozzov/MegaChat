package com.example.megachat.entity;

import com.example.megachat.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageEntity {

    private MessageType type;
    private String content;
    private String sender;
}
