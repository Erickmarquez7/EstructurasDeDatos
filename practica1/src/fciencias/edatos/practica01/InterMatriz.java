//Erick Bernal Márquez
//8 de octubre de 2020
//Interfaz para definir lo métodos de una Matriz
package fciencias.edatos.practica01;

public interface InterMatriz{
    
    
    /** 
     * Indica el numero de renglones de 
     * una Matriz.
     * @return el nùmero de renglones.
     */ 
    public int getRenglones(Matriz a); //O sea, así ---------

    
    /**
     * Indica el número de columnas de
     * una Matriz.
     * @return el número de columnas.
     */
    public int getColumnas(Matriz b); //O sea, así ||||||||||||||
    

    /**
     * Obtiene el elemento en la 
     * posición x, y.
     * @return la posición.
     */
    public double getElemento(Matriz a, int x, int y);

    
    /**
     * Modicica el elemento en la 
     * posición x, y.
     */
    public void modifica(Matriz a, int posx, int posy, int cambio);


    /**
     * Suma de mattrices, suma entrada
     * por entrada de cada Matriz.
     * @return la suma de Matrices.
     */
    public Matriz suma(Matriz a, Matriz b) throws DifferentSizeException;

    
    /**
     * Multiplica una Matriz por un escalar.
     * @return la Matriz multiplicada.
     */
    public Matriz multiEsc(int a, Matriz b);


    /** 
     * Multiplicación de matrices de 
     * acuerdo a la fórmula.
     * @return la matriz multiplicada.
     */
    public Matriz multiMat(Matriz a, Matriz b) throws DifferentSizeException;


    /** 
     * Transforma una Matriz a su transpuesta.
     * @return la Transpuesta de una Matriz.
     */
    public Matriz trans(Matriz a);

    
    /**
     * Verifica si una matriz es igual a otra.
     * @return true si lo es, false en otro caso.
     */
    public boolean esIgual(Matriz a, Matriz b);
    
}
