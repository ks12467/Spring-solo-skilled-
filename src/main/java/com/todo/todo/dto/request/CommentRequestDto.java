package com.todo.todo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long commnetId;
    private String replyUserName;
    private String replyContents;


}
