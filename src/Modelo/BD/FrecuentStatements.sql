/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hacke
 * Created: 6/02/2021
 */

--MUESTRA CONTENIDO DE TABLAS
-- SELECT * FROM categorias LIMIT 100;
-- SELECT * FROM complementos LIMIT 100;
-- SELECT * FROM contratos LIMIT 100;
-- SELECT * FROM deducciones LIMIT 100;
-- SELECT * FROM detallesnominas LIMIT 100;
-- SELECT * FROM empleados LIMIT 100;
-- SELECT * FROM nominas LIMIT 100;

--REINICIA CONTADOR DE PRIMAR KEY
-- ALTER TABLE categorias AUTO_INCREMENT = 1;
-- ALTER TABLE complementos AUTO_INCREMENT = 1;
-- ALTER TABLE contratos AUTO_INCREMENT = 1;
-- ALTER TABLE deducciones AUTO_INCREMENT = 1;
-- ALTER TABLE detallesnominas AUTO_INCREMENT = 1;
-- ALTER TABLE empleados AUTO_INCREMENT = 1;
-- ALTER TABLE nominas AUTO_INCREMENT = 1;

--BORRADO
-- DELETE FROM categorias WHERE categorias.idcategoria = 1;
-- DELETE FROM complementos WHERE complementos.idcomplemento = 1;
-- DELETE FROM contratos WHERE contratos.idcontrato = 1;
-- DELETE FROM deducciones WHERE deducciones.iddeduccion = 1;
-- DELETE FROM detallesnominas WHERE detallesnominas.iddetallesnominas = 1;
-- DELETE FROM empleados WHERE empleados.idempleado = 1;
-- DELETE FROM nominas WHERE nominas.idnomina = 1;

----------------------HISTORIAL---------------------------------------------
-- DELETE FROM empleados WHERE empleados.idempleado = 1;
-- DELETE FROM contratos WHERE contratos.idcontrato = 1;
-- DELETE FROM categorias WHERE categorias.idcategoria = 1;
-- ALTER TABLE categorias AUTO_INCREMENT = 1;
-- ALTER TABLE complementos AUTO_INCREMENT = 1;
-- ALTER TABLE contratos AUTO_INCREMENT = 1;
-- ALTER TABLE deducciones AUTO_INCREMENT = 1;
-- ALTER TABLE detallesnominas AUTO_INCREMENT = 1;
-- ALTER TABLE empleados AUTO_INCREMENT = 1;
-- ALTER TABLE nominas AUTO_INCREMENT = 1;
