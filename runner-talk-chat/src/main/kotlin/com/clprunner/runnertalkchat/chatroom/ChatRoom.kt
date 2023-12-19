package com.clprunner.runnertalkchat.chatroom

import com.clprunner.runnertalkchat.common.document.MongoBaseDocument
import lombok.*
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ChatRooms")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
class ChatRoom(
    private var name: String,
    private var description: String?,
    private var creatorId: String,
    private var participants: Array<String>
) : MongoBaseDocument()
