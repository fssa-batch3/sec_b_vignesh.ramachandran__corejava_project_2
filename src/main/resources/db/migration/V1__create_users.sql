Create Database if not exists java_project;

Use java_project;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    isactive BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


INSERT INTO users (username, email, phone_number, password)
VALUES ("Vignesh", "vignesh@gamil.com", "6379370482", "Vig@1234"),
("Maruthan", "maruthan@gamil.com", "7810012456", "Mar@1234"),
("Boobalan", "boobalan@gamil.com", "9878687542", "Bob@1234");

SELECT * FROM users;

CREATE TABLE IF NOT EXISTS menus (
menu_id INT auto_increment primary KEY,
menu_name VARCHAR(50) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


INSERT INTO menus(menu_name)
VALUES ("Breakfast"),
("Lunch"),
("Hightea"),
("Dinner");

SELECT * FROM menus;


CREATE TABLE IF NOT EXISTS categories (
category_id INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO categories (category_name)
VALUES("Ordinary"),
("Special"),
("VIP");

SELECT * FROM categories;