DROP TABLE IF EXISTS employee;
 
CREATE TABLE employee (
  id INT  PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO employee (id, firstName, lastName, email) VALUES
  (1, 'Abhilash', 'Sahu', 'absahu@gmail.com'),
  (2, 'Bill', 'Gates', 'billGates@gmail.com'),
  (3, 'Lokesh', 'Gupta', 'lokeshGupta@gmail.com');