INSERT INTO category (name) VALUES
                                ('Laptop'),
                                ('Desktop'),
                                ('Printer'),
                                ('Network Device');
INSERT INTO location (name) VALUES
                                ('Oficina Central'),
                                ('Sucursal Norte'),
                                ('Sucursal Sur'),
                                ('Data Center');
INSERT INTO asset (name, category_id, location_id) VALUES
                                                       ('Dell Latitude 5420', 1, 1),
                                                       ('HP EliteDesk 800', 2, 1),
                                                       ('Printer HP LaserJet', 3, 2),
                                                       ('Cisco Router RV340', 4, 4),
                                                       ('Lenovo ThinkPad T14', 1, 3);