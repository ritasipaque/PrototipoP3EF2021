-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema seguridad
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema seguridad
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `umg` DEFAULT CHARACTER SET utf8 ;
USE `umg` ;

-- -----------------------------------------------------
-- Table `umg`.`tbl_modulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_modulo` (
  `PK_id_Modulo` INT NOT NULL,
  `nombre_modulo` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion_modulo` VARCHAR(200) NULL DEFAULT NULL,
  `estado_modulo` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_Modulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `umg`.`tbl_modulo` (`PK_id_Modulo`, `nombre_modulo`, `descripcion_modulo`, `estado_modulo`) VALUES ('0000', 'Seguridad', 'Asignación', '1');
INSERT INTO `umg`.`tbl_modulo` (`PK_id_Modulo`, `nombre_modulo`, `descripcion_modulo`, `estado_modulo`) VALUES ('1000', 'Finanzas', 'Asignación', '1');
INSERT INTO `umg`.`tbl_modulo` (`PK_id_Modulo`, `nombre_modulo`, `descripcion_modulo`, `estado_modulo`) VALUES ('2000', 'Hotelería', 'Asignación', '1');
INSERT INTO `umg`.`tbl_modulo` (`PK_id_Modulo`, `nombre_modulo`, `descripcion_modulo`, `estado_modulo`) VALUES ('3000', 'Comercial', 'Asignación', '1');

-- -----------------------------------------------------
-- Table `umg`.`tbl_aplicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_aplicacion` (
  `PK_id_aplicacion` INT NOT NULL,
  `nombre_aplicacion` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion_aplicacion` VARCHAR(200) NULL DEFAULT NULL,
  `no_reporteAsociado` INT NULL DEFAULT NULL,
  `estado_aplicacion` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_aplicacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `umg`.`tbl_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_usuario` (
  `PK_id_usuario` VARCHAR(25) NOT NULL,
  `nombre_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `apellido_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `username_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `password_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `correo_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `cambio_password` TINYINT NULL DEFAULT NULL,
  `estado_usuario` TINYINT NULL DEFAULT NULL,
  `ultima_conexion` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `umg`.`tbl_bitacora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_bitacora` (
  `PK_id_bitacora` INT NOT NULL AUTO_INCREMENT,
  `PK_id_usuario` VARCHAR(25) NOT NULL,
  `fecha` VARCHAR(25)  null DEFAULT NULL,
  `hora` VARCHAR(25) NULL DEFAULT NULL,
  `host1` VARCHAR(45) NULL DEFAULT NULL,
  `ip` VARCHAR(25) NULL DEFAULT NULL,
  `accion` VARCHAR(50) NULL DEFAULT NULL,
  `tabla` VARCHAR(45) NULL DEFAULT NULL,
`PK_id_Modulo` int (25) NULL DEFAULT NULL,
PRIMARY KEY (`PK_id_bitacora`),
 CONSTRAINT `fk_PK_id_Modulo`
 FOREIGN KEY (`PK_id_Modulo`)
REFERENCES `umg`.`tbl_modulo` (`PK_id_Modulo`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `umg`.`tbl_perfil_encabezado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_perfil_encabezado` (
  `PK_id_perfil` INT NOT NULL,
  `nombre_perfil` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion_perfil` VARCHAR(200) NULL DEFAULT NULL,
  `estado_perfil` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_perfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `umg`.`tbl_perfil_encabezado` (`PK_id_perfil`, `nombre_perfil`, `descripcion_perfil`, `estado_perfil`) VALUES ('1', 'perfil1', 'prueba en el perfil 1', '1');

-- -----------------------------------------------------
-- Table `umg`.`tbl_perfil_detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_perfil_detalle` (
  `PK_id_perfil` INT NOT NULL,
  `PK_id_aplicacion` INT NOT NULL,
  `ingresar` TINYINT NULL DEFAULT NULL,
  `consultar` TINYINT NULL DEFAULT NULL,
  `modificar` TINYINT NULL DEFAULT NULL,
  `eliminar` TINYINT NULL DEFAULT NULL,
  `imprimir` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_perfil`, `PK_id_aplicacion`),
    CONSTRAINT `fk_Perfil_detalle_Aplicacion1`
    FOREIGN KEY (`PK_id_aplicacion`)
    REFERENCES `umg`.`tbl_aplicacion` (`PK_id_aplicacion`),
  CONSTRAINT `fk_Perfil_detalle_Perfil1`
    FOREIGN KEY (`PK_id_perfil`)
    REFERENCES `umg`.`tbl_perfil_encabezado` (`PK_id_perfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `umg`.`tbl_perfil_detalle` (`PK_id_perfil`, `PK_id_aplicacion`, `ingresar`, `consultar`, `modificar`, `eliminar`, `imprimir` ) VALUES ('1', '2004', '1', '1', '1', '1', '1');
INSERT INTO `umg`.`tbl_perfil_detalle` (`PK_id_perfil`, `PK_id_aplicacion`, `ingresar`, `consultar`, `modificar`, `eliminar`, `imprimir` ) VALUES ('1', '2204', '1', '1', '1', '1', '1');
INSERT INTO `umg`.`tbl_perfil_detalle` (`PK_id_perfil`, `PK_id_aplicacion`, `ingresar`, `consultar`, `modificar`, `eliminar`, `imprimir` ) VALUES ('1', '2205', '1', '1', '1', '1', '1');
INSERT INTO `umg`.`tbl_perfil_detalle` (`PK_id_perfil`, `PK_id_aplicacion`, `ingresar`, `consultar`, `modificar`, `eliminar`, `imprimir` ) VALUES ('1', '2001', '1', '1', '1', '1', '1');
INSERT INTO `umg`.`tbl_perfil_detalle` (`PK_id_perfil`, `PK_id_aplicacion`, `ingresar`, `consultar`, `modificar`, `eliminar`, `imprimir` ) VALUES ('1', '2002', '1', '1', '1', '1', '1');

-- -----------------------------------------------------
-- Table `umg`.`tbl_usuario_aplicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_usuario_aplicacion` (
  `PK_id_usuario` VARCHAR(25) NOT NULL,
  `PK_id_aplicacion` INT NOT NULL,
  `ingresar` TINYINT NULL DEFAULT NULL,
  `consulta` TINYINT NULL DEFAULT NULL,
  `modificar` TINYINT NULL DEFAULT NULL,
  `eliminar` TINYINT NULL DEFAULT NULL,
  `imprimir` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`PK_id_usuario`, `PK_id_aplicacion`),
    CONSTRAINT `fk_tbl_usuario_aplicacion_tbl_aplicacion1`
    FOREIGN KEY (`PK_id_aplicacion`)
    REFERENCES `umg`.`tbl_aplicacion` (`PK_id_aplicacion`),
  CONSTRAINT `fk_Usuario_aplicacion_Usuario1`
    FOREIGN KEY (`PK_id_usuario`)
    REFERENCES `umg`.`tbl_usuario` (`PK_id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `umg`.`tbl_usuario_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_usuario_perfil` (
  `PK_id_usuario` VARCHAR(25) NOT NULL,
  `PK_id_perfil` INT NOT NULL,
  PRIMARY KEY (`PK_id_usuario`, `PK_id_perfil`),
    CONSTRAINT `fk_Usuario_perfil_Perfil1`
    FOREIGN KEY (`PK_id_perfil`)
    REFERENCES `umg`.`tbl_perfil_encabezado` (`PK_id_perfil`),
  CONSTRAINT `fk_Usuario_perfil_Usuario1`
    FOREIGN KEY (`PK_id_usuario`)
    REFERENCES `umg`.`tbl_usuario` (`PK_id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `umg`.`tbl_aplicacion_a_modulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `umg`.`tbl_aplicacion_a_modulos` (
  `PK_id_modulo` INT(25) NOT NULL,
`PK_id_aplicacion` INT NOT NULL,
  PRIMARY KEY (`PK_id_modulo`,`PK_id_aplicacion` ),
    CONSTRAINT `fk_tbl_aplicacion_a_modulos_aplicacion1`
    FOREIGN KEY (`PK_id_aplicacion`)
    REFERENCES `umg`.`tbl_aplicacion` (`PK_id_aplicacion`),
  CONSTRAINT `fk_tbl_aplicacion_a_modulos_modulo`
    FOREIGN KEY (`PK_id_modulo`)
    REFERENCES `umg`.`tbl_modulo` (`PK_id_Modulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Insert usuarios (Hoteleria)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('1', 'jefferson', 'davila', 'jeff', '8cb2237d0679ca88db6464eac60da96345513964', 'jeff@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('2', 'admin', 'admin', 'admin', '8cb2237d0679ca88db6464eac60da96345513964', 'esduardo@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('3', 'leonel', 'dominguez', 'laionel', '8cb2237d0679ca88db6464eac60da96345513964', 'leonel@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('4', 'luis', 'gomes', 'luisk', '8cb2237d0679ca88db6464eac60da96345513964', 'luisg@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('5', 'gerson', 'lee', 'gersonl', '8cb2237d0679ca88db6464eac60da96345513964', 'gersonl@gmail.com', '1', '1', '2021-05-02 21:00:48');
-- -----------------------------------------------------
-- Insert aplicacion (Hoteleria)
-- -----------------------------------------------------
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2001', 'Mantenimiento Ama de Llaves', 'Proceso para el área de hoteleria', '1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2002', 'Mantenimiento de Formas de pago', 'Proceso para el área de hoteleria','1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2003', 'Mantenimiento de Habitaciones', 'Proceso para el área de hoteleria', '1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2004', 'Mantenimiento de Clientes', 'Proceso para el área de hoteleria', '1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2005', 'Mantenimiento de Pisos', 'Proceso para el área de hoteleria', '1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2006', 'Mantenimiento de Servicios', 'Proceso para el área de hoteleria', '1', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2201', 'Proceso de Reservacion de Habitacion', 'Proceso para el área de hoteleria', '2', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2202', 'Proceso Entregar o Recibir Habitacion', 'Proceso para el área de hoteleria', '2', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2203', 'Proceso de Facturación', 'Proceso para el área de hoteleria', '2', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2204', 'Registro de Objetos', 'Proceso para el área de hoteleria', '2', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('2205', 'Proceso de Reservacion de Habitacion', 'Proceso para el área de hoteleria', '2', '1');
-- -----------------------------------------------------
-- Insert usuario_aplicacion (Hoteleria)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2001', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2002', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2003', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2004', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2005', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2006', '1', '1', '1', '1', '1');

INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2001', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2002', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2003', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2004', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2005', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('3', '2006', '1', '1', '1', '1', '1');

INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2001', '0', '0', '0', '0', '0');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2002', '0', '0', '0', '0', '0');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2003', '0', '0', '0', '0', '0');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2004', '0', '0', '0', '0', '0');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2005', '0', '0', '0', '0', '0');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('4', '2006', '0', '0', '0', '0', '0');

INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2201', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2202', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2203', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2204', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '2205', '1', '1', '1', '1', '1');

INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('1', '2201', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('1', '2202', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('1', '2203', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('1', '2204', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('1', '2205', '1', '1', '1', '1', '1');



-- -----------------------------------------------------
-- Insert usuarios (Comercial)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('11', 'carlos', 'carlos', 'carlos', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'cflorezd@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('10', 'esduardo', 'esduardo', 'esduardo', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'esduardo@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('12', 'diana', 'diana', 'diana', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'diana@gmail.com', '1', '1', '2021-05-02 21:00:48');
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES ('13', 'rita', 'rita  ', 'rita', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'esduardo@gmail.com', '1', '1', '2021-05-02 21:00:48');
-- -----------------------------------------------------
-- Insert aplicacion (Comercial)
-- -----------------------------------------------------
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3001', 'Mantenimiento productos', 'areacomercial', '4', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3002', 'Mantenimiento Deudores', 'areacomercial', '2', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3003', 'Mantenimiento Proveedores', 'areacomercial', '3', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3004', 'Mantenimiento productos', 'areacomercial', '4', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3005', 'Mantenimiento productos', 'areacomercial', '4', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3006', 'Mantenimiento productos', 'areacomercial', '4', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3007', 'Mantenimiento productos', 'areacomercial', '4', '1');
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES ('3008', 'Mantenimiento productos', 'areacomercial', '4', '1');
-- -----------------------------------------------------
-- Insert usuario_aplicacion (Comercial)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3001', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3002', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3003', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3004', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3005', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3006', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3007', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('2', '3008', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3001', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3002', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3003', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3004', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3005', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3006', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3007', '1', '1', '1', '1', '1');
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES ('11', '3008', '1', '1', '1', '1', '1');



-- -----------------------------------------------------
-- Insert tbl_usuario (Finanzas)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario` (`PK_id_usuario`, `nombre_usuario`, `apellido_usuario`, `username_usuario`, `password_usuario`, `correo_usuario`, `cambio_password`, `estado_usuario`, `ultima_conexion`) VALUES
('1000', 'Diego', 'Vásquez', 'dievas2001', 'gamer070', 'dvasquez@yahoo.es', 0, 1, '2021-05-17 23:00:00');
-- -----------------------------------------------------
-- Insert tbl_aplicacion (Finanzas)
-- -----------------------------------------------------
INSERT INTO `tbl_aplicacion` (`PK_id_aplicacion`, `nombre_aplicacion`, `descripcion_aplicacion`, `no_reporteAsociado`, `estado_aplicacion`) VALUES
(1000, 'Mantenimiento de Clasificación de Cuentas', 'Clasificar las cuentas contables', 1000, 1),
(1001, 'Mantenimiento de Períodos Fiscales', 'Mantenimiento de Períodos Fiscales', 1001, 1),
(1002, 'Mantenimiento de Tipos de Transacción', 'Mantenimiento de Tipos de Transacción', 1002, 1),
(1003, 'Mantenimiento de Cuenta Contable', 'Mantenimiento de Cuenta Contable', 1003, 1),
(1004, 'Mantenimiento de Tipo de Asiento Contable', 'Mantenimiento de Tipo de Asiento Contable', 1004, 1),
(1005, 'Mantenimiento de Divisas', 'Mantenimiento de Divisas', 1005, 1),
(1006, 'Mantenimiento de Cuenta Bancaria', 'Mantenimiento de Cuenta Bancaria', 1006, 1),
(1007, 'Mantenimiento de Tipo Persona', 'Mantenimiento de Tipo Persona', 1007, 1),
(1008, 'Mantenimiento de Bancos', 'Mantenimiento de Bancos', 1008, 1),
(1009, 'Mantenimiento de Cuentahabientes', 'Mantenimiento de Cuentahabientes', 1009, 1),
(1101, 'Proceso de Partidas Contables', 'Proceso de Partidas Contables', 1101, 1),
(1102, 'Proceso de Encabezado Partida Contable', 'Proceso de Encabezado Partida Contable', 1102, 1),
(1103, 'Proceso de Transacción Bancaria', 'Proceso de Transacción Bancaria', 1103, 1),
(1105, 'Proceso de Emisión de Cheques', 'Proceso de Emisión de Cheques', 1105, 1);
-- -----------------------------------------------------
-- Insert tbl_usuario_aplicacion (Finanzas)
-- -----------------------------------------------------
INSERT INTO `tbl_usuario_aplicacion` (`PK_id_usuario`, `PK_id_aplicacion`, `ingresar`, `consulta`, `modificar`, `eliminar`, `imprimir`) VALUES
('1000', 1000, 1, 1, 1, 1, 1),
('1000', 1001, 1, 1, 1, 1, 1),
('1000', 1002, 1, 1, 1, 1, 1),
('1000', 1003, 1, 1, 1, 1, 1),
('1000', 1004, 1, 1, 1, 1, 1),
('1000', 1005, 1, 1, 1, 1, 1),
('1000', 1006, 1, 1, 1, 1, 1),
('1000', 1007, 1, 1, 1, 1, 1),
('1000', 1008, 1, 1, 1, 1, 1),
('1000', 1009, 1, 1, 1, 1, 1),
('1000', 1101, 1, 1, 1, 1, 1),
('1000', 1102, 1, 1, 1, 1, 1),
('1000', 1103, 1, 1, 1, 1, 1),
('1000', 1105, 1, 1, 1, 1, 1);