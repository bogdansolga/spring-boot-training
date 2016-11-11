-- run these commands before starting the project, in order to setup the database and the connecting user
BEGIN TRANSACTION;

CREATE USER postgres WITH PASSWORD 'postgres';

CREATE DATABASE spring_boot;
GRANT ALL PRIVILEGES ON DATABASE spring_boot TO postgres;

COMMIT;