package gestor;

import exception.MyException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.AsignaturaEmpresa;
import modelo.AsignaturaPresencial;

public class GestorMatricula {

    private static TreeMap<String, Alumno> listaAlumnos = new TreeMap<>();
    private static String validaBusquedaExpediente = "^ENLACES_[0-9]{6}$";

    public static Alumno altaAlumno(String nombre) throws MyException {
        Alumno A = new Alumno(nombre);
        listaAlumnos.put(A.getExpediente(), A);
        return A;
    }

    /**
     * BUSCA A UN ALUMNO POR SU EXPEDIENTE Y LO DEVUELVE, VALIDA QUE EL EXPEDIENTE NO EXISTA, O NO SEA VALIDO 
     * @param numeroExpedienteAlumno
     * @return
     * @throws MyException 
     */
    public static Alumno buscaAlumno(String numeroExpedienteAlumno) throws MyException {
        if (numeroExpedienteAlumno != null && numeroExpedienteAlumno.matches(validaBusquedaExpediente)) { //QUE SEA ENLACES_XXXXXX  Y NO SEA NULL
            Alumno alumnoEncontrado = listaAlumnos.get(numeroExpedienteAlumno); // UBICA AL ALUMNO GRACIAS A LA CLAVE (EXPEDIENTE) Y SE ALMACENA EN ALUMNOENCONTRADO
            if (alumnoEncontrado != null) {
                return alumnoEncontrado;
            } else {
                throw new MyException("No se ha encontrado el expediente del alumno");
            }
        } else {
            throw new MyException("Introduce un expediente valido");
        }
    }

    /**
     * CREA EL LISTADO DE INFORMACION DETALLADA DEL ALUMNO INTRODUCIDO, Y LO
     * GUARDA EN UN STRING, EL CUAL DEVUELVE AL MAIN PARA QUE LO MUESTRE POR
     * CONSOLA
     *
     * @param ALUMNO a
     * @return String listado
     */
    public static String informacionAlumnoDetallada(Alumno a) {
        String listadoDetallado = "\nEXPEDIENTE: " + a.getExpediente() + " | NOMBRE: " + a.getNombre().toUpperCase() + "\n";
        listadoDetallado += "------------------------------------------------------------\n";
        String formatoFila = "%-20s %-15s %-10s%n";
        listadoDetallado += String.format(formatoFila, "ASIGNATURA", "TIPO", "ESTADO");
        listadoDetallado += "------------------------------------------------------------\n";
        for (Asignatura asig : a.getListaAsiganturas()) {//RECORRE EL ARRAY DE ASIGANTURAS QUE TIENE ALUMNO//
            String tipo = (asig instanceof AsignaturaPresencial) ? "PRESENCIAL" : "EMPRESA"; // SI NO ES PRESENCIAL, ES DE EMPRESA TRUE/FALSE//
            String estado = asig.estaAprobado() ? "\u001B[32mAPROBADO\u001B[0m" : "\u001B[31mSUSPENSO\u001B[0m";
            listadoDetallado += String.format(formatoFila, asig.getNombre().toUpperCase(), tipo, estado);
            if (asig instanceof AsignaturaPresencial) {
                float[] notas = ((AsignaturaPresencial) asig).getNotas();//RECORRE EL ARRAY DE NOTAS QUE TIENE ASIGNATURA PRESENCIAL//
                listadoDetallado += "> Notas: [ ";
                for (float n : notas) {
                    listadoDetallado += n + "  ";
                }
                listadoDetallado += "]\n";
            } else {
                float notaFinal = ((AsignaturaEmpresa) asig).getNotaFinal();
                listadoDetallado += "> Nota final: " + notaFinal + "\n";
            }
            listadoDetallado += "\n"; 
        }
        listadoDetallado += "------------------------------------------------------------\n";
        return listadoDetallado;
    }

    /**
     * RECORRE LA COLECCION, CREA EL LISTADO DE INFORMACION GENERAL, Y LO GUARDA
     * EN UN STRING, EL CUAL DEVUELVE AL MAIN PARA QUE LO MUESTRE POR CONSOLA
     *
     * @return STRING listado
     * @throws MyException
     */
    public static String informacionAlumnos() throws MyException {
        if (listaAlumnos.isEmpty()) { // SI ESTA VACIA LA COLECCION //
            throw new MyException("No hay alumnos registrados en el sistema.");
        }
        String formato = "%-18s %-25s\n";
        String listado = String.format(formato, "EXPEDIENTE", "NOMBRE");
        listado += "------------------------------------------------------------\n";
        for (Alumno a1 : listaAlumnos.values()) {
            listado += String.format(formato,
                    a1.getExpediente(),
                    a1.getNombre().toUpperCase());
        }
        listado += "------------------------------------------------------------\n";
        return listado;
    }
    
    /**
     * RECORRE LA COLECCION DE ALUMNOS, RECORRE LAS NOTAS DE L0S ALUMNOS, Y  DETERMINA SI ESTAN APROBADAS
     * MEDIENTE LOGICA BOOLEANA Y &&, SI UNA ES FALSE, NO PROMOCIONA, UNA VEZ DETERMINADO ESTO
     * HACE LA LISTA DE ALUMNOS PROMOCIONADOS, VERIFICA QUE NO ESTE VACIA O QUE NINGUN ALUMNO PROMOCIONE
     * @return String
     * @throws MyException 
     */
    public static String obtenerAlumnosPromocionan() throws MyException {
        if (listaAlumnos.isEmpty()) {
            throw new MyException("No hay alumnos en el sistema para evaluar la promoción.");
        }
        String formato = "%-18s %-25s %-10s%n";
        String listadoAlumnosPromocionan = String.format(formato, "EXPEDIENTE", "NOMBRE", "RESULTADO");
        listadoAlumnosPromocionan += "------------------------------------------------------------\n";
        boolean Promociona = false;
        for (Alumno al : listaAlumnos.values()) { //RECORRE TODOS LOS ALUMNOS//
            boolean todoAprobado = true;
            for (Asignatura asig : al.getListaAsiganturas()) {//RECORRE TODAS LAS ASIGNATURAS DEL ALUMNO EN ESTA VUELTA DE BUCLE//
                todoAprobado = todoAprobado && asig.estaAprobado(); //TODAS DEBEN SER TRUE, EN LO QUE UNA ES FALSE, SE QUEDA SIEMPRE FALSE POR LA LOGICA BOOLEANA TRUE FALSE = FALSE//
            }
            if (todoAprobado) {
                listadoAlumnosPromocionan += String.format(formato,
                        al.getExpediente(),
                        al.getNombre().toUpperCase(),
                        "\u001B[32mPROMOCIONA\u001B[0m");
                Promociona = true;
            }
        }
        if (!Promociona) {
            return "Ningun alumno promociona.\n";
        }
        listadoAlumnosPromocionan += "------------------------------------------------------------\n";
        return listadoAlumnosPromocionan;

    }

    /**
     * DA DE ALTA ASIGNATURAPRESENCIAL CON SUS RESPECTIVOS ATRIBUTOS, Y SE GUARDAN EN LA COLECCION DE ASIGNATURAS 
     * @param A
     * @param practicas
     * @param id
     * @param nombreAsigatura
     * @return
     * @throws MyException 
     */
    public static AsignaturaPresencial altaAsignaturaPresencial(Alumno A, int practicas, String id, String nombreAsigatura) throws MyException {
        AsignaturaPresencial AP1 = new AsignaturaPresencial(practicas, id, nombreAsigatura);
        A.llenaListaAsiganturas(AP1);
        return AP1;
    }

    /**
     * DA DE ALTA ASIGNATURAEMPRESA CON SUS RESPECTIVOS ATRIBUTOS, Y SE GUARDAN EN LA COLECCION DE ASIGNATURAS
     * @param A
     * @param nombreEmpresa
     * @param notaFinal
     * @param id
     * @param nombreAsignatura
     * @throws MyException 
     */
    public static void altaAsignaturaEmpresa(Alumno A, String nombreEmpresa, float notaFinal, String id, String nombreAsignatura) throws MyException {
        AsignaturaEmpresa AE1 = new AsignaturaEmpresa(nombreEmpresa, notaFinal, id, nombreAsignatura);
        A.llenaListaAsiganturas(AE1);
    }

}
