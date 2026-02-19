package main;

import exception.MyException;
import java.util.Scanner;
import modelo.AsiganturaEmpresa;
import modelo.AsignaturaPresencial;
import modelo.Asignatura;

public class Main {
    
    private static Scanner consola = new Scanner(System.in); 
    
    /**
     * MUESTRA EL MENU DE LAS OPCIONES DISPONIBLES POR CONSOLA
     */
    public static void menu() {
        System.out.println("\n==========================================");
        System.out.println("     SISTEMA DE GESTION ACADEMICA     ");
        System.out.println("==========================================");
        System.out.println("  1. <> Alta de Alumno");
        System.out.println("  2. <> Poner Notas");
        System.out.println("  3. <> Informacion Detallada");
        System.out.println("  4. <> Listado General");
        System.out.println("  5. <> Alumnos que Promocionan");
        System.out.println("  6. <> Salir");
        System.out.println("==========================================");
        System.out.print("<> Seleccione una opcion: ");
    }
    
    public static int pideOpcion () throws MyException{
        String reguex = "[1-7]1";
        int opcion = 0;
        String opcionString = consola.nextLine(); //PIDE LA OPCION POR CONSOLA//
        if(opcionString != null){
            try {
                opcion = Integer.parseInt(opcionString);
            } catch (NumberFormatException exc) {
                throw new MyException(" - Introduce una opcion valida - ");
            }
        }
        if(opcion>=1 && opcion<=6){
            
        }
        
        return opcion;
    }

    public static void main(String[] args) {
        //HASTA QUE NO HAYA UN RETURN DENTRO, NO PARA EL BUCLE//
        while (true){ 
            menu();
            return;
        }
        
        /**
        try {
            //PRUEBA ASIGANTURAS//
            AsiganturaEmpresa AE1 = new AsiganturaEmpresa("Hiberus", 6f, "H234", "Programacion");
            AsignaturaPresencial AP1 = new AsignaturaPresencial(5, "E234", "Programacion");
            System.out.println(AE1);
            System.out.println(AP1);
        } catch (MyException exc) {
            System.out.println(exc.getMessage());
        }*/

    }

}
