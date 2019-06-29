# StudentAppRepo
This is a small spring boot based application which will insert and retrieve student based information.

# How to download and run

1. Download the application by running git clone https://github.com/pratikm121/StudentAppRepo.git
2. Compile and install the maven project : mvn clean install
3. Run the application from command line : mvn spring-boot:run
4. Download and install Mysql 5.7 on your machine. Once done create the database and table for the application. 
Database name :- rithmofficedb
Table name :- student

Script to be run before the application :- 

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(250) NOT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


Sample Data for Inserting into table

insert into student (firstname, lastname, address , dob) values ('Pratik', 'Mehta', 'Rotterdam Central', '1985-03-05');
insert into student (firstname, lastname, address , dob) values ('John', 'Doe', 'Amsterdam Central', '1990-02-11');
commit;


4. Access the application home page : http://localhost:9090/cgi/home/

