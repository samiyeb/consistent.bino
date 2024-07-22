DROP TABLE IF EXISTS Goal;

CREATE TABLE IF NOT EXISTS Goal (
   id INT,
   goalTitle varchar(255),
   goalDescription varchar(255),
   PRIMARY KEY (id)
);