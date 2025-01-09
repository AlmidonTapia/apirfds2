CREATE DATABASE ActividadesDB;

USE ActividadesDB;

CREATE TABLE Actividad (
    idActividad CHAR(36) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fecha_hora_inicio DATETIME NOT NULL,
    fecha_hora_termino DATETIME NOT NULL,
    primary key(idActividad)
);

select *from Actividad
INSERT INTO Actividad (idActividad, nombre, fecha_hora_inicio, fecha_hora_termino) VALUES 
('Ac001','Reunión de equipo', '2025-01-10 10:00:00', '2025-01-10 11:00:00'),
('Ac002','Presentación del proyecto', '2025-01-12 14:00:00', '2025-01-12 15:00:00');