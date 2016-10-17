create table CEHS_CONTEXT_HELP (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SCREEN_ID varchar(255) not null,
    HELP_TEXT longvarchar,
    --
    primary key (ID)
);
