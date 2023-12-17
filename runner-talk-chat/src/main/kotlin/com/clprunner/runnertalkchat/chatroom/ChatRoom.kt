package com.clprunner.runnertalkchat.chatroom

import com.clprunner.runnertalkchat.common.document.MongoBaseDocument
import lombok.*
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ChatRooms")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
class ChatRoom : MongoBaseDocument() {
    private lateinit var name: String

    private var description: String? = null

    private lateinit var creatorId: String

    private lateinit var participants: Array<String>

//    @TODO need to add lastMessage field
}
