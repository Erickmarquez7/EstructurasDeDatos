//Erick Bernal Márquez. 317042522
//8 de octubre de 2020
package fciencias.edatos.practica01;

/**
 * Clase para excepciones del tamaño de una matriz.
 */
public class DifferentSizeException extends Exception {

    /**
     * Constructor vacío.
     */
    public DifferentSizeException() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public DifferentSizeException(String mensaje) {
        super(mensaje);
    }
}
