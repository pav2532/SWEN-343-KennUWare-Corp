insert into Item (name, unitPrice) values ("Fitness", "10.0");

insert into Item (name, unitPrice) values ("health", "20.0");
insert into Item (name, unitPrice) values ("whatever", "40.0");

insert into ItemOrders (orderId, itemId, quantity) values ("1", "1", "10");
insert into ItemOrders (orderId, itemId, quantity) values ("2", "1", "5");

insert into Region (name) values ("Northeast");
insert into Region (name) values ("Southeast");

insert into Employee (name, type, password, regionId) values ("SalesRep1", 0, "test", 1);
insert into Employee (name, type, password, regionId) values ("RegionalManager1", 1, "test", 1);
insert into Employee (name, type, password, regionId) values ("TheGeneral", 2, "test", 1);
insert into Employee (name, type, password, regionId) values ("SalesRep2", 0, "test", 1);

insert into Store (regionID, name, address) values (1, "Best Buy", "Some address");
insert into Store (regionID, name, address) values (4, "Radioshack", "Another address");
insert into Store (regionID, name, address) values (2, "Circuit City", "Who needs addresses?");
insert into Store (regionID, name, address) values (3, "Wal-Mart", "The corner of mulberry and main street");

insert into StoreEmployee (storeID, employeeID) values (1, 1);
insert into StoreEmployee (storeID, employeeID) values (1, 2);
insert into StoreEmployee (storeID, employeeID) values (1, 3);
insert into StoreEmployee (storeID, employeeID) values (3, 4);
insert into StoreEmployee (storeID, employeeID) values (2, 5);
insert into StoreEmployee (storeID, employeeID) values (1, 6);

insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Ben", 123, "1234567890987654", "10/22");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Not Ben", 456, "8484848484848484", "12/31");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Ben", 1, "1234567890987654", "10/22");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Not Ben", 4, "8484848484848484", "12/31");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Kind of Ben", 1098, "9999999999999999", "1/25");

