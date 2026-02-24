package utilidades;

import java.util.Scanner;

public class UtilidadesConsola {
    
    private static  Scanner consola = new Scanner (System.in);

    /**
     * PIDE UN STRING POR CONSOLA Y LO DEVUELVE 
     * @return 
     */
    public static String pideStringConsola(String mensaje) {
        System.out.print(mensaje);
        return consola.nextLine();
    }
    
    

}
