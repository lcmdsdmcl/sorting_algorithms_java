Sorting Alogrithms Using Array Data Type in Java
------------------------------------------------

The program has 3 parts:

DefineArray: contains an array data type from scratch (define array, insert element, delete element, swap element, display elements, linear & binary search)

Sorting: 5 major sorting algorithms: bubble, selection, insertion, shell and quick sort

JDBC: to get data from an sql table

------------------------------------------------

Run sql_random.sql script or:

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

---------------------------------------------

By default I used 100.000 elements.
Bubble Sorting, O(N^2)...
shellSort elapsedTime (ms) = 20374
big O = 1.410065408E9

Selection Sorting, O(N^2)...
shellSort elapsedTime (ms) = 8971
big O = 1.410065408E9

Insertion Sorting, O(N^2)...
shellSort elapsedTime (ms) = 6787
big O = 1.410065408E9

Shell Sorting, O(N*(logN)^2)...
shellSort elapsedTime (ms) = 33
big O = 2500000.0

Creating backup...
Quick Sorting, O(N*logN)...
(no actual data as I'm going to create a non-recursive version)
