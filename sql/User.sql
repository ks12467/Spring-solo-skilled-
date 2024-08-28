CREATE TABLE UserEntity (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            userName VARCHAR(20) NOT NULL,
                            email VARCHAR(100) NOT NULL,
                            createDate DATETIME NOT NULL,
                            updateDate DATETIME NOT NULL
);