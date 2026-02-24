
package modelo;

import exception.MyException;
import java.util.Set;
import java.util.TreeSet;

public class Alumno {
    
    //REGUEX COMO CONSTANTE PARA MEJORAR RENDIMIENTO//
    private static final String REGEX_NOMBRE = "^[A-Za-zñÑáéíóúÁÉÍÓÚ ]{1,25}$";//PERMITE SOLO LETRAS MAYUSCULAS Y MINUCULAS,TILDES,Ñ Y ESPACIOS.PERMITE ENTRE 1 Y 25 LETRAS
    
    private static int contador = 0;
    private String expediente;
    private String nombreAlumno;
    private Set<Asignatura> listaAsiganturas;

    public Alumno(String nombre) throws MyException {
        setNombre(nombre);
        creaExpediente();
        listaAsiganturas = new TreeSet<Asignatura>();
    }
    
    /**
     * GENERA EL EXPEDIENTE DEL ALUMNO, CON EL CONTADOR INCREMNETAL CADA VEZ QUE SE CREA UN ALUMNO, EL EXPEDIENTE SE GENERA 
     * CON LAS CONDICIONES ESTABLECIDAS POR EL ENUNCIADO MEDIENTE String.format();
     */
    private void creaExpediente() {
        String expediente = String.format("ENLACES_%06d", this.contador); //STRING FORMAT, ENLACES_%06d ASI LE DOY FORMATO AL EXPIENDTE CON 6 CEROS Y LA d REPRESENTA LA VARIABLE// 
        this.expediente = expediente;
        contador++;
    }

    /**
     * INTRODUCE EL NOMBRE, VALIDA QUE CUMPLA LAS RESTRICCIONES DEL REGUEX, Y DEVUELVE UNA EXCEPTION SI NO ES CORRECTO
     * @param String nombre
     * @throws MyException 
     */
    public void setNombre(String nombre) throws MyException {
        if(nombre != null && nombre.matches(REGEX_NOMBRE)){ //TAMBIEN VERIFICA QUE NO SEA NULL
            this.nombreAlumno = nombre;
        }else {
            throw new MyException("El nombre del alumno, no es valido");
        }
        
    }
   
    /**
     * AGREGA LA ASIGATURA AL ARRAY DEVOLVIENDO UN TRUE, POR EL CONTRARIO, SI NO LA PUEDE AGREGAR (DUPLICADOS) DEVUELVE FALSE, ESTO LO RECIBIRA EL MAIN PARA NOTIFICAR AL USUARIO
     * @param Asignatur asignatura
     * @return boolean 
     */
    public void llenaListaAsiganturas (Asignatura asignatura){
        listaAsiganturas.add(asignatura); //TRUE SI HA REALIZADO EL ADD Y FALSE SI HAY UN PROBLEMA (estan duplicados) Y NO LO PUDO AGREGAR//
    }
    
    
    //GETTERS//
    public String getExpediente() {
        return expediente;
    }

    public String getNombre() {
        return nombreAlumno;
    }

    public Set<Asignatura> getListaAsiganturas() {
        return listaAsiganturas;
    }

    @Override
    public String toString() {
        return "Nombre de alumno " + nombreAlumno + "\nExpediente = " + expediente + "\nListaAsiganturas=" + listaAsiganturas;
    }
    
    
            
}
