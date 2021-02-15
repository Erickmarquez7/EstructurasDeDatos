//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;

import java.util.Random;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
* Implementación abstracta de un HashMap.
* @author Emmanuel Cruz Hernández.
* @version 1.0 Enero 2021.
* @since Estructuras de Datos 2021-1.
*/
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V>{

	/** Cantidad de entradas en el diccionario. */
	protected int n = 0;

	/** Capacidad de la tabla. */
	protected int capacity;

	/** Factor primo para calcular longitudes. */
	private int prime;

	/** Cantidad del cambio y escala. */
	private long scale, shift;

	/**
	* Crea un nuevo AbstractHashMap. 
	* @param cap la capacidad de la tabla.
	* @param p el factor primo.
	*/
	public AbstractHashMap(int cap, int p){
		prime = p;
		capacity = cap;
		Random rn = new Random();
		scale = rn.nextInt(prime-1) + 1;
		shift = rn.nextInt(prime);
		createTable();
	}

	/**
	* Crea un nuevo AbstractHashMap.
	* @param cap la capacidad de la tabla.
	*/
	public AbstractHashMap(int cap){
		this(cap, 109345121);
	}

	/**
	* Crea un nuevo AbstractHashMap.
	*/
	public AbstractHashMap(){
		this(17);
	}

	@Override
	public int size(){
		return n;
	}

	@Override
	public V get(K key){
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V put(K key, V value){
		V older = bucketPut(hashValue(key), key, value);
		if(n > capacity/2)
			resize(2 * capacity - 1);
		return older;
	}

	@Override
	public V remove(K key){
		return bucketRemove(hashValue(key), key);
	}

	// UTILIDADES PRIVADAS.

	private int hashValue(K key){
		return (int) ((Math.abs(key.hashCode( ) * scale + shift) % prime) % capacity);
	}

	private void resize(int newCap){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
		for (Entry<K,V> e : entrySet())
			buffer.add(e);
		capacity = newCap;
		createTable();
		n = 0;
		for(Entry<K,V> e : buffer)
			put(e.getKey(), e.getValue());
	}

	// MÉTODOS ABSTRACTOS PROTEGIDOS A IMPLEMENTAR EN SUBCLASES

	/**
	* Crea una nueva tabla vacía de la misma longitud
	* que la capacidad de elementos.
	*/
	protected abstract void createTable();

	/**
	* Obtiene un elemento almacenado en uno de los buckets.
	* @param h el identificador del bucket a obtener.
	* @param k la clave del elemento a obtener.
	* @return el elemento con clave k almacenado en el bucket h.
	*/
	protected abstract V bucketGet(int h, K key);

	/**
	* Agrega un nuevo elemento a un bucket dentro de una tabla.
	* @param h el identificador del bucket a almacenar un elemento.
	* @param k la clave del elemento a almacenar.
	* @param v el elemento a almacenar.
	* @return el alemento antiguo o null en caso de no existir.
	*/
	protected abstract V bucketPut(int h, K k, V v);

	/**
	* Elimina un elemento almacenado en un bucket de la tabla.
	* @param h el identificador del bucket donde eliminar un elemento.
	* @param k la clave del elemento a eliminar.
	* @return el elemento eliminado o null en caso de no existir.
	*/
	protected abstract V bucketRemove(int h, K k);

	/**
	* Crea un iterador de las entradas almacenadoras.
	* @return un iterador con las entradas.
	*/
	public abstract Iterable<Entry<K,V>> entrySet();
}
