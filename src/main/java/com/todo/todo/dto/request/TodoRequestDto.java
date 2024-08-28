package com.todo.todo.dto.request;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TodoRequestDto {
    private Long userId;
    private String userName;
    private String title;
    private String contents;


}
