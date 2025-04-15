CREATE TABLE admins (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_enabled BOOLEAN NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL
);

CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    title_esp VARCHAR(255) NOT NULL UNIQUE,
    title_en VARCHAR(255) NOT NULL UNIQUE,
    description_esp TEXT NOT NULL,
    description_en TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    personal BOOLEAN NOT NULL,
    site VARCHAR(255) NOT NULL,
    code VARCHAR(255),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE technologies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    icon VARCHAR(255) NOT NULL
);

CREATE TABLE project_technologies (
    project_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    PRIMARY KEY (project_id, technology_id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (technology_id) REFERENCES technologies(id) ON DELETE CASCADE ON UPDATE CASCADE
);