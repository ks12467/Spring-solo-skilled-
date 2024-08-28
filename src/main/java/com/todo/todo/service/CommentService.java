package com.todo.todo.service;


import com.todo.todo.dto.request.CommentRequestDto;
import com.todo.todo.dto.response.CommentResponseDto;
import com.todo.todo.entity.CommentEntity;
import com.todo.todo.entity.TodoEntity;
import com.todo.todo.repository.CommentRepository;
import com.todo.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    public CommentResponseDto createComment(Long todoId, CommentRequestDto commentRequestDto) {
        TodoEntity todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("일정을 찾을 수 없습니다."));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContents(commentRequestDto.getReplyContents());
        commentEntity.setReplyCreatedAt(LocalDateTime.now());
        commentEntity.setTodo(todo);

        CommentEntity saveComment = commentRepository.save(commentEntity);
        return new CommentResponseDto(saveComment);
    }

    public List<CommentResponseDto> getCommentByTodoId(Long todoId) {
        List<CommentEntity> comments = commentRepository.findByTodo_TodoId(todoId);
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    public CommentResponseDto updateComments(Long commentId, CommentRequestDto commentRequestDto) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("댓글을 찾을 수 없습니다."));

        comment.setContents(commentRequestDto.getReplyContents());
        comment.setReplyCreatedAt(LocalDateTime.now());

        CommentEntity updateComment = commentRepository.save(comment);
        return new CommentResponseDto(updateComment);
    }

    public void deleteComment(Long commentId) {
        if(!commentRepository.existsById(commentId)) {
            throw new NoSuchElementException("댓글을 찾을 수 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
