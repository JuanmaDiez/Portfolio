CREATE TABLE admins (
    id AUTO_INCREMENT BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL,
    CONSTRAINT admins_pk PRIMARY KEY (id),
    CONSTRAINT admins_unique_em UNIQUE KEY (email),
    CONSTRAINT admins_unique_un UNIQUE KEY (username)
);

CREATE TABLE projects (
    id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    technologies JSON NOT NULL,
    personal TINY_INT(1) NOT NULL,
    CONSTRAINT projects_pk PRIMARY KEY (id),
    CONSTRAINT projects_unique UNIQUE KEY (title),
);