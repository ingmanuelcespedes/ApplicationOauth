-- CREATE BY ALEXANDER MANUEL CESPEDES LEONARDO
-- DEVELOPER SENIOR TI , ANALISTA DE SISTEMAS - ATENTO PERU
-- Database: mariadb

-- DROP DATABASE mariadb;

-- BEGIN APPLICATION AUTHENTICATION
-- CREATE SEQUENCE FOT TABLE MC_TYPE_OAUTH
/*
CREATE A PROJECT AUTHENTICATION FOR ANY APPLICATION
TECHNOLOGIES:
-JAVA
-POSTGRESQL
-SQL SERVER (REPLICA)
-ANDROID
-SPRING JPA
*/
CREATE SEQUENCE SEQ_MC_TYPE_OAUTH START 1;
-- CREATE TABLE TYPE_OAUTH
CREATE TABLE IF NOT EXISTS MC_TYPE_OAUTH
(
    MC_TYPE_OAUTH_ID INTEGER PRIMARY KEY DEFAULT nextval('SEQ_MC_TYPE_OAUTH'),-- CONSTRAINT  PK_MC_TYPE_OAUTH , 
    MC_TYPE_OAUTH_DESCRIPTION VARCHAR(100) NOT NULL,
    MC_TYPE_OAUTH_STATUS CHAR(1) NOT NULL,
    MC_TYPE_OAUTH_ALTER_USER VARCHAR(100) NULL,
    MC_TYPE_OAUTH_ALTER_DATE VARCHAR(100) NULL,
    MC_TYPE_OAUTH_ALTER_NETWORK VARCHAR(100) NULL        
)

-- CREATE SEQUENCE FOR TABLE MC_USERS
CREATE SEQUENCE SEQ_MC_USERS START 1;

-- CREATE TABLE MC_USERS
CREATE TABLE IF NOT EXISTS  MC_USERS
(
    MC_USERS_ID INTEGER CONSTRAINT PK_MC_USERS PRIMARY KEY  DEFAULT NEXTVAL ('SEQ_MC_USERS'),
    MC_USERS_NAME VARCHAR(100) NOT NULL,
    MC_USERS_DESCRIPTION VARCHAR(200) NULL,
    MC_USERS_BIRTH_DATE VARCHAR(10) NULL,
    MC_USERS_GENDER CHAR(1) NULL,
    MC_USERS_LOGIN VARCHAR(100) NOT NULL,
    MC_USERS_PASS VARCHAR(100) NOT NULL,
    MC_USERS_TYPE INTEGER CONSTRAINT FK_MC_USERS_MC_TYPE_OAUTH   REFERENCES MC_TYPE_OAUTH(MC_TYPE_OAUTH_ID),
    MC_USERS_ALTER_USER VARCHAR(100) NULL,
    MC_USERS_ALTER_DATE VARCHAR(100) NULL,
    MC_USERS_ALTER_NETWORK VARCHAR(100)
)


-- CREATE SEQUENCE MC_SESSION_OAUTH
CREATE SEQUENCE SEQ_MC_SESSION_OAUTH START 1;

-- CREATE TABLE MC_SESSION_OAUTH
CREATE TABLE IF NOT EXISTS  MC_SESSION_OAUTH
(
    MC_SESSION_OAUTH_ID INTEGER CONSTRAINT PK_MC_SESSION_OAUTH PRIMARY KEY   DEFAULT NEXTVAL('SEQ_MC_SESSION_OAUTH'),
    MC_SESSION_OAUTH_USERS_ID INTEGER CONSTRAINT FK_SESSION_OAUTH_USERS REFERENCES MC_USERS(MC_USERS_ID),
    MC_SESSION_OAUTH_USER_LOGIN VARCHAR(100) NULL,
    MC_SESSION_OAUTH_DATE_BEGIN VARCHAR(20) NULL,
    MC_SESSION_OAUTH_DATE_END VARCHAR(20) NULL
)

-- ANALYSIS SECURITY MENU-USER
-- CREATE SEQUENCE MC_MENU_ADVANCED
CREATE SEQUENCE SEQ_MC_MENU_ADVANCED START 1;

-- CREATE TABLE MC_MENU_ADVANCED
CREATE TABLE IF NOT EXISTS  MC_MENU_ADVANCED
(
	MC_MENU_ADVANCED_ID INTEGER CONSTRAINT PK_MC_MENU_ADVANCED PRIMARY KEY  DEFAULT NEXTVAL('SEQ_MC_MENU_ADVANCED'),
    MC_MENU_ADVANCED_NAME VARCHAR(100),
    MC_MENU_ADVANCED_STATUS CHAR(1),
    MC_MENU_ADVANCED_ALTER_USER VARCHAR(100) NULL,
    MC_MENU_ADVANCED_ALTER_DATE VARCHAR(100) NULL,
    MC_MENU_ADVANCD_ALTER_NETWORK VARCHAR(100)
    
)

--CREATE SEQUENCE MC_MENU_ITEM
CREATE SEQUENCE SEQ_MC_MENU_ITEM START 1;

-- CREATE TABLE MC_MENU_ITEM
CREATE TABLE IF NOT EXISTS  MC_MENU_ITEM
(
    MC_MENU_ITEM_ID INTEGER CONSTRAINT PK_MC_MENU_ITEM PRIMARY KEY  DEFAULT NEXTVAL('SEQ_MC_MENU_ITEM'),
    MC_MENU_ITEM_NAME VARCHAR(100),
    MC_MENU_ITEM_STATUS CHAR(1),
    MC_MENU_ITEM_ALTER_USER VARCHAR(100),
    MC_MENU_ITEM_ALTER_DATE VARCHAR(100),
    MC_MENU_ITEM_ALTER_NETWORK VARCHAR(100)
)

--CREATE SEQUENCE MC_USERS_ITEM_MENU
CREATE SEQUENCE SEQ_MC_USERS_ITEM_MENU START 1;

--CREATE TABLE MC_USERS_ITEM_MENU
CREATE TABLE IF NOT EXISTS  MC_USERS_ITEM_MENU
(
    MC_USERS_ITEM_MENU_ID INTEGER CONSTRAINT PK_MC_USERS_ITEM_MENU PRIMARY KEY  DEFAULT NEXTVAL('SEQ_MC_USERS_ITEM_MENU'),
    MC_MENU_ITEM_MENU_ITEM_ID INTEGER CONSTRAINT FK_MC_USERS_ITEM_MENU_ITEM REFERENCES MC_MENU_ITEM(MC_MENU_ITEM_ID),
    MC_MENU_ITEM_USERS_ID INTEGER CONSTRAINT FK_MC_USERS_ITEM_MENU_USERS REFERENCES MC_USERS(MC_USERS_ID)
)

-- CREATE SEQUENCE MC_USERS_MENU_OAUTH
CREATE SEQUENCE SEQ_MC_USERS_MENU_OAUTH START 1;

--CREATE TABLE MC_USERS_MENU_OAUTH
CREATE TABLE IF NOT EXISTS MC_USERS_MENU_OAUTH
(
    MC_USERS_MENU_OAUTH_ID INTEGER CONSTRAINT PK_MC_USERS_MENU_OAUTH PRIMARY KEY  DEFAULT NEXTVAL('SEQ_MC_USERS_MENU_OAUTH'),
    MC_USERS_MENU_OAUTH_MENU_ID INTEGER CONSTRAINT FK_MC_USERS_MENU_OAUTH_MENU REFERENCES MC_MENU_ADVANCED(MC_MENU_ADVANCED_ID),
    MC_USERS_MENU_OAUTH_USERS_ID INTEGER CONSTRAINT FK_MC_USERS_MENU_OAUTH_USERS REFERENCES MC_USERS(MC_USERS_ID)
)





