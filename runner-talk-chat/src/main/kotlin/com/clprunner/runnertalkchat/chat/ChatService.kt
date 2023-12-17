package com.clprunner.runnertalkchat.chat

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class ChatService{
//    fun GetChatContent(): ChatDTO{
//        return ChatDTO(1, 1, "text", "안녕", 1, emptyArray<Short>(), Timestamp(System.currentTimeMillis()), DBSave(false, false));
//    }

    private val chatRepository: ChatRepository? = null
//    fun ListChatContent(room: Short): List<ChatDTO> {
//        return emptyList()
//    }
//
//    fun CreateChatContent(chatDTO: ChatDTO) {
//        chatRepository.insert(chatDTO)
//    }
//
//    fun DeleteChatContentByMsg(chatRoom: Short, content: String) {
//        return
//    }
//
//    fun DeleteChatContentByRoom(chatRoom: Short) {
//        return
//    }
}