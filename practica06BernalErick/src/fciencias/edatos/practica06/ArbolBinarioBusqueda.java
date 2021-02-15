//Erick Bernal Márquez		317042522
//Practica 6:arboles		enero/2021
//Feliz año
package fciencias.edatos.practica06;
import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
 * Implementación de un árbol binario de búsqueda.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0, Noviembre 2020.
 * @since Estructuras de datos 2021-1.
 */
public class ArbolBinarioBusqueda<T> implements TDAArbolBinarioBusqueda<T>{
    
    /**
     * Nodo binario de un arbol de binario de busqueda.
     */
    public class NodoBinario<T>{
	
	/** Nodo padre. */
	private NodoBinario<T> padre;
	
	/** Hijo izquierdo. */
	private NodoBinario<T> izquierdo;
	
	/** Hijo derecho. */
	private NodoBinario<T> derecho;
	
	/** Elemento almacenado en el nodo. */
	private T elemento;
	
	/** Peso del nodo. */
	private String nombre;
	
	public String getNombre(){
	    return nombre;
	}

	 /** Nos dice si el vértice tiene un izquierdo.
	 */
	public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        /** Nos dice si el vértice tiene un derecho.
	 */
	public boolean hayDerecho() {
            return derecho != null;
        }
        
        public NodoBinario<T> getIzquierdo(){
        if(!hayIzquierdo()) {
              throw new NoSuchElementException("El vértice no tiene hijo izquierdo");
            }
            return izquierdo;
        }
        
        public NodoBinario<T> getDerecho(){
        if(!hayDerecho()) {
              throw new NoSuchElementException("El vértice no tiene hijo derecho");
            }
            return derecho;
        
        }

	/** @return la altura del vértice.
	 */
          public int altura() {
            int auxIzquierdo = -1;
            int auxDerecho = -1;
            if(hayDerecho())
                auxDerecho = derecho.altura();
            if(hayIzquierdo())
                auxIzquierdo = izquierdo.altura();
            if(auxIzquierdo >= auxDerecho)
                return auxIzquierdo + 1;
            else
                return auxDerecho +1;
        }
	/**
	 * Crea un nuevo nodo binario.
	 * @param elemento el elemento almacenar en el nodo.
	 * @param nombre el nombre del nodo.
	 */
	public NodoBinario(T elemento, String nombre){
	    this.elemento = elemento;
	    this.nombre=nombre;
	}
	
	/**
	 * Crear un nuevo nodo binario con padre.
	 * @param elemento el elemento almacenar en el nodo.
	 * @param nombre el nombre del nodo.
	 * @param padre el padre del nodo creado.
	 */
	public NodoBinario(T elemento, String nombre, NodoBinario<T> padre){
	    this.elemento = elemento;
	    this.nombre=nombre;
	    this.padre = padre;
	}

	/**  @return una representación en cadena del vértice.
         */
        public String toString() {
            String cadena = "";
            cadena += elemento;
            return cadena;
        }
        
        public T getElemento(){
            return elemento;
        }
	
    }
    
    /** Root. */
    private NodoBinario<T> raiz;
    
    public NodoBinario<T> getRaiz(){	
    return raiz;
    }		
    	

    /** @return la altura del árbol.
     */
    public int altura() {
        if (raiz == null){
	    return -1;
        } else
	    return raiz.altura();
    }
    
    
    public T retrieve(String nombre){
	NodoBinario<T> buscado = retrieveAux(nombre, raiz);
	if(buscado == null)
	    return null;
	return buscado.elemento;
    }
    
    /**
     * Recorrer los nodos del arbol hasta llegar
     * a aquel con clave k.
     * @param nombre la clave buscada.
     * @param actual el nodo actual del recorrido sobre el arbol.
     * @return el nodo con clave nombre.
     */
    private NodoBinario<T> retrieveAux(String nombre, NodoBinario<T> actual){
	if(actual == null)
	    return null;
	
	if(nombre.equals(actual.nombre))
	    return actual;
	
	if( nombre.compareTo(actual.nombre) < 0 )
	    return retrieveAux(nombre, actual.izquierdo);
	else
	    return retrieveAux(nombre, actual.derecho);
    }
    
    
    public void insert(T e, String nombre){
	NodoBinario<T> nuevo = new NodoBinario<T>(e, nombre);
	if(raiz == null){
	    raiz = nuevo;
	} else{
	    insertAux(nuevo, raiz);
	}
    }
    
    /**
     * Permite agregar un nuevo elemento a un arbol.
     * @param nuevo el nuevo nodo a agregar.
     * @param actual el nodo auxiliar del recorrido.
     */
    private void insertAux(NodoBinario<T> nuevo, NodoBinario<T> actual){
	if( nuevo.nombre.compareTo(actual.nombre) < 0 ){
	    if(actual.izquierdo == null){
		nuevo.padre = actual;
		actual.izquierdo = nuevo;
	    } else{
		insertAux(nuevo, actual.izquierdo);
	    }
	} else{
	    if(actual.derecho == null){
		nuevo.padre = actual;
		actual.derecho = nuevo;
	    } else{
		insertAux(nuevo, actual.derecho);
	    }
	}
    }
    
    public T delete(String nombre){
	NodoBinario<T> eliminado = retrieveAux(nombre, raiz);
	return removeAux(eliminado);
    }
    
    /**
     * Permite eliminar un nodo de un arbol.
     * @param eliminado el nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     */
    public T removeAux(NodoBinario<T> eliminado){
    	if(eliminado == null){
			return null;
		}

		NodoBinario<T> padre = eliminado.padre;
		T elemento = eliminado.elemento;

		// Cuando eliminado no tiene hijos.
		if(eliminado.izquierdo==null && eliminado.derecho==null){
			if(padre == null){
				raiz = null;
				return elemento;
			}
			if(padre.izquierdo == eliminado){
				padre.izquierdo = null;
			} else{
				padre.derecho = null;
			}
			return elemento;
		}

		// Cuando eliminado tiene dos hijos.
		if(eliminado.izquierdo!=null && eliminado.derecho!=null){
			NodoBinario<T> maximo = findMax(eliminado.izquierdo);

			eliminado.nombre = maximo.nombre;
			eliminado.elemento = maximo.elemento;

			removeAux(maximo);
			return elemento;
		}

		// Cuando eliminado tiene un hijo.

		NodoBinario<T> reemplazado = eliminado.derecho!=null? eliminado.derecho : eliminado.izquierdo;

		if(padre == null){
			if(eliminado.izquierdo != null){
				raiz = eliminado.izquierdo;
			} else{
				raiz = eliminado.derecho;
			}
			return elemento;
		}

		if(padre.izquierdo == eliminado){ // Hijo izquierdo
			padre.izquierdo = reemplazado;
			reemplazado.padre = padre;
		} else{ // Hijo derecho
			padre.derecho = reemplazado;
			reemplazado.padre = padre;
		}

		return elemento;
	}

	public NodoBinario<T> findMin(NodoBinario<T> actual){
		NodoBinario min=actual;

		while(min.izquierdo!=null){
			min=min.izquierdo;
		}
		return min;
	}
    
    /**
     * Encuentra el elemento máximo a partir de un nodo actual
     * @param actual el nodo a partir del cual buscar el máximo.
     * @return nodo que contiene al elemento con peso máximo
     * a partir del nodo actual.
     */
    private NodoBinario<T> findMax(NodoBinario<T> actual){
	NodoBinario<T> max = actual;
	
	while(max.derecho != null){
	    max = max.derecho;
	}
	
	return max;
    }
    
    public T findMin(){
	if(raiz == null)
	    return null;
	NodoBinario<T> min = raiz;
	
	while(min.izquierdo != null)
	    min = min.izquierdo;
	
	return min.elemento;
    }
    
    public T findMax(){
	if(raiz == null)
	    return null;
	NodoBinario<T> max = raiz;
	
	while(max.derecho != null)
	    max = max.derecho;
		
	return max.elemento;
    }
    

    
    //Código hecho (guiado) a partir del libro "Estructura de datos con java Moderno", Canek Peláez. Cap 12.
    /** @return una representación en cadena del árbol.
     */
    @Override public String toString() {
        if(raiz == null){
	    return "";
        }
        int[] Arr = new int[altura() + 1];
        for (int i = 0; i < altura() + 1; i++)
            Arr[i] = 0;
	return auxToString(raiz, 0, Arr);
    }
    

    /**Método auxiliar dibujaEspacios.
     */
     private String dibujaEspacios(int l, int[] arreglo) {
       String cadena = "";
       for (int i = 0; i < l; i++)
         if(arreglo[i] == 1)
          cadena += "│  ";
         else
          cadena += "   ";
     return cadena;
     }

    /** Método auxiliar auxToString
     */
    //> significa que es hijo izquierdo, >> significa que es hijo derecho
     private String auxToString(NodoBinario<T> v, int l, int[] arreglo) {
       String cadena = v.getNombre() + "\n";
       arreglo[l] = 1;
       if (v.hayIzquierdo() && v.hayDerecho()) {
           cadena += dibujaEspacios(l, arreglo);
           cadena += "├─›";
           cadena += auxToString(v.izquierdo, l + 1, arreglo);
           cadena += dibujaEspacios(l, arreglo);
           cadena += "└─»";
           arreglo[l] = 0;
           cadena += auxToString(v.derecho, l + 1, arreglo);
       } else if (v.hayIzquierdo()) {
           cadena += dibujaEspacios(l, arreglo);
           cadena += "└─›";
           arreglo[l] = 0;
           cadena += auxToString(v.izquierdo, l + 1, arreglo);
       } else if (v.hayDerecho()) {
           cadena += dibujaEspacios(l, arreglo);
           cadena += "└─»";
           arreglo[l] = 0;
           cadena += auxToString(v.derecho, l + 1, arreglo);
       }
       return cadena;     }
    
}

