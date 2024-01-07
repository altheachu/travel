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
    daily_seqno INT,
    cancel_flag VARCHAR(1),
    foreign key (customer_id) references CUSTOMER(id)
);

CREATE TABLE IF NOT EXISTS PRODUCT (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(20) NOT NULL,
    price DECIMAL(6,2) NOT NULL,
    stock_qty DECIMAL(6,2) NOT NULL,
    type VARCHAR(20) NOT NULL,
    disabled VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDERSDETAIL (
    id INT PRIMARY KEY auto_increment,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    order_qty INT NOT NULL,
    foreign key (order_id) references ORDERS(id),
    foreign key (product_id) references PRODUCT(id),
);

CREATE TABLE IF NOT EXISTS EVENTLOG (
    id INT PRIMARY KEY auto_increment,
    msg_id VARCHAR(5) NOT NULL,
    send_time VARCHAR(20) NOT NULL,
    content VARCHAR(200) NOT NULL,
    type VARCHAR(10) NOT NULL,
);

INSERT INTO PRODUCT(id, name, price, stock_qty, type, disabled) VALUES (1, '10-day Norway', 1000, 10, 'Group Travel', 'N');
INSERT INTO PRODUCT(id, name, price, stock_qty, type, disabled) VALUES (2, '5-day Japan', 200, 5, 'Group Travel', 'N');
INSERT INTO CUSTOMER(id, name, phone, email) VALUES (1, 'althea', '039551287', 'chuchu1214@gmail.com');