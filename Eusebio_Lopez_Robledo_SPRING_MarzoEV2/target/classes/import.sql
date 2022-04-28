INSERT INTO cliente VALUES(010,'ALICIA PEREZ','CALLE SOL, 5');
INSERT INTO cliente VALUES(011,'JAVIER GARCÍA','CALLE LUNA, 21');
INSERT INTO cliente VALUES(012,'JAVIER SANCHEZ','CALLE ESTRELLA PROCIÓN S/N');
INSERT INTO cliente VALUES(013,'ALICIA MORALES','CALLE ARCO 89');
INSERT INTO cliente VALUES(014,'JUAN LOPEZ','AVDA REAL 1');
INSERT INTO cliente VALUES(015,'JAVIER PONCE','POLG LA RED 32');
INSERT INTO cliente VALUES(016,'ISMAEL MARTINEZ','LA ALAMEDA 15');


INSERT INTO marca VALUES('M1','Keter');
INSERT INTO marca VALUES('M2','Buko');
INSERT INTO marca VALUES('M3','Giovara');
INSERT INTO marca VALUES('M4','Black+Decker');

INSERT INTO jardineria VALUES('P1','Grifo de jardin','descripción p1',1,'M1');
INSERT INTO jardineria VALUES('P2','cortadora de césped','descripción p2',1,'M3');
INSERT INTO jardineria VALUES('P3','motosierra','descripción p3',1,'M1');
INSERT INTO jardineria VALUES('P4','compostadora','descripción p4',3,'M2');
INSERT INTO jardineria VALUES('P5','cortadora de césped','descripción p5',1,'M2');
INSERT INTO jardineria VALUES('P6','cortadora de césped','descripción p6',1,'M4');
INSERT INTO jardineria VALUES('P7','sierra de lama','descripción p7',2,'M3');



INSERT INTO alquiler ( cod_cli,cod_prod,fechainicio,fechafin,preciodia,valoracion) VALUES(010,'P2', '2021/02/21 08:22:22','2021/02/22 08:22:22',12,8);

INSERT INTO alquiler ( cod_cli,cod_prod,fechainicio,fechafin,preciodia,valoracion) VALUES(010,'P3', '2021/03/21 08:22:22','2021/03/23 08:22:22',14,5);

INSERT INTO alquiler ( cod_cli,cod_prod,fechainicio,fechafin,preciodia,valoracion) VALUES(010,'P2', '2021/01/15 08:22:22','2021/01/22 08:22:22',22,4);

INSERT INTO alquiler ( cod_cli,cod_prod,fechainicio,fechafin,preciodia,valoracion) VALUES(012,'P6', '2021/02/21 08:22:22','2021/02/22 08:22:22',10,8);

INSERT INTO alquiler ( cod_cli,cod_prod,fechainicio,fechafin,preciodia,valoracion) VALUES(015,'P2', '2021/05/10 08:22:22','2021/05/25 08:22:22',12,4);
