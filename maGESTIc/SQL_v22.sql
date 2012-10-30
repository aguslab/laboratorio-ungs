drop database Magesti;

create database Magesti;
use Magesti;


-- 
-- TABLE: calidad 
CREATE TABLE calidad (
    id_calidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);



-- 
-- TABLE: cliente 
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(100) NOT NULL,
    cuit VARCHAR(15) NOT NULL,
    cond_iva VARCHAR(50),
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(30),
    mail VARCHAR(30) NOT NULL,
    nombre_contacto VARCHAR(100),
    telefono_contacto VARCHAR(30),
    mail_contacto VARCHAR(50),
    direccion_entrega VARCHAR(100) NOT NULL,
    activo BOOLEAN NOT NULL
);

-- 
-- TABLE: formato_papel 
CREATE TABLE formato_papel (
    id_formato_papel INT AUTO_INCREMENT PRIMARY KEY,
    tamanio VARCHAR(10) NOT NULL
);




-- 
-- TABLE: materiales 
CREATE TABLE materiales (
    id_materiales INT AUTO_INCREMENT PRIMARY KEY,
    id_elemento INT NOT NULL,
    gramaje INT NOT NULL,
    poses_x_pliego INT NOT NULL,
    pliegos_netos INT NOT NULL,
    pliegos_en_demasia INT NOT NULL,
    pliegos_x_hoja INT NOT NULL,
    hojas INT NOT NULL,
    id_calidad INT NOT NULL,
    id_variante INT NOT NULL,
    id_formato_papel INT NOT NULL
);

-- 
-- TABLE: orden_trabajo 
CREATE TABLE orden_trabajo (
    id_orden_trabajo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(50) NOT NULL,
    id_cliente INT NOT NULL,
    f_confeccion DATE NOT NULL,
    f_prometida DATE NOT NULL,
    nombre_trabajo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    cantidad_a_entregar INT NOT NULL,
    cantidad_preimpresion INT NOT NULL,
    ancho FLOAT NOT NULL,
    alto FLOAT NOT NULL,
    apaisado BOOLEAN NOT NULL,
    estado VARCHAR(15) NOT NULL,
    hojas_utilizadas INT NULL,
	f_cierre	DATE,
	f_entrega	DATE
);



-- 
-- TABLE: proceso 
CREATE TABLE proceso (
    id_proceso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);



CREATE TABLE procesos_x_orden_trabajo (
    id_proceso INT NOT NULL,
    id_orden_trabajo INT NOT NULL,
    tercerizada BOOLEAN NOT NULL,
    id_proveedor INT,
    cumplida BOOLEAN NOT NULL,
    observacion VARCHAR(100),
    indice INT NOT NULL,
    PRIMARY KEY (id_proceso , id_orden_trabajo)
);

SELECT o.id_orden_trabajo, c.razon_social, o.f_confeccion,o.f_prometida,o.cantidad_a_entregar, o.estado,o.hojas_utilizadas, pot.id_proceso FROM orden_trabajo o, cliente c, procesos_x_orden_trabajo pot  WHERE f_prometida>=" + fechaHoyAND o.id_cliente=c.id_cliente AND o.estado <> cerrada ORDER BY f_prometida LIMIT 0,5;




-- 
-- TABLE: proveedor 
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(100),
    cuit VARCHAR(15) NOT NULL,
    cond_iva VARCHAR(50),
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(30),
    mail VARCHAR(30) NOT NULL,
    nombre_contacto VARCHAR(100),
    telefono_contacto VARCHAR(30),
    mail_contacto VARCHAR(30),
    direccion_retiro VARCHAR(100),
    activo BOOLEAN NOT NULL
);


-- 
-- TABLE: elemento 
CREATE TABLE elemento (
    id_elemento INT AUTO_INCREMENT PRIMARY KEY,
    id_orden_trabajo INT NOT NULL,
    tipo_elemento VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL
);


CREATE TABLE hojas_utilizadas (
    id_hojas_utilizadas INT AUTO_INCREMENT PRIMARY KEY,
    id_elemento INT NOT NULL,
    cantidad INT NOT NULL,
    f_hora DATETIME NOT NULL,
    estado_OT VARCHAR(15) NOT NULL
);


-- CREATE TABLE hojas_utilizadas_x_elementos(
-- 	id_hojas_utilizadas		INT 		 	NOT NULL,
-- 	id_elemento             INT 			NOT NULL,	
-- 	f_hora					DATETIME		NOT NULL,
-- 	estado_OT				VARCHAR(15)		NOT NULL,
-- PRIMARY KEY (id_hojas_utilizadas, id_elemento)
-- );



-- 
-- TABLE: variante 
--

CREATE TABLE variante(
    id_variante    INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(30)    NOT NULL
);




-- 

CREATE TABLE detalle (
    id_detalle INT AUTO_INCREMENT,
    id_solicitud_compra INT NOT NULL,
    cantidad INT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    id_calidad INT NOT NULL,
    id_formato_papel INT NOT NULL,
    id_variante INT NOT NULL,
    gramaje SMALLINT NOT NULL,
    precio_unitario FLOAT(8 , 2 ) NOT NULL,
    unidad_medida_del_precio VARCHAR(10) NOT NULL,
    importe FLOAT(8 , 2 ) NOT NULL,
    recibido BOOLEAN NOT NULL,
    PRIMARY KEY (id_detalle , id_solicitud_compra)
);







-- 

CREATE TABLE recepcion_pedido (
    id_recepcion_pedido INT AUTO_INCREMENT,
    id_solicitud_compra INT NOT NULL,
    estado VARCHAR(15) NOT NULL,
    f_h_recibido DATETIME,
    incidente VARCHAR(500),
    PRIMARY KEY (id_recepcion_pedido , id_solicitud_compra)
);




-- 

CREATE TABLE solicitud_compra (
    id_solicitud_compra INT AUTO_INCREMENT,
    f_confeccion DATE NOT NULL,
    id_proveedor INT NOT NULL,
    vendedor VARCHAR(50) NOT NULL,
    id_orden_trabajo INT,
    envia_proveedor BOOLEAN NOT NULL,
    direccion_retiro VARCHAR(100),
    f_entrega DATE NOT NULL,
    horario_entrega CHAR(1) NOT NULL,
    subtotal FLOAT(8 , 2 ) NOT NULL,
    porcentaje_iva FLOAT(8 , 2 ) NOT NULL,
    monto_iva FLOAT(8 , 2 ) NOT NULL,
    total FLOAT(8 , 2 ) NOT NULL,
    PRIMARY KEY (id_solicitud_compra)
);




-- 

CREATE TABLE Stock (
    id_stock INT AUTO_INCREMENT,
    id_orden_trabajo INT,
    id_solicitud_compra INT NOT NULL,
    cant_hojas_totales INT NOT NULL,
	cant_hojas_usadas INT	NOT NULL,
    marca VARCHAR(50) NOT NULL,
    id_calidad INT NOT NULL,
    id_formato INT NOT NULL,
    id_variante INT NOT NULL,
	gramaje		INT	NOT NULL,
	Remanente		  BOOLEAN	NOT NULL,
	activo 		BOOLEAN NOT NULL,
    
    PRIMARY KEY (id_stock , id_solicitud_compra)
);


CREATE TABLE Egreso_Stock (
    id_egreso_stock 	 INT AUTO_INCREMENT,
    id_stock 			 INT NOT NULL,
	id_materiales		 INT,
	empleado			 VARCHAR(50) NOT NULL,
	cant_hojas_retiradas INT	NOT NULL,
    fecha				 DATE	NOT NULL,

    PRIMARY KEY (id_egreso_stock)
);


alter table egreso_stock
add column empleado VARCHAR(50) NOT NULL;

alter table stock
change remanente remanente BOOLEAN NOT NULL;

drop table egreso_stock;
select * from stock;


SELECT * FROM elemento WHERE id_orden_trabajo=2;
SELECT * FROM materiales;
select * from egreso_stock;

--
-- 			ALTER TABLES
--



-- 
-- TABLE: materiales 
--

ALTER TABLE materiales ADD CONSTRAINT Refcalidad 
    FOREIGN KEY (id_calidad)
    REFERENCES calidad(id_calidad)
;

ALTER TABLE materiales ADD CONSTRAINT Refvariante 
    FOREIGN KEY (id_variante)
    REFERENCES variante(id_variante)
;

ALTER TABLE materiales ADD CONSTRAINT Refformato_papel 
    FOREIGN KEY (id_formato_papel)
    REFERENCES formato_papel(id_formato_papel)
;

ALTER TABLE materiales ADD CONSTRAINT Refelemento 
    FOREIGN KEY (id_elemento)
    REFERENCES elemento(id_elemento)
;

-- 
-- TABLE: orden_trabajo 
--

ALTER TABLE orden_trabajo ADD CONSTRAINT Refcliente
    FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente)
;




-- 
-- TABLE: procesos_x_orden_trabajo 
--

ALTER TABLE procesos_x_orden_trabajo ADD CONSTRAINT Refproceso 
    FOREIGN KEY (id_proceso)
    REFERENCES proceso(id_proceso)
;

ALTER TABLE procesos_x_orden_trabajo ADD CONSTRAINT Reforden_trabajo 
    FOREIGN KEY (id_orden_trabajo)
    REFERENCES orden_trabajo(id_orden_trabajo)
;

ALTER TABLE procesos_x_orden_trabajo ADD CONSTRAINT RefProveedor
   FOREIGN KEY (id_proveedor)
   REFERENCES proveedor(id_proveedor)
;


-- 
-- TABLE: elemento
--

ALTER TABLE elemento ADD CONSTRAINT Refordentrabajo 
    FOREIGN KEY (id_orden_trabajo)
    REFERENCES orden_trabajo(id_orden_trabajo)
;



-- 
-- TABLE: hojas_utilizadas
--


ALTER TABLE hojas_utilizadas ADD CONSTRAINT Refelemento1
    FOREIGN KEY (id_elemento)
    REFERENCES elemento(id_elemento)
;



-- 
-- TABLE: detalle 
--



ALTER TABLE detalle ADD CONSTRAINT Refsolicitud_compra
    FOREIGN KEY (id_solicitud_compra)
    REFERENCES solicitud_compra(id_solicitud_compra)
;


ALTER TABLE detalle ADD CONSTRAINT Refcalidad1 
    FOREIGN KEY (id_calidad)
    REFERENCES calidad(id_calidad)
;

ALTER TABLE detalle ADD CONSTRAINT Refvariante1 
    FOREIGN KEY (id_variante)
    REFERENCES variante(id_variante)
;

ALTER TABLE detalle ADD CONSTRAINT Refformato_papel1
    FOREIGN KEY (id_formato_papel)
    REFERENCES formato_papel(id_formato_papel)
;




-- 
-- TABLE: recepcion_pedido 
--



ALTER TABLE recepcion_pedido ADD CONSTRAINT Refsolicitud_compra1
    FOREIGN KEY (id_solicitud_compra)
    REFERENCES solicitud_compra(id_solicitud_compra)
;



-- 
-- TABLE: Solicitud_Compra 
--

ALTER TABLE solicitud_compra ADD CONSTRAINT Reforden_trabajo1 
    FOREIGN KEY (id_orden_trabajo)
    REFERENCES orden_trabajo(id_orden_trabajo)
;




-- 
-- TABLE: Stock 
--



ALTER TABLE Stock ADD CONSTRAINT Refsolicitud_compra_stock 
    FOREIGN KEY (id_solicitud_compra)
    REFERENCES solicitud_compra(id_solicitud_compra)
;

ALTER TABLE Stock ADD CONSTRAINT Reforden_trabajo_stock 
    FOREIGN KEY (id_orden_trabajo)
    REFERENCES orden_trabajo(id_orden_trabajo)
;




-- 
-- TABLE: Egreso_Stock 
--

ALTER TABLE Egreso_Stock ADD CONSTRAINT RefStock 
    FOREIGN KEY (id_stock)
    REFERENCES stock(id_stock)
;

ALTER TABLE Egreso_Stock ADD CONSTRAINT RefMateriales 
    FOREIGN KEY (id_materiales)
    REFERENCES materiales(id_materiales)
;






--         --
-- INSERTS -- 
--         --




-- Tabla Calidad

INSERT INTO calidad
VALUES(DEFAULT,"Ilustración");
INSERT INTO calidad
VALUES(DEFAULT,"Obra");
INSERT INTO calidad
VALUES(DEFAULT,"Autoadhesivo");
INSERT INTO calidad
VALUES(DEFAULT,"Autoadhesivo Obra");
INSERT INTO calidad
VALUES(DEFAULT,"Autoadhesivo PVC");
INSERT INTO calidad
VALUES(DEFAULT,"Autoadhesivo OPP");
INSERT INTO calidad
VALUES(DEFAULT,"Cartulina");
INSERT INTO calidad
VALUES(DEFAULT,"Encapada");
INSERT INTO calidad
VALUES(DEFAULT,"Cartón");
INSERT INTO calidad
VALUES(DEFAULT,"PVC");
INSERT INTO calidad
VALUES(DEFAULT,"OPP");
INSERT INTO calidad
VALUES(DEFAULT,"Especial");
INSERT INTO calidad
VALUES(DEFAULT,"Comercial Color");
INSERT INTO calidad
VALUES(DEFAULT,"Químico CB (1º)");
INSERT INTO calidad
VALUES(DEFAULT,"Químico CFB (2º)");
INSERT INTO calidad
VALUES(DEFAULT,"Químico CF (3º)");
INSERT INTO calidad
VALUES(DEFAULT,"Opalina");


-- Tabla Clientes

INSERT INTO cliente
VALUES(DEFAULT, "Lorenzo Publicidad","30680776531", "Responsable inscripto", "Juan Manuel de Rosas 775, Buenos Aires, Argentina",
"46991786","papeleraLorenzo@pl.com", "Lorenzo Arce","46991786","lorenzoarce@pl.com",
"Juan Manuel de Rosas 775, BUenos Aires, Argentina",TRUE); 

INSERT INTO cliente
VALUES(DEFAULT, "Supermercado La Plata","30128577761", "Responsable inscripto", "Otto Krause 4950, B1667GYJ Malvinas Argentinas, Buenos Aires, Argentina",
"46300100","comprasmalvarg@cmpc.com.ar", "Magali Zarate","49181535","rrhhempleos@cmpc.com.ar",
"Otto Krause 4950, B1667GYJ Malvinas Argentinas, Buenos Aires, Argentina",TRUE); 

INSERT INTO cliente
VALUES(DEFAULT, "Ledesma SRL","30501199255", "Responsable exento", "Av. Corrientes 415 8º piso - Capital Federal",
"43781570","rrhh@ledesmapapel.com", "Laura García","43781555","rrhh@ledesmapapel.com",
"Est. La Bellaca - Estación Islas, 25 de Mayo - Buenos Aires",TRUE); 

INSERT INTO cliente
VALUES(DEFAULT, "Rivadavia S.A","30193113142", "Responsable exento", "Av Rivadavia 17617",
"46276888","rivadaviasa@rivadavia.com.ar", "Jorge Guirao","41152850","jorgueguirao@rivadavia.com.ar",
"Av Rivadavia 17617",TRUE); 

INSERT INTO cliente
VALUES(DEFAULT, "Minimercado La curva","30643668175", "Responsable Monotributista", "Moreno (calle 48) 4302
San Martin - Buenos Aires - Argentina",
"47550932","info@papel-9dejulio.com.ar", "Sara DelQuiara"," 47523864","SaraDelQui@papel-9dejulio.com.ar",
"Moreno (calle 48) 4302 San Martin - Buenos Aires - Argentina",TRUE); 




-- Tabla formato_papel

INSERT INTO formato_papel
VALUES(DEFAULT,"45x64");
INSERT INTO formato_papel
VALUES(DEFAULT,"48x65");
INSERT INTO formato_papel
VALUES(DEFAULT,"50x65");
INSERT INTO formato_papel
VALUES(DEFAULT,"50x70");
INSERT INTO formato_papel
VALUES(DEFAULT,"58x92");
INSERT INTO formato_papel
VALUES(DEFAULT,"61x86");
INSERT INTO formato_papel
VALUES(DEFAULT,"63x88");
INSERT INTO formato_papel
VALUES(DEFAULT,"65x95");
INSERT INTO formato_papel
VALUES(DEFAULT,"66x96");
INSERT INTO formato_papel
VALUES(DEFAULT,"70x100");
INSERT INTO formato_papel
VALUES(DEFAULT,"72x102");
INSERT INTO formato_papel
VALUES(DEFAULT,"76x102");
INSERT INTO formato_papel
VALUES(DEFAULT,"74x110");
INSERT INTO formato_papel
VALUES(DEFAULT,"82x118");
INSERT INTO formato_papel
VALUES(DEFAULT,"89x117");



-- Tabla Variante

INSERT INTO variante
VALUES(DEFAULT, "Brillante");
INSERT INTO variante
VALUES(DEFAULT, "Semi Mate");
INSERT INTO variante
VALUES(DEFAULT, "Mate");
INSERT INTO variante
VALUES(DEFAULT, "Transparente");
INSERT INTO variante
VALUES(DEFAULT, "Blanco");
INSERT INTO variante
VALUES(DEFAULT, "Amarillo");
INSERT INTO variante
VALUES(DEFAULT, "Rosa");
INSERT INTO variante
VALUES(DEFAULT, "Verde");
INSERT INTO variante
VALUES(DEFAULT, "Celeste");
INSERT INTO variante
VALUES(DEFAULT, "Blanca/Blanca");
INSERT INTO variante
VALUES(DEFAULT, "Blanca/Kraft");




-- Tabla proceso

INSERT INTO proceso
VALUES(DEFAULT, "CTP");

INSERT INTO proceso
VALUES(DEFAULT, "Películas");

INSERT INTO proceso
VALUES(DEFAULT, "Copia de chapas");

INSERT INTO proceso
VALUES(DEFAULT, "Corte inicial");

INSERT INTO proceso
VALUES(DEFAULT, "Impresión");

INSERT INTO proceso
VALUES(DEFAULT, "Barniz");

INSERT INTO proceso
VALUES(DEFAULT, "Laminado");

INSERT INTO proceso
VALUES(DEFAULT, "Trazado");

INSERT INTO proceso
VALUES(DEFAULT, "Puntillado");

INSERT INTO proceso
VALUES(DEFAULT, "Medio corte");

INSERT INTO proceso
VALUES(DEFAULT, "Troquelado");

INSERT INTO proceso
VALUES(DEFAULT, "Doblado");

INSERT INTO proceso
VALUES(DEFAULT, "Intercalado");

INSERT INTO proceso
VALUES(DEFAULT, "Emblocado");

INSERT INTO proceso
VALUES(DEFAULT, "Agujereado");

INSERT INTO proceso
VALUES(DEFAULT, "Abrochado");

INSERT INTO proceso
VALUES(DEFAULT, "Encuadernación");

INSERT INTO proceso
VALUES(DEFAULT, "Confección de sobres");

INSERT INTO proceso
VALUES(DEFAULT, "Corte final");

INSERT INTO proceso
VALUES(DEFAULT, "Empaque");


-- Tabla Proveedor

INSERT INTO proveedor
VALUES(DEFAULT,"Distribuidora ALOS","33584426553","Responsable inscripto","Sarmiento 3035,CABA",
"48648436","distalos@distalos.com.ar", "Romina Gonzalez","48645103","rrhh@distalos.com.ar","Sarmiento 3035,CABA",TRUE);

INSERT INTO proveedor
VALUES(DEFAULT,"DISPA CENTER SA","31996532871","Responsable inscripto","Monteagudo 619 (Calle 95),San Martín,
Buenos Aires", "47521122","info@embalcenter.com", "Romina Gonzalez","47521122","rrhhRGonzalez@embalcenter.com","Monteagudo 619 (Calle 95),San Martín,
Buenos Aires",TRUE);

INSERT INTO proveedor
VALUES(DEFAULT,"GARBER SRL","31996532871","Responsable monotributista","Gurruchaga 469,
Buenos Aires", "48541621","info@garber.com.ar", "Matías Taborda","48545731","matiTaborda@hotmail.com","Gurruchaga 469,
Buenos Aires",TRUE);

INSERT INTO proveedor
VALUES(DEFAULT,"Todo Resmas","30958592096","Responsable exento","Av.Jujuy 1019,
CABA", "49424935","ventas@TodoResmas.com.ar", "Roberto Caridi","49424839","ventas@TodoResmas.com.ar","Av.Jujuy 1019,
CABA",TRUE);

INSERT INTO proveedor
VALUES(DEFAULT,"Pioneer Argentina Papelera","29935210001","Responsable exento","Av. Triunvirato 4231,
CABA", "45221238","ventas@pap.com.ar", "Juana Gaiani","45735932","mayorista@pap.com.ar","Av.Constituyentes 4581/85, CABA",TRUE);



-- para Stock
insert into orden_trabajo values(1,"Stockear", 1, "2012-01-01", "2012-01-01", "Stockear", "",0, 0,0.0,0.0,false,"Pendiente",null,null,null);

