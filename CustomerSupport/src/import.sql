insert into employee (name, type, password, regionId) values ("CSAgent1", 0, "test", 1);
insert into employee (name, type, password, regionId) values ("CSAgent2", 0, "test", 1);
insert into employee (name, type, password, regionId) values ("Tina", 1, "test", 1);
insert into employee (name, type, password, regionId) values ("CSManager", 1, "test", 1);

insert into returns (RMA, reason, storeID, type) values ("B35426", "It broke", 1, 0);
insert into returns (RMA, reason, storeID, type) values ("MGDS3523", "Screen fell off", 1, 1);
insert into returns (RMA, reason, storeID, type) values ("G43753", "Hated it", 1, 0);

insert into customer (address, name) values ("555 Temp road", "Joey");
insert into customer (address, name) values ("IDK", "Bobby");
insert into customer (address, name) values ("SATISFIED?", "Tina");

insert into datetrail (returnsID, approveDenyDate, receiveDate, requestDate, resolveDate) values (2, '2000-05-12 07:34:25', '2000-05-13 07:34:25', '2000-05-14 07:34:25', '2000-05-15 15:23:25');
insert into datetrail (returnsID, approveDenyDate, receiveDate, requestDate, resolveDate) values (3, '2020-08-16 07:34:25', '2000-05-13 07:34:25', '1998-05-14 07:34:25', '1996-05-15 15:23:25');

insert into region (name) values ("Northeast");
insert into region (name) values ("Southeast");

insert into refund (RMA, refund) values ("Mnds3325", 69.3);
insert into refund (RMA, refund) values ("325342", 536.67);
insert into refund (RMA, refund) values ("ddsewt", 164.3);
insert into refund (RMA, refund) values ("FDSG325", 125.30);