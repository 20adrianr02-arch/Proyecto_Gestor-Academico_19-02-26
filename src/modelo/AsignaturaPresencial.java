package modelo;

import exception.MyException;

public class AsignaturaPresencial extends Asignatura { //EXTENDS HEREDA DE ASIGNATURA//

    private int practicas;
    private float notas[];

    public AsignaturaPresencial(int practicas, String id, String nombre) throws MyException {
        super(id, nombre);
        setPractica(practicas);
        this.notas = new float[practicas]; //INICIALIZO EL ARRAY EN EL CONTRUCTOR, Y LE ASIGNO DIRECTAMENTE LA CANTIDAD DE NOTAS//
    }
    
    /**
     * VERIFICA QUE LA NOTA SEA ENTRE 1 Y 10, SI NO CUMPLE ESTA CONDICION
     * CONDICION DEVUELVE UNA EXCEPTION, Y SI LA CUMPLE DEVUELVE LA NOTA VALIDA
     *
     * @param int practicas
     * @throws MyException
     */
    public float validaNota(float nota) throws MyException {
        if (nota >= 1 && nota <= 10) {
            return nota;
        } else {
            throw new MyException("La nota introducida, no es valida (1-10)");
        }
    }
    
    /**
     * AGREGA NOTA YA VALIDADA EN EL ARRAY
     * @param float nota
     * @param int posicion 
     */
    public void agregaNotaAsignatura (float nota, int posicion ) throws MyException{
        notas[posicion] = validaNota(nota);
    }
    
    //SETTERS//
    /**
     * VERIFICA QUE LAS PRACTICAS ESTEN ENTRE 1 Y 14, SI NO CUMPLE ALGUNA
     * CONDICION DEVUELVE UNA EXCEPTION
     *
     * @param int practicas
     * @throws MyException
     */
    public void setPractica(int practica) throws MyException {
        if (practica >= 1 && practica <= 14) {
            this.practicas = practica;
        } else {
            throw new MyException("La cantidad de practicas, no es valida (1-14)");
        }
    }

    /**
     * VALIDA QUE LAS NOTAS SEAN MAYOR QUE 4, Y LA MEDIA DE TODAS LAS NOTAS DE
     * LAS PRTACTICAS SEA MAYOR QUE 5, SI NO SE CUMPLE ESTA RESTRICCION DUEVUELE
     * FALSE(NO APROBADO)
     *
     * @return boolean aprobado
     */
    public boolean estaAprobado() {
        boolean aprobado = true;
        float media = 0;
        float nota = 0;
        for (int i = 0; i < this.practicas; i++) { //RECORRE EL ARRAY DE NOTAS Y LAS SUMA PARA LUEGO SACAR LA MEDIA//
            nota = notas[i];
            media = media + nota;
            if (nota < 4) { //VALIDA LA NOTA DE LAS PRACTICAS SEA MAYOR QUE 4 PARA APROBAR
                aprobado = false;
            }
        }
        if (media / this.practicas < 5) { //VALIDA QUE LA MEDIA DE LAS PRACTICAS SEA MAYOR QUE 5 PARA APROBAR//
            aprobado = false;
        }
        return aprobado;
    }

    //GETTERS//
    public int getPracticas() {
        return practicas;
    }

    public float[] getNotas() {
        return notas;
    }
    
    /**
     * LLAMA AL TO STRING DE LA CLASE PADRE, Y SE UNIFICAN AMBOS TO STRINGS
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " AsignaturaPresencial{" + "practicas=" + practicas + ", notas=" + notas + '}';
    }

}
