//Erick Bernal Márquez. 317042522
//8 de octubre de 2020
package fciencias.edatos.practica01;

import java.util.Scanner;
import java.io.Serializable;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.InputMismatchException;


public class Matriz implements InterMatriz, Serializable{
    //atributo matriz
    public double[][] matriz;

    //Métodos
    public Matriz(int a, int b){
	matriz = new double[a][b];
    }


    /** 
     * Indica el numero de renglones de 
     * una Matriz.
     * @return el nùmero de renglones.
     */ 
    public int getRenglones(Matriz a) //O sea, así ---------
    {
	return a.matriz.length;
    }
    
    /**
     * Indica el número de columnas de
     * una Matriz.
     * @return el número de columnas.
     */
    public int getColumnas(Matriz b) //O sea, así ||||||||||||||
    {
	return b.matriz[0].length;
    }

    /**
     * Obtiene el elemento en la 
     * posición x, y.
     * @return la posición.
     */
    public double getElemento(Matriz a, int x, int y){
	return a.matriz[x-1][y-1];
    }

    
    /**
     * Modifica el elemento en la 
     * posición x, y.
     */
    public void modifica(Matriz a, int posx, int posy, int cambio){
	a.matriz[posx-1][posy-1] = cambio;

    }


    /**
     * Suma de mattrices, suma entrada
     * por entrada de cada Matriz.
     * @return la suma de Matrices.
     */
    public Matriz suma(Matriz a, Matriz b) throws DifferentSizeException{
	if (a.matriz.length != b.matriz.length || a.matriz[0].length != b.matriz[0].length) 
	    throw new DifferentSizeException("Diferente tamaño");  //por si difiere de tamaño
	
	Matriz nueva = new Matriz(a.matriz.length, a.matriz[0].length);
	for(int i = 0; i <nueva.matriz.length; i++){
	    for(int j = 0; j<nueva.matriz[i].length; j++){
		nueva.matriz[i][j] = a.matriz[i][j] + b.matriz[i][j];
	    }
	}
	return nueva;
    }

    
    /**
     * Multiplica una Matriz por un escalar.
     * @return la Matriz multiplicada.
     */
    public Matriz multiEsc(int n, Matriz a){
	Matriz nueva = new Matriz(a.matriz.length, a.matriz[0].length);
	for(int i = 0; i <nueva.matriz.length; i++){
	    for(int j = 0; j<nueva.matriz[i].length; j++){
		nueva.matriz[i][j] = n * a.matriz[i][j]; //asignacion de valores
	    }
	}
	return nueva;
    }


    /** 
     * Multiplicación de matrices de 
     * acuerdo a la fórmula.
     * @return la matriz multiplicada.
     */
    public Matriz multiMat(Matriz a, Matriz b) throws DifferentSizeException{
	if (a.matriz[0].length != b.matriz.length)
	    throw new DifferentSizeException("Diferente tamaño"); //por si difiere de largo y ancho

	Matriz nueva = new Matriz(a.matriz.length, b.matriz[0].length);
	
	for (int i = 0; i < a.matriz.length; i++) {
            for (int j = 0; j < b.matriz[0].length; j++) {
                for (int k = 0; k < a.matriz[0].length; k++) {
                    
                    nueva.matriz[i][j] += a.matriz[i][k] * b.matriz[k][j]; //asignación de valores
                }
            }
        }   
	
	return nueva;
    }


    /** 
     * Transforma una Matriz a su transpuesta.
     * @return la Transpuesta de una Matriz.
     */
    public Matriz trans(Matriz a){
	Matriz nueva = new Matriz(a.matriz[0].length, a.matriz.length);
	for(int i = 0; i <nueva.matriz.length; i++){
	    for(int j = 0; j<nueva.matriz[i].length; j++){
		nueva.matriz[i][j] = a.matriz[j][i];  //asignación de valores
	    }
	}
	return nueva;
    }

    
    /**
     * Verifica si una matriz es igual a otra.
     * @return true si lo es, false en otro caso.
     */
    public  boolean esIgual(Matriz a, Matriz b){
	if (a.matriz.length != b.matriz.length || a.matriz[0].length != b.matriz[0].length) //Comparamos primero largo y ancho
	    return false;
	for(int i = 0; i <a.matriz.length; i++){
	    for(int j = 0; j<a.matriz[i].length; j++){
		if (a.matriz[i][j] != b.matriz[i][j])  //si alguno no es igual desde ahí podemo decir que es falso
		    return false;
	    }
	}
	return true;
    }

    /** 
     * Representación de una Matriz en cadena.
     * @return la matriz.
     */
    @Override
    public String toString(){
	String res = "";
	for(int i = 0; i < matriz.length; i++){
	    res+="\n";
	    for(int j = 0; j<matriz[i].length; j++){
		res += matriz[i][j]+"  "; 
	    }
	}
	return res;
    }


    //Serilizador

    public void ser(String nombre, Matriz m) throws IOException{
	//	FileOutputStream file = new FileOutputStream(nombre);
	ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(nombre)); //nombre con que vamo a GUARDAR, serializar el archivo
	output.writeObject(m);
	output.close();
    }

    
    //
    public Matriz des(String nombre) throws IOException, ClassNotFoundException{
	//FileInputStream file = new FileInputStream(nombre);
	//ObjectInputStream input = new ObjectInputStream(new FileInputStream(nombre));  //Deserializar
	//Matriz m = (Matriz) input.readObject();
	//t = (Transaction) input.readObject();
	//input.close();
	//return m;              No sé porqué pero creo que así no funciona, así que mejor lo dejo como está abajo

	FileInputStream file = new FileInputStream(nombre);
	ObjectInputStream input = new ObjectInputStream(file);
	Matriz m;
	m = (Matriz) input.readObject();
	input.close();
	return m;
	
    }
    
    
   //MAIN 
   public static void main(String[] args){
	System.out.println("¿Qué desea hacer?");
	Scanner sc  = new Scanner(System.in);
	Matriz madre = new Matriz(0,0); //objeto con que haremos operaciones
	int menu;

					/*Descomentar todo esto para crear 4 matrices 
	Matriz a1 = new Matriz(5,5);
	a1.matriz[0][0] = 1;
	a1.matriz[0][1] = 3;
	a1.matriz[0][2] = 2;
	a1.matriz[0][3] = 1;
	a1.matriz[0][4] = 2;
	a1.matriz[1][0] = 1;
	a1.matriz[1][1] = 5;
	a1.matriz[1][2] = 0;
	a1.matriz[1][3] = 3;
	a1.matriz[1][4] = 6;
	a1.matriz[2][0] = 3;
	a1.matriz[2][1] = 2;
	a1.matriz[2][2] = 1;
	a1.matriz[2][3] = 3;
	a1.matriz[2][4] = 1;
	a1.matriz[3][0] = 3;
	a1.matriz[3][1] = 4;
	a1.matriz[3][2] = 2;
	a1.matriz[3][3] = 3;
	a1.matriz[3][4] = 2;
	a1.matriz[4][0] = 1;
	a1.matriz[4][1] = 0;
	a1.matriz[4][2] = 2;
	a1.matriz[4][3] = 1;
	a1.matriz[4][4] = 3;

	Matriz b1 = new Matriz(3,5);
	b1.matriz[0][0] = 0;
	b1.matriz[0][1] = 2;
	b1.matriz[0][2] = 1;
	b1.matriz[0][3] = 2;
	b1.matriz[0][4] = 3;
	b1.matriz[1][0] = 4;
	b1.matriz[1][1] = 5;
	b1.matriz[1][2] = 7;
	b1.matriz[1][3] = 0;
	b1.matriz[1][4] = 1;
	b1.matriz[2][0] = 3;
	b1.matriz[2][1] = 4;
	b1.matriz[2][2] = 1;
	b1.matriz[2][3] = 2;
	b1.matriz[2][4] = 1;

	Matriz b2 = new Matriz(3,5);
	b2.matriz[0][0] = 0;
	b2.matriz[0][1] = 2;
	b2.matriz[0][2] = 1;
	b2.matriz[0][3] = 2;
	b2.matriz[0][4] = 3;
	b2.matriz[1][0] = 4;
	b2.matriz[1][1] = 5;
	b2.matriz[1][2] = 7;
	b2.matriz[1][3] = 0;
	b2.matriz[1][4] = 1;
	b2.matriz[2][0] = 3;
	b2.matriz[2][1] = 4;
	b2.matriz[2][2] = 1;
	b2.matriz[2][3] = 2;
	b2.matriz[2][4] = 1;

	Matriz c1 = new Matriz(5,3);
	c1.matriz[0][0] = 2;
	c1.matriz[0][1] = 3;
	c1.matriz[0][2] = 0;
	c1.matriz[1][0] = 1;
	c1.matriz[1][1] = 4;
	c1.matriz[1][2] = 5;
	c1.matriz[2][0] = 2;
	c1.matriz[2][1] = 5;
	c1.matriz[2][2] = 1;
	c1.matriz[3][0] = 1;
	c1.matriz[3][1] = 1;
	c1.matriz[3][2] = 4;
	c1.matriz[4][0] = 1;
	c1.matriz[4][1] = 3;
	c1.matriz[4][2] = 1;
	
	try{
	madre.ser("A",a1);
	madre.ser("B",b1);
	madre.ser("B2",b2);  //para comprobar que son iguales
	madre.ser("C1",c1);

	}catch(Exception e){
	    System.out.println("");
	}                                               Hasta aqui*/
	
	do{
	    System.out.println("\n0.- Crear una Matariz\n1.- Número de renglones de una Matriz\n2.- Nùmero de columnas de una Matriz\n3.- Obtener el elemento de una Matriz\n4.- Modificar algún elemento de una matriz\n5.- Suma de Matrices\n6.- Multiplicación de una Matriz por escalar\n7.- Multiplicación de matrices\n8.- Traspuesta de una Matriz\n9.- Saber si una Matriz es igual a otra\n10.-Ver una matriz\nCualquier otro número: salir");
	    
	    while(!sc.hasNextInt()) {  
		System.out.println("\tEse no es un número\t"); //solo paara verificar que se le pase un numero
		sc.next();
	    }
	    
	    menu = sc.nextInt(); //para seleccionar opcioón deseada deseable:V
	    
	    switch(menu){
		
	    case 0:
		try {
		    System.out.println("Inserte número de columnas: ");
		    int col = sc.nextInt();
		    System.out.println("Inserte número de renglones: ");
		    int ren = sc.nextInt();
		    System.out.println("Nombre con que quiere gurdar la matriz");
		    String nombre = sc.next(); 
		    Matriz nueva = new Matriz(ren, col);
		    System.out.println(nueva);//se imprime en pantalla y se guarda
		    madre.ser(nombre, nueva);
		    System.out.println("Archivo guardado");
		}catch (NegativeArraySizeException nase){
		    System.out.println("Algún numero es negativo");
		}catch(InputMismatchException ime){
		    System.out.println("La matriz solo admite números");
		}catch (Exception e){
		    System.out.println("No se puedo guardar el archivo ): Perdón.");
		}
		break;
		
		
		//cambiar os argumentos de los s.o.p()
	    case 1:
		System.out.println("Escriba el nombre del archivo de la matriz: ");
		String archi1 = sc.next();
		try{
		    Matriz m = madre.des(archi1);
		    System.out.println(m);//se imprime en pantalla
		    System.out.println("Número de renglones: "+ madre.getRenglones(m));
		} catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		
		break;

		
	    case 2:
		System.out.println("Escriba el nombre del archivo de la matriz: ");
		String archi2 = sc.next();
		try{
		    Matriz m = madre.des(archi2);
		    System.out.println(m); //se imprime en pantalla y se guarda
		    System.out.println("Número de renglones: "+ madre.getRenglones(m));
		} catch(IOException ioe){
		    System.out.println("Matriz inexistente ):");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;
		
		
	    case 3:
		System.out.println("Escriba el nombre de la matriz: ");
		String archi3 = sc.next();
		try {
		    System.out.println("Inserte columna a elegir: ");
		    int col3 = sc.nextInt();
		    System.out.println("Inserte renglón a elegir: ");
		    int ren3 = sc.nextInt();
		    Matriz m = madre.des(archi3);
		    System.out.println("El elemento a obtener en la posición "+col3+ ", "+ren3+" es: "+ madre.getElemento(m, col3, ren3));
		    System.out.println(m);//se imprime en pantalla
		    
		}catch(InputMismatchException ime){
		    System.out.println("Solo números");
		}catch(ArrayIndexOutOfBoundsException aioe){
		    System.out.println("Posición inexistente");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		}catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;

		
	    case 4:
		System.out.println("Escriba el nombre de la matriz");
		String archi4 = sc.next();
		try{
		    System.out.println("Inserte columna a cambiar: ");
		    int col4 = sc.nextInt();
		    System.out.println("Inserte renglón a cambiar: ");
		    int ren4 = sc.nextInt();
		    System.out.println("Escriba un número a cambiar: ");
		    int camb = sc.nextInt();
		    Matriz m = madre.des(archi4);
		    madre.modifica(m, ren4, col4, camb);
		    System.out.println(m);
		    System.out.println("Cambio hecho");//se imprime en pantalla y se guarda
		    madre.ser(""+archi4,m);
		    System.out.println("Archivo guardado como: "+archi4);
		}catch(ArrayIndexOutOfBoundsException aioe ){
		    System.out.println("Posición inexistente");
		}catch(InputMismatchException ime){
		    System.out.println("La matriz solo admite números");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;

		
	    case 5:
		System.out.println("Ingrese la matriz A para sumar: ");
		String archi5a = sc.next();
		System.out.println("Ingrese la matriz B para sumar: ");
		String archi5b = sc.next();
		try{
		    Matriz sumA = madre.des(archi5a);
		    Matriz sumB = madre.des(archi5b);   
		    Matriz resulSum = madre.suma(sumA, sumB);
		    System.out.println(resulSum);
		    madre.ser(""+archi5a+"+"+archi5b,resulSum);//se imprime en pantalla y se guarda
		    System.out.println("Archivo guardado como: "+archi5a+"+"+archi5b);
		} catch(DifferentSizeException dse){
		    System.out.println(dse.getMessage());
		} catch(NullPointerException ne){
		    System.out.println("Matriz inexistente");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		
		break;
		
		
	    case 6:
		try{
		    System.out.println("Ingrese escalar a multiplicar: ");
		    int esc = sc.nextInt();
		    System.out.println("Ingrese matriz a multiplicar: ");
		    String archi6 = sc.next();
		    Matriz m = madre.des(archi6);
		    Matriz resulEsc = madre.multiEsc(esc, m);
		    System.out.println(resulEsc);
		    madre.ser(""+archi6+"x"+esc,resulEsc);//se imprime en pantalla y se guarda
		    System.out.println("Archivo guardado como: "+archi6+"x"+esc+", sí, es una equis, no un asterisco(*)");   
		}catch(InputMismatchException ime){
		    System.out.println("Solo números");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;
		
		
	    case 7:
		System.out.println("Ingrese matriz A a multiplicar: ");
		String archi7a = sc.next();
		System.out.println("Ingrese matriz B a multiplicar: ");
		String archi7b = sc.next();
		try{
		    Matriz a = madre.des(archi7a);
		    Matriz b = madre.des(archi7b);
		    Matriz resMul = madre.multiMat(a, b);
		    System.out.println(resMul);
		    madre.ser(""+archi7a+"x"+archi7b,resMul);//se imprime en pantalla y se guarda
		    System.out.println("Archivo guardado como: "+archi7a+"x"+archi7b+", sí, es una equis");
		} catch(DifferentSizeException dse){
		    System.out.println(dse.getMessage());
		} catch(NullPointerException ne){
		    System.out.println("Matriz inexistente");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;
		
		
	    case 8:
		System.out.println("Ingrese matriz a transponer: ");
		String archi8 = sc.next();
		try{
		    Matriz m = madre.des(archi8);
		    Matriz resul = madre.trans(m);
		    System.out.println(resul);
		    madre.ser(""+archi8+"Tr",resul);//se imprime en pantalla y se guarda
		    System.out.println(resul);
		    System.out.println("Matriz guardada como: "+archi8+"Tr");
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}

		break;
		
		
	    case 9:
		System.out.println("Ingrese matriz A a comparar: ");
		String archi9a = sc.next();
		System.out.println("Ingrese matriz B a comparar: ");
		String archi9b = sc.next();
		try{
		    Matriz a = madre.des(archi9a);
		    Matriz b = madre.des(archi9b);
		    System.out.println(a);
		    System.out.println(b);
		    System.out.println(madre.esIgual(a,b));
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		break;

		
	    case 10:
		System.out.println("Nombre de la matriz que desea ver: ");
		String archi10 = sc.next();
		try{
		    Matriz m = madre.des(archi10);
		    System.out.println(m); //se imprime en pantalla
		}catch(IOException ioe){
		    System.out.println("Matriz inexistente");
		} catch(ClassNotFoundException cnfe){
		    System.out.println("Matriz inexistente.");
		}
		
		break;

		
	    default:
		System.out.println("Programa terminado");
		return;
	    }
	    
	} while(menu != -1); //aqui acaba el do 
	
	return; //aqui acaba el main
	
	
   }
    
    
}
