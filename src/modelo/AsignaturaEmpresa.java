package modelo;

import exception.MyException;

public class AsignaturaEmpresa extends Asignatura {
    private static final String REGUEX_NOMBRE_EMPRESA = "^[A-Za-zñÑáéíóúÁÉÍÓÚ0-9 ]{1,20}$"; //PERMITE SOLO LETRAS MAYUSCULAS Y MINUCULAS,TILDES,Ñ Y ESPACIOS.PERMITE ENTRE 1 Y 20 LETRAS
    private String nombreEmpresa;
    private float notaFinal;

    public AsignaturaEmpresa(String nombreEmpresa, float notaFinal, String id, String nombre) throws MyException {
        super(id, nombre);
        setNombreEmpresa(nombreEmpresa);
        setNotaFinal(notaFinal);
    }
    

    //SETTERS//
    /**
     * VALIDA QUE LA NOTA FINAL SEA MAYOR QUE CERO Y MENOR QUE DIEZ, SI NO DEVUELVE UNA EXCEPTION
     * @param float notaFinal
     * @throws MyException 
     */
    public void setNotaFinal(float notaFinal) throws MyException {
        if (notaFinal >= 0 && notaFinal <= 10) {
            this.notaFinal = notaFinal;
        } else {
            throw new MyException("Nota final introducida no es valida");
        }

    }

    /**
     * INTRODUCE EL NOMBRE, VALIDA QUE CUMPLA LAS RESTRICCIONES DEL REGUEX, Y
     * DEVUELVE UNA EXCEPTION SI NO ES CORRECTO
     *
     * @param String nombre
     * @throws MyException
     */
    public void setNombreEmpresa(String nombreEmpresa) throws MyException {
        if (nombreEmpresa != null && nombreEmpresa.matches(this.REGUEX_NOMBRE_EMPRESA)) { //TAMBIEN VERIFICA QUE NO SEA NULL
            this.nombreEmpresa = nombreEmpresa;
        } else {
            throw new MyException("El nombre de la empresa, no es valido");
        }

    }
    
    /**
     * VALIDA QUE LA NOTA FINAL, SEA MAYOR QUE 5, SI NO LO ES, DEVUELVE FALSE
     * @return boolean
     */
    @Override
    public boolean estaAprobado() {
        boolean aprobado = true;
        if (notaFinal<5){
            aprobado = false;
        }
        return aprobado;
    }
    

    //GETTERS//
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    @Override
    public String toString() {
        return super.toString() + "AsiganturaEmpresa{" + "nombreEmpresa=" + nombreEmpresa + ", notaFinal=" + notaFinal + '}';
    }
    
    

}
