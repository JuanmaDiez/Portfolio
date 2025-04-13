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
    titleEsp VARCHAR(255) NOT NULL,
    titleEn VARCHAR(255) NOT NULL,
    descriptionEsp TEXT NOT NULL,
    descriptionEn TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    personal TINYINT(1) NOT NULL,
    site VARCHAR(255) NOT NULL,
    code VARCHAR(255),
    created_at DATETIME NOT NULL,
    CONSTRAINT projects_pk PRIMARY KEY (id),
    CONSTRAINT projects_unique_esp UNIQUE KEY (titleEsp),
    CONSTRAINT projects_unique_en UNIQUE KEY (titleEn)
);

CREATE TABLE technologies (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255) NOT NULL,
    CONSTRAINT technologies_pk PRIMARY KEY (id),
    CONSTRAINT technologies_unique UNIQUE KEY (name)
);

CREATE TABLE project_technologies (
    project_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    CONSTRAINT project_technologies_pk PRIMARY KEY (project_id, technology_id),
    CONSTRAINT project_technologies_projects_fk FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT project_technologies_technologies_fk FOREIGN KEY (technology_id) REFERENCES technologies(id) ON DELETE CASCADE ON UPDATE CASCADE
);