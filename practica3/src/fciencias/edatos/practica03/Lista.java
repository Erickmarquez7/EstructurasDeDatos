//Erick Bernal Márquez        317042522
//Practica 3                  Nov/2020
package fciencias.edatos.practica03;

import java.lang.IndexOutOfBoundsException;

/**
* Implementación de una lista doblemente ligada.
* @author Emmanuel Cruz Hernández.
* @version 1.0 2020.
* @since Estructuras de datos 2021-1.
*/
public class Lista<T> implements TDALista<T>{

	/**
	* Clase nodo para listas doblemente ligadas.
	*/
	public class NodoDoble<T>{

		/** Elemento a almacenar. */
		private T elemento;

		/** Nodo anterior. */
		private NodoDoble<T> anterior;

		/** Nodo siguiente. */
		private NodoDoble<T> siguiente;

		/**
		* Crea un nuevo nodo doble.
		* @param elemento el elemento a almacenar en el nodo.
		*/
		public NodoDoble(T elemento){
			this.elemento = elemento;
		}

		/**
		* Obtiene el elemento.
		* @return el elemento almacenado en el nodo.
		*/
		public T getElemento(){
			return elemento;
		}

		/**
		* Obtiene el nodo siguiente.
		* @return la referencia del nodo siguiente.
		*/
		public NodoDoble<T> getSiguiente(){
			return siguiente;
		}

		/**
		* Obtiene el nodo anterior.
		* @return la referencia del nodo anterior.
		*/
		public NodoDoble<T> getAnterior(){
			return anterior;
		}

		/**
		* Modifica el valor almacenado en el nodo.
		* @param elemento el nuevo elemento a almacenar en el nodo.
		*/
		public void setElemento(T elemento){
			this.elemento = elemento;
		}

		/**
		* Modifica el nodo siguiente.
		* @param siguiente el nuevo nodo siguiente.
		*/
		public void setSiguiente(NodoDoble<T> siguiente){
			this.siguiente = siguiente;
		}

		/**
		* Modifica el nodo anterior.
		* @param anterior el nuevo nodo anterior.
		*/
		public void setAnterior(NodoDoble<T> anterior){
			this.anterior = anterior;
		}
	}

	/** La cabeza de la lista. */
	private NodoDoble<T> cabeza;

	/** La cola de la lista. */
	private NodoDoble<T> cola;

	/** Cantidad de elementos agregados. */
	private int cantidad;
	
    public NodoDoble<T> getCabeza(){
    	return cabeza;
    }
    
    public NodoDoble<T> getRabo(){
    	return cola;
    }

    @Override
    public boolean isEmpty(){
        return cantidad == 0;
    }

    @Override
    public int size(){
        return cantidad;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException{
    	if(i<0 || i>cantidad)
        	throw new IndexOutOfBoundsException("Rango inválido para obtener.");

        if(i == 0){
        	return cabeza.getElemento();
        } else if(i == cantidad-1){
        	return cola.getElemento();
        } else{
        	NodoDoble<T> iterador = cabeza;
        	for(int j = 0; j<i; j++){
        		iterador = iterador.getSiguiente();
        	}

        	return iterador.getElemento();
        }
    }

    @Override
    public void add(int i, T obj) throws IndexOutOfBoundsException{
        if(i<0 || i>cantidad+1)
        	throw new IndexOutOfBoundsException("Rango inválido para agregar.");

        NodoDoble<T> nuevo = new NodoDoble<>(obj);

        if(cantidad == 0){
        	cabeza = nuevo;
        	cola = nuevo;
        	cantidad++;
        	return;
        }

        if(i == 0){
        	nuevo.setSiguiente(cabeza);
        	cabeza.setAnterior(nuevo);
        	cabeza = nuevo;
        } else if(i == cantidad){
        	nuevo.setAnterior(cola);
        	cola.setSiguiente(nuevo);
        	cola = nuevo;
        } else{
        	NodoDoble<T> iterador = cabeza;
        	for(int j = 0; j<i-1; j++){
        		iterador = iterador.getSiguiente();
        	}

        	nuevo.setSiguiente(iterador.getSiguiente());
        	nuevo.setAnterior(iterador);
        	iterador.setSiguiente(nuevo);
        	nuevo.getSiguiente().setAnterior(nuevo);
        }

        cantidad++;
    }

    @Override
    public T set(int i, T obj) throws IndexOutOfBoundsException{
    	if(i<0 || i>cantidad)
        	throw new IndexOutOfBoundsException("Rango inválido para modificar.");

        T antiguo = null;

        if(i==0){
        	antiguo = cabeza.getElemento();
        	cabeza.setElemento(obj);
        } else if(i==cantidad-1){
        	antiguo = cola.getElemento();
        	cola.setElemento(obj);
        } else{
        	NodoDoble<T> iterador = cabeza;
        	for(int j = 0; j<i; j++){
        		iterador = iterador.getSiguiente();
        	}

        	antiguo = iterador.getElemento();
        	iterador.setElemento(obj);
        }

        return antiguo;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
    	if(i<0 || i>cantidad)
        	throw new IndexOutOfBoundsException("Rango inválido para eliminar.");
        T eliminado = null;

        if(cantidad == 1){
        	eliminado = cabeza.getElemento();
        	cabeza = null;
        	cola = null;
        	cantidad--;
        	return eliminado;
        }

        if(i == 0){
        	eliminado = cabeza.getElemento();
        	cabeza = cabeza.getSiguiente();
        	cabeza.setAnterior(null);
        } else if(i == cantidad-1){
        	eliminado = cola.getElemento();
        	cola = cola.getAnterior();
        	cola.setSiguiente(null);
        } else{
        	NodoDoble<T> iterador = cabeza;
        	for(int j = 0; j<i; j++){
        		iterador = iterador.getSiguiente();
        	}
        	eliminado = iterador.getElemento();
        	iterador.getAnterior().setSiguiente(iterador.getSiguiente());
        	iterador.getSiguiente().setAnterior(iterador.getAnterior());
        }

        cantidad--;

        return eliminado;
    }

    /**
    * Permite saber si un elemento está contenido en una lista.
    * @param elemento a verificar si está en la lista.
    * @return true si el elemento está contenido, false en otro.
    */
    public boolean isContained(T obj){
    	if(this.isEmpty())
    		return false;
    	if(cola.getElemento().equals(obj))
    		return true;

    	NodoDoble<T> iterador = cabeza;
    	while(iterador!=null){
    		if(iterador.getElemento().equals(obj))
    			return true;
    		iterador = iterador.getSiguiente();
    	}
        return false;
    }

    /**
    * No permite vaciar la lista.
    */
    public void clean(){
        cabeza = null;
        cola = null;
        cantidad = 0;
    }

    /**
    * Muestra los elementos contenidos en una lista.
    */
    public void muestra(){
    if(isEmpty())
    System.out.println("Lista vacía");
   	else {        NodoDoble<T> iterador = cabeza;
	
   	     for(int i = 0; i<cantidad; i++){
   	         System.out.println(iterador.getElemento());
   	         iterador = iterador.getSiguiente();
   	     }
   	 }
 	}	
}		
