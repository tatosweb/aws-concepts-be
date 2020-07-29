CREATE SCHEMA `programing_poll` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `programing_poll`.`user_poll` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `age` INT NOT NULL,
  `preferred_language` VARCHAR(45) NOT NULL,
  `work_place` VARCHAR(100) NULL,
  `profession` VARCHAR(60) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

