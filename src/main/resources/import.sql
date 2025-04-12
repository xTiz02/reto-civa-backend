-- Marcas
INSERT INTO marcas (nombre) VALUES ('Mercedes-Benz');
INSERT INTO marcas (nombre) VALUES ('Volvo');
INSERT INTO marcas (nombre) VALUES  ('Scania');
INSERT INTO marcas (nombre) VALUES ('MAN');
INSERT INTO marcas (nombre) VALUES ('Iveco');

-- Buses
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B001', 'ABC-123',  'Asientos de cuero, aire acondicionado', 1, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B002', 'DEF-456',  'WiFi, cargadores USB', 2, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B003', 'GHI-789',  'Espacio para sillas de ruedas', 3, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES('B004', 'JKL-101',  'Cámaras de seguridad, luces LED', 4, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B005', 'MNO-202',  'Asientos reclinables, entretenimiento a bordo', 5, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B006', 'PQR-303',  'Conectividad Bluetooth, climatización', 1, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B007', 'STU-404',  'Autonomía eléctrica, recarga rápida', 2, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B008', 'VWX-505',  'Baño a bordo, amplio maletero', 3, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B009', 'YZA-606',  'Suspensión neumática, control de estabilidad', 4, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B010', 'BCD-707',  'Detector de fatiga del conductor', 5, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B011', 'EFG-808', 'Asientos ergonómicos, iluminación ambiental', 1, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B012', 'HIJ-909', 'Puertos HDMI, sonido envolvente', 2, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B013', 'KLM-010', 'Refrigerador, cafetera a bordo', 3, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B014', 'NOP-111', 'Pantallas individuales, internet satelital', 4, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B015', 'QRS-212', 'Sistema de navegación GPS, micrófono para guía', 5, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B016', 'TUV-313', 'Luces de lectura personales, aire purificado', 1, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B017', 'WXY-414', 'Sistema de cámaras 360°, pantalla frontal', 2, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B018', 'ZAB-515', 'Asientos calefaccionados, cargadores inalámbricos', 3, true);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B019', 'CDE-616', 'Luces automáticas, control de clima dual', 4, false);
INSERT INTO buses (numero_bus, placa, caracteristicas, marca_id, activo) VALUES ('B020', 'FGH-717', 'Botón de pánico, salidas de emergencia mejoradas', 5, true);

-- Usuarios (la contraseña es 1234 encriptada)
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$NRFP84zXJobHNEr1xlRzhOYb4lkY4ukRcG3oNMkexJAhqSGnX3pbu', true);
INSERT INTO users (username, password, enabled) VALUES ('usuario', '$2a$10$GcXjFyWNnAGq4/wkiCQ2DO79c3bmeeqPo78zZmbRWSUK9mfQ46U5S', true);


-- Permisos de usuarios
INSERT INTO user_entity_permissions (user_entity_id, permissions) VALUES (1, 'bus_view_detail');
INSERT INTO user_entity_permissions (user_entity_id, permissions) VALUES (1, 'buses_view_all');
INSERT INTO user_entity_permissions (user_entity_id, permissions) VALUES (2, 'buses_view_all');