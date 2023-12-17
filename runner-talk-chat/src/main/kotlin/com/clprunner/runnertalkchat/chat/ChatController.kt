package com.clprunner.runnertalkchat.chat

//import org.springframework.web.bind.annotation.DeleteMapping
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chat/contents")
class ChatController {
    private val chatService: ChatService? = null
    // 메세지 추가
//    @PostMapping("/api/v1/chat/contents")
//    fun InsertChatContent(@RequestBody chatDTO: ChatDTO) = {
//        chatService.CreateChatContent(chatDTO)
//    }
//
//    // 채팅방 전체 메세지 조회
//    @GetMapping("/api/v1/chat/contents/room/{room}")
//    fun ListChatContent(@PathVariable room: Short) = chatService.ListChatContent(room)
//
//    // 메세지 단일 삭제
//    @DeleteMapping("/api/v1/chat/contents/room/{room}/message/{message}")
//    fun DeleteChatContentByMsg(@PathVariable room: Short, @PathVariable message: String) = chatService.DeleteChatContentByMsg(room, message)
//
//    @DeleteMapping("/api/v1/chat/contents/room/{room}")
//    fun DeleteChatByRoom(@PathVariable room: Short) = chatService.DeleteChatContentByRoom(room)
}