create database SIM
use SIM
create table SIMCourseSchedule(
	id Integer identity(1,1) primary key,
	weekno varchar(200) ,
	week varchar(200) ,
	section varchar(200) ,
	courseName varchar(200) ,
	classes varchar(200) ,
	num varchar(200) ,
	teacher varchar(200) ,
	address varchar(200) ,
	courseType  varchar(200) ,
)


