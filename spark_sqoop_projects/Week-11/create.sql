/* Lavanya Surikapuram - WEEK 11 TABLE CREATION */

/* Drop the database if it exists */
DROP DATABASE IF EXISTS `hadoopguide`;

/* hadoopguide DB creation */
CREATE DATABASE hadoopguide;
use hadoopguide;

/* Granting PRIVILEGES */
GRANT ALL PRIVILEGES ON hadoopguide.* TO 'root'@'localhost';

/* Drop the table if it exists */
DROP TABLE IF EXISTS `widgets`;

/* widgets table creation-schema*/
CREATE TABLE widgets(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
widget_name VARCHAR(64) NOT NULL,
price DECIMAL(10,2),
design_date DATE,
version INT,
design_comment VARCHAR(100)); 
