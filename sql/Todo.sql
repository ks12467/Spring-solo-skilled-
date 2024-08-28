CREATE TABLE TodoEntity (
                            todoId BIGINT AUTO_INCREMENT PRIMARY KEY,
                            title VARCHAR(50) NOT NULL,
                            contents VARCHAR(500) NOT NULL,
                            createdAt DATETIME NOT NULL,
                            updatedAt DATETIME NOT NULL,
                            userId BIGINT,
                            FOREIGN KEY (userId) REFERENCES UserEntity(id)
);