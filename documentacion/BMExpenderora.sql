DROP DATABASE BMExpendedora;
CREATE DATABASE BMExpendedora;
USE BMExpendedora;

CREATE TABLE TDinero(
	Codigo      INT	 PRIMARY KEY AUTO_INCREMENT,
	Nombre      VARCHAR(20) NOT NULL,
	Existencias INT NOT NULL,
        Valor       FLOAT NOT NULL
)CHARACTER SET utf8 COLLATE utf8_spanish_ci;

CREATE TABLE TProducto
(
    Codigo      INT PRIMARY KEY AUTO_INCREMENT,
    Nombre      VARCHAR(20) NOT NULL,
    Descripcion VARCHAR(100),
    Precio      FLOAT NOT NULL,
    Existencias INT NOT NULL,
    Imagen      MEDIUMBLOB 
)CHARACTER SET utf8 COLLATE utf8_spanish_ci;

INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Billete 5",10,2);   #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 2",10,2);    #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 1",20,1);    #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.5",20,0.5);#10 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.2",50,0.2);#10 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.1",10,0.1);#1 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.05",20,0.05);#1 €