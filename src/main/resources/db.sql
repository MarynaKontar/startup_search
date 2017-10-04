-- CREATE SCHEMA IF NOT EXISTS `STARTUP` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ; --
USE `startup2` ;

create table IF NOT EXISTS address
(
 id bigint auto_increment
  primary key,
 country varchar(455) null,
 city varchar(455) null,
 region varchar(455) null,
 constraint address_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;


create table IF NOT EXISTS businessplan
(
 id bigint auto_increment
  primary key,
 idea varchar(2000) null,
 currentState varchar(455) null,
 market varchar(455) null,
 constraint businessplan_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;



create table IF NOT EXISTS education
(
 id bigint auto_increment
  primary key,
 user_username varchar(45) null,
 educationalInstitution varchar(455) null,
 faculty varchar(455) null,
 educationalStage varchar(455) null,
 fieldOfStudy varchar(455) null,
 modeOfStudy enum('FULL_TIME', 'REMOTE', 'EVENING', 'EXTRAMURAL') null,
 untilDate timestamp default '0000-00-00 00:00:00' not null,
 fromDate timestamp default '0000-00-00 00:00:00' not null,
 constraint education_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table IF NOT EXISTS experience
(
 id bigint auto_increment
  primary key,
 user_username varchar(45) null,
 company varchar(455) null,
 position varchar(455) null,
 responsibility varchar(455) null,
 untilDate timestamp default '0000-00-00 00:00:00' not null,
 fromDate timestamp default '0000-00-00 00:00:00' not null,
 constraint experience_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table IF NOT EXISTS project
(
 id bigint auto_increment
  primary key,
 name varchar(455) null,
 user_username varchar(45) null,
 funds decimal(19,2) null,
 minInvestment decimal(19,2) null,
 industry varchar(455) null,
 businessPlan_id bigint null,
 address_id bigint null,
 description varchar(2000) null,
 lastChange timestamp default '0000-00-00 00:00:00' not null,
 isActive tinyint(1) default '1' null,
 constraint project_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table IF NOT EXISTS interest
(
 id bigint auto_increment
  primary key,
 name varchar(455) null,
 description varchar(2000) null,
 budget int null,
 user_username varchar(45) null,
 country varchar(45) null,
 industry varchar(45) null,
 lastChange timestamp default '0000-00-00 00:00:00' null,
 constraint interest_id_uindex
 unique (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;



create table IF NOT EXISTS user
(
 username varchar(45) not null
  primary key,
 PASSWORD varchar(455) not null,
 firstName varchar(45) null,
 lastName varchar(45) null,
 email varchar(45) null,
 phoneNumber varchar(45) null,
 country varchar(45) null,
 city varchar(45) null,
 personalPageFotoLink varchar(455) null,
 aboutMe varchar(455) null,
 youtubeLink varchar(455) null,
 skills varchar(455) null,
 profileFotoLink varchar(455) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;

create table IF NOT EXISTS user_roles
(
 user_username varchar(45) not null,
 role varchar(45) not null,
 primary key (user_username, role)
)ENGINE=InnoDB DEFAULT CHARSET=utf8
;

