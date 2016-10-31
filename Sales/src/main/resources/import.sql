insert into Item (name, unitPrice) values ("Fitness", "10.0");
insert into Item (name, unitPrice) values ("health", "20.0");
insert into Item (name, unitPrice) values ("whatever", "40.0");

insert into ItemOrders (orderId, itemId, quantity) values ("1", "1", "10");
insert into ItemOrders (orderId, itemId, quantity) values ("2", "1", "5");
insert into ItemOrders (orderId, itemId, quantity) values ("3", "1", "1");
insert into ItemOrders (orderId, itemId, quantity) values ("4", "1", "2");

insert into Region (name) values ("Northeast");
insert into Region (name) values ("Southeast");

insert into Employee (name, type, password, regionId) values ("SalesRep1", 0, "test", 1);
insert into Employee (name, type, password, regionId) values ("RegionalManager1", 1, "test", 1);
insert into Employee (name, type, password, regionId) values ("TheGeneral", 2, "test", 1);
insert into Employee (name, type, password, regionId) values ("SalesRep2", 0, "test", 1);
insert into Employee (name, type, password, regionId) values ("SalesRep3", 0, "test", 1);

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

insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Ben", 123, "370177912516026", "10/22");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Not Ben", 456, "372459690681232", "12/31");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Ben", 1, "375349952034831", "10/22");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Not Ben", 4, "347981228607357", "12/31");
insert into SalesOrder (customerName, employeeID, creditCardNumber, expirationDate) values ("Kind of Ben", 1098, "346915338548193", "1/25");