-- create table playlist with columns id, name, data
create TABLE `jukebox`.`playist` (
  `playlist_id` INT NOT NULL AUTO_INCREMENT,
  `playlist_name` VARCHAR(100) NOT NULL,
  `playlist_data` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`playlist_id`));


