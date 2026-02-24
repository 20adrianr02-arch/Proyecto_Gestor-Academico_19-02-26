package main;

import exception.MyException;
import gestor.Gestor;
import java.util.Scanner;
import modelo.Alumno;
import modelo.AsignaturaEmpresa;
import modelo.AsignaturaPresencial;
import modelo.Asignatura;
import utilidades.UtilidadesConsola;

public class Main {

    //PALETA COLORES//
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    //REGUEX//
    private static String validaOpcionMenu = "^[1-6]$"; //VALIDA QUE SOLAMENTE HAYA UN NUMERO ENTRE EL 1 Y EL 6
    private static String validaOpcionSubMenu = "^[1-3]$";
    private static String validaOpcionSeguir = "^[1-2]$";

    private static Scanner consola = new Scanner(System.in);

    /**
     * MUESTRA EL MENU DE LAS OPCIONES DISPONIBLES POR CONSOLA
     */
    public static void menu() {
        System.out.println("\n==========================================");
        System.out.println("                LOS ENLACES             ");
        System.out.println("==========================================");
        System.out.println("  1. >Alta de Alumno");
        System.out.println("  2. >Poner Notas");
        System.out.println("  3. >Informacion Detallada");
        System.out.println("  4. >Listado General");
        System.out.println("  5. >Alumnos que Promocionan");
        System.out.println("  6. >Salir");
        System.out.println("==========================================");
        System.out.print("<> Seleccione una opcion: ");
    }

    /**
     * PIDE OPCION POR CONSOLA, VALIDA QUE NO SEA NULO Y QUE SEA UN NUMERO ENTRE
     * EL 1-6, SI LA OPCION NO ES CORRECTA, DEVUELVE UNA EXCEPTION PARA INFORMAR
     * AL USUARIO
     *
     * @return int opcion
     */
    public static int pideOpcion() throws MyException {
        int opcion = 0;
        String opcionString = consola.nextLine(); //PIDE LA OPCION POR CONSOLA//
        if (opcionString != null && opcionString.matches(validaOpcionMenu)) { // SI NO ES NULO Y COINCIDE CON EL REGUEX
            opcion = Integer.parseInt(opcionString);
        } else {
            throw new MyException("Introduce una opcion valida (1-6)");
        }
        return opcion;
    }

    /**
     * EJECUTA TODAS LAS OPCIONES DEL MENU
     *
     * @param opcion
     */
    public static void logicaMenu(int opcion) {

        switch (opcion) {

            case 1 -> {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n==========================================");
                    System.out.println("                ALTA ALUMNO               ");
                    System.out.println("==========================================");
                    try {
                        Alumno A = Gestor.altaAlumno(UtilidadesConsola.pideStringConsola("<> Introduce nombre del alumno "));
                        System.out.println(ANSI_VERDE + "[ALUMNO CREADO CON EXITO]" + ANSI_RESET);
                        subMenuAltaAlumno();
                        logicaSubMenu(pideOpcionSubMenu(), A); //SUBMENU//
                    } catch (MyException exc) {
                        System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                    }
                }
            }
            case 2 -> {

                System.out.println("opcion2");
            }
            case 3 -> {

                System.out.println("opcion3");
            }
            case 4 -> {

                System.out.println("opcion4");
            }
            case 5 -> {

                System.out.println("opcion5");
            }
            case 6 -> {

                System.out.println("SALIR");
            }

        }
    }
//                                                       javadoc

    public static void subMenuAltaAlumno() {

        System.out.println("\n==========================================");
        System.out.println("                ASIGNATURAS               ");
        System.out.println("==========================================");
        System.out.println("  1. >Presencial");
        System.out.println("  2. >Empresa");
        System.out.println("  3. >Salir de Asignatura \n");
        System.out.print("<> Selecciona una asignatura para el alumno: ");

    }

    /**
     * PIDE OPCION POR CONSOLA, VALIDA QUE NO SEA NULO Y QUE SEA UN NUMERO ENTRE
     * EL 1-3, SI LA OPCION NO ES CORRECTA, DEVUELVE UNA EXCEPTION PARA INFORMAR
     * AL USUARIO
     *
     * @return int opcion
     */
    public static int pideOpcionSubMenu() throws MyException {
        int opcion = 0;
        String opcionString = consola.nextLine(); //PIDE LA OPCION POR CONSOLA//
        if (opcionString != null && opcionString.matches(validaOpcionSubMenu)) { // SI NO ES NULO Y COINCIDE CON EL REGUEX
            opcion = Integer.parseInt(opcionString);
        } else {
            throw new MyException("Introduce una opcion valida (1-3)");
        }
        return opcion;
    }

    public static void logicaSubMenu(int opcion, Alumno A) {
        boolean salir = false;
        while (!salir) {
            switch (opcion) {
                case 1 -> {

                    System.out.println("\n==========================================");
                    System.out.println("           ASIGNATURA PRESENCIAL          ");
                    System.out.println("==========================================");
                    String nombreAsignatura = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Asignatura ");
                    String id = UtilidadesConsola.pideStringConsola("<> Introduce el id de la Asignatura " + nombreAsignatura);
                    String nombreEmpresa = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Empresa ");
                    String notaFinalString = UtilidadesConsola.pideStringConsola("<> Introduce la nota final ");
                    try {
                        float notaFinal = testNotaFinal(notaFinalString);
                        Gestor.AltaAsiganaturaEmpresa(A, nombreEmpresa, notaFinal, id, nombreAsignatura);
                    } catch (MyException exc) {
                        System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                    }
                }

                case 2 -> {
                    boolean salirAE = false;
                    while (!salirAE) {
                        System.out.println("\n==========================================");
                        System.out.println("            ASIGNATURAS EMPRESA           ");
                        System.out.println("==========================================");
                        int opcionSeguir = 0;
                        System.out.println("Desea agregar asignatura a " + A.getNombre() + " ?");
                        System.out.println("  1. >Si");
                        System.out.println("  2. >No");
                        System.out.print("<> Introduce una opcion : ");
                        try {
                            opcionSeguir = pideOpcionSeguir();
                        } catch (MyException exc) {
                            System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                        }
                        if (opcionSeguir == 1) {
                            String nombreAsignatura = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Asignatura: ");
                            String id = UtilidadesConsola.pideStringConsola("<> Introduce el id de la Asignatura " + nombreAsignatura + ": ");
                            String nombreEmpresa = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Empresa: ");
                            String notaFinalString = UtilidadesConsola.pideStringConsola("<> Introduce la nota final: ");
                            try {
                                float notaFinal = testNotaFinal(notaFinalString);
                                Gestor.AltaAsiganaturaEmpresa(A, nombreEmpresa, notaFinal, id, nombreAsignatura);
                            } catch (MyException exc) {
                                System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                            }
                        }else {
                            salirAE = true;
                        }
                        
                    }

                }
                case 3 -> {
                    salir = true;

                }
            }
        }
    }

    public static int pideOpcionSeguir() throws MyException {
        int opcion = 0;
        String opcionString = consola.nextLine();
        if (opcionString != null && opcionString.matches(validaOpcionSeguir)) { 
            opcion = Integer.parseInt(opcionString);
        }else {
            throw new MyException("Introduce una opcion valida (1-2)");
        }
    
        return opcion;
    }

    /**
     * VALIDA QUE LA NOTA INTRODUCIDA NO SEA NULL Y SEA UN FLOAT, ADEMAS PASA EL
     * STRING A FLOAT, DEVUELVE UNA EXCEPTION SI NO SE PUEDE CASTEAR A FLOAT
     *
     *
     * @return int numPracticas
     */
    public static float testNotas(String notas) throws MyException {
        float nota = 0;
        if (notas != null) {
            try {
                nota = Float.parseFloat(notas);
            } catch (NumberFormatException exc) {
                throw new MyException("Debes introducir notas validad (1-10)");
            }

        }
        return nota;
    }

    /**
     * VALIDA QUE LA PRACTICA INTRODUCIDA NO SEA NULL Y SEA UN NUMERO, ADEMAS
     * PASA EL STRING A INT, DEVUELVE UNA EXCEPTION SI NO SE PUEDE PASAR A INT
     *
     *
     * @return int numPracticas
     */
    public static int testPracticas(String practicas) throws MyException {
        int numPracticas = 0;
        if (practicas != null) {
            try {
                numPracticas = Integer.parseInt(practicas); //PASA DE STRING A INT//
            } catch (NumberFormatException exc) {
                throw new MyException("Debes introducir el (NUMERO) de practicas");
            }

        }
        return numPracticas;
    }

    /**
     * VALIDA QUE LA NOTAFINAL INTRODUCIDA NO SEA NULL Y SEA UN FLOAT, ADEMAS
     * PASA EL STRING A FLOAT, DEVUELVE UNA EXCEPTION SI NO SE PUEDE CASTEAR A
     * FLOAT
     *
     *
     * @return int numPracticas
     */
    public static float testNotaFinal(String notas) throws MyException {
        float notaFinal = 0;
        if (notas != null) {
            try {
                notaFinal = Float.parseFloat(notas); //PASA DE STRING A FLOAT//
            } catch (NumberFormatException exc) {
                throw new MyException("Debes introducir una nota valida (1,0-10,0)");
            }

        }
        return notaFinal;
    }

    public static void main(String[] args) {

        boolean salir = false;
        while (!salir) {
            menu();
            try {
                logicaMenu(pideOpcion());
            } catch (MyException exc) {
                System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage());
            }
        }

    }

}
