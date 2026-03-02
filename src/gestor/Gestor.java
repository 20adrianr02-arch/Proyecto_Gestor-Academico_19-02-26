package gestor;

import exception.MyException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.AsignaturaEmpresa;
import modelo.AsignaturaPresencial;

public class Gestor {

    private static TreeMap<String, Alumno> listaAlumnos = new TreeMap<>();
    private static String validaBusquedaExpediente = "^ENLACES_[0-9]{6}$";

    public static Alumno altaAlumno(String nombre) throws MyException {
        Alumno A = new Alumno(nombre);
        listaAlumnos.put(A.getExpediente(), A);
        return A;
    }

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
     * CREA EL LISTADO DE INFORMACION DETALLADA DEL ALUMNO INTRODUCIDO, 
     * Y LO GUARDA EN UN STRING, EL CUAL DEVUELVE AL MAIN PARA QUE LO MUESTRE POR CONSOLA
     *
     * @param a
     * @return
     */
    public static String informacionAlumnoDetallada(Alumno a) {
        String ficha = "\nEXPEDIENTE: " + a.getExpediente() + " | NOMBRE: " + a.getNombre().toUpperCase() + "\n";
        ficha += "------------------------------------------------------------\n";
        String formato = "%-20s %-15s %-10s%n";
        ficha += String.format(formato, "ASIGNATURA", "TIPO", "ESTADO");
        ficha += "------------------------------------------------------------\n";
        for (Asignatura asig : a.getListaAsiganturas()) {
            String tipo = (asig instanceof AsignaturaPresencial) ? "PRESENCIAL" : "EMPRESA";
            String estado = asig.estaAprobado() ? "\u001B[32mAPROBADO\u001B[0m" : "\u001B[31mSUSPENSO\u001B[0m";
            ficha += String.format(formato, asig.getNombre().toUpperCase(), tipo, estado);
        }
        ficha += "------------------------------------------------------------\n";
        return ficha;
    }
    
    /**
     * RECORRE LA COLECCION, CREA EL LISTADO DE INFORMACION GENERAL, Y LO GUARDA 
     * EN UN STRING, EL CUAL DEVUELVE AL MAIN PARA QUE LO MUESTRE POR CONSOLA
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
    
    public static String listadoSoloAprobados() {
        String listadoAprobados = " ";
        if (listadoAprobados==" "){
            listadoAprobados = "No hay ningun alumno aprobado";
        }
        for (Alumno a1 : listaAlumnos.values()) {
            a1.getListaAsiganturas();
        }
    }

    public static AsignaturaPresencial AltaAsiganaturaPresencial(Alumno A, int practicas, String id, String nombreAsigatura) throws MyException {
        AsignaturaPresencial AP1 = new AsignaturaPresencial(practicas, id, nombreAsigatura);
        A.llenaListaAsiganturas(AP1);
        return AP1;
    }

    public static void AltaAsiganaturaEmpresa(Alumno A, String nombreEmpresa, float notaFinal, String id, String nombreAsignatura) throws MyException {
        AsignaturaEmpresa AE1 = new AsignaturaEmpresa(nombreEmpresa, notaFinal, id, nombreAsignatura);
        A.llenaListaAsiganturas(AE1);
    }

}
