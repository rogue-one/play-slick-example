# --- !Ups

create table "employee_data" ("id" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, "name" VARCHAR(254) NOT NULL,"resume" VARCHAR(254) NOT NULL, "additionalInformation" VARCHAR(254) NOT NULL);


# --- !Downs
;
drop table "employee_data";