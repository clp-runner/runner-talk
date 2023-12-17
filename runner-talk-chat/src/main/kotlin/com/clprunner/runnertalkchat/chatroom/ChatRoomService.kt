package com.clprunner.runnertalkchat.chatroom

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class ChatRoomService(val chatRollRepository: ChatRoomRepository) {

}
