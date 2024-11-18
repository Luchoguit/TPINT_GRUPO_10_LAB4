CREATE DATABASE tp_integrador;
USE tp_integrador;


-- Creacion de las tablas

CREATE TABLE provincias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL
);

CREATE TABLE localidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    provincia_id INT,
    FOREIGN KEY (provincia_id) REFERENCES provincias(id)
);

CREATE TABLE clientes(
	id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(11) UNIQUE NOT NULL,
    cuil VARCHAR(11) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo ENUM('M','F') NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(50) NOT NULL,
	id_localidad INT NOT NULL,
    id_provincia INT NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_localidad) REFERENCES localidades(id),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id),
	estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES clientes(id),
	nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(25) NOT NULL,
    tipo ENUM('admin', 'cliente') NOT NULL DEFAULT 'cliente',
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE tipos_de_cuentas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(25) NOT NULL
);

CREATE TABLE cuentas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cbu VARCHAR(22) UNIQUE NOT NULL,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 10000,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    id_tipoCuenta INT NOT NULL,
    FOREIGN KEY (id_tipoCuenta) REFERENCES tipos_de_cuentas(id),
	estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Solicitudes_alta_cuenta(
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    id_tipoCuenta INT NOT NULL,
    FOREIGN KEY (id_tipoCuenta) REFERENCES tipos_de_cuentas(id),
	respondida BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tipos_de_movimientos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(25) NOT NULL
);


CREATE TABLE movimientos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT NOT NULL,
	FOREIGN KEY (id_cuenta) REFERENCES cuentas(id),
    id_tipoMovimiento INT NOT NULL,
    FOREIGN KEY (id_tipoMovimiento) REFERENCES tipos_de_movimientos(id),
    detalle VARCHAR(100) NOT NULL,
    fechaHora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    importe DECIMAL(15,2) NOT NULL,
    id_cuentaDestino INT NULL,
    FOREIGN KEY (id_cuentaDestino) REFERENCES cuentas(id),
    Saldo_disponible DECIMAL(15,2) NOT NULL
);

CREATE TABLE Prestamos (
    id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_cuenta INT NOT NULL,
    fecha_alta DATE NOT NULL,
    importe_pedido DECIMAL(15, 2) NOT NULL,
    plazo_meses INT NOT NULL,
    importe_mensual DECIMAL(15, 2) NOT NULL,
    cantidad_cuotas INT NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id)
);

CREATE TABLE Solicitudes_Prestamos(
id_solicitud INT PRIMARY KEY AUTO_INCREMENT,
id_prestamo INT NOT NULL,
FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo),
aprobado BOOLEAN NOT NULL DEFAULT FALSE,
estado BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE Cuotas (
    id_cuota INT PRIMARY KEY AUTO_INCREMENT,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo)
);

DELIMITER //

CREATE TRIGGER GenerarCbuYNumeroCuenta
BEFORE INSERT ON cuentas
FOR EACH ROW
BEGIN
    DECLARE nuevoCbu VARCHAR(22);
    DECLARE duplicado BOOLEAN;
    DECLARE ultimoNumeroCuenta BIGINT;

    SET NEW.cbu = NULL;

    -- Generar CBU único
    REPEAT
        SET nuevoCbu = LPAD(FLOOR(RAND() * 10000000000000000000000), 22, '0');
        SELECT EXISTS(SELECT 1 FROM cuentas WHERE cbu = nuevoCbu) INTO duplicado;
    UNTIL duplicado = FALSE
    END REPEAT;

    SET NEW.cbu = nuevoCbu;

    -- Generar el número de cuenta consecutivo
    SELECT IFNULL(MAX(numero_cuenta), 999999999) + 1 INTO ultimoNumeroCuenta
    FROM cuentas;

    SET NEW.numero_cuenta = ultimoNumeroCuenta;
END //


DELIMITER ;

DELIMITER //

CREATE TRIGGER insertSolicitudPrestamo
AFTER INSERT ON Prestamos
FOR EACH ROW
BEGIN
    INSERT INTO Solicitudes_Prestamos (id_prestamo)
    VALUES (NEW.id_prestamo);
END;
//

DELIMITER ;


DELIMITER $$

CREATE TRIGGER after_transferencia
AFTER INSERT ON movimientos
FOR EACH ROW
BEGIN
    
    IF NEW.id_cuentaDestino IS NOT NULL THEN
      
        IF (SELECT saldo FROM cuentas WHERE id = NEW.id_cuenta) < NEW.importe THEN
           
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Saldo insuficiente en la cuenta de origen';
        ELSE
            
            UPDATE cuentas
            SET saldo = saldo - NEW.importe
            WHERE id = NEW.id_cuenta;

           
            UPDATE cuentas
            SET saldo = saldo + NEW.importe
            WHERE id = NEW.id_cuentaDestino;
        END IF;
    END IF;
END $$

DELIMITER ;



-- Insercion TiposDeMovimiento
INSERT INTO tipos_de_movimientos (id, descripcion) VALUES
(1, 'Alta de cuenta'),
(2, 'Alta de prestamo'),
(3, 'Pago de prestamo'),
(4, 'Transferencia');


-- Insercion tipos de cuentas
INSERT INTO tipos_de_cuentas (descripcion) VALUES
('Caja de ahorro'),
('Cuenta corriente');

-- Insercion de provincias
INSERT INTO provincias (nombre) VALUES
('Buenos Aires'),
('CABA'),
('Cordoba'),
('Santa Fe'),
('Mendoza'),
('Neuquen'),
('Salta'),
('Tucuman'),
('Chaco'),
('Santiago del Estero'),
('Corrientes'),
('Misiones'),
('Jujuy'),
('Rio Negro'),
('Chubut');

-- Insercion de localidades

-- Buenos Aires
INSERT INTO localidades (nombre, provincia_id) VALUES
('La Plata', 1), ('Mar del Plata', 1), ('Bahia Blanca', 1),
('Tigre', 1), ('Lomas de Zamora', 1), ('Quilmes', 1),
('Avellaneda', 1), ('San Isidro', 1), ('Lanus', 1),
('Moron', 1), ('Vicente Lopez', 1), ('San Fernando', 1),
('Tres de Febrero', 1), ('Berazategui', 1), ('Campana', 1),
('Escobar', 1), ('Zarate', 1), ('General Pacheco', 1),
('Pehuajo', 1), ('Necochea', 1);

-- CABA
INSERT INTO localidades (nombre, provincia_id) VALUES
('Retiro', 2), ('Palermo', 2), ('Recoleta', 2),
('San Telmo', 2), ('Belgrano', 2), ('Barracas', 2),
('Caballito', 2), ('Flores', 2), ('La Boca', 2),
('Once', 2), ('Puerto Madero', 2), ('Almagro', 2),
('Colegiales', 2), ('Villa Devoto', 2), ('Villa del Parque', 2),
('Villa Urquiza', 2), ('Villa Luro', 2), ('Liniers', 2),
('Boedo', 2);

-- Cordoba
INSERT INTO localidades (nombre, provincia_id) VALUES
('Cordoba Capital', 3), ('Villa Maria', 3), ('Rio Cuarto', 3),
('La Falda', 3), ('Bell Ville', 3), ('San Francisco', 3),
('Carlos Paz', 3), ('Morteros', 3), ('Alta Gracia', 3),
('Villa Allende', 3), ('Salsipuedes', 3), ('Cosquin', 3),
('Tancacha', 3), ('La Calera', 3), ('James Craik', 3),
('Las Varillas', 3), ('Cruz del Eje', 3), ('Despenaderos', 3),
('Arroyito', 3), ('Capilla del Monte', 3), ('Pico Truncado', 3);

-- Santa Fe
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rosario', 4), ('Santa Fe', 4), ('Venado Tuerto', 4),
('Rafaela', 4), ('Reconquista', 4), ('Ceres', 4),
('San Lorenzo', 4), ('Pueblo Esther', 4), ('El Trebol', 4),
('Firmat', 4), ('Franco', 4), ('Las Parejas', 4),
('Carcarana', 4), ('Canada de Gomez', 4), ('Esperanza', 4),
('Santo Tome', 4), ('San Justo', 4), ('Galvez', 4),
('Roldan', 4), ('San Vicente', 4);

-- Mendoza
INSERT INTO localidades (nombre, provincia_id) VALUES
('Mendoza Capital', 5), ('San Rafael', 5), ('Godoy Cruz', 5),
('Guaymallen', 5), ('Lujan de Cuyo', 5), ('Maipu', 5),
('Las Heras', 5), ('Rivadavia', 5), ('General Alvear', 5),
('Tunuyan', 5), ('Tupungato', 5), ('Palmira', 5),
('Lavalle', 5), ('Las Compuertas', 5), ('San Martin', 5),
('Junin', 5), ('Malargue', 5), ('La Paz', 5),
('Villa Nueva', 5), ('El Bolson', 5), ('Calle Larga', 5);

-- Neuquen
INSERT INTO localidades (nombre, provincia_id) VALUES
('Neuquen', 6), ('San Martin de los Andes', 6), ('Plottier', 6),
('Cutral Co', 6), ('Zapala', 6), ('Villa La Angostura', 6),
('Chos Malal', 6), ('Senillosa', 6), ('Alumine', 6),
('Picun Leufu', 6), ('El Chocon', 6), ('Las Lajas', 6),
('Junin de los Andes', 6), ('Andacollo', 6), ('Loncopue', 6),
('San Patricio del Chanar', 6), ('Villa El Chocon', 6),
('Neuquen', 6), ('Bajo de Picheuta', 6), ('Rincon de los Andes', 6),
('Sierra de la Ventana', 6);

-- Salta
INSERT INTO localidades (nombre, provincia_id) VALUES
('Salta Capital', 7), ('Oran', 7), ('Tartagal', 7),
('Metan', 7), ('Rosario de la Frontera', 7), ('Cafayate', 7),
('El Carril', 7), ('San Lorenzo', 7), ('Guachipas', 7),
('La Merced', 7), ('Seclantas', 7), ('Cachi', 7),
('Salinas Grandes', 7), ('General Gumes', 7), ('Iruya', 7),
('Molinos', 7), ('San Antonio de los Cobres', 7),
('Campo Quijano', 7), ('Rivadavia', 7), ('Aguaray', 7);

-- Tucuman
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Miguel de Tucuman', 8), ('Tafi Viejo', 8), ('Concepcion', 8),
('Yerba Buena', 8), ('Simoca', 8), ('Monteros', 8),
('Aguilares', 8), ('Famailla', 8), ('Bella Vista', 8),
('Las Talitas', 8), ('El Cadillal', 8), ('Lules', 8),
('Chicligasta', 8), ('Villa 9 de Julio', 8), ('Alberdi', 8),
('Santiago del Estero', 8), ('Santa Rosa de Lima', 8),
('La Cocha', 8), ('El Manantial', 8), ('San Jose de Metan', 8),
('Rio Seco', 8);

-- Chaco
INSERT INTO localidades (nombre, provincia_id) VALUES
('Resistencia', 9), ('Corrientes', 9), ('Barranqueras', 9),
('Fontana', 9), ('Villa Angela', 9), ('Saenz Pena', 9),
('Las Brenas', 9), ('Charata', 9), ('General Pinedo', 9),
('Makalle', 9), ('San Bernardo', 9), ('Puerto Vilelas', 9),
('Colonia Benitez', 9), ('La Leonesa', 9), ('Pampa del Indio', 9),
('Los Frentones', 9), ('Quitilipi', 9), ('Napenay', 9),
('Hermoso Campo', 9), ('La Clotilde', 9), ('Castelli', 9);

-- Santiago del Estero
INSERT INTO localidades (nombre, provincia_id) VALUES
('Santiago del Estero', 10), ('La Banda', 10), ('Termas de Rio Hondo', 10),
('Frias', 10), ('Anatuya', 10), ('Quimili', 10),
('Colonia Dora', 10), ('Beltran', 10), ('El Bobadal', 10),
('Tiro Federal', 10), ('Agua Nueva', 10), ('Clodomira', 10),
('Las Termas', 10), ('Selva', 10), ('Rio Hondo', 10),
('San Martin', 10), ('Pinto', 10), ('Cafayate', 10),
('Santiago del Estero', 10), ('Hernandez', 10), ('Estacion Clodomira', 10);

-- Corrientes
INSERT INTO localidades (nombre, provincia_id) VALUES
('Corrientes', 11), ('Goya', 11), ('Esquina', 11),
('Mercedes', 11), ('Monte Caseros', 11), ('Saladas', 11),
('Bella Vista', 11), ('Colonia Libertad', 11), ('Colon', 11),
('San Carlos', 11), ('San Luis del Palmar', 11), ('Ituzaingo', 11),
('Concepcion', 11), ('Loreto', 11), ('Paso de la Patria', 11),
('Riachuelo', 11), ('Santa Rosa', 11), ('San Roque', 11),
('Alvear', 11), ('San Miguel', 11), ('Empedrado', 11);

-- Misiones
INSERT INTO localidades (nombre, provincia_id) VALUES
('Posadas', 12), ('Obera', 12), ('Eldorado', 12),
('Puerto Iguazu', 12), ('Apostoles', 12), ('San Vicente', 12),
('Leandro N. Alem', 12), ('San Pedro', 12), ('Montecarlo', 12),
('Garupa', 12), ('Capiovi', 12), ('Itacaruae', 12),
('Candelaria', 12), ('Colonia Aurora', 12), ('Cruz de Santa Ana', 12),
('Campo Grande', 12), ('Piray', 12), ('Salto Encantado', 12),
('3 de Mayo', 12), ('Rafael Castillo', 12), ('Pueblo Illia', 12);

-- Jujuy
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Salvador de Jujuy', 13), ('Perico', 13), ('Palpala', 13),
('La Quiaca', 13), ('Humahuaca', 13), ('Tilcara', 13),
('El Carmen', 13), ('Libertador General San Martin', 13),
('San Pedro', 13), ('Abra Pampa', 13), ('Cangrejillos', 13),
('Susques', 13), ('San Antonio', 13), ('Yala', 13),
('Valle Grande', 13), ('Monterrico', 13), ('Ledesma', 13),
('Pampa Blanca', 13), ('Santa Clara', 13), ('Tumbaya', 13),
('La Mendieta', 13);

-- Rio Negro
INSERT INTO localidades (nombre, provincia_id) VALUES
('Viedma', 14), ('Carmen de Patagones', 14), ('General Roca', 14),
('Cipolletti', 14), ('Catriel', 14), ('Allen', 14),
('Villa Regina', 14), ('San Antonio Oeste', 14), ('Las Grutas', 14),
('Ingeniero Jacobacci', 14), ('El Bolson', 14), ('Bariloche', 14),
('Cohen', 14), ('General Godoy', 14), ('Lamarque', 14),
('Luis Beltran', 14), ('Chichinales', 14), ('Valcheta', 14),
('Rincon de los Sauces', 14), ('Mainque', 14), ('Nueve de Julio', 14);

-- Chubut
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rawson', 15), ('Trelew', 15), ('Comodoro Rivadavia', 15),
('Puerto Madryn', 15), ('Esquel', 15), ('Dolavon', 15),
('Gaiman', 15), ('Sarmiento', 15), ('Rio Mayo', 15),
('Tecka', 15), ('Camacho', 15), ('Las Plumas', 15),
('El Hoyo', 15), ('Cushamen', 15), ('Laguna Frey', 15),
('Epuyen', 15), ('El Maiten', 15), ('Paso de Indios', 15),
('Dina Huapi', 15), ('Puerto Piramides', 15), ('Carrenleufu', 15);