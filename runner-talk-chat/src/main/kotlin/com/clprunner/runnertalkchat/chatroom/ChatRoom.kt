package com.clprunner.runnertalkchat.chatroom

import com.clprunner.runnertalkchat.common.document.MongoBaseDocument
import lombok.*
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ChatRooms")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
class ChatRoom(
    var name: String,
    var description: String?,
    var creatorId: String,
    var participants: Array<String>
) : MongoBaseDocument()
