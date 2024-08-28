CREATE TABLE CommentEntity (
                               replyId BIGINT AUTO_INCREMENT PRIMARY KEY,
                               contents VARCHAR(200) NOT NULL,
                               replyCreatedAt DATETIME NOT NULL,
                               todoId BIGINT,
                               FOREIGN KEY (todoId) REFERENCES TodoEntity(todoId)
);