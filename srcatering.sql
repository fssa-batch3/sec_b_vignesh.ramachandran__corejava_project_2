USE vignesh_ramachandran_corejava_project;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number BIGINT NOT NULL,
    password VARCHAR(8) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS menus (
id INT auto_increment primary KEY,
menu_name VARCHAR(50) NOT NULL,
description TEXT NOT NULL,
image VARCHAR(255) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



CREATE TABLE IF NOT EXISTS categories (
id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- https://iili.io/HWh0ZrB.jpg
-- https://iili.io/HWh0b71.png
-- https://iili.io/HWh0DdP.jpg
-- https://iili.io/HWhX7YQ.jpg
-- https://iili.io/HWhXYvV.jpg
-- https://iili.io/HWhXayB.jpg


CREATE TABLE IF NOT EXISTS category_images (
	id INT AUTO_INCREMENT PRIMARY KEY,
	menu_id INT,
	category_id INT,
    image VARCHAR(255) NOT NULL,
	FOREIGN KEY (menu_id) REFERENCES menus(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);



CREATE TABLE IF NOT EXISTS dishes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dish_name VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  quantity_unit enum ('NOS', 'GRAMS') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

	
CREATE TABLE IF NOT EXISTS dish_price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_date TIMESTAMP ,
    price INT NOT NULL,
    dish_id INT,
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
);



CREATE TABLE IF NOT EXISTS category_dishes(
  id INT PRIMARY KEY AUTO_INCREMENT,
  menu_id INT,
  category_id INT,
  dish_id INT,
  FOREIGN KEY (menu_id) REFERENCES menus(id),
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (dish_id) REFERENCES dishes(id),
  status BOOLEAN NOT NULL DEFAULT true
);




-- CREATE TABLE IF NOT EXISTS orders(
-- 	id INT PRIMARY KEY AUTO_INCREMENT,
--     user_id INT,
--     no_of_guest INT,
--     total_cost INT,
--     order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
--     delivery_date TIMESTAMP NOT NULL,
--     order_status ENUM ("DELIVERED", "NOT_DELIVERED", "CANCELLED") NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES users(id)
-- );

-- select * from orders;



-- CREATE TABLE IF NOT EXISTS order_product(
-- 	id INT PRIMARY KEY AUTO_INCREMENT,
--     order_id INT, 
--     menu_id INT,
-- 	category_id INT,
--     dish_id INT,
--     price_id INT,
--     FOREIGN KEY (order_id) REFERENCES orders(id),
--     FOREIGN KEY (menu_id) REFERENCES menus(id),
--     FOREIGN KEY (category_id) REFERENCES categories(id),
--     FOREIGN KEY (dish_id) REFERENCES category_dish(dish_id),
--     FOREIGN KEY (price_id) REFERENCES dish_price(id)
-- );






