INSERT INTO users (name, email, phone_number, password)
VALUES ("Vignesh", "vignesh@gmail.com", 6379370482, "Vig@1234"),
("Maruthan", "maruthan@gmail.com", 7810012456, "Mar@1234"),
("Boobalan", "boobalan@gmail.com", 9878687542, "Bob@1234");

-- findall users
SELECT * FROM users;

SELECT * FROM address_book;


INSERT INTO menus(menu_name, description,image)
VALUES ("Breakfast", "SR Catering Service offers best breakfast menu that is available for all parties and weddings; we also assist customers to create a personalized menu just according to client’s taste and perfect for the occasion","https://iili.io/HWhcQZx.jpg"),
("Lunch", "SR Catering Service provides delicious lunch menu that suits all functions and parties; we also allow clients to create a personalized menu just according to their taste, budget","https://iili.io/HWhlhPV.jpg"),
("Hightea","SR Catering Service gives evening tea and snacks menu which is available for all functions; we also help customers to create a personalized menu just according to client’s taste and perfect for the occasion","https://iili.io/HWhlXMQ.jpg"),
("Dinner", "SR Catering Service present a detailed dinner menu which is available for all parties, wedding and reception; we also aid customers to create a personalized and special menu just according to the taste and budget","https://iili.io/HWhlwKB.jpg");

SELECT * FROM menus;



INSERT INTO categories (category_name)
VALUES("Ordinary"),
("Special"),
("VIP");

SELECT * FROM categories;


INSERT INTO category_images (menu_id, category_id, image)
VALUES(1 , 1, "https://iili.io/HWh0ZrB.jpg"),
(1,2,"https://iili.io/HWh0b71.png"),
(1,3,"https://iili.io/HWh0DdP.jpg");


SELECT * FROM category_images;


INSERT INTO dishes (dish_name, quantity, quantity_unit)
VALUES ("MINI LADDU",  1, "NOS"),
("IDLY",  2, "NOS"),
("KITCHADI",  100, "GRAMS"),
("VADA",  1, "NOS");


select * from dishes;


INSERT INTO dish_price (price, dish_id)
VALUES (10, 1),
(15, 2),
(30,3),
(10, 4);

Select * from dish_price;


INSERT INTO category_dishes(menu_id, category_id, dish_id)
VALUES(1,1,1),
(1,1,2),
(1,1,3),
(1,1,4);

SELECT * FROM category_dishes;

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

INSERT INTO cart(user_id, menu_id, category_id, no_of_guest, total_cost, delivery_date)
VALUES (1, 1, 1, 50, 200, "2024-01-01");

SELECT * FROM cart;
select count(id) FROM cart WHERE user_id = 4;



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

select * from orders;

CREATE TABLE IF NOT EXISTS order_products(
	id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT, 
    dish_id INT,
    price_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (dish_id) REFERENCES category_dishes(dish_id),
    FOREIGN KEY (price_id) REFERENCES dish_price(id)
);

SELECT * FROM order_products;
