insert into clientes (nombre, apellido, email, create_at) VALUES ('Allan', 'Grant','drAGrant@montanaU.mu', '2017-08-28');
insert into clientes (nombre, apellido, email, create_at) VALUES ('Ian', 'Malcolm','itsChaosBaby@hotmail.com', '2017-08-28');
insert into clientes (nombre, apellido, email, create_at) VALUES ('Ellie', 'Sadler','persiac@gmail.com', '2022-02-10');
insert into clientes (nombre, apellido, email, create_at) VALUES ('Lewis', 'Dodgson','barbasol@outlook.com', '1993-02-10');
insert into clientes (nombre, apellido, email, create_at) VALUES ('Dennis', 'Nedry','magicword@gmail.com', '1995-02-10');

insert into facturas (descripcion, observacion, id_cliente, create_at) VALUES ('Factura 1', 'Observaciones',1, '2017-09-28');
insert into facturas (descripcion, observacion, id_cliente, create_at) VALUES ('Factura 2', 'Observaciones',1, '2017-10-13');
insert into facturas (descripcion, observacion, id_cliente, create_at) VALUES ('Factura 3', 'Observaciones',2, '2017-09-30');
insert into facturas (descripcion, observacion, id_cliente, create_at) VALUES ('Factura 4', 'Observaciones',2, '2017-10-01');

insert into productos (nombre, precio, create_at) VALUES ('Folios', 4, '2017-10-01');
insert into productos (nombre, precio, create_at) VALUES ('Boligrafo azul', 0.60, '2017-10-01');
insert into productos (nombre, precio, create_at) VALUES ('Boligrafo rojo', 0.60, '2017-10-01');
insert into productos (nombre, precio, create_at) VALUES ('Lapiz', 0.40, '2017-10-01');

insert into items (id_factura, id_producto, cantidad) VALUES (1,1,4);
insert into items (id_factura, id_producto, cantidad) VALUES (1,2,3);
insert into items (id_factura, id_producto, cantidad) VALUES (2,1,4);
insert into items (id_factura, id_producto, cantidad) VALUES (2,3,2);
insert into items (id_factura, id_producto, cantidad) VALUES (3,4,5);
insert into items (id_factura, id_producto, cantidad) VALUES (3,1,2);
insert into items (id_factura, id_producto, cantidad) VALUES (4,4,2);
insert into items (id_factura, id_producto, cantidad) VALUES (4,3,6);

