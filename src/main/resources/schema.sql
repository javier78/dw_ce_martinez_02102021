DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
CREATE TABLE `customers` (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);
CREATE TABLE `products` (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    sku VARCHAR(250) NOT NULL,
    name VARCHAR(250) DEFAULT NULL,
    description VARCHAR(250) DEFAULT NULL,
    price DECIMAL(20,2) DEFAULT NULL
);
CREATE TABLE `orders` (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id INT,
  sku VARCHAR(250) NOT NULL,
  total DECIMAL(20,2) DEFAULT NULL,
  created_at DATETIME DEFAULT NULL,
  foreign key (customer_id) references `customers`(id),
  foreign key (sku) references `products`(sku)
);
