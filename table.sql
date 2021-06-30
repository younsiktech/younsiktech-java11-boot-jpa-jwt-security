/*
필요시 emoji 저장을 위해서 사용
데이터베이스 생성
CREATE DATABASE { DATABASE_NAME } CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
또는 변경
ALTER DATABASE { DATABASE_NAME }  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
*/

CREATE TABLE users
(
    id bigint AUTO_INCREMENT
        PRIMARY KEY,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(255) NULL,
    name varchar(20) NOT NULL,
    nickname varchar(30) NOT NULL,
    phone_number varchar(255) NOT NULL,
    gender varchar(255) NULL,
    status varchar(255) NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    CONSTRAINT uk_email UNIQUE (email)
) ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4  COLLATE=utf8mb4_unicode_ci;

CREATE TABLE purchases
(
    id varchar(12) NOT NULL
        PRIMARY KEY,
    user_id bigint NULL,
    name varchar(255) NULL,
    payment_date datetime NULL,
    status varchar(255) NULL,
    updated_at datetime NULL,
    created_at datetime NULL
) ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4  COLLATE=utf8mb4_unicode_ci;

CREATE INDEX purchases_user_id_index ON purchases (user_id);