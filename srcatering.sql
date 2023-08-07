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