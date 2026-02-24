
package modelo;

import exception.MyException;

public abstract class Asignatura implements Comparable<Asignatura>{
    
    //REGUX CONSTANTES PARA Y MEJORAR RENDIMIENTO//   
    private static final String REGEX_ID = "^[a-zA-Z][a-zA-Z0-9]{3}$"; //VALIDA QUE EMPIECE POR UNA LETRA, Y QUE CONTENGA 3 CARACTERES, NO PUEDE SUPERAR MAS DE 4 DIGITOS// 
    private static final String REGEX_NOMBRE = "^[A-Za-zñÑáéíóúÁÉÍÓÚ ]{1,20}$";//PERMITE SOLO LETRAS MAYUSCULAS Y MINUCULAS,TILDES,Ñ Y ESPACIOS.PERMITE ENTRE 1 Y 20 LETRAS
    
    private String id;
    private String nombre;

    public Asignatura(String id, String nombre) throws MyException {
        setId(id);
        setNombre(nombre);
    }
    
    public abstract boolean estaAprobado(); //OBLIGA A LAS CLASES HIJAS A IMPLEMENTAR EL METODO
  
    //SETTERS//
    /**
     * INTRODUCE EL ID, VALIDA QUE CUMPLA LAS RESTRICCIONES DEL REGUEX, Y DEVUELVE UNA EXCEPTION SI NO ES CORRECTO
     * @param String id
     * @throws MyException 
     */
    public void setId(String id) throws MyException {
        if(id != null && id.matches(REGEX_ID)){ //COMPRUEBA QUE EL ID CUMPLE CON LOS PARAMETROS DE REGUEX, SI LOS CUMPLE TRUE, SI NO FALSE//
            this.id = id;
        }else {
            throw new MyException("El id de asignatura, no es valido");//MUESTRA AL USUARIO EL FALLO//
        }
    }
    
    /**
     * INTRODUCE EL NOMBRE, VALIDA QUE CUMPLA LAS RESTRICCIONES DEL REGUEX, Y DEVUELVE UNA EXCEPTION SI NO ES CORRECTO
     * @param String nombre
     * @throws MyException 
     */
    public void setNombre(String nombre) throws MyException {
        if(nombre != null && nombre.matches(REGEX_NOMBRE)){ //TAMBIEN VERIFICA QUE NO SEA NULL
            this.nombre = nombre;
        }else {
            throw new MyException("El nombre de asignatura, no es valido");
        }
        
    }
    
    //GETTERS//
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    @Override
    public int compareTo(Asignatura otro) {
        if (otro == null) {
            return 1;
        } // A LOS NULOS LOS MANDA AL FINAL DE LA LISTA //
        return this.id.compareTo(otro.getId());
    }
    
    
    
    
    
}
