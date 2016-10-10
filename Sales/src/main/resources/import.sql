# Region ids are auto-generated
insert into region (name) values ("Northeast");
insert into region (name) values ("Southeast");

insert into employee (name, type, password, regionId) values ("SalesRep1", 0, "test", 1);
insert into employee (name, type, password, regionId) values ("RegionalManager1", 1, "test", 1);
insert into employee (name, type, password, regionId) values ("TheGeneral", 2, "test", 1);
insert into employee (name, type, password, regionId) values ("SalesRep2", 0, "test", 1);

insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Ben", 123, "1234567890987654", "10/22");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Not Ben", 456, "8484848484848484", "12/31");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Kind of Ben", 1098, "9999999999999999", "1/25");
