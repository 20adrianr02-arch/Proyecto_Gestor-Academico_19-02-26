package main;

import exception.MyException;
import gestor.GestorMatricula;
import java.util.Iterator;
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
    public static boolean logicaMenu(int opcion) {
        boolean salirMenu = false;
        switch (opcion) {
            case 1 -> {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n==========================================");
                    System.out.println("                ALTA ALUMNO               ");
                    System.out.println("==========================================");
                    try {
                        Alumno a = GestorMatricula.altaAlumno(UtilidadesConsola.pideStringConsola("<> Introduce nombre del alumno: "));
                        logicaSubMenu(a);
                        salir = true;
                    } catch (MyException exc) {
                        System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                    }
                }
            }
            case 2 -> {
                System.out.println("\n==========================================");
                System.out.println("              INGRESAR NOTA               ");
                System.out.println("==========================================");
                try {
                    Alumno a = GestorMatricula.buscaAlumno(UtilidadesConsola.pideStringConsola("Introduce el expediente del Alumno(ENLACES_XXXXXX): \n<> ")); //PIDE Y BUSCA EL ALUMNO POR EXPEDIENTE
                    System.out.println(ANSI_VERDE + "[ALUMNO ENCONTRADO CON EXITO]" + ANSI_RESET + "\n");
                    Iterator<Asignatura> it = a.getListaAsiganturas().iterator();//CREO EL ITERATOR Y LE ASIGNO LA COLECCION DE ASIGANTURAS//
                    while (it.hasNext()) {
                        Asignatura asig = it.next();//GUARDA LA ASIGANTURA DE LA COLECCION//
                        if (asig instanceof AsignaturaPresencial) { //IDENTIFICO LAS PRESENCIALES DE LAS DE EMPRESA//
                            System.out.println("Agrega las notas de asignatura " + asig.getNombre().toUpperCase() + " (total de practicas " + ((AsignaturaPresencial) asig).getPracticas() + ")");
                            subeNotas((AsignaturaPresencial) asig); //CASTEO A PRESENCIAL, PARA PODER RECORRER EL ARRAY DE NOTAS//
                            System.out.println(ANSI_VERDE + "[NOTAS DE " + asig.getNombre().toUpperCase() + " AGREGADAS CON EXITO] \n" + ANSI_RESET);
                        } else if (asig instanceof AsignaturaEmpresa) {
                            subeNotaFinal((AsignaturaEmpresa) asig); //CASTEO A EMPRESA
                            System.out.println(ANSI_VERDE + "[NOTA FINAL DE " + asig.getNombre().toUpperCase() + " AGREGADA CON EXITO]\n" + ANSI_RESET);
                        }
                    }
                } catch (MyException exc) {
                    System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                }

            }

            case 3 -> {
                System.out.println("\n==========================================");
                System.out.println("          INFORMACION DETALLADA           ");
                System.out.println("==========================================");
                try {
                    Alumno a = GestorMatricula.buscaAlumno(UtilidadesConsola.pideStringConsola("Introduce el expediente (ENLACES_XXXXXX): \n<>"));
                    String informacionDetallada = GestorMatricula.informacionAlumnoDetallada(a);
                    System.out.println(informacionDetallada);

                } catch (MyException exc) {
                    System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                }

            }

            case 4 -> {
                System.out.println("\n==========================================");
                System.out.println("              LISTADO GENERAL             ");
                System.out.println("==========================================");
                try {
                    String listado = GestorMatricula.informacionAlumnos();
                    System.out.println(listado);
                } catch (MyException exc) {
                    System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                }
            }

            case 5 -> {
                System.out.println("\n==========================================");
                System.out.println("                PROMOCIONAN               ");
                System.out.println("==========================================");
                try {
                    System.out.println(GestorMatricula.obtenerAlumnosPromocionan());
                } catch (MyException exc) {
                    System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                }

            }

            case 6 -> {
                System.out.println("\u001B[31m[SALISTE DEL PROGRAMA]\u001B[0m");
                salirMenu = true;
            }

        }
        return salirMenu;
    }

    /**
     * MUESTRA EL SUBMENU POR CONSOLA
     */
    public static void subMenuAltaAlumno(Alumno A) {

        System.out.println("\n==========================================");
        System.out.println("           ASIGNATURAS (" + A.getNombre().toUpperCase() + ")            ");
        System.out.println("==========================================");
        System.out.println("  1. >Agregar asignatura presencial");
        System.out.println("  2. >Agregar asignatura empresa");
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

    /**
     * EJECUTA EL SWITCH Y EL WHILE DEL SUBMENU, PIDE EL ALIUMNO AL CUAL SE LE
     * VAN A AGREGAR LAS ASIGANTURAS ADEMAS EJECUTA Y LLAMA A LAS FUNCIONES
     * PERTIENENTES DE CADA CASO, INCLUYE LA LOGICA DE EJECUCION DEL MENU
     *
     * @param Alumno A
     */
    public static void logicaSubMenu(Alumno a) { //A PARA GUARDAR LAS ASIGNATURAS CREADAS EN LA COLECCION//
        boolean salir = false;
        while (!salir) {
            subMenuAltaAlumno(a);
            try {
                int opcion = pideOpcionSubMenu();
                switch (opcion) {
                    case 1 -> {
                        System.out.println("\n==========================================");
                        System.out.println("           ASIGNATURA PRESENCIAL          ");
                        System.out.println("==========================================");
                        String nombreAsignatura = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Asignatura: ");
                        String id = UtilidadesConsola.pideStringConsola("<> Introduce el id de la Asignatura " + nombreAsignatura + ": ");
                        String practicasString = UtilidadesConsola.pideStringConsola("<> Introduce la cantidad de practicas: ");
                        try {
                            int practicas = testPracticas(practicasString);
                            AsignaturaPresencial ap1 = GestorMatricula.altaAsignaturaPresencial(a, practicas, id, nombreAsignatura);
                            System.out.println(ANSI_VERDE + "[ASIGNATURA AGREGADA CON EXITO]" + ANSI_RESET);
                        } catch (MyException exc) {
                            System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                        }
                    }

                    case 2 -> {
                        System.out.println("\n==========================================");
                        System.out.println("            ASIGNATURAS EMPRESA           ");
                        System.out.println("==========================================");
                        String nombreAsignatura = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Asignatura: ");
                        String id = UtilidadesConsola.pideStringConsola("<> Introduce el id de la Asignatura " + nombreAsignatura + ": ");
                        String nombreEmpresa = UtilidadesConsola.pideStringConsola("<> Introduce el nombre de la Empresa: ");
                        try {
                            float notaFinal = 1;
                            GestorMatricula.altaAsignaturaEmpresa(a, nombreEmpresa, notaFinal, id, nombreAsignatura);
                            System.out.println(ANSI_VERDE + "[ASIGNATURA AGREGADA CON EXITO]" + ANSI_RESET);
                        } catch (MyException exc) {
                            System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
                        }

                    }
                    case 3 -> {
                        if (a.getListaAsiganturas().isEmpty()) { //SI LA COLECCION DE ASIGNATURAS ESTA VACIA, VUELVE A PEDIR UNA ASIGNATURA//
                            throw new MyException("Debes matricular al menos una asignatura");
                        }
                        salir = true;

                    }
                }
            } catch (MyException e) {
                System.out.println(ANSI_ROJO + e.getMessage() + ANSI_RESET);
            }

        }
    }

    /**
     * RECORRE EL ARRAY DE NOTAS DE ASIGNATURA PRESENCIAL, PIDE AL USUARIO LAS
     * NOTAS POR CONSOLA Y SI NO SON CORRECTAS GESTIONA LA EXCEPTION
     *
     * @param AsignaturaPresencial as
     */
    public static void subeNotas(AsignaturaPresencial as) {
        int i = 0;
        int numPracticas = as.getPracticas();
        while (i < numPracticas) {//UTILIZO UN WHILE, POR SI LA NOTA NO ES VALIDA, NO AVANCE LA POSICION//
            try {
                String notaString = UtilidadesConsola.pideStringConsola("<> Introduce nota para la practica N-" + (i + 1) + ": ");
                float nota = testNotas(notaString);
                as.agregaNotaAsignatura(nota, i);
                System.out.println(ANSI_VERDE + "[NOTA " + (i + 1) + " REGISTRADA CON EXITO]" + ANSI_RESET + "\n");
                i++;
            } catch (MyException exc) {
                System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
            }
        }
    }

    /**
     * ASIGNA LA NOTA FINAL DE LA ASIGANTURA EMPRESA DEVUELVE UNA EXCEPTION SI
     * HAY ALGUN FALLO, HASTA QUE LA NOTA FINAL, NO SEA CORRECTA NO SALE DEL
     * BUCLE
     *
     * @param AsignaturaEmpresa as
     * @param float notaFinal
     */
    public static void subeNotaFinal(AsignaturaEmpresa as) {
        boolean correcto = false;
        while (!correcto) {
            try {
                String notaString = UtilidadesConsola.pideStringConsola("<> Agrega la nota final de " + as.getNombre().toUpperCase() + ": ");
                float nota = testNotaFinal(notaString);
                as.setNotaFinal(nota);
                correcto = true;
            } catch (MyException exc) {
                System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage() + ANSI_RESET);
            }
        }
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
                throw new MyException("Debes introducir una nota valida (1-10)");
            }

        }
        return notaFinal;
    }

    /**
     * CREA 3 ALUMNOS DE PRUEBA DE FORMA MANUAL, COMO PIDE EL ENUNCIADO DEL
     * PROYECTO
     */
    public static void creacionAlumnosPrueba() throws MyException {
        //CREACION DE ALUMNOS//
        Alumno alumno1 = GestorMatricula.altaAlumno("Pedro Fernandez");
        Alumno alumno2 = GestorMatricula.altaAlumno("Sofia Rodriguez");
        Alumno alumno3 = GestorMatricula.altaAlumno("Camila Motos");

        //ASIGANCION DE ASIGNATURAS EMPRESA//
        Asignatura alumno1_asig1 = new AsignaturaEmpresa("Hiberus", 7.7f, "P123", "Programacion");
        alumno1.llenaListaAsiganturas(alumno1_asig1);

        AsignaturaEmpresa alumno2_asig1 = new AsignaturaEmpresa("Hiberus", 9.4f, "P321", "Programacion");
        alumno2.llenaListaAsiganturas(alumno2_asig1);

        AsignaturaEmpresa alumno2_asig2 = new AsignaturaEmpresa("CAIXA", 8f, "B321", "Base de Datos");
        alumno2.llenaListaAsiganturas(alumno2_asig2);

        AsignaturaEmpresa alumno3_asig1 = new AsignaturaEmpresa("Hiberus", 3f, "P512", "Programacion");
        alumno3.llenaListaAsiganturas(alumno3_asig1);

        //ASIGANCION DE ASIGNATURAS PRESENCIAL//
        AsignaturaPresencial alumno1_asig2 = new AsignaturaPresencial(5, "P523", "Programacion");
        alumno1.llenaListaAsiganturas(alumno1_asig2);
        alumno1_asig2.agregaNotaAsignatura(5, 0);
        alumno1_asig2.agregaNotaAsignatura(6, 1);
        alumno1_asig2.agregaNotaAsignatura(6, 2);
        alumno1_asig2.agregaNotaAsignatura(3, 3);
        alumno1_asig2.agregaNotaAsignatura(4, 4);

        AsignaturaPresencial alumno1_asig3 = new AsignaturaPresencial(9, "P361", "Programacion");
        alumno1.llenaListaAsiganturas(alumno1_asig3);
        alumno1_asig3.agregaNotaAsignatura(3, 0);
        alumno1_asig3.agregaNotaAsignatura(7, 1);
        alumno1_asig3.agregaNotaAsignatura(5, 2);
        alumno1_asig3.agregaNotaAsignatura(4, 3);
        alumno1_asig3.agregaNotaAsignatura(9, 4);
        alumno1_asig3.agregaNotaAsignatura(3, 5);
        alumno1_asig3.agregaNotaAsignatura(2, 6);
        alumno1_asig3.agregaNotaAsignatura(7, 7);
        alumno1_asig3.agregaNotaAsignatura(8, 8);

        AsignaturaPresencial alumno2_asig3 = new AsignaturaPresencial(4, "B751", "Base de Datos");
        alumno2.llenaListaAsiganturas(alumno2_asig3);
        alumno2_asig3.agregaNotaAsignatura(5, 0);
        alumno2_asig3.agregaNotaAsignatura(9, 1);
        alumno2_asig3.agregaNotaAsignatura(6, 2);
        alumno2_asig3.agregaNotaAsignatura(7, 3);

        AsignaturaPresencial alumno3_asig2 = new AsignaturaPresencial(3, "P872", "Programacion");
        alumno3.llenaListaAsiganturas(alumno3_asig2);
        alumno3_asig2.agregaNotaAsignatura(3, 0);
        alumno3_asig2.agregaNotaAsignatura(6, 1);
        alumno3_asig2.agregaNotaAsignatura(8, 2);
    }

    public static void main(String[] args) {
        boolean salir = false;
        try {
            creacionAlumnosPrueba(); //CREACION DE ALUMNOS DE PRUEBA//
        } catch (MyException ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        while (!salir) { //FUNCIONAMIENTO DEL PROYECTO //
            menu();
            try {
                salir = logicaMenu(pideOpcion());
            } catch (MyException exc) {
                System.out.println(ANSI_ROJO + "[ERROR] " + exc.getMessage());
            }
        }
    }

}
