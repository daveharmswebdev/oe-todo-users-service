drop table if exists users;
drop table if exists hibernate_sequence;

CREATE TABLE users (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   first_name varchar(255) NULL,
   last_name varchar(255) NULL,
   phone varchar(100) NULL,
   email varchar(255) NULL,
   address varchar(255) NULL,
   postal_zip varchar(10) NULL,
   region varchar(50) NULL,
   country varchar(100) NULL
) engine = InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

INSERT INTO users (first_name, last_name, phone, email, address,postal_zip,region,country)
VALUES
    ('Ross','William','1-687-888-7839','vel.arcu@protonmail.net','6871 Donec Avenue','30064','North Island','Peru'),
    ('Dennis','Berger','1-787-828-2931','id.ante.nunc@aol.ca','9463 Ut St.','261826','Newfoundland and Labrador','Vietnam'),
    ('Bell','Wells','(568) 767-8688','vehicula.aliquet@protonmail.ca','572-937 Fusce Rd.','65974-530','Dadra and Nagar Haveli','United Kingdom'),
    ('Kay','Alston','1-152-154-7914','sit@icloud.com','990-3245 Rutrum. Avenue','211156','Vorarlberg','Chile'),
    ('Tamara','Pittman','1-216-229-1464','aliquam.fringilla@icloud.couk','832-4855 Massa. Street','30619','Guanacaste','Belgium'),
    ('Alvin','Rivas','1-930-771-6163','aliquam.ultrices.iaculis@protonmail.couk','137 Etiam Rd.','1346 GR','South Jeolla','Peru'),
    ('Belle','Johns','(355) 703-2487','vitae.velit@hotmail.com','3201 Sociis Street','17754','Thái Bình','Peru'),
    ('Audra','Torres','1-867-533-4734','iaculis.aliquet.diam@outlook.ca','605-9328 Curabitur Street','P7 3BG','Puntarenas','New Zealand'),
    ('Stacey','Reilly','1-813-224-4797','nulla.interdum@aol.net','Ap #547-256 Dapibus Rd.','Y2P 8L4','Western Australia','Germany'),
    ('Jasper', 'French', '(769) 558-7185', 'dolor.nonummy.ac@yahoo.com', '152-4005 Nunc Street', '76827', 'Nuevo León', 'Sweden');
