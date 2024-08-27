package com.todo.todo.controller;


import com.todo.todo.dto.request.CommentRequestDto;
import com.todo.todo.dto.response.CommentResponseDto;
import com.todo.todo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/{id}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(todoId,commentRequestDto);
    }

    @GetMapping
    public List<CommentResponseDto> getCommentByTodoId(@PathVariable Long todoId){
        return commentService.getCommentByTodoId(todoId);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComments(commentId,commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
