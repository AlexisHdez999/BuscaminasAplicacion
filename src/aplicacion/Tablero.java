package aplicacion ;

import java.util.Random;

/*Clase para elaborar un tablero de juego
 *@author Alexis Hernández Magaña
 *
 */

public class Tablero{

    private Posicion[][] tablero ;
    
/*Método constructor para  un tablero de juego
 *@author Alexis Hernández Magaña
 *
 */
    public Tablero(int n, int m){

	tablero = new Posicion[n][m];
   }


    public Posicion[][] obtenerTablero(){
	return tablero;

    }

   
   private String [] obtenerCoordenadas(int minas){
       int filas = tablero.length ; 
       int columnas = tablero[0].length ;
       
       
        Random rnd = new Random();
        String[] posicionesAleatorias = new String[minas];
        
        int f = 0, c = 0;
        
        for(int i = 0; i < posicionesAleatorias.length; i++) {
        
        f = rnd.nextInt(filas);
        c = rnd.nextInt(columnas);
        String fconvertido = String.valueOf(f);
        String cconvertido = String.valueOf(c);
        String coordenada = fconvertido + "," + cconvertido;
        String coordenadaIgual = null;
        String coordenadaDiferente = null;
        
        if(i < 1) {
              posicionesAleatorias[i] = coordenada;    
        
        } else {
          
            for(int h = 0; h < (i + 1); h++) {
                if (coordenada.equals(posicionesAleatorias[h])) {
                    coordenadaIgual = coordenada;
                    break;
                }
            }

	    coordenadaDiferente = coordenada;
            
        if(coordenadaIgual == null) {
             posicionesAleatorias[i] = coordenadaDiferente;
           
         } else {
                i--; 
	}
	}
       }
	return posicionesAleatorias ;    
  }

/*Método para generar coordenandas para minas
 *@author Alexis Hernández Magaña
 *@param minas - numero de minas
 *@return int [][] - arreglo con las coordenadas generadas.
 */ 
	public int [][] obtenerPosiciones(int minas){
	String [] coordenadasAleatorias = this.obtenerCoordenadas(minas) ;
       

        int[] arregloFilas = new int[coordenadasAleatorias.length];
        int[] arregloColumnas = new int[coordenadasAleatorias.length];
        
        String cadena = new String();
        int corte;
        String numFilaS, numColumnaS;
       
        for(int i = 0; i < coordenadasAleatorias.length; i++) {
            cadena = coordenadasAleatorias[i];
            corte = cadena.indexOf(",");
            numFilaS = cadena.substring(0, corte);
            cadena = cadena.substring((corte+1), cadena.length());
            numColumnaS = cadena;
            
            int noFilaI = Integer.parseInt(numFilaS);
            int noColumnaI = Integer.parseInt(numColumnaS);
            
            arregloFilas[i] = noFilaI;
            arregloColumnas[i] = noColumnaI;

	}
	

	  int [][] arreglo = new int [2][coordenadasAleatorias.length] ;
	    arreglo[0] = arregloFilas ;
	    arreglo[1] = arregloColumnas  ;
	    return arreglo ;

	}

/*Método para llenar minas en el tablero
 *@author Alexis Hernández Magaña
 *@param coodenadas - arreglo con las coordenadas de las minas
 */    
    public void llenarMinas(int [][] coordenadas){
	
	int n1, n2 ;

	for(int r = 0; r < coordenadas[0].length; r++){
            n1 = coordenadas[0][r];
            n2 = coordenadas[1][r];
            
           tablero[n1][n2] = new Mina();  
        }	
	
      }

/*Método para llenar el tablero con objetos número
 *@author Alexis Hernández Magaña
 *
 */    
    
    public void llenarTablero(){
	for (int i = 0 ; i < tablero.length ; i++){
	    for (int j = 0 ; j < tablero[0].length ; j++){
		if (tablero[i][j]== null){

		    tablero[i][j] = new Numero();
		}

	    }
	    
	}
    }

 /*Método para aumentar los números cerca todas las minas
 *@author Alexis Hernández Magaña
 */ 
     
    public void establecerTablero(int [][] posiciones){

    for (int i = 0 ; i < posiciones[0].length ; i++){

	this.establecerNumero(posiciones[0][i], posiciones [1][i]);   
     }

    }
    
  /*Método para aumentar los números cerca de las minas
 *@author Alexis Hernández Magaña
 *@param a - primera coordenada de una mina
 *@param b - segunda coordenada de una mina
 */ 
      public void establecerNumero(int a, int b){
	  if ((a + 1 < tablero.length ) && ( tablero[a+1][b] instanceof Numero)){   
       	 tablero[a+1][b].aumentarNumero();
	  } 

	  if ((b+1 < tablero[0].length) && (a + 1 < tablero.length ) && ( tablero[a+1][b+1] instanceof Numero) ){
	     tablero [a+1][b+1].aumentarNumero();
	   }
	    
	  if ((b-1 >= 0) && (a + 1 < tablero.length ) && ( tablero[a+1][b-1] instanceof Numero)){
	     tablero [a+1][b-1].aumentarNumero();
        	}
	    
       
	
	  if ((a-1 >= 0)  && ( tablero[a-1][b] instanceof Numero) ){
	  tablero[a-1][b].aumentarNumero();
	  }
	  
	  if ((b-1 >= 0)&& (a-1 >= 0)   && ( tablero[a-1][b-1] instanceof Numero)){
	     tablero [a-1][b-1].aumentarNumero();
	   }

	  if ((b+1 < tablero[0].length)&& (a-1 >= 0)   && ( tablero[a-1][b+1] instanceof Numero)){
	  tablero[a-1][b+1].aumentarNumero(); 
	  
	  }
	  
	 
	  if ((b+1 < tablero[0].length)  && ( tablero[a][b+1] instanceof Numero)){
	     tablero [a][b+1].aumentarNumero();
	   } 
       

	  if ((b-1 >= 0)  && ( tablero[a][b-1] instanceof Numero) ){
	  tablero[a][b-1].aumentarNumero();	
          }
	  
      }
	
    
    public void asignarTablero(int i, int j, Posicion p){

	tablero[i][j] = p ;

    }

    
    public String toString(){
	String a = "" ;
	for (int i = 0 ; i < tablero.length ; i++){
	   
	    for (int j= 0 ; j < tablero[0].length ; j++){
		a += "|" + tablero [i][j] ;
	    }
	     a += "\n" ;
	}
	return a ; 
   }
    
}
