USE vignesh_ramachandran_corejava_project;
SET time_zone = 'Asia/Kolkata'; 

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

CREATE TABLE IF NOT EXISTS address_book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    door_no VARCHAR(10), 
    street_name VARCHAR(255) NOT NULL,
    sub_locality VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    pincode INT,
    status BOOLEAN DEFAULT TRUE,
    set_as_default BOOLEAN,
    selected BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

SELECT * FROM address_book;





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

CREATE TABLE IF NOT EXISTS cart(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
    menu_id INT,
    category_id INT,
    no_of_guest INT,
    total_cost INT,
    delivery_date DATE NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (menu_id) REFERENCES menus(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);


CREATE TABLE IF NOT EXISTS orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    menu_id INT,
    category_id INT,
    no_of_guest INT,
    total_cost INT,
    order_date TIMESTAMP NOT NULL,
    delivery_date DATE NOT NULL,
    order_status ENUM ("DELIVERED", "NOT_DELIVERED", "CANCELLED") NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (menu_id) REFERENCES menus(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);


CREATE TABLE IF NOT EXISTS order_products(
	id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT, 
    dish_id INT,
    price_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (dish_id) REFERENCES category_dishes(dish_id),
    FOREIGN KEY (price_id) REFERENCES dish_price(id)
);



-- INSERT INTO orders(user_id, no_of_guest, total_cost, order_date, delivery_date, order_status,menu_id, category_id)
-- VALUES(2, 10, 100, "2023-09-09 13:46:27.405025", 2023-10-06, "NOT_DELIVERED", 1, 1);

-- drop table order_products;
-- drop table orders;




