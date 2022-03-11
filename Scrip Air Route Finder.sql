/*
--Creacion de la base de datos Air Route Finder
*/

CREATE SCHEMA `aerolinea_argentina` ;

/*
--Se crea la tabla de aeropuertos con sus nombres y abreviaciones
*/
CREATE TABLE `aerolinea_argentina`.`aeropuerto` (
  `idaeropuerto` INT NOT NULL AUTO_INCREMENT,
  `abreviacion` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`idaeropuerto`));

/*
--Se crea la tabla de vuelos para cargar vuelos
*/
CREATE TABLE `aerolinea_argentina`.`vuelo` (
  `idvuelo` INT NOT NULL AUTO_INCREMENT,
  `numero_vuelo` VARCHAR(45) NOT NULL,
  `fecha` TIMESTAMP NOT NULL,
  `precio` INT NOT NULL,
  `aeropuerto_origen` VARCHAR(45) NOT NULL,
  `aeropuerto_destino` VARCHAR(45) NOT NULL,
  `tiempo_vuelo` VARCHAR(45) NOT NULL,
  `demora` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idvuelo`),
  UNIQUE INDEX `numero_vuelo_UNIQUE` (`numero_vuelo` ASC));

/*
--Inserto los nombres de los aeropuertos para la tabla aeropuerto
*/
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('JUJ', 'Aeropuerto de San Salvador de Jujuy');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('SLA', 'Aeropuerto internacional de Salta');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('FMA', 'Aeropuerto de Formosa');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('RES', 'Aeropuerto de Resistencia ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('SDE', 'Aeropuerto de Santiago del Estero');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('TUC', 'Aeropuerto internacional de Tucumán');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('CTC', 'Aeropuerto de Catamarca');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('PSS', 'Aeropuerto de Posadas');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ('IRJ', 'Aeropuerto de La Rioja');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'SFN', 'Aeropuerto de Santa Fe - Sauce Viejo ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'COR', 'Aeropuerto de Córdoba Pajas Blancas');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'PRA', 'Aeropuerto de Parana');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'LUQ', 'Aeropuerto de Villa de Merlo San Luis');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'UAQ', 'Aeropuerto de San Juan ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'AFA', 'Aeropuerto de San Rafael Mendoza');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'MDZ', 'Aeropuerto de Mendoza El Plumerillo');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'NQN', 'Aeropuerto de Neuquén');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'RSA', 'Aeropuerto de Santa Rosa La Pampa ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'BRC', 'Aeropuerto de Bariloche ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'EQS', 'Aeropuerto de Esquel');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'REL', 'Aeropuerto de Trelew');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'CRD', 'Aeropuerto de Comodoro Rivadavia');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'PMY', 'Aeropuerto de Puerto Madryn ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'RGL', 'Aeropuerto de Río Gallegos');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'USH', 'Aeropuerto internacional de Ushuaia');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'BHI', 'Aeropuerto de Bahía Blanca');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'MDQ', 'Aeropuerto de Mar del Plata ');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'ROS', 'Aeropuerto de Rosario Fisherton');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'AEP', 'Aeroparque Jorge Newbery Buenos Aires');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'VDM', 'Aeropuerto de Viedma-Río Negro');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'CPC', 'Aeropuerto de Chapelco - Neuquen	');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'RCU', 'Aeropuerto de Río Cuarto - Cordoba');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'RHD', 'Aeropuerto de Termas de Río Hondo - Santiago del Estero');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'IGR', 'Aeropuerto de Puerto Iguazú (Cataratas)- Misiones');
INSERT INTO `aerolinea_argentina`.`aeropuerto` (`abreviacion`, `nombre`) VALUES ( 'FTE', 'Aeropuerto de El Calafate (Los Glaciares) - Santa Cruz');

/*
--Inserto algunos vuelos
*/

/*
--Vuelo de una escala ejemplo: Origen: JUJ Destino: AEP para la fecha 2022-03-09
*/
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1000','2022-03-11 07:05',4035,'JUJ','AEP', '01:55', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1001','2022-03-11 08:30',2223,'JUJ','COR', '01:20', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1002','2022-03-11 09:50',4116,'SLA','AEP', '02:00', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1003','2022-03-11 10:30',2221,'SLA','COR', '01:14', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1004','2022-03-11 11:45',3412,'FMA','AEP', '01:35', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1005','2022-03-11 13:00',2789,'RES','AEP', '01:30', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1006','2022-03-11 14:00',2590,'RES','COR', '01:25', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1007','2022-03-11 14:56',3385,'SDE','AEP', '01:26', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1008','2022-03-11 15:30',4523,'TUC','AEP', '01:45', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1009','2022-03-11 16:20',1571,'TUC','COR', '00:57', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1010','2022-03-11 16:38',3250,'CTC','AEP', '01:30', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1011','2022-03-11 17:20',3223,'PSS','AEP', '01:41', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1012','2022-03-11 18:10',3250,'IRJ','AEP', '01:26', '00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` ( `numero_vuelo`, `fecha`, `precio`, `aeropuerto_origen`, `aeropuerto_destino`, `tiempo_vuelo`, `demora`) VALUES ( 'AR 1013','2022-03-11 18:30',1815,'SFN','AEP', '00:53', '00:00');
/*
--Vuelos de 3 escalas ejemplo:
Origen: JUJ
Destino: AEP
para la fecha: 2022-03-10
*/
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2000','2022-03-12 07:05:00',4035,'JUJ','AEP','01:55','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2001','2022-03-12 08:30:00',2223,'JUJ','COR','01:20','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2002','2022-03-12 09:50:00',1000,'COR','AEP','02:00','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2003','2022-03-12 10:30:00',8221,'SLA','COR','01:14','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2004','2022-03-12 11:45:00',2412,'SLA','AFA','01:35','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2005','2022-03-12 13:00:00',2789,'AFA','COR','01:30','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2006','2022-03-12 14:00:00',2590,'RES','COR','01:25','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2007','2022-03-12 14:56:00',9385,'RES','AEP','01:26','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2008','2022-03-12 15:30:00',4523,'RES','PSS','01:45','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2009','2022-03-12 16:20:00',1571,'PSS','SFN','00:57','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2010','2022-03-12 16:38:00',9250,'CTC','AEP','01:30','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2011','2022-03-12 17:20:00',3223,'CTC','PSS','01:41','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2012','2022-03-12 18:10:00',2250,'PSS','AEP','01:26','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 2013','2022-03-12 18:30:00',1815,'SFN','AEP','00:53','00:00');

/*
--Vuelos de 3 escalas ejemplo:
Origen: JUJ
Destino: AEP
para la fecha: 2022-03-11
*/
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 3000','2022-03-13 08:10:00',5620,'JUJ','AEP','05:00','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 3001','2022-03-13 09:10:00',5200,'JUJ','TUC','03:00','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 3002','2022-03-13 11:15:00',50,'TUC','CTC','06:00','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 3003','2022-03-13 12:20:00',100,'CTC','AEP','09:00','00:00');

/*
--Vuelos que aparecen en la documentacion como ejemplo
Origen: REL (TRELEW)
Destino (PSS) POSADAS
para la fecha 2022-03-14
*/

INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4001','2022-03-14 09:10:00',1509,'REL','BHI','01:15','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4002','2022-03-14 10:10:00',2305,'BHI','NQN','01:15','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4003','2022-03-14 11:15:00',2275,'NQN','MDZ','01:19','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4004','2022-03-14 12:20:00',3439,'NQN','AEP','01:25','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4005','2022-03-14 13:10:00',3060,'MDZ','AEP','01:34','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4006','2022-03-14 14:10:00',4089,'REL','AEP','01:15','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4007','2022-03-14 15:15:00',897,'REL','CRD','01:00','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4008','2022-03-14 16:20:00',3358,'CRD','AEP','02:15','00:00');
INSERT INTO `aerolinea_argentina`.`vuelo` (`numero_vuelo`,`fecha`,`precio`,`aeropuerto_origen`,`aeropuerto_destino`,`tiempo_vuelo`,`demora`) VALUES ('AR 4009','2022-03-14 16:20:00',3223,'AEP','PSS','01:41','00:00');