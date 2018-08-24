CREATE DATABASE IF NOT EXISTS scaffold;

-- User Table
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(40) NOT NULL,
  pwd_hash VARCHAR(255) NOT NULL,
  salt VARCHAR (255) NOT NULL,
  sex TINYINT(2) NOT NULL DEFAULT 0,
  last_password_reset DATETIME NULL
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar NOT NULL,
  desc varchar null ,
  enable bit(1) not null default b'0'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;

DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar NOT NULL,
  desc varchar null ,
  permission varchar not null,
  enable bit(1) not null default b'0'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;

DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  permission_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;
