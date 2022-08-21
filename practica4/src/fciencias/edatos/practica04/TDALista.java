//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;


public interface TDALista<T>{

    /**
     * Devuelve un valor lógico que indica si la lista
     * tiene elementos o no. 
     */
    public boolean isEmpty();
    
    /**
     * Devuelve el número de elementos de la lista.
     */
    public int size();    
    
    /**
     * Devuelve el objeto que se encuentra en la posición
     * i-ésima de la lista.     
     */
    public T get(int i) throws IndexOutOfBoundsException;  
    
    /**
     * Inserta el objeto obj en la i-ésima posición de la lista.
     */
    public void add(int i, T obj) throws IndexOutOfBoundsException;
    
    /**
     * Sustituye el objeto en la i-ésima posición de la lista por el objeto obj
     * y regresa el objeto sustituido.
    */
    public T set(int i, T obj) throws IndexOutOfBoundsException;
    
    /**
     * Elimina y devuelve el elemento ubicado en la i-ésima posición de la lista.     
     */
    public T remove(int i) throws IndexOutOfBoundsException;  

    /**
    * No permite vaciar la lista.
    */
    public void clean();
    
    public void muestra();
    
}
