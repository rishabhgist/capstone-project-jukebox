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

-- insert values into songs
insert into `jukebox`.`song` (`song_id`, `song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) VALUES ('2', 'Lie 2 You', 'Pop', 'Leonell Cassio', '4.02', 'LeonellCassio', 'src/main/resources/songs/lie-2-you-ft-dylan-emmet.wav');
insert into `jukebox`.`song` (`song_id`, `song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) VALUES ('3', 'Night Sky', 'Rock', 'Leonell Cassio', '3.13', 'LeonellCassio', 'src/main/resources/songs/night-sky-ft-julia-mihevc.wav');
insert into `jukebox`.`song` (`song_id`, `song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) VALUES ('4', 'Town', 'Party', 'Be Corbal', '3.02', 'BeCorbal', 'src/main/resources/songs/town.wav');
insert into `jukebox`.`song` (`song_id`, `song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) VALUES ('5', 'Trendy Summer', 'Pop', 'Pavel Yudin', '2.39', 'PavelInsight', 'src/main/resources/songs/trendy-summer.wav');
insert into `jukebox`.`song` (`song_id`, `song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) VALUES ('6', 'Tropical Summer', 'Party', 'Music Unlimited', '2.35', 'PavelInsight', 'src/main/resources/songs/tropical-summer.wav');

-- display all song details
select * from `jukebox`.`song`;

-- display song by ID
select * from `jukebox`.`song` where(`song_id` = ?);


