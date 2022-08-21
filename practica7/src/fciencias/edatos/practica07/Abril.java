//Erick Bernal Márquez		317042522
//Practica 7:mapas		enero/2021

package fciencias.edatos.practica07;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.DOMException;
import java.util.Scanner;

public class Abril{

    /** Dada una cadena devuelve los numeros que hay en ella.
     * @param str la cadena a separar.
     * @return res los numeros de la cadena-
     */
    public static Double daNumero(String str){
	//declaramos el numero para irlo guardando como cadena
	String numero="";
	for(int i = 0; i<str.length(); i++ ){ //no usamos forEach porque necesitamos el indice de la cadena
	    //lo pasamos a caracter
	    char ch = str.charAt(i);
	    //si es numero lo sumamos a la cadena
	    if (Character.isDigit(ch))
		numero+=ch;
	} 
	//lo convertimos a Int, si la cadena no tiene numeros entonces sabemos que es 1
	if (numero == "")
	    return 1.0;
	Double res = Double.valueOf(numero);
	return res;
    }

    //es muy parecido al de arriba xd
    /** Dada una cadena devuelve la cadena que hay en ella.
     * @param str la cadena a separar.
     * @return cadena los cadena sola de la cadena-
     */
    public static String daCadena(String str){
	//declaramos el numero para irlo guardando como cadena
	String cadena="";
	for(int i = 0; i<str.length(); i++ ){
	    //lo pasamos a caracter
	    char ch = str.charAt(i);
	    //si no es numero lo sumamos a la cadena
	    if (!Character.isDigit(ch))
		cadena+=ch;
	}
	return cadena;
    }

    /** Comprueba si el incio de una cadena es un numero.
     * @param str la cadena a comprobar.
     * @return res el resultado.
     **/
    public static boolean esNumero(String str){
	return Character.isDigit(str.charAt(0));

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DOMException, NumeroException{
	
	LectorDom lector = new LectorDom();
	
	UnsortedTableMap<String, Elemento> mapa = lector.lee("tabla-periodica.xml",1);

	Scanner sc = new Scanner(System.in);

	String menu;

	do{

	    System.out.println("\nElemento químicos ¿Qué quiere?\n0.-Salir\n1.-Ver tabla\n2.-Ver un elemento en específico\n3.-Calcular peso molecular de una fórmula"); 
	    
	    menu = sc.nextLine();

	    switch(menu){
	    case "1":
		lector.lee("tabla-periodica.xml",0);

		break;

	    case "2":
		System.out.println("Escriba el Símbolo del elemento");
		String busca = sc.next();
		Elemento buscado = mapa.get(busca);
		if (buscado != null)
		    System.out.println(buscado);
		else
		    System.out.println("El elemento no existe");
		sc.nextLine();

		break;

	    case "3":
		System.out.println("Escriba fórmula seprando por puntos los elementos químicos");
		//leemos cadena
		String formula = sc.nextLine();
		//separamos los elementos de la formula
		String[] separa = formula.split("\\.");
		//pa ir sumando el peso
		Double peso = 0.0;
		Elemento eMap=null;
		try{
		    for(String s : separa){
			//separamos los numeros de la cadena
			Double numero = daNumero(s);
			String cadena = daCadena(s);
			eMap = mapa.get(cadena);
			//debemos saber si empieza con numero o no existe el elemento
			if (esNumero(s) ||  eMap == null)
			    throw new NumeroException("");
			//si no empieza con numero y lo encontró
			else 
			    peso+=eMap.getMasa()*numero;
		    }//final del for
		    System.out.println("El peso molecular de la fórmula "+formula+" es: "+peso);
		}catch(Exception e){
		    System.out.println("La formula empieza con un numero o no existe");
		}
		break;
		
	    case "0": //pa salir del ciclo
		return;

	    default: //pa seguir hasta que se le pase un 0
		System.out.println("Opción no valida");
		continue;
	    }

	}while(true);
	
    }
}
