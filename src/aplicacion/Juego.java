package aplicacion ;
/*Clase para empezar el juego buscaminas
 *@author Alexis Hernández Magaña
 *
 */

public class Juego{

    Tablero tablero ;
    int minas ;
    Posicion [][] tableroPantalla ;

 /*método para construir un juego
 *@author Alexis Hernández Magaña
 *@param filas - filas del tablero.
 *@param columnas - columnas del tablero.
 *@param minas - minas del tablero.
 */

    public Juego (int filas, int columnas, int minas){

	tablero = new Tablero(filas, columnas);
	this.minas = minas ;
	tableroPantalla = new Posicion[filas][columnas];
    }

 /*Método para generar una partida
 *@author Alexis Hernández Magaña
 */
    public void generarPartida(){

	int[][] posiciones = tablero.obtenerPosiciones(minas);
	tablero.llenarMinas(posiciones);
	tablero.llenarTablero();
	tablero.establecerTablero(posiciones);
//
    }

 /*Método para mostrar una casilla
 *@author Alexis Hernández Magaña
 *@param fila - fila que se desea mostrar  del tablero.
 *@param columna - columna que se desea mostrar del tablero
 */
    public void mostrarCasilla(int fila, int columna){
	int contador = 0 ;
	int casillas = ((tablero.obtenerTablero().length*tablero.obtenerTablero()[0].length)) - minas ;
	    
    tableroPantalla[fila][columna] = tablero.obtenerTablero()[fila][columna];
		
     System.out.println(this);

     if ( tablero.obtenerTablero()[fila][columna] instanceof Mina){
	 System.out.println("Usted perdió");
	 System.out.println(this.obtenerTableroJuego()); 
     } else{

      for (int i = 0 ; i < tableroPantalla.length; i++){
	for (int j = 0 ; j < tableroPantalla[0].length ; j++){
	    if (tableroPantalla[i][j] != null ){
		contador++ ;
		if (contador == casillas){
		    System.out.println("Usted ganó");
		    System.out.println(this.obtenerTableroJuego()); 
		}
	    }
	}
      }
     }
    }

/*Método para marcar una casilla
 *@author Alexis Hernández Magaña
 *@param f - fila del tablero.
 *@param c - columna del tablero.
 */
    public void marcarCasilla(int f, int c){
	tableroPantalla [f][c] = new Marca() ;
	 System.out.println(this);
    }
/*Método para desmarcar una casilla
 *@author Alexis Hernández Magaña
 *@param f - fila del tablero.
 *@param c - columna del tablero.
 */
    public void desmarcarCasilla (int f, int c){
	tableroPantalla [f][c] = null ;
	 System.out.println(this);
    }

/*Método para obtener el tablero de juego
 *@author Alexis Hernández Magaña
 *@return Tablero - Tablero de juego
 */
    public Tablero obtenerTableroJuego(){
	return tablero ;
    }

    
      public Posicion[][] obtenerTableroPantalla(){
	return tableroPantalla ;
    }
    

public String toString(){
    String a = "" ;

    for (int i = 0 ; i < tableroPantalla.length; i++){
	for (int j = 0 ; j < tableroPantalla[0].length ; j++){
	    if (tableroPantalla[i][j] == null){
	       a += "|"  + " " ;	
	    } else {
	     
	    a += "|" + tableroPantalla[i][j] +  "" ;
	    }
	}
	
	a += "|\n" ;
    }
    return a ;
}


}

