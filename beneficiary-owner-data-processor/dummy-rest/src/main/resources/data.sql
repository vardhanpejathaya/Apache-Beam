DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
  account_id INT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  balance INT,
  account_opening_date VARCHAR(250) DEFAULT NULL,
  address_line_1 VARCHAR(250) DEFAULT NULL,
  address_line_2 VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  country VARCHAR(250) DEFAULT NULL
);

INSERT INTO accounts (account_id, first_name, last_name, balance, account_opening_date, address_line_1, address_line_2, city, country) VALUES
  (12131, 'Aliko', 'Dangote', 2345, '05-August-2010', 'House No.1', 'Seattle', 'Washington', 'USA'),
  (19634, 'Bill', 'Clinton', 24345, '31-August-2019', 'House No.6', 'San Diego', 'California', 'USA'),
  (32454, 'Catherine', 'Teresa', 20345, '08-November-2013', 'House No.5', 'Watford', 'London', 'UK'),
  (65436, 'Fred', 'James', 42345, '07-January-2009', 'House No.19', 'Brooklyn', 'New York', 'USA'),
  (32908, 'Hall', 'Cooper', 23485, '05-April-2017', 'House No.21', 'Chiba', 'Tokyo', 'Japan'),
  (23723, 'Andrew', 'Flintoff', 32345, '15-July-2015', 'House No.41', 'Pinggu District', 'Beijing', 'China'),
  (87329, 'Max', 'More', 345, '25-September-2013', 'House No.18', 'Blacktown', 'Sydney', 'Australia'),
  (92932, 'Harry', 'Potter', 4345, '05-December-2018', 'House No.16', 'Northam Town', 'Perth', 'Australia'),
  (87659, 'Peter', 'John', 12345, '06-June-2001', 'House No.20', 'Pine Town', 'Durban', 'South Africa'),
  (42343, 'Adam', 'Jobs', 23450, '09-May-2007', 'House No.30', 'Patil Sadan', 'Mumbai', 'India');