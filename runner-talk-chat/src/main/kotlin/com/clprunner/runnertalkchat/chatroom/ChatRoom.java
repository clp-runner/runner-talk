package com.clprunner.runnertalkchat.chatroom;

import com.clprunner.runnertalkchat.common.document.MongoBaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ChatRooms")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
public class ChatRoom extends MongoBaseDocument {

    @NonNull
    private String name;
    private String description;
    @NonNull
    private String creatorId;
    @NonNull
    private String[] participants;

//    @TODO need to add lastMessage field
}
