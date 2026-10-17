-- Crea una base de datos en caso de no existir
CREATE DATABASE IF NOT EXISTS SistemaJudicial;
USE SistemaJudicial;

-- Crear tabla Usuario (Base para todos)
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    cedula VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    rol ENUM('Administrador', 'Abogado', 'Cliente') NOT NULL
);

-- Crear tabla Abogado (Hereda de Usuario)
CREATE TABLE Abogado (
    id INT PRIMARY KEY,
    registro VARCHAR(50) UNIQUE NOT NULL,  -- Número de registro profesional
    especialidad VARCHAR(100) NOT NULL,    -- Rama del derecho
    experiencia INT NOT NULL,              -- Años de experiencia
    honorarioHora DECIMAL(10,2) NOT NULL,  -- Tarifa por hora
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Crear tabla Cliente (Hereda de Usuario)
CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    numeroCaso VARCHAR(20) UNIQUE NOT NULL,
    descripcionCaso TEXT NOT NULL,
    estadoCaso ENUM('En proceso', 'Abierto', 'Cerrado') NOT NULL,
    fechaRegistro DATE DEFAULT (CURDATE()), -- Fecha automática
    abogadoAsignado INT, -- ID del abogado asignado
    notasAdicionales TEXT,
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (abogadoAsignado) REFERENCES Abogado(id) ON DELETE SET NULL
);

-- Crear tabla Administrador (Hereda de Usuario)
CREATE TABLE Administrador (
    id INT PRIMARY KEY,
    nivelAcceso ENUM('superadmin', 'gestor') NOT NULL,  -- Nivel de acceso
    fechaIngreso DATE DEFAULT (CURDATE()), -- Fecha automática
    estadoCuenta BOOLEAN NOT NULL DEFAULT TRUE, -- Activo o inactivo
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Insertar usuarios base
INSERT INTO Usuario (user, password, nombre, apellido, cedula, email, telefono, rol) VALUES
('admin1', 'adminpass', 'Juan', 'Perez', '111222333', 'juan@email.com', '0981112223', 'Administrador'),
('carlosl', 'abogado123', 'Carlos', 'Lopez', '1122334455', 'carlos@email.com', '0981122334', 'Abogado'),
('pedror', 'cliente123', 'Pedro', 'Ramirez', '3344556677', 'pedro@email.com', '0983344556', 'Cliente');

-- Insertar administrador vinculado al usuario creado
INSERT INTO Administrador (id, nivelAcceso, fechaIngreso, estadoCuenta) VALUES
(1, 'superadmin', '2023-05-15', TRUE);

-- Insertar abogado vinculado al usuario creado
INSERT INTO Abogado (id, registro, especialidad, experiencia, honorarioHora) VALUES
(2, 'REG12345', 'Derecho Penal', 10, 150.00);

-- Insertar cliente vinculado al usuario y asignarle un abogado
INSERT INTO Cliente (id, numeroCaso, descripcionCaso, estadoCaso, fechaRegistro, abogadoAsignado, notasAdicionales) VALUES
(3, 'CASE2024001', 'Demanda por daños y perjuicios', 'En proceso', '2024-01-10', 2, 'Caso con urgencia');
-- Mostrar tablas
SHOW TABLES;
SELECT * FROM Usuario;
SELECT * FROM Administrador;
SELECT * FROM Cliente;
SELECT * FROM Abogado;
