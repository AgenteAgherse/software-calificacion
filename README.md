# Proyecto de Software de Calificación de Alumnos.


# Descripción
En el entorno educativo de un colegio, surge la necesidad de crear un software que facilite la gestión de notas de los estudiantes. Este software permitirá el registro, seguimiento y cálculo de las calificaciones de los estudiantes en diferentes asignaturas, contribuyendo a una administración más eficiente y a una comunicación transparente de los padres y tutores.

# Detalles del problema
El colegio busca una solución para gestionar las calificaciones de los estudiantes de manera más efectiva. El software deberá ser capaz de registrar las notas asignadas por los profesores en distintas asignaturas y presentarlas de manera organizada para su análisis y seguimiento. Además, deberá brindar la posibilidad de generar informes para los padres y tutores.


## Base de datos
En este caso, la base de datos es hecha mediante un SGBD como MySQL. Esto debido a que primero, la estructura de los datos no es horizontal. En cambio, es una información que necesita ser estructurada sí o sí por lo que es descartado usar bases de datos no relacionales (NoSQL). Otra de las razones es que constantemente va a estar escribiendo datos.

### ¿Por qué usar SQL y no las NoSQL en este proyecto?
Cuando se usan bases de datos no relacionales como MongoDB, las funciones de estos SGBD son más que todo para la lectura de datos y evitar escribir tantas consultas por lo que puede ahorrar tiempo en ejecución.

Como se dijo anteriormente, dado que se necesita constantemente estar escribiendo datos, se toman bases de datos SQL como MySQL o Postgre. En este caso, se tomará MySQL dado que es el lenguaje estructurado en bases de datos más famosa por el momento.

### Razones de la estructura de la base de datos
La base de datos actualmente contiene 7 tablas de las cuales, se puede tomar la información de las tablas de la siguiente manera:
 * Personas es una tabla que guarda la información tanto de los estudiantes como los profesores. Por cuestiones de normalización y evitar redundancia, se crea esta tabla y las tablas de profesores y estudiantes guardan solo la información específica.
 * Hay una relación entre la tabla de estudiante y materia que genera una tabla terciaria para tener una tabla que guarde la información de los registros específicamente en vez de generar tablas caóticas en el caso de los estudiantes o de la materia.
 * Lo mismo pasa con las notas de los estudiantes y las tareas. En este caso, la tabla Notas guarda la información de la nota y un comentario que el profesor decida poner.
 * Hay triggers que hacen que la base de datos guarde unos datos predeterminados si no son ingresados dentro del rango correcto (Protocolo ACID. *Consistencia*). Por ejemplo, la tabla *Notas* es una tabla que tiene un trigger que evita que el usuario ponga una nota menor a 0 o mayor a 5.

El modelo entidad relación es el siguiente:

![Diagrama entidad relación del proyecto](.\Database\MER.png)

> Nota: Se cambia el atributo tipo_identificacion de INT a VARCHAR.