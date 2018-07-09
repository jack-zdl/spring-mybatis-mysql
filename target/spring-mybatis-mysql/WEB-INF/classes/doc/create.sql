CREATE TABLE `spring-server`.`tbl_book` (
	`id` VARCHAR(32) NOT NULL,
	`number` VARCHAR(32) NOT NULL,
	`price` VARCHAR(32) NOT NULL,
	`create_date_time` DATETIME NOT NULL,
	`create_user_login_name` VARCHAR(32) NOT NULL,
	PRIMARY KEY (`id`)
)

CREATE TABLE `ncyp`.`tbl_user` (
  `username` VARCHAR(32) BINARY NOT NULL,
  `name` VARCHAR(32) BINARY NOT NULL,
  `password` VARCHAR(32) BINARY NOT NULL,
  `validate` CHAR(1) NOT NULL,
  `money` VARCHAR(32) BINARY NOT NULL
)

CREATE TABLE `ncyp`.`tbl_operatelog` (
  `id` VARCHAR(32) BINARY NOT NULL,
  `operate_page` VARCHAR(32) BINARY NOT NULL,
  `operate_type` VARCHAR(32) BINARY NOT NULL,
  `operator` VARCHAR(32) NOT NULL,
  `operate_time` DATE NOT NULL,
	`operate_desc` VARCHAR(32) NOT NULL,
	 PRIMARY KEY (`id`)
)