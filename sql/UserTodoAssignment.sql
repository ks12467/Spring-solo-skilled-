CREATE TABLE UserTodoAssignment (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    userId BIGINT,
                                    todoId BIGINT,
                                    FOREIGN KEY (userId) REFERENCES UserEntity(id),
                                    FOREIGN KEY (todoId) REFERENCES TodoEntity(todoId)
);