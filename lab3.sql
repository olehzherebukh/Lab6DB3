CREATE DATABASE IF NOT EXISTS zherebukh;
USE zherebukh;

DROP TABLE IF EXISTS event_has_artist;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS event_ticket;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS delivery;
DROP TABLE IF EXISTS event_type;
DROP TABLE IF EXISTS sex;
DROP TABLE IF EXISTS state;

CREATE TABLE event_type (
  event_type_id INT NOT NULL AUTO_INCREMENT,
  type_of_event VARCHAR(45) NOT NULL,
  PRIMARY KEY (event_type_id))
ENGINE = InnoDB;

CREATE TABLE event (
  event_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  city VARCHAR(45) NULL,
  location VARCHAR(45) NULL,
  event_type_event_type_id INT NOT NULL,
  PRIMARY KEY (event_id),
  INDEX fk_event_event_type1_idx (event_type_event_type_id ASC) VISIBLE,
  CONSTRAINT fk_event_event_type1
    FOREIGN KEY (event_type_event_type_id)
    REFERENCES zherebukh.event_type (event_type_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE state (
  id INT NOT NULL AUTO_INCREMENT,
  name_of_state VARCHAR(45) NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE delivery (
  way_of_delivery_id INT NOT NULL AUTO_INCREMENT,
  way_of_delivery VARCHAR(45) NULL,
  PRIMARY KEY (way_of_delivery_id))
ENGINE = InnoDB;

CREATE TABLE event_ticket (
  event_ticket_id INT NOT NULL AUTO_INCREMENT,
  date VARCHAR(45) NOT NULL,
  time VARCHAR(45) NOT NULL,
  place VARCHAR(45) NOT NULL,
  event_event_id INT NOT NULL,
  is_available TINYINT NULL,
  state_id INT NOT NULL,
  sector_name VARCHAR(45) NULL,
  delivery_way_of_delivery_id INT NOT NULL,
  PRIMARY KEY (event_ticket_id),
  INDEX fk_event_ticket_event1_idx (event_event_id ASC) VISIBLE,
  INDEX fk_event_ticket_ordered1_idx (state_id ASC) VISIBLE,
  INDEX fk_event_ticket_delivery1_idx (delivery_way_of_delivery_id ASC) VISIBLE,
  CONSTRAINT fk_event_ticket_event1
    FOREIGN KEY (event_event_id)
    REFERENCES zherebukh.event (event_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_event_ticket_ordered1
    FOREIGN KEY (state_id)
    REFERENCES zherebukh.state (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_event_ticket_delivery1
    FOREIGN KEY (delivery_way_of_delivery_id)
    REFERENCES zherebukh.delivery (way_of_delivery_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE sex (
  sex_id INT NOT NULL AUTO_INCREMENT,
  sex_of_artist VARCHAR(45) NULL,
  PRIMARY KEY (sex_id))
ENGINE = InnoDB;

CREATE TABLE artist (
   artist_id INT NOT NULL AUTO_INCREMENT,
  first_name_of_artist VARCHAR(45) NOT NULL,
  last_name_of_artist VARCHAR(45) NOT NULL,
  sex_sex_id INT NOT NULL,
  PRIMARY KEY (artist_id),
  INDEX fk_artist_sex1_idx (sex_sex_id ASC) VISIBLE,
  CONSTRAINT fk_artist_sex1
    FOREIGN KEY (sex_sex_id)
    REFERENCES zherebukh.sex (sex_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE event_has_artist (
  event_event_id INT NOT NULL,
  artist_artist_id INT NOT NULL,
  PRIMARY KEY (event_event_id, artist_artist_id),
  INDEX fk_event_has_artist_artist1_idx (artist_artist_id ASC) VISIBLE,
  INDEX fk_event_has_artist_event1_idx (event_event_id ASC) VISIBLE,
  CONSTRAINT fk_event_has_artist_event1
    FOREIGN KEY (event_event_id)
    REFERENCES zherebukh.event (event_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_event_has_artist_artist1
    FOREIGN KEY (artist_artist_id)
    REFERENCES zherebukh.artist (artist_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO event_type (event_type_id, type_of_event) VALUES
 (1, 'Концерт'),
 (2, 'Театр'),
 (3, 'Кіно'),
 (4, 'Фестиваль');
 
INSERT INTO event (event_id, name, city, location, event_type_event_type_id) VALUES
 (1, 'Skillet', 'Львів', 'Політех', 1),
 (2, 'Onuka', 'Харків', 'вул. Шевченка', 2),
 (3, 'IT Arena', 'Київ', 'вул. Франка', 3),
 (4, 'Молодвіж', 'Одеса', 'вул. Січових Стрільців', 1),
 (5, 'Хакатон', 'Львів', 'Французький бульвар', 2),
 (6, 'ComicCOn', 'Київ', 'Хрещатик', 2),
 (7, 'WarGamingFest', 'Київ', 'вул. Мазепи', 1),
 (8, 'ArtLecture', 'Запоріжжя', 'вул. Богдана Хмельницького', 4),
 (9, 'LoungeFest', 'Львів', 'вулю Степана Бандери', 2),
 (10, 'PoliticSchool', 'Харків', 'вул. Січова', 1);
 
INSERT INTO state (id, name_of_state) VALUES
 (1, 'В наявності'),
 (2, 'Не в наявності');
 
INSERT INTO delivery (way_of_delivery_id, way_of_delivery) VALUES
 (1, 'E-mail'),
 (2, 'Нова Пошта'),
 (3, 'УкрПошта');
 
INSERT INTO event_ticket (event_ticket_id, date, time, place, event_event_id, is_available, state_id, sector_name, delivery_way_of_delivery_id) VALUES
 (1, '15.09.19', '14:00', 4, 1, 1, 2, 'Балкон', 1),
 (2, '18.09.19', '17:00', 12, 2, 1, 2, 'Сидяче місце', 1),
 (3, '21.09.19', '18:30', 1, 7, 1, 2, 'Сидяче місце', 1),
 (4, '28.09.19', '19:45', 7, 3, 1, 2, 'Сидяче місце', 1),
 (5, '12.09.19', '23:00', 19, 4, 1, 2, 'Віп-ложа', 1),
 (6, '14.09.19', '12:00', 23, 5, 1, 2, 'Сидяче місце', 2),
 (7, '7.10.19', '17:15', 45, 8, 2, 2, 'Балкон', 1),
 (8, '23.11.19', '14:15', 21, 10, 1, 2, 'Сидяче місце', 1),
 (9, '10.12.19', '16:30', 26, 6, 1, 2, 'Сидяче місце', 1),
 (10, '20.12.19', '18:20', 76, 9, 1, 2, 'Сидяче місце', 1);
 
INSERT INTO sex (sex_id, sex_of_artist) VALUES
 (1, 'Чоловік'),
 (2, 'Жінка');
 
INSERT INTO artist (artist_id, first_name_of_artist, last_name_of_artist, sex_sex_id) VALUES
 (1, 'Олег', 'Винник', 1),
 (2, 'Кончіта', 'Вюрст', 2),
 (3, 'Оксана', 'Могилевська', 2),
 (4, 'Тарас', 'Тополя', 1),
 (5, 'Ярослав', 'Клочник', 1);   
 
INSERT INTO event_has_artist (event_event_id, artist_artist_id) VALUES
 (2, 1),
 (6, 1),
 (9, 1),
 (1, 2),
 (3, 3),
 (4, 3),
 (5, 3),
 (7, 4),
 (10, 4),
 (8, 5);