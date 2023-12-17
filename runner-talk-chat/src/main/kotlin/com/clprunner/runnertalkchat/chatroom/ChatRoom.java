package com.clprunner.runnertalkchat.chatroom;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ChatRooms")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class ChatRoom {
}
