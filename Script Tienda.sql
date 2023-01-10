create database tienda;
use tienda;
create table Fabricantes(
  CLFAB int Primary Key,
  Nombre varchar(30)
)ENGINE=InnoDB;

create table Articulos(
  CLART int Primary Key,
  Nombre varchar(30),
  Precio int,
  CLFAB int,
  Foreign Key (CLFAB) References Fabricantes(CLFAB)
  ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

insert into Fabricantes values(1,'Kingston');
insert into Fabricantes values(2,'Adata');
insert into Fabricantes values(3,'Logitech');
insert into Fabricantes values(4,'Lexar');
insert into Fabricantes values(5,'Seagate');

insert into Articulos values(1,'Teclado',100,3);
insert into Articulos values(2,'Disco Duro 300Gb',500,5);
insert into Articulos values(3,'Mouse',80,3);
insert into Articulos values(4,'Memoria USB',140,4);
insert into Articulos values(5,'Memoria Ram',290,1);
insert into Articulos values(6,'Disco Duro Extraible 250 Gb',650,5);
insert into Articulos values(7,'Memoria USB',279,1);
insert into Articulos values(8,'DVD Rom',450,2);
insert into Articulos values(9,'CD Rom',200,2);
insert into Articulos values(10,'Tarjeta de Red',180,3);