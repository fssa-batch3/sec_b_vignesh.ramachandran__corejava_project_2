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


INSERT INTO users (name, email, phone_number, password)
VALUES ("Vignesh", "vignesh@gmail.com", 6379370482, "Vig@1234"),
("Maruthan", "maruthan@gmail.com", 7810012456, "Mar@1234"),
("Boobalan", "boobalan@gmail.com", 9878687542, "Bob@1234");

-- findall users
SELECT * FROM users;





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
  quantity_unit enum ('NOS', 'GRAMS') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO dishes (dish_name, quantity, quantity_unit)
VALUES ("MINI LADDU",  1, "NOS"),
("IDLY",  2, "NOS"),
("KITCHADI",  100, "GRAMS"),
("VADA",  1, "NOS");


select * from dishes;




-- update
-- update dishes Set quantity = 1
-- Where id = 1;


CREATE TABLE IF NOT EXISTS dish_price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_date TIMESTAMP ,
    price INT NOT NULL,
    dish_id INT,
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
);

INSERT INTO dish_price (price, dish_id)
VALUES (10, 1),
(15, 2),
(30,3),
(10, 4);

SELECT * From dish_price;


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
(1,1,4);

SELECT * FROM category_dish;

-- drop table category_dish;
-- drop table dish_price;
-- drop table dishes;

SELECT d.id, d.dish_name, d.quantity, d.quantity_unit, cd.menu_id, cd.category_id, dp.price FROM dishes d JOIN category_dish cd ON d.id = cd.dish_id
JOIN dish_price dp ON d.id = dp.dish_id WHERE d.id = 1;


-- SELECT c.dish_id, dp.price FROM category_dish c INNER JOIN dish_price dp ON c.dish_id = dp.dish_id WHERE c.menu_id = 1 AND c.category_id =1;

-- SELECT d.dish_name
-- FROM dishes d
-- JOIN category_dish cd ON d.id = cd.dish_id
-- WHERE cd.menu_id = 1 AND cd.category_id = 2;

CREATE TABLE IF NOT EXISTS orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    no_of_guest INT,
    total_cost INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    order_status ENUM ("DELIVERED", "NOT_DELIVERED", "CANCELLED") NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

select * from orders;



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

-- getting menu_id,category_id, dish_id and price for each dish
SELECT cd.menu_id, cd.category_id, cd.dish_id, dp.id as price_id
FROM category_dish cd 
JOIN dish_price dp ON cd.dish_id = dp.dish_id 
WHERE cd.menu_id=1 AND cd.category_id = 1 AND status = 1;

-- getting total cost by menu_id and category_id
SELECT SUM(dp.price) AS total_cost
FROM dish_price AS dp
JOIN category_dish cd ON cd.dish_id = dp.dish_id
WHERE cd.menu_id = 1 AND cd.category_id = 1 AND status = 1;




