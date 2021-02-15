//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;

import java.util.Map.Entry;

/**
* Implementación abstracta de un Mapa de forma abstracta.
* @author Emmanuel Cruz Hernández.
* @version 1.0 Enero 2021.
* @since Estructuras de Datos 2021-1.
*/
public abstract class AbstractMap<K,V> implements Map<K,V>{

	protected static class MapEntry<K,V> implements Entry<K,V>{

		/** Clave del elemento. */
		private K k;

		/** Elemento almacenado. */
		private V v;

		/**
		* Crea un nuevo MapEntry.
		* @param key la clave de la entrada.
		* @param value el elemento almacenasdo en la entrada.
		*/
		public MapEntry(K key, V value){
			k = key;
			v = value;
		}

		/**
		* Accede a la clave de la entrada.
		* @return la clave de la entrada.
		*/
		public K getKey(){
			return k;
		}

		/**
		* Accede al elemento de la entrada.
		* @return el elemento de la entrada.
		*/
		public V getValue(){
			return v;
		}

		/**
		* Modifica la clave de la entrada.
		* @param key la nueva clave a asignar.
		*/
		public void setKey(K key){
			this.k = key;
		}

		/**
		* Modifica el valor almacenado en la entrada.
		* @param value el valor nuevo a asignar.
		* @return el antiguo valor almacenado.
		*/
		public V setValue(V value){
			V old = v;
			v = value;
			return old;
		}
	}
	
	@Override
	public boolean isEmpty(){
		return size() == 0;
	}
}
