package com.clprunner.runnertalkchat.chat

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository: MongoRepository<ChatDTO, String>, QuerydslPredicateExecutor<ChatDTO>
