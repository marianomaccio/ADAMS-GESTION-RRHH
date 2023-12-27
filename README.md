Vamos a modelar y programar un sistema de creación de empleados para el departamento de RRHH de una empresa.

De los empleados interesa conocer su NIF, nombre, apellidos, puesto, salario mensual y datos de la nómina.

Para los datos de la nómina tendremos, salario base, plus de convenio, complementos y descuento de IRPF que será en torno al 10-18% del salario base (nótese la simplificación en relación a la realidad, este valor se calculará con un aleatorio en ese rango).

La clase empleado tendrá un método que calculará el salario neto que percibirá mensualmente (salarioMensual), que será la suma del salario base, plus, complementos y descontando el IRPF.

Hay dos tipos de empleado, empleado técnico y empleado ejecutivo. Del empleado técnico interesa conocer el listado de cualificaciones que posee y del ejecutivo interesa saber lo que cobra en comisiones por objetivos anualmente (en neto), este valor afectará al cálculo del salario neto mensual que percibe el ejecutivo.

Partiendo del anterior modelo, se pide realizar un programa que presente un menú de opciones por consola que permita: crear empleados (opción 1), buscar empleado (opción 2), o visualizar un resumen de todos los empleados (opción 3) y salir (opción 4). Las opciones 2 y 3 mostrarán el mensaje correspondiente si aún no se han creado empleados.

La opción 1 permitirá ir dando de alta empleados de distintos tipos, a elección del usuario, solicitando por consola uno a uno todos los datos del empleado (se permite por simplicidad pedir solo el salario base de los datos de la nómina y el resto pueden ser cantidades fijas), y los datos específicos por tipo de empleado (ejecutivo o técnico). Una vez se han creado al menos cinco empleados, se permitirá la opción de salir de la creación de usuarios.

Se deja a elección de programador la forma en la que se mostrará el usuario buscado (opción 2) o el resumen de empleados (opción 3).

Los usuarios se guardarán en un fichero en disco (persistirán aun habiendo salido del programa y se podrán leer en futuras ejecuciones), y se leerá de este fichero para listarlos o buscar un empleado, se recomienda realizar una precarga en una colección en Java (el tipo de colección a utilizar se deja a elección del programador), para hacer uso de ella en los listados y búsquedas. Hay que tener en cuenta que esa colección habrá que refrescarla en caso de que se hayan creado usuarios nuevos posteriormente a su carga.

Cuando el usuario decida salir, se mostrará el mensaje de despedida correspondiente.