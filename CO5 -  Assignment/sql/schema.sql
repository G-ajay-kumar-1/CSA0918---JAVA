CREATE DATABASE IF NOT EXISTS co5_assignment;
USE co5_assignment;
CREATE TABLE IF NOT EXISTS accounts (
  account_id INT PRIMARY KEY,
  holder_name VARCHAR(100) NOT NULL,
  balance DECIMAL(12,2) NOT NULL CHECK (balance >= 0)
);
CREATE TABLE IF NOT EXISTS bus_passes (
  pass_id VARCHAR(20) PRIMARY KEY,
  register_no VARCHAR(20) NOT NULL,
  student_name VARCHAR(100) NOT NULL,
  route_no INT NOT NULL,
  boarding_stop VARCHAR(100) NOT NULL,
  validity_date DATE NOT NULL,
  amount_paid DECIMAL(10,2) NOT NULL
);
INSERT INTO accounts(account_id,holder_name,balance) VALUES
(1,'Student A',2000.00),(2,'Student B',1000.00)
ON DUPLICATE KEY UPDATE holder_name=VALUES(holder_name);
