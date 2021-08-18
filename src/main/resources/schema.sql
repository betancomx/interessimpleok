DROP TABLE IF EXISTS intsimpletable;

CREATE TABLE intsimple(
    id INT AUTO_INCREMENT PRIMARY KEY,
    payment_number INT NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    payment_date VARCHAR(10) NOT NULL    
);
