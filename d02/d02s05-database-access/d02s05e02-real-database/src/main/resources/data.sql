-- run these commands before starting the project, in order to setup the database and the connecting user
CREATE USER spring_boot_admin WITH PASSWORD 'spring_boot_admin';

CREATE DATABASE spring_boot;
GRANT ALL PRIVILEGES ON DATABASE spring_boot TO spring_boot_admin;