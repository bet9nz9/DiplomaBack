--------------------------objtype----------------------------------------------------------
INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Logger'); 		  --1

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Entrance');  		  --2

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'E-key');     		  --3

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)  
VALUES (OBJTYPE_SEQ.nextval, 'Type');	   		  --4

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Role'); 	   		  --5

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Building'); 		  --6

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Address');    		  --7

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'User');  			  --8

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Contact');  		  --9

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Contact_Type');  	  --10

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'AD');  			  --11

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Utility');  		  --12

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Category');  		  --13

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'Service');  		  --14

INSERT INTO objtype(OBJECT_TYPE_ID,CODE)
VALUES (OBJTYPE_SEQ.nextval, 'ServiceType');  		  --15

---------------------------------Attrtype Logger-------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (1, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Logger'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), 'entrance_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (2, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Logger'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'E-key'), 'ekey_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (3, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Logger'), 'date');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (4, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Logger'), 'time');

--------------------------------Attrtype Entrance------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (5, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), 'type_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (6, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), 'entrance_status');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (7, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Building'), 'building_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (50, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Entrance'), 'isEntrance_available');

--------------------------------Attrtype E_key---------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (8, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'E-key'), 'key');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (9, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'E-key'), 'is_active');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (10, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'E-key'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'user_id');

--------------------------------Attrtype Type---------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (11, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Type'), 'value');

--------------------------------Attrtype Role---------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (14, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Role'), 'role');

--------------------------------Attrtype Building-----------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (15, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Building'), 'number');

--------------------------------Attrtype Adress-------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (16, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Building'), 'building_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (17, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), 'flat');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (48, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'),'user_id');

insert into ATTRTYPE(attr_id, object_type_id, code)
VALUES (49, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), 'apartment_number');

--------------------------------Attrtype User---------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (21, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'email');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (22, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'password');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (23, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'first_name');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (24, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'last_name');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (25, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'patronymic');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (26, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'is_active');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (27, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'recive_utility_notif');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (28, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Role'), 'role_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (55, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'activationCode');

--------------------------------Attrtype Contact------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (29, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Contact'), 'value');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (30, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Contact'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Contact_Type'), 'contact_type_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (31, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Contact'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'user_id');

--------------------------------Attrtype Contact_Type-------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (51, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Contact_Type'), 'contact_type');

--------------------------------Attrtype AD-----------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (32, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), 'text');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (33, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), 'date');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (34, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), 'title');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (35, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Category'), 'category_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (36, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), 'createdBy');

--------------------------------Attrtype Utility------------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (38, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'month_and_year');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (39, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'current_month_read');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (47, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'last_month_read');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (40, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'amount_to_pay');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (41, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'status');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (42, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), 'photo_url');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (43, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), 'service_id');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (18, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Utility'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), 'adress_id');

--------------------------------Attrtype Category-----------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (44, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Category'), 'important');

--------------------------------Attrtype Services-----------------------------------------
INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (37, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), 'bank_book');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (45, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), 'title');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, CODE)
VALUES (46, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), 'tariff');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (52, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'ServiceType'), 'service_type');

INSERT INTO attrtype(ATTR_ID, OBJECT_TYPE_ID, OBJECT_TYPE_ID_REF, CODE)
VALUES (53, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Service'), (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Address'), 'ref_address');

--------------------------------Objects---------------------------------------------------
-- List values
insert into Lists(LIST_VALUE_ID, VALUE) values
(LISTS_SEQ.nextval, 'true');

insert into Lists(LIST_VALUE_ID, VALUE) values
(LISTS_SEQ.nextval, 'false');

-- Buildings
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Building'), 'Building#1', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(15, OBJECTS_SEQ.currval, 1, null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Building'), 'Building#2', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(15, OBJECTS_SEQ.currval, 2, null, null);

-- EntranceType
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Type'), '??????????', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(11, OBJECTS_SEQ.currval, '???????? ?? ????????????????', null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Type'), '????????????????', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(11, OBJECTS_SEQ.currval, '???????????? ????????????????', null, null);

-- Role
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Role'), 'ADMIN', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(14, OBJECTS_SEQ.currval, 'admin', null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Role'), 'USER', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(14, OBJECTS_SEQ.currval, 'user', null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Role'), 'GUARD', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(14, OBJECTS_SEQ.currval, 'guard', null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Role'), 'GUEST', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(14, OBJECTS_SEQ.currval, 'guest', null, null);

-- contact_type
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Contact_Type'), 'EMAIL', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(51, OBJECTS_SEQ.currval, 'email', null, null);

insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE WHERE CODE = 'Contact_Type'), 'PHONE_NUMBER', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(51, OBJECTS_SEQ.currval, 'phone number', null, null);


/*?????????????? ????????????????????????????*/
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'User'), 'Admin', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(21, OBJECTS_SEQ.currval, 'adm@adm.adm', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(22, OBJECTS_SEQ.currval, '$2a$10$QTraGFlviVE79GnHykhRDeFtMRgRWFrWim7doT0ci5aeCOzLoZrgO', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(23, OBJECTS_SEQ.currval, 'Admin', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(24, OBJECTS_SEQ.currval, 'Admin', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(25, OBJECTS_SEQ.currval, 'Admin', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(26, OBJECTS_SEQ.currval, null, null, (select LIST_VALUE_ID from LISTS where VALUE = 'true'));

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(27, OBJECTS_SEQ.currval, null, null, (select LIST_VALUE_ID from LISTS where VALUE = 'true'));

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(55, OBJECTS_SEQ.currval, null, null, null);

insert into OBJREFERENCE(attr_id, reference, object_id) VALUES
(28, (select OBJECT_ID from OBJECTS where NAME = 'ADMIN'), OBJECTS_SEQ.currval);

/*?????????????????? ?????? ?????????????????? ??????????????????*/
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJECT_TYPE_ID from OBJTYPE where CODE = 'Category'), '????????????', '?????????????????????? ???????????? ?????? ????????????');

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(44, OBJECTS_SEQ.currval, null, null, (select LIST_VALUE_ID from LISTS where VALUE = 'false'));

/*?????????????????? ?????????????????? ?????? ????????????*/
insert into OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES
(OBJECTS_SEQ.nextval, null, (select OBJTYPE.OBJECT_TYPE_ID from OBJTYPE where CODE = 'AD'), 'Start message', null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(32, OBJECTS_SEQ.currval, '???????????????? ?????????????????????????? ??????????????????????????????! ?? ????. ??????????????????????????.', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(33, OBJECTS_SEQ.currval, null, 1647890766410, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(34, OBJECTS_SEQ.currval, '????????????, ?????????? ????????????????????????!', null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(35, OBJECTS_SEQ.currval, (select OBJECT_ID from OBJECTS where NAME = '????????????'), null, null);

insert into ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES
(36, OBJECTS_SEQ.currval, (select OBJECT_ID from OBJECTS where NAME = 'Admin'), null, null);

insert into OBJREFERENCE(attr_id, reference, object_id) VALUES
(35, (select OBJECT_ID from OBJECTS where NAME = '????????????'), OBJECTS_SEQ.currval);

insert into OBJREFERENCE(attr_id, reference, object_id) VALUES
(36, (select OBJECT_ID from OBJECTS where NAME = 'Admin' and object_type_id = 8), OBJECTS_SEQ.currval);

/*?????????????????? ??????????????????*/
INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJECTS_SEQ.nextval, null, (SELECT OBJECT_TYPE_ID FROM OBJTYPE WHERE CODE = 'ServiceType'), '????????????????', '????????????????');

INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJECTS_SEQ.nextval, null, (SELECT OBJECT_TYPE_ID FROM OBJTYPE WHERE CODE = 'ServiceType'), '????????', '????????');

INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJECTS_SEQ.nextval, null, (SELECT OBJECT_TYPE_ID FROM OBJTYPE WHERE CODE = 'ServiceType'), '??????????????????????????', '??????????????????????????');

INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJECTS_SEQ.nextval, null, (SELECT OBJECT_TYPE_ID FROM OBJTYPE WHERE CODE = 'ServiceType'), '??????', '??????');

INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJECTS_SEQ.nextval, null, (SELECT OBJECT_TYPE_ID FROM OBJTYPE WHERE CODE = 'ServiceType'), '????????', '????????');

commit;