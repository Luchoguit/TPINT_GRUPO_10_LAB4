CREATE DATABASE tp_integrador;
USE tp_integrador;


-- Creación de las tablas

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
	id_localidad INT NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_localidad) REFERENCES localidades(id)
);

CREATE TABLE usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES clientes(id),
	nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contraseña VARCHAR(25) NOT NULL,
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
    FOREIGN KEY (id_cuentaDestino) REFERENCES cuentas(id)
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
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta)
);

CREATE TABLE Cuotas (
    id_cuota INT PRIMARY KEY AUTO_INCREMENT,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo)
);

-- Inserción TiposDeMovimiento
INSERT INTO tipos_de_movimientos (id, descripcion) VALUES
(1, 'Alta de cuenta'),
(2, 'Alta de préstamo'),
(3, 'Pago de préstamo'),
(4, 'Transferencia');


-- Inserción tipos de cuentas
INSERT INTO tipos_de_cuentas (descripcion) VALUES
('Caja de ahorro'),
('Cuenta corriente');

-- Inserción de provincias
INSERT INTO provincias (nombre) VALUES
('Buenos Aires'),
('CABA'),
('Córdoba'),
('Santa Fe'),
('Mendoza'),
('Neuquén'),
('Salta'),
('Tucumán'),
('Chaco'),
('Santiago del Estero'),
('Corrientes'),
('Misiones'),
('Jujuy'),
('Río Negro'),
('Chubut');

-- Inserción de localidades

-- Buenos Aires
INSERT INTO localidades (nombre, provincia_id) VALUES
('La Plata', 1), ('Mar del Plata', 1), ('Bahía Blanca', 1), 
('Tigre', 1), ('Lomas de Zamora', 1), ('Quilmes', 1), 
('Avellaneda', 1), ('San Isidro', 1), ('Lanús', 1), 
('Morón', 1), ('Vicente López', 1), ('San Fernando', 1), 
('Tres de Febrero', 1), ('Berazategui', 1), ('Campana', 1), 
('Escobar', 1), ('Zárate', 1), ('General Pacheco', 1), 
('Pehuajó', 1), ('Necochea', 1);

-- CABA
INSERT INTO localidades (nombre, provincia_id) VALUES
('Retiro', 2), ('Palermo', 2), ('Recoleta', 2), 
('San Telmo', 2), ('Belgrano', 2), ('Barracas', 2), 
('Caballito', 2), ('Flores', 2), ('La Boca', 2), 
('Once', 2), ('Puerto Madero', 2), ('Almagro', 2), 
('Colegiales', 2), ('Villa Devoto', 2), ('Villa del Parque', 2), 
('Villa Urquiza', 2), ('Villa Luro', 2), ('Liniers', 2), 
('Boedo', 2);

-- Córdoba
INSERT INTO localidades (nombre, provincia_id) VALUES
('Córdoba Capital', 3), ('Villa María', 3), ('Río Cuarto', 3), 
('La Falda', 3), ('Bell Ville', 3), ('San Francisco', 3), 
('Carlos Paz', 3), ('Morteros', 3), ('Alta Gracia', 3), 
('Villa Allende', 3), ('Salsipuedes', 3), ('Cosquín', 3), 
('Tancacha', 3), ('La Calera', 3), ('James Craik', 3), 
('Las Varillas', 3), ('Cruz del Eje', 3), ('Despeñaderos', 3), 
('Arroyito', 3), ('Capilla del Monte', 3), ('Pico Truncado', 3);

-- Santa Fe
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rosario', 4), ('Santa Fe', 4), ('Venado Tuerto', 4), 
('Rafaela', 4), ('Reconquista', 4), ('Ceres', 4), 
('San Lorenzo', 4), ('Pueblo Esther', 4), ('El Trébol', 4), 
('Firmat', 4), ('Franco', 4), ('Las Parejas', 4), 
('Carcarañá', 4), ('Cañada de Gómez', 4), ('Esperanza', 4), 
('Santo Tomé', 4), ('San Justo', 4), ('Gálvez', 4), 
('Roldán', 4), ('San Vicente', 4);

-- Mendoza
INSERT INTO localidades (nombre, provincia_id) VALUES
('Mendoza Capital', 5), ('San Rafael', 5), ('Godoy Cruz', 5), 
('Guaymallén', 5), ('Luján de Cuyo', 5), ('Maipú', 5), 
('Las Heras', 5), ('Rivadavia', 5), ('General Alvear', 5), 
('Tunuyán', 5), ('Tupungato', 5), ('Palmira', 5), 
('Lavalle', 5), ('Las Compuertas', 5), ('San Martín', 5), 
('Junín', 5), ('Malargüe', 5), ('La Paz', 5), 
('Villa Nueva', 5), ('El Bolsón', 5), ('Calle Larga', 5);

-- Neuquén
INSERT INTO localidades (nombre, provincia_id) VALUES
('Neuquén', 6), ('San Martín de los Andes', 6), ('Plottier', 6), 
('Cutral Co', 6), ('Zapala', 6), ('Villa La Angostura', 6), 
('Chos Malal', 6), ('Senillosa', 6), ('Aluminé', 6), 
('Picún Leufú', 6), ('El Chocón', 6), ('Las Lajas', 6), 
('Junín de los Andes', 6), ('Andacollo', 6), ('Loncopué', 6), 
('San Patricio del Chañar', 6), ('Villa El Chocón', 6), 
('Neuquén', 6), ('Bajo de Picheuta', 6), ('Rincón de los Andes', 6), 
('Sierra de la Ventana', 6);

-- Salta
INSERT INTO localidades (nombre, provincia_id) VALUES
('Salta Capital', 7), ('Orán', 7), ('Tartagal', 7), 
('Metán', 7), ('Rosario de la Frontera', 7), ('Cafayate', 7), 
('El Carril', 7), ('San Lorenzo', 7), ('Guachipas', 7), 
('La Merced', 7), ('Seclantás', 7), ('Cachi', 7), 
('Salinas Grandes', 7), ('General Güemes', 7), ('Iruya', 7), 
('Molinos', 7), ('San Antonio de los Cobres', 7), 
('Campo Quijano', 7), ('Rivadavia', 7), ('Aguaray', 7);

-- Tucumán
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Miguel de Tucumán', 8), ('Tafí Viejo', 8), ('Concepción', 8), 
('Yerba Buena', 8), ('Simoca', 8), ('Monteros', 8), 
('Aguilares', 8), ('Famaillá', 8), ('Bella Vista', 8), 
('Las Talitas', 8), ('El Cadillal', 8), ('Lules', 8), 
('Chicligasta', 8), ('Villa 9 de Julio', 8), ('Alberdi', 8), 
('Santiago del Estero', 8), ('Santa Rosa de Lima', 8), 
('La Cocha', 8), ('El Manantial', 8), ('San José de Metán', 8), 
('Río Seco', 8);

-- Chaco
INSERT INTO localidades (nombre, provincia_id) VALUES
('Resistencia', 9), ('Corrientes', 9), ('Barranqueras', 9), 
('Fontana', 9), ('Villa Ángela', 9), ('Sáenz Peña', 9), 
('Las Breñas', 9), ('Charata', 9), ('General Pinedo', 9), 
('Makallé', 9), ('San Bernardo', 9), ('Puerto Vilelas', 9), 
('Colonia Benitez', 9), ('La Leonesa', 9), ('Pampa del Indio', 9), 
('Los Frentones', 9), ('Quitilipi', 9), ('Napenay', 9), 
('Hermoso Campo', 9), ('La Clotilde', 9), ('Castelli', 9);

-- Santiago del Estero
INSERT INTO localidades (nombre, provincia_id) VALUES
('Santiago del Estero', 10), ('La Banda', 10), ('Termas de Río Hondo', 10), 
('Frías', 10), ('Añatuya', 10), ('Quimilí', 10), 
('Colonia Dora', 10), ('Beltrán', 10), ('El Bobadal', 10), 
('Tiro Federal', 10), ('Agua Nueva', 10), ('Clodomira', 10), 
('Las Termas', 10), ('Selva', 10), ('Río Hondo', 10), 
('San Martín', 10), ('Pinto', 10), ('Cafayate', 10), 
('Santiago del Estero', 10), ('Hernández', 10), ('Estación Clodomira', 10);

-- Corrientes
INSERT INTO localidades (nombre, provincia_id) VALUES
('Corrientes', 11), ('Goya', 11), ('Esquina', 11), 
('Mercedes', 11), ('Monte Caseros', 11), ('Saladas', 11), 
('Bella Vista', 11), ('Colonia Libertad', 11), ('Colón', 11), 
('San Carlos', 11), ('San Luis del Palmar', 11), ('Ituzaingó', 11), 
('Concepción', 11), ('Loreto', 11), ('Paso de la Patria', 11), 
('Riachuelo', 11), ('Santa Rosa', 11), ('San Roque', 11), 
('Alvear', 11), ('San Miguel', 11), ('Empedrado', 11);

-- Misiones
INSERT INTO localidades (nombre, provincia_id) VALUES
('Posadas', 12), ('Oberá', 12), ('Eldorado', 12), 
('Puerto Iguazú', 12), ('Apóstoles', 12), ('San Vicente', 12), 
('Leandro N. Alem', 12), ('San Pedro', 12), ('Montecarlo', 12), 
('Garupá', 12), ('Capioví', 12), ('Itacaruaré', 12), 
('Candelaria', 12), ('Colonia Aurora', 12), ('Cruz de Santa Ana', 12), 
('Campo Grande', 12), ('Piray', 12), ('Salto Encantado', 12), 
('3 de Mayo', 12), ('Rafael Castillo', 12), ('Pueblo Illia', 12);

-- Jujuy
INSERT INTO localidades (nombre, provincia_id) VALUES
('San Salvador de Jujuy', 13), ('Perico', 13), ('Palpalá', 13), 
('La Quiaca', 13), ('Humahuaca', 13), ('Tilcara', 13), 
('El Carmen', 13), ('Libertador General San Martín', 13), 
('San Pedro', 13), ('Abra Pampa', 13), ('Cangrejillos', 13), 
('Susques', 13), ('San Antonio', 13), ('Yala', 13), 
('Valle Grande', 13), ('Monterrico', 13), ('Ledesma', 13), 
('Pampa Blanca', 13), ('Santa Clara', 13), ('Tumbaya', 13), 
('La Mendieta', 13);

-- Río Negro
INSERT INTO localidades (nombre, provincia_id) VALUES
('Viedma', 14), ('Carmen de Patagones', 14), ('General Roca', 14), 
('Cipolletti', 14), ('Catriel', 14), ('Allen', 14), 
('Villa Regina', 14), ('San Antonio Oeste', 14), ('Las Grutas', 14), 
('Ingeniero Jacobacci', 14), ('El Bolsón', 14), ('Bariloche', 14), 
('Cohen', 14), ('General Godoy', 14), ('Lamarque', 14), 
('Luis Beltrán', 14), ('Chichinales', 14), ('Valcheta', 14), 
('Rincón de los Sauces', 14), ('Mainqué', 14), ('Nueve de Julio', 14);

-- Chubut
INSERT INTO localidades (nombre, provincia_id) VALUES
('Rawson', 15), ('Trelew', 15), ('Comodoro Rivadavia', 15), 
('Puerto Madryn', 15), ('Esquel', 15), ('Dolavon', 15), 
('Gaiman', 15), ('Sarmiento', 15), ('Río Mayo', 15), 
('Tecka', 15), ('Camacho', 15), ('Las Plumas', 15), 
('El Hoyo', 15), ('Cushamen', 15), ('Laguna Frey', 15), 
('Epuyén', 15), ('El Maitén', 15), ('Paso de Indios', 15), 
('Dina Huapi', 15), ('Puerto Pirámides', 15), ('Carrenleufú', 15);