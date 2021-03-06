drop table OBJTYPE CASCADE CONSTRAINTS;
drop table ATTRTYPE CASCADE CONSTRAINTS;
drop table Lists CASCADE CONSTRAINTS;
drop table OBJECTS CASCADE CONSTRAINTS;
drop table ATTRIBUTES CASCADE CONSTRAINTS;
drop table OBJREFERENCE CASCADE CONSTRAINTS;

drop sequence objects_seq;
drop sequence attrtype_seq;
drop sequence objtype_seq;
drop sequence attributes_seq;
drop sequence objreference_seq;
drop sequence lists_seq;

create sequence objtype_seq;
create sequence attrtype_seq;
create sequence objects_seq;
create sequence attributes_seq;
create sequence objreference_seq;
create sequence lists_seq;


-- Таблица описаний объектных типов
CREATE TABLE OBJTYPE
  (
    OBJECT_TYPE_ID NUMBER NOT NULL ENABLE,
    PARENT_ID      NUMBER,
    CODE           VARCHAR2(20) NOT NULL UNIQUE,
    NAME           VARCHAR2(200 BYTE),
    DESCRIPTION    VARCHAR2(1000 BYTE),
    CONSTRAINT CON_OBJECT_TYPE_ID PRIMARY KEY (OBJECT_TYPE_ID),
    CONSTRAINT CON_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ON DELETE CASCADE ENABLE
  );
  
  CREATE TABLE ATTRTYPE (
    ATTR_ID      		NUMBER NOT NULL,
    OBJECT_TYPE_ID 		NUMBER NOT NULL,
	OBJECT_TYPE_ID_REF 	NUMBER,
    CODE         		VARCHAR2(20),
    NAME         		VARCHAR2(200 BYTE),
    CONSTRAINT CON_ATTR_ID PRIMARY KEY (ATTR_ID),
    CONSTRAINT CON_ATTR_OBJECT_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID),
	CONSTRAINT CON_ATTR_OBJECT_TYPE_ID_REF FOREIGN KEY (OBJECT_TYPE_ID_REF) REFERENCES OBJTYPE (OBJECT_TYPE_ID)
);

create table Lists
(
    list_value_id number not null,
    value varchar(4000),
    CONSTRAINT CON_list_value_id PRIMARY KEY (list_value_id)
);

CREATE TABLE OBJECTS (
    OBJECT_ID      NUMBER NOT NULL,
    PARENT_ID      NUMBER,
    OBJECT_TYPE_ID NUMBER NOT NULL,
    NAME           VARCHAR2(2000 BYTE),
    DESCRIPTION    VARCHAR2(4000 BYTE),
    CONSTRAINT CON_OBJECTS_ID PRIMARY KEY (OBJECT_ID),
    CONSTRAINT CON_PARENTS_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE DEFERRABLE,
    CONSTRAINT CON_OBJ_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID)
);

CREATE TABLE ATTRIBUTES
  (
    attr_id number not null,
    object_id number not null,
    value varchar2(4000 byte),
    date_value number,
    list_value_id number,
    constraint  con_ATR_list_value_id foreign key (list_value_id) references Lists (list_value_id) on delete cascade,
    constraint  con_ATR_attr_id foreign key (attr_id) references AttrType (attr_id) on delete cascade,
    constraint con_ATR_object_id foreign key (object_id) references Objects (object_id) on delete cascade,
	constraint CON_ATTRIBUTES_pkey primary key (attr_id, object_id)
  );  

CREATE TABLE OBJREFERENCE
  (
    ATTR_ID   NUMBER NOT NULL,
    REFERENCE NUMBER NOT NULL,
    OBJECT_ID NUMBER NOT NULL,
	CONSTRAINT CON_OBJREFERENCE_PK PRIMARY KEY (ATTR_ID,REFERENCE,OBJECT_ID),
    CONSTRAINT CON_REFERENCE FOREIGN KEY (REFERENCE) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
    CONSTRAINT CON_ROBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
    CONSTRAINT CON_RATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE
  ); 

COMMIT;