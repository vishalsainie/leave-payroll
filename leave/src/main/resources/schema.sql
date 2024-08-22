CREATE TABLE IF NOT EXISTS `leave` (
    `leave_id` INT AUTO_INCREMENT PRIMARY KEY,
    `employee_id` INT NOT NULL,
    `category` VARCHAR(20) NOT NULL,
    `reason` VARCHAR(20) NOT NULL,
--    `communication_sw` BOOLEAN,
    `leave_date` VARCHAR(30)
);