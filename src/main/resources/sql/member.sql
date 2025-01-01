CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    name      VARCHAR(255),
    password  VARCHAR(255),
    role      VARCHAR(255)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;