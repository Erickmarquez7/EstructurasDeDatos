//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;


/**
 * Tipo de Datos Abstracto de una cola.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020.
 * @since Estructuras de datos 2021-1.
 */
public interface TDACola<T>{

    /**
     * Agrega un nuevo elemento al final de la cola.
     * @param t el elemento a agregar.
     */
    public void enqueue(T t);

    /**
    * Regresa y elimina el elemento en el principio de la cola.
    * @return el elemento en el tope de la pila.
    */
    public T dequeue();

    /**
    * Regresa el elemento en el principio de la cola. Sin eliminarlo.
    * @return el elemento en el principio de la cola. Null si no hay elementos.
    */
    public T first();

    /**
    * Regresa la cantidad de elementos contenidos.
    * @return la cantidad de elementos.
    */
    public int size();

    /**
    * Verifica si la cola contiene elementos.
    * @return <i>true</i> si la cola es vacía, <i>false</i> en otro caso.
    */
    public boolean isEmpty();

    /**
    * Elimina todos los elementos contenidos en la cola.
    */
    public void clear();
    
    public void muestra();

}
