-- Insertando datos en la tabla Empresa
INSERT INTO empresa (nombre_empresa, ruc, direccion, telefono, correo)
VALUES
    ('Transporte Emtrafesa S.A.C.', '20123456789', 'Av. Siempre Viva 123', '012345678', 'contacto@emtrafesa.com'),
    ('Transportes El Sol S.A.C.', '20198765432', 'Calle Falsa 456', '098765432', 'info@elsol.com')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla UserEmtraf
INSERT INTO user_emtraf (correo, contrasena, tipo_usuario)
VALUES
    ('cliente1@emtrafesa.com', 'hashed_password1', 'CLIENTE'),
    ('empleado1@emtrafesa.com', 'hashed_password2', 'EMPLEADO')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Cliente
INSERT INTO cliente (nombre, apellidos, user_emtraf_id, tipo_documento, numero_documento, telefono, numero_telefono)
VALUES
    ('Juan', 'Pérez', 1, 'DNI', '12345678', 'CELULAR', '987654321'),
    ('Maria', 'Gonzales', 2, 'CARNET_DE_EXTRANJERIA', '87654321', 'CELULAR', '912345678')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Empleado
INSERT INTO empleado (user_emtraf_id, experiencia)
VALUES
    (2, '5 años de experiencia en conducción de buses interprovinciales')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Bus
INSERT INTO bus (placa, tipo_servicio, modelo, estado_bus, numero_pisos)
VALUES
    ('ABC-123', 'CAMA', 'Volvo 2020', 'HABILITADO', 2),
    ('XYZ-987', 'SEMICAMA', 'Mercedes Benz 2019', 'INHABILITADO', 2)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Ruta
INSERT INTO ruta (origen, destino, empleado_id)
VALUES
    ('Lima', 'Trujillo',   1),
    ('Lima', 'Arequipa',   1)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Itinerario
INSERT INTO itinerario (ruta_id, bus_id, tiene_escalas, hora_salida, hora_llegada, empleado_id)
VALUES
    (1, 1, FALSE, '08:00:00', '13:30:00', 1),
    (2, 2, TRUE, '21:00:00', '09:00:00', 1)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Pasaje
INSERT INTO pasaje (cliente_id, itinerario_id, fecha_compra, total, estado)
VALUES
    (1, 1, CURRENT_TIMESTAMP, 120.50, 'COMPRADO'),
    (2, 2, CURRENT_TIMESTAMP, 150.75, 'COMPRADO')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Pago
INSERT INTO pago (pasaje_id, cliente_id, empresa_id, fecha_pago, monto, metodo_pago, estado_pago)
VALUES
    (1, 1, 1, CURRENT_TIMESTAMP, 120.50, 'PAYPAL', 'PAGADO'),
    (2, 2, 2, CURRENT_TIMESTAMP, 150.75, 'TARJETA', 'PAGADO')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Pasajero
INSERT INTO pasajero (nombre, apellidos, sexo, tipo_documento, numero_documento, fecha_nacimiento)
VALUES
    ('Pedro', 'Ramírez', 'MASCULINO', 'DNI', '11223344', '1990-04-15'),
    ('Laura', 'Martínez', 'FEMENINO', 'CARNET_DE_EXTRANJERIA', '22334455', '1985-07-22')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla Postergacion
INSERT INTO postergacion (pasaje_id, nuevo_itinerario_id, fecha_postergacion)
VALUES
    (1, 2, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla PasajePasajero
INSERT INTO pasaje_pasajero (pasaje_id, pasajero_id)
VALUES
    (1, 1),
    (2, 2)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla DisponibilidadItinerario para el itinerario con id 1
INSERT INTO disponibilidad_itinerario (itinerario_id, fecha_viaje)
VALUES
    (1, '2024-11-24'), -- Fecha de disponibilidad para el itinerario con id 1
    (1, '2024-11-25'),
    (1, '2024-11-26'),
    (2, '2024-11-24'), -- Fecha de disponibilidad para el itinerario con id 2
    (2, '2024-11-25')
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla precios_por_piso para el itinerario con id 1
INSERT INTO precios_por_piso (itinerario_id, piso, precio)
VALUES
    (1, 1,100.50), -- Precio para el primer piso del itinerario con id 1
    (1, 2,120.75), -- Precio para el segundo piso del itinerario con id 1
    (2, 1,80.50),  -- Precio para el primer piso del itinerario con id 2
    (2, 2, 95.00)   -- Precio para el segundo piso del itinerario con id 2
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla asientos_por_piso para el bus con id 1
INSERT INTO asientos_por_piso (bus_id, piso, cantidad_asientos)
VALUES
    (1, 1, 30), -- Bus con id 1, piso 1, 30 asientos
    (1, 2, 25), -- Bus con id 1, piso 2, 25 asientos
    (2, 1, 35), -- Bus con id 2, piso 1, 35 asientos
    (2, 2, 20)  -- Bus con id 2, piso 2, 20 asientos
    ON CONFLICT DO NOTHING;

-- Para el bus con placa 'ABC-123' (ID = 1)
INSERT INTO asiento (numero_asiento, estado, piso, bus_id)
VALUES
    (1, 'DISPONIBLE', 1, 1),
    (2, 'OCUPADO', 1, 1),
    (3, 'DISPONIBLE', 1, 1),
    (4, 'OCUPADO', 2, 1),
    (5, 'DISPONIBLE', 2, 1),
    (6, 'DISPONIBLE', 2, 1)
    ON CONFLICT DO NOTHING;

-- Para el bus con placa 'XYZ-987' (ID = 2)
INSERT INTO asiento (numero_asiento, estado, piso, bus_id)
VALUES
    (1, 'DISPONIBLE', 1, 2),
    (2, 'OCUPADO', 1, 2),
    (3, 'DISPONIBLE', 1, 2),
    (4, 'OCUPADO', 2, 2),
    (5, 'DISPONIBLE', 2, 2),
    (6, 'DISPONIBLE', 2, 2)
    ON CONFLICT DO NOTHING;

-- Insertando datos en la tabla HistorialPago
INSERT INTO historial_pago (cliente_id, pasaje_id, monto_pagado, metodo_pago, fecha_pago, estado_pago, transaccion_id)
VALUES
    (1, 1, 120.50, 'PAYPAL', CURRENT_TIMESTAMP, 'PAGADO', 'TXN12345'),
    (2, 2, 150.75, 'TARJETA', CURRENT_TIMESTAMP, 'PAGADO', 'TXN98765')
    ON CONFLICT DO NOTHING;

