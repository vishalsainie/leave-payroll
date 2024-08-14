CREATE TABLE IF NOT EXISTS `leave` (
    `leave_id` INT AUTO_INCREMENT PRIMARY KEY,
    `employee_id` INT NOT NULL,
    `category` VARCHAR(30) NOT NULL,
    `reason` VARCHAR(30) NOT NULL,
    `leave_date` VARCHAR(30)
);