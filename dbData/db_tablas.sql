CREATE DATABASE tp_integrador;
USE tp_integrador;


-- CreaciÃ³n de las tablas

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
    FOREIGN KEY (id_provincia) REFERENCES provincias(id)
    
);

CREATE TABLE usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES clientes(id),
	nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contraseÃ±a VARCHAR(25) NOT NULL,
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
    FOREIGN KEY (id_tipoCuenta) REFERENCES tipos_de_cuentas(id)
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
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id)
);

CREATE TABLE Cuotas (
    id_cuota INT PRIMARY KEY AUTO_INCREMENT,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo)
);

-- InserciÃ³n TiposDeMovimiento
INSERT INTO tipos_de_movimientos (id, descripcion) VALUES
(1, 'Alta de cuenta'),
(2, 'Alta de prÃ©stamo'),
(3, 'Pago de prÃ©stamo'),
(4, 'Transferencia');


-- InserciÃ³n tipos de cuentas
INSERT INTO tipos_de_cuentas (descripcion) VALUES
('Caja de ahorro'),
('Cuenta corriente');

-- InserciÃ³n de provincias
INSERT INTO provincias (nombre) VALUES
('Buenos Aires'),
('CABA'),
('CÃ³rdoba'),
('Santa Fe'),
('Mendoza'),
('NeuquÃ©n'),
('Salta'),
('TucumÃ¡n'),
('Chaco'),
('Santiago del Estero'),
('Corrientes'),
('Misiones'),
('Jujuy'),
('RÃ­o Negro'),
('Chubut');

-- InserciÃ³n de localidades

-- Buenos Aires
INSERT INTO localidades (nombre, provincia_id) VALUES
('La Plata', 1), ('Mar del Plata', 1), ('BahÃ­a Blanca', 1), 
('Tigre', 1), ('Lomas de Zamora', 1), ('Quilmes', 1), 
('Avellaneda', 1), ('San Isidro', 1), ('LanÃºs', 1), 
('MorÃ³n', 1), ('Vicente LÃ³pez', 1), ('San Fernando', 1), 
('Tres de Febrero', 1), ('Berazategui', 1), ('Campana', 1), 
('Escobar', 1), ('ZÃ¡rate', 1), ('General Pacheco', 1), 
('PehuajÃ³', 1), ('Necochea', 1);

-- CABA
INSERT INTO localidades (nombre, provincia_id) VALUES
('Retiro', 2), ('Palermo', 2), ('Recoleta', 2), 
('San Telmo', 2), ('Belgrano', 2), ('Barracas', 2), 
('Caballito', 2), ('Flores', 2), ('La Boca', 2), 
('Once', 2), ('Puerto Madero', 2), ('Almagro', 2), 
('Colegiales', 2), ('Villa Devoto', 2), ('Villa del Parque', 2), 
('Villa Urquiza', 2), ('Villa Luro', 2), ('Liniers', 2), 
('Boedo', 2);

-- CÃ³rdoba
INSERT INTO localidades (nombre, provincia_id) VALUES
('CÃ³rdoba Capital', 3), ('Villa MarÃ­a', 3), ('RÃ­o Cuarto', 3), 
('La Falda', 3), ('Bell Ville', 3), ('San Francisco', 3), 
('Carlos Paz', 3), ('Morteros', 3), ('Alta Gracia', 3), 
('Villa Allende', 3), ('Salsipuedes', 3), ('CosquÃ­n', 3), 
('Tancacha', 3), ('La Calera', 3), ('James Craik', 3), 
('Las Varillas', 3), ('Cruz del Eje', 3), ('DespeÃ±aderos', 3), 
('Arroyito', 3), ('Capilla del Monte', 3), ('Pico Truncado', 3);

-- Santa Fe
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rosario', 4), ('Santa Fe', 4), ('Venado Tuerto', 4), 
('Rafaela', 4), ('Reconquista', 4), ('Ceres', 4), 
('San Lorenzo', 4), ('Pueblo Esther', 4), ('El TrÃ©bol', 4), 
('Firmat', 4), ('Franco', 4), ('Las Parejas', 4), 
('CarcaraÃ±Ã¡', 4), ('CaÃ±ada de GÃ³mez', 4), ('Esperanza', 4), 
('Santo TomÃ©', 4), ('San Justo', 4), ('GÃ¡lvez', 4), 
('RoldÃ¡n', 4), ('San Vicente', 4);

-- Mendoza
INSERT INTO localidades (nombre, provincia_id) VALUES
('Mendoza Capital', 5), ('San Rafael', 5), ('Godoy Cruz', 5), 
('GuaymallÃ©n', 5), ('LujÃ¡n de Cuyo', 5), ('MaipÃº', 5), 
('Las Heras', 5), ('Rivadavia', 5), ('General Alvear', 5), 
('TunuyÃ¡n', 5), ('Tupungato', 5), ('Palmira', 5), 
('Lavalle', 5), ('Las Compuertas', 5), ('San MartÃ­n', 5), 
('JunÃ­n', 5), ('MalargÃ¼e', 5), ('La Paz', 5), 
('Villa Nueva', 5), ('El BolsÃ³n', 5), ('Calle Larga', 5);

-- NeuquÃ©n
INSERT INTO localidades (nombre, provincia_id) VALUES
('NeuquÃ©n', 6), ('San MartÃ­n de los Andes', 6), ('Plottier', 6), 
('Cutral Co', 6), ('Zapala', 6), ('Villa La Angostura', 6), 
('Chos Malal', 6), ('Senillosa', 6), ('AluminÃ©', 6), 
('PicÃºn LeufÃº', 6), ('El ChocÃ³n', 6), ('Las Lajas', 6), 
('JunÃ­n de los Andes', 6), ('Andacollo', 6), ('LoncopuÃ©', 6), 
('San Patricio del ChaÃ±ar', 6), ('Villa El ChocÃ³n', 6), 
('NeuquÃ©n', 6), ('Bajo de Picheuta', 6), ('RincÃ³n de los Andes', 6), 
('Sierra de la Ventana', 6);

-- Salta
INSERT INTO localidades (nombre, provincia_id) VALUES
('Salta Capital', 7), ('OrÃ¡n', 7), ('Tartagal', 7), 
('MetÃ¡n', 7), ('Rosario de la Frontera', 7), ('Cafayate', 7), 
('El Carril', 7), ('San Lorenzo', 7), ('Guachipas', 7), 
('La Merced', 7), ('SeclantÃ¡s', 7), ('Cachi', 7), 
('Salinas Grandes', 7), ('General GÃ¼emes', 7), ('Iruya', 7), 
('Molinos', 7), ('San Antonio de los Cobres', 7), 
('Campo Quijano', 7), ('Rivadavia', 7), ('Aguaray', 7);

-- TucumÃ¡n
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Miguel de TucumÃ¡n', 8), ('TafÃ­ Viejo', 8), ('ConcepciÃ³n', 8), 
('Yerba Buena', 8), ('Simoca', 8), ('Monteros', 8), 
('Aguilares', 8), ('FamaillÃ¡', 8), ('Bella Vista', 8), 
('Las Talitas', 8), ('El Cadillal', 8), ('Lules', 8), 
('Chicligasta', 8), ('Villa 9 de Julio', 8), ('Alberdi', 8), 
('Santiago del Estero', 8), ('Santa Rosa de Lima', 8), 
('La Cocha', 8), ('El Manantial', 8), ('San JosÃ© de MetÃ¡n', 8), 
('RÃ­o Seco', 8);

-- Chaco
INSERT INTO localidades (nombre, provincia_id) VALUES
('Resistencia', 9), ('Corrientes', 9), ('Barranqueras', 9), 
('Fontana', 9), ('Villa Ã ngela', 9), ('SÃ¡enz PeÃ±a', 9), 
('Las BreÃ±as', 9), ('Charata', 9), ('General Pinedo', 9), 
('MakallÃ©', 9), ('San Bernardo', 9), ('Puerto Vilelas', 9), 
('Colonia Benitez', 9), ('La Leonesa', 9), ('Pampa del Indio', 9), 
('Los Frentones', 9), ('Quitilipi', 9), ('Napenay', 9), 
('Hermoso Campo', 9), ('La Clotilde', 9), ('Castelli', 9);

-- Santiago del Estero
INSERT INTO localidades (nombre, provincia_id) VALUES
('Santiago del Estero', 10), ('La Banda', 10), ('Termas de RÃ­o Hondo', 10), 
('FrÃ­as', 10), ('AÃ±atuya', 10), ('QuimilÃ­', 10), 
('Colonia Dora', 10), ('BeltrÃ¡n', 10), ('El Bobadal', 10), 
('Tiro Federal', 10), ('Agua Nueva', 10), ('Clodomira', 10), 
('Las Termas', 10), ('Selva', 10), ('RÃ­o Hondo', 10), 
('San MartÃ­n', 10), ('Pinto', 10), ('Cafayate', 10), 
('Santiago del Estero', 10), ('HernÃ¡ndez', 10), ('EstaciÃ³n Clodomira', 10);

-- Corrientes
INSERT INTO localidades (nombre, provincia_id) VALUES
('Corrientes', 11), ('Goya', 11), ('Esquina', 11), 
('Mercedes', 11), ('Monte Caseros', 11), ('Saladas', 11), 
('Bella Vista', 11), ('Colonia Libertad', 11), ('ColÃ³n', 11), 
('San Carlos', 11), ('San Luis del Palmar', 11), ('ItuzaingÃ³', 11), 
('ConcepciÃ³n', 11), ('Loreto', 11), ('Paso de la Patria', 11), 
('Riachuelo', 11), ('Santa Rosa', 11), ('San Roque', 11), 
('Alvear', 11), ('San Miguel', 11), ('Empedrado', 11);

-- Misiones
INSERT INTO localidades (nombre, provincia_id) VALUES
('Posadas', 12), ('OberÃ¡', 12), ('Eldorado', 12), 
('Puerto IguazÃº', 12), ('ApÃ³stoles', 12), ('San Vicente', 12), 
('Leandro N. Alem', 12), ('San Pedro', 12), ('Montecarlo', 12), 
('GarupÃ¡', 12), ('CapiovÃ­', 12), ('ItacaruarÃ©', 12), 
('Candelaria', 12), ('Colonia Aurora', 12), ('Cruz de Santa Ana', 12), 
('Campo Grande', 12), ('Piray', 12), ('Salto Encantado', 12), 
('3 de Mayo', 12), ('Rafael Castillo', 12), ('Pueblo Illia', 12);

-- Jujuy
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Salvador de Jujuy', 13), ('Perico', 13), ('PalpalÃ¡', 13), 
('La Quiaca', 13), ('Humahuaca', 13), ('Tilcara', 13), 
('El Carmen', 13), ('Libertador General San MartÃ­n', 13), 
('San Pedro', 13), ('Abra Pampa', 13), ('Cangrejillos', 13), 
('Susques', 13), ('San Antonio', 13), ('Yala', 13), 
('Valle Grande', 13), ('Monterrico', 13), ('Ledesma', 13), 
('Pampa Blanca', 13), ('Santa Clara', 13), ('Tumbaya', 13), 
('La Mendieta', 13);

-- RÃ­o Negro
INSERT INTO localidades (nombre, provincia_id) VALUES
('Viedma', 14), ('Carmen de Patagones', 14), ('General Roca', 14), 
('Cipolletti', 14), ('Catriel', 14), ('Allen', 14), 
('Villa Regina', 14), ('San Antonio Oeste', 14), ('Las Grutas', 14), 
('Ingeniero Jacobacci', 14), ('El BolsÃ³n', 14), ('Bariloche', 14), 
('Cohen', 14), ('General Godoy', 14), ('Lamarque', 14), 
('Luis BeltrÃ¡n', 14), ('Chichinales', 14), ('Valcheta', 14), 
('RincÃ³n de los Sauces', 14), ('MainquÃ©', 14), ('Nueve de Julio', 14);

-- Chubut
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rawson', 15), ('Trelew', 15), ('Comodoro Rivadavia', 15), 
('Puerto Madryn', 15), ('Esquel', 15), ('Dolavon', 15), 
('Gaiman', 15), ('Sarmiento', 15), ('RÃ­o Mayo', 15), 
('Tecka', 15), ('Camacho', 15), ('Las Plumas', 15), 
('El Hoyo', 15), ('Cushamen', 15), ('Laguna Frey', 15), 
('EpuyÃ©n', 15), ('El MaitÃ©n', 15), ('Paso de Indios', 15), 
('Dina Huapi', 15), ('Puerto PirÃ¡mides', 15), ('CarrenleufÃº', 15);