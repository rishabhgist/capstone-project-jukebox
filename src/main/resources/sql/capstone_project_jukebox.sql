-- create database jukebox
create DATABASE IF NOT EXISTS `jukebox`;

-- use database jukebox
USE `jukebox`;

-- create table song with columns id, name, genre, artist, length
CREATE TABLE IF NOT EXISTS `jukebox`.`song`(
  `song_id` INT NOT NULL AUTO_INCREMENT,
  `song_name` VARCHAR(100) NOT NULL,
  `song_genre` VARCHAR(100) NOT NULL,
  `song_artist` VARCHAR(100) NOT NULL,
  `song_length` DOUBLE NOT NULL,
  PRIMARY KEY (`song_id`));

-- create table playlist with columns id, name, data
CREATE TABLE `jukebox`.`playist` (
  `playlist_id` INT NOT NULL AUTO_INCREMENT,
  `playlist_name` VARCHAR(100) NOT NULL,
  `playlist_data` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`playlist_id`));
