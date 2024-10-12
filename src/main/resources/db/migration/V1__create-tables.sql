create table users(
    id uuid primary key,
    login varchar(50) not null,
    password text not null
);

create table keys(
    id uuid primary key,
    key_text uuid not null,
    active boolean not null default(true)
);