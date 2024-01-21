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
    pdt_url VARCHAR(100) NOT NULL,
    pdt_alt VARCHAR(20) NOT NULL,
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

INSERT INTO PRODUCT(id, name, price, stock_qty, type, pdt_url, pdt_alt, disabled) VALUES (1, '10-day North Europe', 4000, 10, 'Package Tour', 'https://lh3.google.com/u/0/d/1-39WJ3KANp6rh_KH8j3MrfUdnrTLST1W=w1920-h879-iv1', 'north_europe.jpg', 'N');
INSERT INTO PRODUCT(id, name, price, stock_qty, type, pdt_url, pdt_alt, disabled) VALUES (2, '5-day North Japan', 1700, 5, 'Package Tour', 'https://lh3.google.com/u/0/d/1KWDov0o05TJAG1qdcIbTUfDgfCoAc3TM=w1920-h879-iv1', 'north_japan.jpg', 'N');
INSERT INTO PRODUCT(id, name, price, stock_qty, type, pdt_url, pdt_alt, disabled) VALUES (3, '5-day Vietnam', 600, 3, 'Flight/Hotel Package', 'https://lh3.google.com/u/0/d/1D77R1GUtRxf8_BcN8Lc6ed_ZpWqw9_eC=w1920-h879-iv1', 'vietnam.jpg', 'N');
INSERT INTO PRODUCT(id, name, price, stock_qty, type, pdt_url, pdt_alt, disabled) VALUES (4, '5-day South Korea', 640, 1, 'Flight/Hotel Package', 'https://lh3.google.com/u/0/d/1qqa6GfHDma_tPbjdb-OVJurG5lAWOKfO=w1920-h879-iv1', 'south_korea.jpg', 'N');

INSERT INTO CUSTOMER(id, name, phone, email) VALUES (1, 'althea', '039551287', 'chuchu1214@gmail.com');