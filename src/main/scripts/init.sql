DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (`restaurant_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20) NOT NULL, `address` VARCHAR(100), `contact` VARCHAR(15), `rating` VARCHAR(5)) ENGINE=InnoDB;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (`menu_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT, `restaurant_id` INT NOT NULL, `name` VARCHAR(20) NOT NULL, `description` VARCHAR(255), KEY `restaurant_id` (`restaurant_id`), CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)) ENGINE=InnoDB;

DROP TABLE IF EXISTS `menu_item`;
CREATE TABLE `menu_item` (`menu_item_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT, `menu_id` INT NOT NULL, `name` VARCHAR(20) NOT NULL, `description` VARCHAR(255), `price` VARCHAR(10), KEY `menu_id` (`menu_id`), CONSTRAINT `menu_item_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)) ENGINE=InnoDB;
