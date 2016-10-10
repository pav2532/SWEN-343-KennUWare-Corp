# Region ids are auto-generated
insert into region (name) values ("Northeast");
insert into region (name) values ("Southeast");

insert into employee (name, type, password, regionId) values ("SalesRep1", 0, "test", 1);
insert into employee (name, type, password, regionId) values ("RegionalManager1", 1, "test", 1);
insert into employee (name, type, password, regionId) values ("TheGeneral", 2, "test", 1);
insert into employee (name, type, password, regionId) values ("SalesRep2", 0, "test", 1);