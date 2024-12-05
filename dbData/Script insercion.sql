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

-- Insercion de movimientos de alta de cuenta para cada cuenta creada
INSERT INTO movimientos (id_cuenta, id_tipoMovimiento, detalle, importe, Saldo_disponible) VALUES
(1, 1, 'Alta de cuenta', 0.00, 10000.00),
(2, 1, 'Alta de cuenta', 0.00, 10000.00),
(3, 1, 'Alta de cuenta', 0.00, 10000.00),
(4, 1, 'Alta de cuenta', 0.00, 10000.00),
(5, 1, 'Alta de cuenta', 0.00, 10000.00),
(6, 1, 'Alta de cuenta', 0.00, 10000.00),
(7, 1, 'Alta de cuenta', 0.00, 10000.00),
(8, 1, 'Alta de cuenta', 0.00, 10000.00),
(9, 1, 'Alta de cuenta', 0.00, 10000.00),
(10, 1, 'Alta de cuenta', 0.00, 10000.00),
(11, 1, 'Alta de cuenta', 0.00, 10000.00),
(12, 1, 'Alta de cuenta', 0.00, 10000.00),
(13, 1, 'Alta de cuenta', 0.00, 10000.00),
(14, 1, 'Alta de cuenta', 0.00, 10000.00),
(15, 1, 'Alta de cuenta', 0.00, 10000.00),
(16, 1, 'Alta de cuenta', 0.00, 10000.00),
(17, 1, 'Alta de cuenta', 0.00, 10000.00),
(18, 1, 'Alta de cuenta', 0.00, 10000.00),
(19, 1, 'Alta de cuenta', 0.00, 10000.00),
(20, 1, 'Alta de cuenta', 0.00, 10000.00),
(21, 1, 'Alta de cuenta', 0.00, 10000.00),
(22, 1, 'Alta de cuenta', 0.00, 10000.00),
(23, 1, 'Alta de cuenta', 0.00, 10000.00),
(24, 1, 'Alta de cuenta', 0.00, 10000.00),
(25, 1, 'Alta de cuenta', 0.00, 10000.00);

