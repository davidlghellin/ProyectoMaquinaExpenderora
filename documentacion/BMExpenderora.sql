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

CREATE TABLE TMovimientos(
    IdMovimiento    INT PRIMARY KEY AUTO_INCREMENT,
    Fecha           TIMESTAMP  	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    IdProducto      INT NOT NULL,
    Cantidad        INT NOT NULL,
    CONSTRAINT ActualizarMaquina FOREIGN KEY (IdProducto) REFERENCES TProducto(Codigo)
)CHARACTER SET utf8 COLLATE utf8_spanish_ci;

INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Billete 5",10,5);   #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 2",10,2);    #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 1",20,1);    #20 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.5",20,0.5);#10 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.2",50,0.2);#10 €
INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES ("Moneda 0.1",10,0.1);#1 €

# productos
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("CocaCola","Refrescos",1.2,10);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("CocaCola","Refrescos",1.2,10);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Coca Zero","Refrescos",1.2,20);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Coca Zero","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Fanta L.","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Fanta L.","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Fanta N.","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Fanta N.","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Nestea","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Nestea","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Sprite","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Sprite","Refrescos",1.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("RedBull","Bebida energética",1.5,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("RedBull","Bebida energética",1.5,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Monster","Bebida energética",2.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Monster","Bebida energética",2.2,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Agua","Agua refrescante",1.1,0);
INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias) VALUES ("Agua","Agua refrescante",1.1,0);


#INSERT INTO TMovimientos (IdProducto,Cantidad) VALUES (1,9);