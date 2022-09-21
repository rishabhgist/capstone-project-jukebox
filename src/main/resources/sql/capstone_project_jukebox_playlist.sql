-- create table playlist with columns id, name, data
create TABLE `jukebox`.`playist` (
  `playlist_id` INT NOT NULL AUTO_INCREMENT,
  `playlist_name` VARCHAR(100) NOT NULL,
  `playlist_data` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`playlist_id`));

-- insert playlist values in table
insert into `jukebox`.`playist` (`playlist_id`, `playlist_name`, `playlist_data`) VALUES ('1', 'Party', '1,5,6');
insert into `jukebox`.`playist` (`playlist_id`, `playlist_name`, `playlist_data`) VALUES ('2', 'Jazz', '3,4,2');
insert into `jukebox`.`playist` (`playlist_id`, `playlist_name`, `playlist_data`) VALUES ('3', 'Pop', '1,2,5');


