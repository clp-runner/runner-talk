package com.clprunner.runnertalkchat.common.document

import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

@Getter
abstract class MongoBaseDocument {
    @Id
    private val id: String? = null

    @CreatedDate
    private val createdAt: ZonedDateTime? = null

    @LastModifiedDate
    private val updatedAt: ZonedDateTime? = null
}