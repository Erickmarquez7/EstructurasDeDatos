//Erick Bernal Márquez		317042522
//Practica Rep: Pilas		enero/2021
package fciencias.edatos.practica08;

import java.util.EmptyStackException;

/**
 * Implementación de una pila con arreglos.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020.
 * @since Estructuras de datos 2021-1.
 */
public class PilaArreglo<T> implements TDAPila<T>{

    /** Arreglo con propiedades de lista. */
    T[] arreglo;

    /** Numero maximo de elementos. */
    private final int MAX_NUM = 255;

    /** Tope de la pila. */
    private int tope;

    public T[] getArreglo(){
    	return arreglo;
    }
    /**
    * Crea una nueva pila.
    * @param longitud la longitud de la pila.
    */
    public PilaArreglo(int longitud){
        if(longitud<0){
            arreglo = (T[]) new Object[MAX_NUM];
        } else{
            arreglo = (T[]) new Object[longitud];
        }
        tope = -1;
    }

    /**
    * Crea una nueva pila.
    */
    public PilaArreglo(){
        arreglo = (T[]) new Object[MAX_NUM];
        tope = -1;
    }
    
    @Override
    public void push(T t) throws IllegalStateException{
        if(arreglo.length == tope+1){
            throw new IllegalStateException("Pila llena");
        }

        arreglo[++tope] = t;
    }

    @Override
    public T pop() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        T eliminado = arreglo[tope];
        arreglo[tope--] = null;
        return eliminado;
    }

    @Override
    public T top() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        return arreglo[tope];
    }

    @Override
    public boolean isEmpty(){
        return tope<0;
    }

    @Override
    public void clear(){
        arreglo = (T[]) new Object[MAX_NUM];
        tope = -1;
    }
    
    public String toString(){
    	String s = "";
    	for(int i = arreglo.length-1; i>=0 ;i--)
    	    s+=arreglo[i]+"\n";
    	return s;
    }   
}
