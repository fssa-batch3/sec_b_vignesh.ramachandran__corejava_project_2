USE vignesh_ramachandran_corejava_project;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number LONG,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, phone_number, password)
VALUES ("Vignesh", "vignesh@gamil.com", 6379370482, "Vig@1234"),
("Maruthan", "maruthan@gamil.com", 7810012456, "Mar@1234"),
("Boobalan", "boobalan@gamil.com", 9878687542, "Bob@1234");

-- findall users
SELECT * FROM users WHERE status =1;


CREATE TABLE IF NOT EXISTS menus (
id INT auto_increment primary KEY,
menu_name VARCHAR(50) NOT NULL,
description TEXT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO menus(menu_name, description)
VALUES ("Breakfast", "SR Catering Service offers best breakfast menu that is available for all parties and weddings; we also assist customers to create a personalized menu just according to client’s taste and perfect for the occasion"),
("Lunch", "SR Catering Service provides delicious lunch menu that suits all functions and parties; we also allow clients to create a personalized menu just according to their taste, budget"),
("Hightea","SR Catering Service gives evening tea and snacks menu which is available for all functions; we also help customers to create a personalized menu just according to client’s taste and perfect for the occasion"),
("Dinner", "SR Catering Service present a detailed dinner menu which is available for all parties, wedding and reception; we also aid customers to create a personalized and special menu just according to the taste and budget");

SELECT * FROM menus;

CREATE TABLE IF NOT EXISTS categories (
id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO categories (category_name)
VALUES("Ordinary"),
("Special"),
("VIP");

SELECT * FROM categories;

CREATE TABLE IF NOT EXISTS dishes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dish_name VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  quantity_unit enum ('nos', 'grams') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO dishes (dish_name, quantity, quantity_unit)
VALUES ("MINI LADDU",  1, "nos"),
("IDLY",  2, "nos"),
("KITCHADI",  100, "grams"),
("VADA",  1, "nos"),
("POORI & VEG GRAVY", 2, "nos");

select * from dishes;


-- update
-- update dishes Set quantity = 1
-- Where id = 1;


CREATE TABLE IF NOT EXISTS dish_price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP,
    price INT NOT NULL,
    dish_id INT,
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
);

INSERT INTO dish_price (price, dish_id)
VALUES (10, 1),
(15, 2),
(30,3),
(10, 4),
(30, 5);

SELECT * From dish_price;


-- update
-- UPDATE dish_price SET end_date = current_timestamp
-- WHERE dish_id = 1;

-- INSERT INTO dish_price(price, dish_id)
-- Values(10,1);


CREATE TABLE IF NOT EXISTS category_dish(
  id INT PRIMARY KEY AUTO_INCREMENT,
  menu_id INT,
  category_id INT,
  dish_id INT,
  FOREIGN KEY (menu_id) REFERENCES menus(id),
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (dish_id) REFERENCES dishes(id),
  status BOOLEAN NOT NULL DEFAULT true
);

INSERT INTO category_dish(menu_id, category_id, dish_id)
VALUES(1,1,1),
(1,1,2),
(1,1,3),
(1,1,4),
(1,1,5);

SELECT * FROM category_dish;

SELECT d.dish_name
FROM dishes d
JOIN category_dish cd ON d.id = cd.dish_id
WHERE cd.menu_id = 1 AND cd.category_id = 2;


