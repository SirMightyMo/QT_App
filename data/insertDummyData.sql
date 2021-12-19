INSERT INTO customer(company,contact,phone,mobile,street,house_number,zip,city,country)VALUES('SpaceX','Elon Musk','+12453221234','152356516345','Teslastreet','1','33245','Austin','USA');
INSERT INTO customer(company,contact,phone,mobile,street,house_number,zip,city,country)VALUES('Apple','Tim Cook','+35545244156','21345525354343','Apple Boulevard','23','4444','Seattle','USA');
INSERT INTO customer(company,contact,phone,mobile,street,house_number,zip,city,country)VALUES('Amazon','Jeff Bezos','+8753221234','85932853959','Amazonroad','444','54252','Seattle','USA');
INSERT INTO service(name,description,internal_rate,external_rate)VALUES('Frontend Design','Erstellen einer Landingpage','50','120');
INSERT INTO service(name,description,internal_rate,external_rate)VALUES('Backend Stuff','API bauen','60','150');
INSERT INTO project(name,start_date,end_date,active,c_id,s_id)VALUES('Projekt 1','2021-01-15','2022-01-15','TRUE','1','1');
INSERT INTO project(name,start_date,end_date,active,c_id,s_id)VALUES('Projekt 1','2021-01-15','2022-01-15','TRUE',(select c_id from customer where company = 'SpaceX'),(select s_id from service where name = 'Frontend Design'));


