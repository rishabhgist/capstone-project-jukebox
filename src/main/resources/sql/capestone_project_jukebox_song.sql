-- create table song with columns id, name, genre, artist, length
create TABLE IF NOT EXISTS `jukebox`.`song`(
  `song_id` INT NOT NULL AUTO_INCREMENT,
  `song_name` VARCHAR(100) NOT NULL,
  `song_genre` VARCHAR(100) NOT NULL,
  `song_artist` VARCHAR(100) NOT NULL,
  `song_length` DOUBLE NOT NULL,
   `song_album` VARCHAR(100) NOT NULL,
   `song_url`   VARCHAR(200) NOT NULL,
  PRIMARY KEY (`song_id`));