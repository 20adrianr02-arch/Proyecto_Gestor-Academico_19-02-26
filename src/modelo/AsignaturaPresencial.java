package modelo;

import exception.MyException;

public class AsignaturaPresencial extends Asignatura { //EXTENDS HEREDA DE ASIGNATURA//

    private int practicas;
    private float notas[];

    public AsignaturaPresencial(int practicas, String id, String nombre) throws MyException {
        super(id, nombre);
        setPracticas(practicas);
        this.notas = new float[practicas]; //INICIALIZO EL ARRAY EN EL CONTRUCTOR, Y LE ASIGNO DIRECTAMENTE LA CANTIDAD DE NOTAS//
    }

    /**
     * VALIDA QUE LA NOTA INTRODUCIDA NO SEA NULL Y SEA UN FLOAT, ADEMAS PASA EL
     * STRING A FLOAT, DEVUELVE UNA EXCEPTION SI NO SE PUEDE CASTEAR A FLOAT
     *
     *
     * @return int numPracticas
     */
    public float testNotas(String notas) throws MyException {
        float nota = 0;
        if (notas != null) {
            try {
                nota = Float.parseFloat(notas); //PASA DE STRING A FLOAT//
            } catch (NumberFormatException exc) {
                throw new MyException("Debes introducir una nota valida (1,0-10,0)");
            }

        }
        return nota;
    }

    /**
     * AGREGA TODAS LAS NOTAS, SI NO ESTAN DENTRO DE PARAMETRO INDICADO
     * INTRODUCE UN CERO
     *
     * @param float nota
     * @param int practicas
     */
    public void agregaNota(float nota, int practicas) {
        for (int i = 0; i < practicas; i++) {
            if (nota >= 1 && nota <= 10) {
                notas[i] = nota;

            } else {
                notas[i] = 0;
            }
        }

    }

    /**
     * VALIDA QUE LA PRACTICA INTRODUCIDA NO SEA NULL Y SEA UN NUMERO, ADEMAS
     * PASA EL STRING A INT, DEVUELVE UNA EXCEPTION SI NO SE PUEDE PASAR A INT
     *
     *
     * @return int numPracticas
     */
    public int testPracticas(String practicas) throws MyException {
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

    //SETTERS//
    /**
     * VERIFICA QUE LAS PRACTICAS ESTEN ENTRE 1 Y 14, SI NO CUMPLE ALGUNA
     * CONDICION DEVUELVE UNA EXCEPTION
     *
     * @param int practicas
     * @throws MyException
     */
    public void setPracticas(int practicas) throws MyException {
        if (practicas >= 1 && practicas <= 14) {
            this.practicas = practicas;
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
