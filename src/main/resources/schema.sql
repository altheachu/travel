CREATE TABLE IF NOT EXISTS CUSTOMER (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(20) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(20) NOT NULL,
);

CREATE TABLE IF NOT EXISTS ORDERS (
    id INT PRIMARY KEY auto_increment,
    order_date DATE NOT NULL,
    order_amt DECIMAL(8,2) NOT NULL,
    customer_id INT,
    foreign key (customer_id) references CUSTOMER(id)
);

CREATE TABLE IF NOT EXISTS PRODUCT (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(20) NOT NULL,
    price DECIMAL(6,2) NOT NULL,
    stock_qty DECIMAL(6,2) NOT NULL,
    type VARCHAR(20) NOT NULL,
);

CREATE TABLE IF NOT EXISTS ORDERSDETAIL (
    id INT PRIMARY KEY auto_increment,
    order_qty INT NOT NULL,
    order_id INT,
    product_id INT,
    foreign key (order_id) references ORDERS(id),
    foreign key (product_id) references PRODUCT(id),
);