USE customers;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    gender CHAR(1) NOT NULL,
    birthday DATE NOT NULL,
    cardnumber VARCHAR(16) NOT NULL
);

INSERT INTO customers (lastname, firstname, gender, birthday, cardnumber) VALUES
    ('Smith', 'John', 'M', '1985-07-21', '4532768191023456'),
    ('Doe', 'Jane', 'F', '1990-05-15', '4485276247538645'),
    ('Brown', 'Charlie', 'M', '1982-12-17', '4716258050958645'),
    ('Wilson', 'Claire', 'F', '1988-03-30', '4532277461209876'),
    ('Johnson', 'Bruce', 'M', '1995-08-09', '4929939187653421'),
    ('Davis', 'Alice', 'F', '1974-11-01', '4485367249784532'),
    ('Miller', 'Frank', 'M', '1980-01-22', '4556737586899855'),
    ('Taylor', 'Linda', 'F', '1983-04-16', '4916119143456782'),
    ('Anderson', 'Julia', 'F', '1993-09-10', '4716550251234567'),
    ('Thomas', 'Roger', 'M', '1992-06-28', '4532056749338457'),
    ('Jackson', 'Evelyn', 'F', '1979-12-30', '4485793387742235'),
    ('White', 'Tobias', 'M', '1977-04-18', '4929405049611425'),
    ('Harris', 'Vanessa', 'F', '1985-11-23', '4539856741923452'),
    ('Garcia', 'Diana', 'F', '1991-03-15', '4532768112345678');

COMMIT;