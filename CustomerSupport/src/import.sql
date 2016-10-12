insert into Employee (name, type, password, regionId) values ("CSAgent1", 0, "test", 1);
insert into Employee (name, type, password, regionId) values ("CSAgent2", 0, "test", 1);
insert into Employee (name, type, password, regionId) values ("Tina", 1, "test", 1);

insert into Employee (name, type, password, regionId) values ("CSManager", 1, "test", 1);

insert into Returns (RMA, reason, storeID, type) values ("B35426", "It broke", 1, 0);
insert into Returns (RMA, reason, storeID, type) values ("MGDS3523", "Screen fell off", 1, 1);
insert into Returns (RMA, reason, storeID, type) values ("G43753", "Hated it", 1, 0);


insert into Employee (name, type, password, regionId) values ("CSManager", 0, "test", 1);
insert into Returns (reason, storeID, type, itemID) values ("It broke", 1, 0, "Yello");
insert into Returns (reason, storeID, type, itemID) values ("Screen fell off", 1, 1, "Active");
insert into Returns (reason, storeID, type) values ("Hated it", 1, 0);

insert into Customer (address, name) values ("555 Temp road", "Joey");
insert into Customer (address, name) values ("IDK", "Bobby");
insert into Customer (address, name) values ("SATISFIED?", "Tina");

insert into DateTrail (returnsID, approveDenyDate, receiveDate, requestDate, resolveDate) values (2, '2000-05-12 07:34:25', '2000-05-13 07:34:25', '2000-05-14 07:34:25', '2000-05-15 15:23:25');
insert into DateTrail (returnsID, approveDenyDate, receiveDate, requestDate, resolveDate) values (3, '2020-08-16 07:34:25', '2000-05-13 07:34:25', '1998-05-14 07:34:25', '1996-05-15 15:23:25');

insert into Region (name) values ("Northeast");
insert into Region (name) values ("Southeast");

insert into Refund (RMA, refund) values ("Mnds3325", 69.3);
insert into Refund (RMA, refund) values ("325342", 536.67);
insert into Refund (RMA, refund) values ("ddsewt", 164.3);
insert into Refund (RMA, refund) values ("FDSG325", 125.30);
