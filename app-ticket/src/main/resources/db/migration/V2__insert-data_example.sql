INSERT INTO ticket (title, description, status, priority, employee_id, asset_id, created_at) VALUES
                                                                                                 ('Laptop no enciende', 'El equipo no responde al botón de encendido', 'OPEN', 'HIGH', 1, 1, '2025-01-10 08:30'),
                                                                                                 ('Problema con impresora', 'La impresora no imprime documentos', 'IN_PROGRESS', 'MEDIUM', 2, 3, '2025-01-11 09:15'),
                                                                                                 ('PC muy lenta', 'El equipo tarda demasiado en iniciar', 'OPEN', 'LOW', 3, 2, '2025-01-12 10:00'),
                                                                                                 ('Sin conexión a red', 'No hay acceso a internet', 'IN_PROGRESS', 'HIGH', 5, 4, '2025-01-13 11:20'),
                                                                                                 ('Pantalla parpadea', 'Pantalla presenta fallos intermitentes', 'CLOSED', 'MEDIUM', 4, 5, '2025-01-09 14:45');



INSERT INTO ticket_comment (ticket_id, comment, created_at) VALUES
                                                                (1, 'Se solicita verificar cargador', '2025-01-10 09:00'),
                                                                (1, 'El cargador funciona correctamente', '2025-01-10 10:15'),

                                                                (2, 'Se reinstaló driver de impresora', '2025-01-11 10:00'),

                                                                (3, 'Pendiente actualización de sistema', '2025-01-12 11:30'),

                                                                (4, 'Se revisa configuración del router', '2025-01-13 12:00'),
                                                                (4, 'Conectividad restaurada', '2025-01-13 15:45'),

                                                                (5, 'Se reemplazó cable de video', '2025-01-09 15:30');


INSERT INTO ticket_status_history (ticket_id, old_status, new_status, changed_at) VALUES(1, 'NEW', 'OPEN', '2025-01-10 08:30'),
                                                                                      (1, 'OPEN', 'IN_PROGRESS', '2025-01-10 09:30'),

                                                                                      (2, 'NEW', 'OPEN', '2025-01-11 09:15'),
                                                                                      (2, 'OPEN', 'IN_PROGRESS', '2025-01-11 10:45'),

                                                                                      (3, 'NEW', 'OPEN', '2025-01-12 10:00'),

                                                                                      (4, 'NEW', 'OPEN', '2025-01-13 11:20'),
                                                                                      (4, 'OPEN', 'IN_PROGRESS', '2025-01-13 12:30'),
                                                                                      (4, 'IN_PROGRESS', 'CLOSED', '2025-01-13 16:00');

