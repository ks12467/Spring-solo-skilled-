package com.todo.todo.dto.response;

import com.todo.todo.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long replyId;
    private String replyContents;
    private LocalDateTime replyCreatedAt;

    public CommentResponseDto(CommentEntity commentEntity) {
        this.replyId = commentEntity.getReplyId();
        this.replyContents = commentEntity.getContents();
        this.replyCreatedAt = commentEntity.getReplyCreatedAt();
    }
}
