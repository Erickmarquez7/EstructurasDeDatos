//Erick Bernal Márquez        317042522
//Practica 4                  Nov/2020

package fciencias.edatos.practica04;

/**
 * Implementación de una cola con listas simplemente
 * ligadas.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020.
 * @since Estructuras de datos 2021-1.
 */
public class Cola<T> implements TDACola<T>{

	/** Lista que toma el comportamiento de una cola. */
	private TDALista<T> cola = new ListaLigada<>();

	@Override
	public void enqueue(T t){
		try{
			cola.add(cola.size(), t);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

    @Override
    public T dequeue(){
    	if(cola.isEmpty())
    		return null;
    	T eliminado = null;
    	try{
    		eliminado = cola.remove(0);
    	} catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return eliminado;
    }

    @Override
    public T first(){
    	if(cola.isEmpty())
    		return null;
    	T eliminado = null;
    	try{
    		eliminado = cola.get(0);
    	} catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	return eliminado;
    }

    @Override
    public int size(){
    	return cola.size();
    }

    @Override
    public boolean isEmpty(){
    	return cola.isEmpty();
    }

    @Override
    public void clear(){
    	cola.clean();
    }
    
    public void muestra(){
    	if(cola.isEmpty())
    		System.out.println("Cola vacìa");
    	else{
    	cola.muestra();
    	}
    }

}
