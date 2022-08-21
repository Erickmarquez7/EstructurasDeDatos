//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
* Implementación de un mapa con buckets (Chain).
* @author Emmanuel Cruz Hernández.
* @version 1.0 Enero 2021.
* @since Estructuras de Datos 2021-1.
*/
public class ChainHashMap<K,V> extends AbstractHashMap<K,V>{
	
	/** Tabla para almacenar los buckets. */
	private UnsortedTableMap<K,V>[] table;

	/**
	* Crea un nuevo ChainHashMap.
	*/
	public ChainHashMap(){
		super();
	}

	public ChainHashMap(int cap){
		super(cap);
	}

	public ChainHashMap(int cap, int p){
		super(cap, p);
	}

	@Override
	protected void createTable(){
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}

	@Override
	protected V bucketGet(int h, K key){
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null)
			return null;
		return bucket.get(key);
	}

	@Override
	protected V bucketPut(int h, K k, V v){
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null)
			bucket = table[h] = new UnsortedTableMap<>();
		int oldSize = bucket.size();
		V anwser = bucket.put(k, v);
		n += (bucket.size() - oldSize);
		return anwser;
	}

	@Override
	protected V bucketRemove(int h, K k){
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null)
			return null;
		int oldSize = bucket.size();
		V anwser = bucket.remove(k);
		n -= oldSize - bucket.size();
		return anwser;
	}

	@Override
	public Iterable<Entry<K,V>> entrySet(){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity ; h++){
			if(table[h] != null){
				for(Entry<K,V> entry : table[h].entrySet()){
					buffer.add(entry);
				}
			}
		}
		return buffer;
	}
}
