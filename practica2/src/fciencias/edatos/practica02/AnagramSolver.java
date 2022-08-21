//Erick Bernal Márquez        317042522
//Practica 2                  oct/2020
package fciencias.edatos.practica02;
import java.util.Scanner;

public class AnagramSolver{
    static int size;
    static char[] word = new char[size];

    public AnagramSolver(int size, char[] word){
	this.size=size;
	this.word=word;
    }

    private static void rotate(int rotator){
	char temp=word[size-rotator];//variable temporal para poner al último porque java no python
	for(int i=size-rotator, j=i+1; j<size; i++, j++){
	    word[i]=word[j];
	}
	word[size-1]=temp; //ponemos la ultima
    }

    
    private static void displayWord(){
	for(int i=0; i<word.length; i++)
	    System.out.print(word[i]);
	System.out.println("");
    }

    public static void doAnagram(int newSize){
	if (newSize==1) return;
	
	else {
	    for(int i=0; i<newSize; i++){
		doAnagram(newSize-1);
		if(newSize==2) displayWord();
		rotate(newSize);	
	    }
	}	
    }
    

    public static void main(String[] args){
    	Scanner sc=new Scanner(System.in);	

	System.out.println("Ingrese una palabra para obtener sus anagramas, procure que no sea una palabra demasiado larga.");
	
	String palabra = sc.next(); //entra palabra
	
	char[] arrPalabra = palabra.toCharArray(); //palabra tranformada a arreglo
	
	AnagramSolver pr= new AnagramSolver(arrPalabra.length, arrPalabra);
	
	doAnagram(arrPalabra.length);
    }
  
}
