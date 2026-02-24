package gestor;

import exception.MyException;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.AsignaturaEmpresa;
import modelo.AsignaturaPresencial;

public class Gestor {

    public static Alumno altaAlumno(String nombre) throws MyException {
        return new Alumno(nombre);
    }

    public static void AltaAsiganaturaPresencial(int practicas, String id, String nombreAsigatura) throws MyException {
        AsignaturaPresencial AP1 = new AsignaturaPresencial(practicas,id,nombreAsigatura);
    }

    public static void AltaAsiganaturaEmpresa(Alumno A,String nombreEmpresa, float notaFinal, String id, String nombreAsignatura) throws MyException {
        AsignaturaEmpresa AE1 = new AsignaturaEmpresa(nombreEmpresa,notaFinal,id,nombreAsignatura);
        A.llenaListaAsiganturas(AE1);
    }

}
