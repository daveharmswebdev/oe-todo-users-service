CREATE TABLE USERS (
   id INT AUTO_INCREMENT PRIMARY KEY,
   first_name varchar(255) NULL,
   last_name varchar(255) NULL,
   phone varchar(100) NULL,
   email varchar(255) NULL,
   address varchar(255) NULL,
   postal_zip varchar(10) NULL,
   region varchar(50) NULL,
   country varchar(100) NULL
);