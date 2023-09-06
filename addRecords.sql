INSERT INTO users (name, email, phone_number, password)
VALUES ("Vignesh", "vignesh@gmail.com", 6379370482, "Vig@1234"),
("Maruthan", "maruthan@gmail.com", 7810012456, "Mar@1234"),
("Boobalan", "boobalan@gmail.com", 9878687542, "Bob@1234");

-- findall users
SELECT * FROM users;


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