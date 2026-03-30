#  Sistema de Gestión Académica (Java Avanzado)

Aplicación backend desarrollada en **Java** para la gestión académica integral de un centro educativo.El sistema permite administrar alumnos, gestionar sus matrículas y procesar las calificaciones obtenidas en diferentes modalidades de asignaturas.

El enfoque principal de este desarrollo es la **robustez del software**, garantizando que la ejecución nunca se interrumpa por excepciones no controladas.

##  Arquitectura y Conceptos Aplicados
* **Colecciones Optimizadas:** * Uso de `TreeMap<String, Alumno>` en el Gestor para búsquedas ultrarrápidas de alumnos mediante su número de expediente (clave única).
  * Uso de `TreeSet<Asignatura>` para las matrículas, garantizando que no haya asignaturas duplicadas y manteniéndolas ordenadas automáticamente gracias a la implementación de la interfaz `Comparable`.
* **Herencia y Polimorfismo:** Implementación de una clase abstracta `Asignatura` de la que heredan `AsignaturaPresencial` y `AsignaturaEmpresa`, cada una sobrescribiendo el método `estaAprobado()` con su propia lógica de negocio.
* **Control de Excepciones:** Creación de la clase personalizada `MyException` para centralizar la validación de datos, asegurando que los errores de formato o lógica de negocio sean capturados y notificados al usuario sin colgar la aplicación.
* **Validación Regex:** Blindaje de la entrada de datos (nombres, IDs, expedientes) mediante expresiones regulares estrictas desde los constructores y setters.

##  Reglas de Negocio Implementadas
* **Generación de Expedientes:** Creación automática y autoincremental de IDs de alumno con formato blindado (ej. `ENLACES_000000`).
* **Cálculo de Promociones:** * *Asignaturas Presenciales:* Requieren una media $\ge 5$ en las prácticas, sin permitir ninguna nota individual inferior a 4.
  * *Asignaturas en Empresa:* Aprobación directa con nota final $\ge 5$.
* **Interfaz de Consola UI/UX:** Menús interactivos formateados con `String.format` y códigos de escape ANSI para proporcionar *feedback* visual (colores) sobre el estado de las operaciones y aprobados/suspensos.

##  Stack Técnico
* **Lenguaje:** Java SE 
* **Estructura:** Arquitectura basada en Gestor/Modelo (separación lógica).
* **Entorno:** NetBeans IDE / Consola.

---

###  Nota para Perfiles Técnicos (FP Dual)
El mayor reto y logro de este proyecto fue diseñar un modelo de datos escalable. Al utilizar clases abstractas e interfaces, el sistema está preparado para el principio "Open/Closed": se pueden añadir nuevas modalidades de asignaturas en el futuro (ej. *AsignaturaOnline*) sin necesidad de modificar la lógica central del `GestorMatricula`.
