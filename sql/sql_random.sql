CREATE DATABASE random_numbers;
USE random_numbers;

CREATE TABLE rand_num (
    number INT NOT NULL
) ENGINE = MYISAM;

DELIMITER $$
CREATE PROCEDURE InsertRand(IN NumRows INT, IN MinVal INT, IN MaxVal INT)
    BEGIN
        DECLARE i INT;
        SET i = 1;
        START TRANSACTION;
        WHILE i <= NumRows DO
            INSERT INTO rand_num VALUES (MinVal + CEIL(RAND() * (MaxVal - MinVal)));
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END$$
DELIMITER ;

CALL InsertRand(100000, 1, 100000);
