--------------------------------------------------------
--  DDL for Sequence CATEGORIES_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."CATEGORIES_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence SUBCATEGORIES_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."SUBCATEGORIES_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence ALCOHOLS_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."ALCOHOLS_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence RECIPES_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."RECIPES_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence PROCESSES_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."PROCESSES_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence ROLES_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."ROLES_ID_SEQ";
--------------------------------------------------------
--  DDL for Sequence USERS_ID_SEQ
--------------------------------------------------------
   CREATE SEQUENCE  "RECIPE"."USERS_ID_SEQ";
--    CREATE SEQUENCE  "RECIPE"."RECIPE.RECIPES_SUBCATEGORIES";

--  DDL for Table CATEGORIES
CREATE TABLE "RECIPE"."CATEGORIES" (
  "ID" NUMBER(11) NOT NULL,
  "NAME" VARCHAR2(255) NOT NULL,
  PRIMARY KEY (ID)
);

--  DDL for Table SUBCATEGORIES
CREATE TABLE "RECIPE"."SUBCATEGORIES" (
  "ID" NUMBER(11) NOT NULL,
  "NAME" VARCHAR2(255) NOT NULL,
  PRIMARY KEY (ID)
);

--  DDL for Table ALCOHOLS
CREATE TABLE "RECIPE"."ALCOHOLS" (
  "ID" NUMBER(11) NOT NULL,
  "NAME" VARCHAR2(255) NOT NULL,
  PRIMARY KEY (ID)
);

--  DDL for Table RECIPES
CREATE TABLE "RECIPE"."RECIPES" (
    "ID" NUMBER(19,0) NOT NULL, 
	"DESCRIPTION" VARCHAR2(512 CHAR) NOT NULL, 
	"NAME" VARCHAR2(256 CHAR) NOT NULL, 
	"IMAGE" VARCHAR2(255 CHAR), 
	"MOVIE" VARCHAR2(255 CHAR), 
    "TIME" NUMBER(10,0), 
	"PRICE" NUMBER(10,0), 
	"CATEGORY_ID" NUMBER(19,0),
    "SUBCATEGORY_ID" NUMBER(19,0),
    "ALCOHOL_ID" NUMBER(19,0),
  PRIMARY KEY (ID)
);

--  DDL for Table PROCESSES
CREATE TABLE "RECIPE"."PROCESSES" (
  "ID" NUMBER(19,0) NOT NULL, 
	"IMAGE" VARCHAR2(255 CHAR), 
	"PROCESS" VARCHAR2(512 CHAR) NOT NULL, 
	"RECIPE_ID" NUMBER(19,0),
    "RECIPESORT_ID" NUMBER(19,0),
  PRIMARY KEY (ID)
);
--  DDL for Table ROLES
CREATE TABLE "RECIPE"."ROLES" (
  "ID" NUMBER(19,0) NOT NULL,  
	"DESCRIPTION" VARCHAR2(150 CHAR) NOT NULL, 
	"NAME" VARCHAR2(40 CHAR) NOT NULL,
  PRIMARY KEY (ID)
);
--  DDL for Table USERS
CREATE TABLE "RECIPE"."USERS" (
 "ID" NUMBER(19,0) NOT NULL, 
	"EMAIL" VARCHAR2(254 CHAR) NOT NULL, 
	"NAME" VARCHAR2(100 CHAR) NOT NULL, 
	"PASSWORD" VARCHAR2(64 CHAR) NOT NULL,
  PRIMARY KEY (ID)
);

--  DDL for Table USERS_ROLES
CREATE TABLE "RECIPE"."USERS_ROLES" (
  "USER_ID" NUMBER(19,0) NOT NULL, 
  "ROLE_ID" NUMBER(19,0) NOT NULL,
  PRIMARY KEY ("USER_ID", "ROLE_ID")
);

--  DDL for Table RECIPES_SUBCATEGORIES
CREATE TABLE "RECIPE"."RECIPES_SUBCATEGORIES" (
  "RECIPE_ID" NUMBER(19,0) NOT NULL, 
  "SUBCATEGORIY_ID" NUMBER(19,0) NOT NULL,
  PRIMARY KEY ("RECIPE_ID", "SUBCATEGORIY_ID")
);


REM INSERTING into RECIPE.CATEGORIES
SET DEFINE OFF;
Insert into RECIPE.CATEGORIES (ID,NAME) values (1,'和食');
Insert into RECIPE.CATEGORIES (ID,NAME) values (2,'洋食');
Insert into RECIPE.CATEGORIES (ID,NAME) values (3,'中華');

REM INSERTING into RECIPE.SUBCATEGORIES
SET DEFINE OFF;
Insert into RECIPE.SUBCATEGORIES (ID,NAME) values (1,'主菜');
Insert into RECIPE.SUBCATEGORIES (ID,NAME) values (2,'おつまみ');
Insert into RECIPE.SUBCATEGORIES (ID,NAME) values (3,'副菜');

REM INSERTING into RECIPE.ALCOHOLS
SET DEFINE OFF;
Insert into RECIPE.ALCOHOLS (ID,NAME) values (1,'ビール');
Insert into RECIPE.ALCOHOLS (ID,NAME) values (2,'赤ワイン');
Insert into RECIPE.ALCOHOLS (ID,NAME) values (3,'白ワイン');

REM INSERTING into RECIPE.RECIPES
SET DEFINE OFF;
Insert into RECIPE.RECIPES (ID,DESCRIPTION,NAME,IMAGE,MOVIE,TIME,PRICE,CATEGORY_ID,SUBCATEGORY_ID,ALCOHOL_ID) values (1,'絶品からあげの作成方法','絶品からあげ','sample1.jpeg','sample1.mp4',30,1000,1,2,1);
Insert into RECIPE.RECIPES (ID,DESCRIPTION,NAME,IMAGE,MOVIE,TIME,PRICE,CATEGORY_ID,SUBCATEGORY_ID,ALCOHOL_ID) values (2,'美味しいぶり大根の作成方法','ぶり大根','sample2.jpeg','sample2.mp4',60,1000,1,1,1);
Insert into RECIPE.RECIPES (ID,DESCRIPTION,NAME,IMAGE,MOVIE,TIME,PRICE,CATEGORY_ID,SUBCATEGORY_ID,ALCOHOL_ID) values (3,'チャーシューの作成方法','チャーシュー','sample3.jpeg','sample3.mp4',240,2000,3,2,1);

REM INSERTING into RECIPE.PROCESSES
SET DEFINE OFF;
Insert into RECIPE.PROCESSES (ID,IMAGE,PROCESS,RECIPE_ID,RECIPESORT_ID) values (1,'sample1.jpeg','鶏モモ肉を一口サイズに切る',1,1);
Insert into RECIPE.PROCESSES (ID,IMAGE,PROCESS,RECIPE_ID,RECIPESORT_ID) values (2,'sample1.jpeg','袋に入れて、調味液を混ぜて下味をつける',1,2);
Insert into RECIPE.PROCESSES (ID,IMAGE,PROCESS,RECIPE_ID,RECIPESORT_ID) values (3,'sample2.jpeg','大根は皮をむき、1cm厚さの半月形に切る。',2,1);
Insert into RECIPE.PROCESSES (ID,IMAGE,PROCESS,RECIPE_ID,RECIPESORT_ID) values (4,'sample3.jpeg','テスト',3,1);

REM INSERTING into RECIPE.ROLES
SET DEFINE OFF;
Insert into RECIPE.ROLES (ID,DESCRIPTION,NAME) values (1,'Admin','管理者');
Insert into RECIPE.ROLES (ID,DESCRIPTION,NAME) values (2,'Assistant','アシスタント');

REM INSERTING into RECIPE.USERS
SET DEFINE OFF;
Insert into RECIPE.USERS (ID,EMAIL,NAME,PASSWORD) values (1,'admin@example.com','管理者です','$2a$10$BplfjrJzU.kS2rXJSIRUiu4A6kJ7cXeqzBWndxLuixCvBip0GZQj2');
Insert into RECIPE.USERS (ID,EMAIL,NAME,PASSWORD) values (2,'staff@example.com','アシスタントです','$2a$10$BplfjrJzU.kS2rXJSIRUiu4A6kJ7cXeqzBWndxLuixCvBip0GZQj2');

REM INSERTING into RECIPE.USERS_ROLES
SET DEFINE OFF;
Insert into RECIPE.USERS_ROLES (USER_ID,ROLE_ID) values (1,1);
Insert into RECIPE.USERS_ROLES (USER_ID,ROLE_ID) values (1,2);
Insert into RECIPE.USERS_ROLES (USER_ID,ROLE_ID) values (2,2);

REM INSERTING into RECIPE.USERS_ROLES
SET DEFINE OFF;
Insert into RECIPE.RECIPES_SUBCATEGORIES (RECIPE_ID,SUBCATEGORIY_ID) values (1,1);
Insert into RECIPE.RECIPES_SUBCATEGORIES (RECIPE_ID,SUBCATEGORIY_ID) values (1,2);


--確認用
SELECT * FROM RECIPE.RECIPES;
SELECT * FROM RECIPE.ALCOHOLS;
SELECT * FROM RECIPE.SUBCATEGORIES;
SELECT * FROM RECIPE.RECIPES_SUBCATEGORIES;

DROP TABLE RECIPE.RECIPES;
DELETE FROM RECIPE.RECIPES;