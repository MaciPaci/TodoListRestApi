CREATE TYPE status AS ENUM ('done', 'in_progress', 'to_do', 'removed');

CREATE TABLE users
(
    id    integer      NOT NULL primary key,
    name  varchar(250) NOT NULL,
    email varchar(100) NOT NULL UNIQUE
);

CREATE TABLE tasks
(
    id          integer      NOT NULL primary key,
    user_id     integer      NOT NULL,
    title       varchar(100) NOT NULL,
    description varchar(500) NOT NULL,
    created_at  timestamp    NOT NULL,
    updated_at  timestamp    NOT NULL,
    status      status       NOT NULL,
    CONSTRAINT fk_tasks
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);
CREATE INDEX tasks_idx ON tasks (user_id);



