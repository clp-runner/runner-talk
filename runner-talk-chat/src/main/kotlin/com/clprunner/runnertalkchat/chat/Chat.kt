package com.clprunner.runnertalkchat.chat


import lombok.*
import java.sql.Timestamp
import javax.persistence.Entity
import com.querydsl.core.annotations.QueryEntity
import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.mapping.Document

data class DBSave (
    val publish: Boolean,
    val subscribe: Boolean
)


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
@Entity
@QueryEntity
@Document(collection = "chatcontents")
data class ChatDTO (
    @NotNull
    val id: Long,
    @NotNull
    val chatroom: Long,
    @NotNull
    val type: String,
    @NotNull
    val content: String,
    @NotNull
    val sender: Short,
    val readBy: Array<Short>,

    @NotNull
    val timestamp: Timestamp,
    val dbSave: DBSave
)