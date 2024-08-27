package com.todo.todo.repository;

import com.todo.todo.entity.TodoEntity;
import com.todo.todo.entity.UserEntity;
import com.todo.todo.entity.UserTodoAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTodoAssignmentRepository  extends JpaRepository<UserTodoAssignment, Long> {
    List<UserTodoAssignment> findByUser(UserEntity user);
    List<UserTodoAssignment> findByTodo(TodoEntity todo);
}
