CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL,
    is_enabled TINYINT(1) NOT NULL,
    account_non_expired TINYINT(1) NOT NULL,
    account_non_locked TINYINT(1) NOT NULL,
    credentials_non_expired TINYINT(1) NOT NULL,
    CONSTRAINT admins_pk PRIMARY KEY (id),
    CONSTRAINT admins_unique_em UNIQUE KEY (email),
    CONSTRAINT admins_unique_un UNIQUE KEY (username)
);

CREATE TABLE projects (
    id BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    technologies JSON NOT NULL,
    personal TINYINT(1) NOT NULL,
    CONSTRAINT projects_pk PRIMARY KEY (id),
    CONSTRAINT projects_unique UNIQUE KEY (title)
);