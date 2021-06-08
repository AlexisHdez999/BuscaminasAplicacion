package aplicacion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BucaminasVistaControlador {

    @FXML
    GridPane gridPane ;

    public void agregarAccion(){
        ObservableList<Node> hijos = gridPane.getChildren();
        for(Node n: hijos){
            StackPane s = (StackPane) n ;
            ObservableList<Node> lista = s.getChildren() ;
            for(Node nd : lista) {
                if(nd instanceof Button) {
                    Button b = (Button) nd;
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            b.setVisible(false);
                        }
                    });
                }
            }
        }
    }

    public void modificarVista(int col, int fil, int minas){
        Button boton = null ;
        StackPane stack = null ;
        Juego juego = new Juego(fil, col, minas) ;
        juego.generarPartida();
        Posicion[][] tablero = juego.obtenerTableroJuego().obtenerTablero() ;

        for(int i=0 ; i < col ; i++){
            for(int j=0 ; j < fil ; j++) {
                stack = new StackPane();
                boton = new Button();
                boton.setPrefSize(20, 20);
                boton.setText("");
                Posicion posicion = tablero[j][i];
                if (posicion.getClass() == Mina.class) {
                    Image mina = new Image("aplicacion/mina.png");
                    ImageView imagen = new ImageView(mina);
                    imagen.setFitHeight(20);
                    imagen.setFitWidth(20);
                    stack.getChildren().addAll(imagen, boton);
                    gridPane.add(stack, i, j);
                } else {
                    Numero numero = (Numero) tablero[j][i] ;
                    Label label = new Label(numero.toString()) ;
                    stack.getChildren().addAll(label, boton);
                    gridPane.add(stack, i, j);
                }
            }
        }
        agregarAccion();
    }

    public void agregarImagen(){
        Image mina = new Image("aplicacion/mina.png") ;
        ImageView imagen = new ImageView(mina );
        gridPane.add(imagen, 1,2) ;
    }
}
