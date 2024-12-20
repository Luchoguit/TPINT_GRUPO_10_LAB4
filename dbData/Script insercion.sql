USE tp_integrador;

-- Insercion de clientes
INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono) VALUES
('12345678', '20123456782', 'Lionel', 'Messi', 'M', 'Argentina', '1987-06-24', 'Calle 10', 18, 1, 'lmessi@gmail.com', '1123456789'),
('23456789', '20234567894', 'Diego', 'Maradona', 'M', 'Argentina', '1960-10-30', 'Calle 20', 18, 1, 'dmaradona@gmail.com', '1223456789'),
('34567890', '20345678901', 'Eva', 'Peron', 'F', 'Argentina', '1919-05-07', 'Calle 30', 18, 1, 'eperon@gmail.com', '1323456789'),
('45678901', '20456789012', 'Carlos', 'Tevez', 'M', 'Argentina', '1984-02-05', 'Calle 40', 18, 1, 'ctevez@gmail.com', '1423456789'),
('56789012', '20567890123', 'Manu', 'Ginobili', 'M', 'Argentina', '1977-07-28', 'Calle 50', 18, 1, 'mginobili@gmail.com', '1523456789'),
('67890123', '20678901234', 'Gustavo', 'Cerati', 'M', 'Argentina', '1959-08-11', 'Calle 60', 18, 1, 'gcerati@gmail.com', '1623456789'),
('78901234', '20789012345', 'Mercedes', 'Sosa', 'F', 'Argentina', '1935-07-09', 'Calle 70', 18, 1, 'msosa@gmail.com', '1723456789'),
('89012345', '20890123456', 'Jorge Luis', 'Borges', 'M', 'Argentina', '1899-08-24', 'Calle 80', 18, 1, 'jlborges@gmail.com', '1823456789'),
('90123456', '20901234567', 'Julio', 'Cortazar', 'M', 'Argentina', '1914-08-26', 'Calle 90', 18, 1, 'jcortazar@gmail.com', '1923456789'),
('01234567', '20012345678', 'Luisa', 'Valenzuela', 'F', 'Argentina', '1938-11-26', 'Calle 100', 18, 1, 'lvalenzuela@gmail.com', '2023456789'),
('11112222', '20111222334', 'Alfonsina', 'Storni', 'F', 'Argentina', '1892-05-29', 'Calle 110', 18, 1, 'astorni@gmail.com', '2123456789'),
('22223333', '20222333445', 'Rene', 'Favaloro', 'M', 'Argentina', '1923-07-12', 'Calle 120', 18, 1, 'rfavaloro@gmail.com', '2223456789'),
('33334444', '20333444556', 'Adalberto', 'Krieger', 'M', 'Argentina', '1941-02-14', 'Calle 130', 18, 1, 'akrieger@gmail.com', '2323456789'),
('44445555', '20444555667', 'Juan Manuel', 'Fangio', 'M', 'Argentina', '1911-06-24', 'Calle 140', 18, 1, 'jfangio@gmail.com', '2423456789'),
('55556666', '20555666778', 'Maria Elena', 'Walsh', 'F', 'Argentina', '1930-02-01', 'Calle 150', 18, 1, 'mwalsh@gmail.com', '2523456789'),
('66667777', '20666777889', 'Leonardo', 'Sbaraglia', 'M', 'Argentina', '1970-06-30', 'Calle 160', 18, 1, 'lsbaraglia@gmail.com', '2623456789'),
('77778888', '20777888990', 'Ricardo', 'Darin', 'M', 'Argentina', '1957-01-16', 'Calle 170', 18, 1, 'rdarin@gmail.com', '2723456789'),
('88889999', '20888999001', 'Luisa', 'Kuliok', 'F', 'Argentina', '1954-03-20', 'Calle 180', 18, 1, 'lkuliok@gmail.com', '2823456789'),
('99990000', '20999000112', 'Susana', 'Gimenez', 'F', 'Argentina', '1944-01-29', 'Calle 190', 18, 1, 'sgimenez@gmail.com', '2923456789'),
('00001111', '20000111223', 'Mirtha', 'Legrand', 'F', 'Argentina', '1927-02-23', 'Calle 200', 18, 1, 'mlegrand@gmail.com', '3023456789'),
('11113333', '20111333445', 'Maria', 'Farias Gomez', 'F', 'Argentina', '1953-07-08', 'Calle 210', 18, 1, 'mfarias@gmail.com', '3123456789'),
('22224444', '20222444556', 'Andres', 'Calamaro', 'M', 'Argentina', '1961-08-22', 'Calle 220', 18, 1, 'acalamaro@gmail.com', '3223456789'),
('33335555', '20333555667', 'Charly', 'Garcia', 'M', 'Argentina', '1951-10-23', 'Calle 230', 18, 1, 'cgarcia@gmail.com', '3323456789'),
('44446666', '20444666778', 'Luis', 'Spinetta', 'M', 'Argentina', '1950-01-23', 'Calle 240', 18, 1, 'lspinetta@gmail.com', '3423456789'),
('55557777', '20555777889', 'Sandrita', 'Martinez', 'F', 'Argentina', '1985-12-14', 'Calle 250', 18, 1, 'smartinez@gmail.com', '3523456789');


-- Insercion de usuarios (clientes)
INSERT INTO usuarios (id, nombre_usuario, contrasenia, tipo) VALUES
(1, 'lionel.messi', 'Messi1234', 'cliente'),
(2, 'diego.maradona', 'Maradona2345', 'cliente'),
(3, 'eva.peron', 'Peron3456', 'cliente'),
(4, 'carlos.tevez', 'Tevez4567', 'cliente'),
(5, 'manuel.ginobili', 'Ginobili5678', 'cliente'),
(6, 'gustavo.cerati', 'Cerati6789', 'cliente'),
(7, 'mercedes.sosa', 'Sosa7890', 'cliente'),
(8, 'jorgeluis.borges', 'Borges8901', 'cliente'),
(9, 'julio.cortazar', 'Cortazar9012', 'cliente'),
(10, 'luisa.valenzuela', 'Valenzuela0123', 'cliente'),
(11, 'alfonsina.storni', 'Storni1111', 'cliente'),
(12, 'rene.favaloro', 'Favaloro2222', 'cliente'),
(13, 'adalberto.krieger', 'Krieger3333', 'cliente'),
(14, 'juan.manuel.fangio', 'Fangio4444', 'cliente'),
(15, 'maria.elena.walsh', 'Walsh5555', 'cliente'),
(16, 'leonardo.sbaraglia', 'Sbaraglia6666', 'cliente'),
(17, 'ricardo.darin', 'Darin7777', 'cliente'),
(18, 'luisa.kuliok', 'Kuliok8888', 'cliente'),
(19, 'susana.gimenez', 'Gimenez9999', 'cliente'),
(20, 'mirtha.legrand', 'Legrand0000', 'cliente'),
(21, 'maria.farias.gomez', 'FariasGomez1234', 'cliente'),
(22, 'andres.calamaro', 'Calamaro2345', 'cliente'),
(23, 'charly.garcia', 'Garcia3456', 'cliente'),
(24, 'luis.spinetta', 'Spinetta4567', 'cliente'),
(25, 'sandrita.martinez', 'Martinez5678', 'cliente');


-- Insercion de clientes para los administradores
INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono) VALUES
('12345679', '20123456789', 'Franco', 'Formia', 'M', 'Argentina', '1990-03-15', 'Calle Falsa 123', 1, 1, 'fforma@gmail.com', '1134567890'),
('23456780', '20234567890', 'Jose', 'Angel', 'M', 'Argentina', '1985-07-22', 'Av. Libertador 456', 2, 2, 'jangel@gmail.com', '1234567891'),
('34567881', '20345678912', 'Luciano', 'Santostefano', 'M', 'Argentina', '1992-05-12', 'Calle Mitre 789', 3, 3, 'lsanto@gmail.com', '1334567892'),
('45678982', '20456789023', 'Joaquin', 'Noceti', 'M', 'Argentina', '1988-09-17', 'Pasaje Colón 321', 4, 4, 'jnoceti@gmail.com', '1434567893'),
('56789083', '20567890134', 'Tamara', 'Herrera', 'F', 'Argentina', '1995-11-05', 'Boulevard Roca 654', 5, 5, 'therrera@gmail.com', '1534567894');


-- Insercion de usuarios para los administradores
INSERT INTO usuarios (id, nombre_usuario, contrasenia, tipo) VALUES
(26, 'AdminFranco', 'Admin1234', 'admin'),
(27, 'AdminJose', 'Admin2345', 'admin'),
(28, 'AdminLucho', 'Admin3456', 'admin'),
(29, 'AdminJoaco', 'Admin4567', 'admin'),
(30, 'AdminTamara', 'Admin5678', 'admin');

-- Insercion de cuentas para cada cliente
INSERT INTO cuentas (numero_cuenta, cbu, id_usuario, id_tipoCuenta, saldo, fecha_creacion) VALUES
('2000000000', '2012345671234567890101', 1, 1, 10000.00, NOW()),
('2000000001', '2012345671234567890202', 2, 2, 10000.00, NOW()),
('2000000002', '2012345671234567890301', 3, 1, 10000.00, NOW()),
('2000000003', '2012345671234567890402', 4, 2, 10000.00, NOW()),
('2000000004', '2012345671234567890501', 5, 1, 10000.00, NOW()),
('2000000005', '2012345671234567890602', 6, 2, 10000.00, NOW()),
('2000000006', '2012345671234567890701', 7, 1, 10000.00, NOW()),
('2000000007', '2012345671234567890802', 8, 2, 10000.00, NOW()),
('2000000008', '2012345671234567890901', 9, 1, 10000.00, NOW()),
('2000000009', '2012345671234567891002', 10, 2, 10000.00, NOW()),
('2000000010', '2012345671234567891101', 11, 1, 10000.00, NOW()),
('2000000011', '2012345671234567891202', 12, 2, 10000.00, NOW()),
('2000000012', '2012345671234567891301', 13, 1, 10000.00, NOW()),
('2000000013', '2012345671234567891402', 14, 2, 10000.00, NOW()),
('2000000014', '2012345671234567891501', 15, 1, 10000.00, NOW()),
('2000000015', '2012345671234567891602', 16, 2, 10000.00, NOW()),
('2000000016', '2012345671234567891701', 17, 1, 10000.00, NOW()),
('2000000017', '2012345671234567891802', 18, 2, 10000.00, NOW()),
('2000000018', '2012345671234567891901', 19, 1, 10000.00, NOW()),
('2000000019', '2012345671234567892002', 20, 2, 10000.00, NOW()),
('2000000020', '2012345671234567892101', 21, 1, 10000.00, NOW()),
('2000000021', '2012345671234567892202', 22, 2, 10000.00, NOW()),
('2000000022', '2012345671234567892301', 23, 1, 10000.00, NOW()),
('2000000023', '2012345671234567892402', 24, 2, 10000.00, NOW()),
('2000000024', '2012345671234567892501', 25, 1, 10000.00, NOW());


INSERT INTO movimientos (id_cuenta, id_tipoMovimiento, detalle, fechaHora, importe, id_cuentaDestino, Saldo_disponible) VALUES
(1, 4, 'Transferencia a cuenta de ahorro', '2024-01-15 10:30:00', 2575.45, 2, 9000.00),
(2, 4, 'Pago de servicio de electricidad', '2024-01-20 14:00:00', 1823.60, 3, 7500.00),
(3, 4, 'Compra en l�nea', '2024-02-02 16:45:00', 3200.30, 4, 6500.00),
(4, 4, 'Pago de tarjeta de cr�dito', '2024-02-05 09:00:00', 4500.75, 5, 5600.00),
(5, 4, 'Pago de impuesto vehicular', '2024-02-10 11:30:00', 710.90, 6, 9500.00),
(6, 4, 'Transferencia a cuenta de inversi�n', '2024-03-01 08:15:00', 5000.00, 7, 10000.00),
(7, 4, 'Retiro en cajero autom�tico', '2024-03-03 12:00:00', 1500.50, 8, 8500.00),
(8, 4, 'Compra en supermercado', '2024-03-10 17:25:00', 320.75, 9, 8000.00),
(9, 4, 'Transferencia de dinero a cuenta externa', '2024-03-15 19:00:00', 4350.00, 10, 7000.00),
(10, 4, 'Pago de colegiatura universitaria', '2024-03-20 13:30:00', 6300.20, 11, 9500.00),
(11, 4, 'Compra de equipo electr�nico', '2024-04-02 10:00:00', 2500.00, 12, 7400.00),
(12, 4, 'Pago de servicios m�dicos', '2024-04-05 14:45:00', 1800.25, 13, 6500.00),
(13, 4, 'Pago de alquiler de oficina', '2024-04-10 09:00:00', 5500.00, 14, 8800.00),
(14, 4, 'Transferencia a cuenta de ahorro personal', '2024-04-15 11:30:00', 4200.30, 15, 9000.00),
(15, 4, 'Pago de seguro de vida', '2024-05-02 16:00:00', 350.90, 16, 8200.00),
(16, 4, 'Compra de materiales de oficina', '2024-05-10 08:45:00', 1200.00, 17, 7800.00),
(17, 4, 'Transferencia para inversi�n a bolsa', '2024-05-20 10:30:00', 3000.00, 18, 9600.00),
(18, 4, 'Pago de impuestos municipales', '2024-06-01 12:00:00', 900.50, 19, 7100.00),
(19, 4, 'Compra de medicamentos', '2024-06-10 14:30:00', 250.30, 20, 8800.00),
(20, 4, 'Transferencia a cuenta de inversi�n', '2024-06-12 08:00:00', 8000.00, 21, 9000.00),
(21, 4, 'Pago de suscripci�n mensual a plataforma', '2024-06-15 10:00:00', 100.00, 22, 7600.00),
(22, 4, 'Compra en tienda departamental', '2024-07-01 18:30:00', 2100.80, 23, 6700.00),
(23, 4, 'Pago de seguro de auto', '2024-07-05 09:45:00', 1500.00, 24, 8500.00),
(24, 4, 'Retiro de fondos', '2024-07-10 15:00:00', 4000.00, 25, 7000.00),
(25, 4, 'Transferencia a cuenta externa', '2024-07-15 13:00:00', 3200.10, 1, 9000.00),
(1, 4, 'Compra en restaurante', '2024-08-01 20:00:00', 250.50, 2, 8700.00),
(2, 4, 'Pago de servicios de internet', '2024-08-05 11:15:00', 1250.00, 3, 6400.00),
(3, 4, 'Transferencia a cuenta de ahorro', '2024-08-15 09:30:00', 3200.00, 4, 9300.00),
(4, 4, 'Pago de servicios de agua', '2024-08-20 17:45:00', 760.20, 5, 7500.00),
(5, 4, 'Compra en tienda de tecnolog�a', '2024-09-02 12:00:00', 4300.00, 6, 8300.00),
(6, 4, 'Pago de renta mensual', '2024-09-05 08:30:00', 3700.75, 7, 9200.00),
(7, 4, 'Pago de mensualidad gimnasio', '2024-09-10 13:00:00', 350.00, 8, 7800.00),
(8, 4, 'Pago de servicios de streaming', '2024-09-12 14:45:00', 220.00, 9, 7100.00),
(9, 4, 'Transferencia a cuenta de ahorro personal', '2024-10-01 10:30:00', 1500.00, 10, 9600.00),
(10, 4, 'Compra de pasajes de avi�n', '2024-10-10 08:00:00', 2700.50, 11, 8300.00),
(11, 4, 'Pago de seguros m�dicos', '2024-10-15 09:15:00', 1150.75, 12, 8000.00),
(12, 4, 'Pago de impuestos federales', '2024-11-01 12:00:00', 2800.00, 13, 9300.00),
(13, 4, 'Transferencia a cuenta bancaria', '2024-11-05 10:45:00', 4900.00, 14, 8700.00),
(14, 4, 'Compra en comercio local', '2024-11-10 17:30:00', 890.00, 15, 7300.00);

update movimientos set fechaHora = '2024-01-01 10:00:00' where id_tipoMovimiento = 1
