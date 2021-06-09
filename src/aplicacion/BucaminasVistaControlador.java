package aplicacion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * clase para el controlador de la vista principal
 */
public class BucaminasVistaControlador {

    /*
    El gridpane donde se colocan las casillas
     */
    @FXML
    private GridPane gridPane ;
    /*boton atrás*/
    @FXML
    private Button boton ;
    /*el campo para los avisos de salida del juego
     */
    @FXML
    private Label salida ;
    /*el tablero del juego*/
    private Posicion[][] tablero ;
    /*el escenario actual*/
    private Stage escenario ;
    /*el número de minas del juego*/
    private int noMinas ;
    /*el contador de las casillas mostradas*/
    private int contador ;
    /*el número total de casillas*/
    private int totalCasillas ;

    //método para agregarle acción a las casillas
    private void agregarAccion(){
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
                            int columna = GridPane.getColumnIndex(s) ;
                            int fila = GridPane.getRowIndex(s);
                            Posicion p = tablero[fila][columna];

                            if(p.getClass() == Mina.class){
                                lanzarAvisoFinal() ;
                                 return ;
                            }
                            contador++ ;
                            revisarGanador();
                        }
                    });
                }
            }
        }
    }

    /**
     * método para moidificar la vista principal de acuerdo al tamaño del tablero
     * solicitado
     * @param col el número de columnas del tablero
     * @param fil el número de fils del tablero
     * @param minas el número de minas en el tablero
     */
    public void modificarVista(int col, int fil, int minas){
        noMinas = minas ;
        totalCasillas = col*fil ;
        contador = 0 ;
        Button boton = null ;
        StackPane stack = null ;
        Juego juego = new Juego(fil, col, minas) ;
        juego.generarPartida();
        tablero = juego.obtenerTableroJuego().obtenerTablero() ;

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
                    String noVecinos =   numero.toString();
                    Label label = new Label(noVecinos) ;
                    label.setStyle("-fx-text-fill:red");
                    stack.getChildren().addAll(label, boton);
                    gridPane.add(stack, i, j);
                }
            }
        }
        agregarAccion();
    }

    //lanza un aviso de final de juego con alert
    private void lanzarFinal(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setContentText("Lo sentimos, usted ha perdido");
        alert.setTitle("Buscaminas");
        alert.setHeaderText("");
       // alert.setWidth(600);
        //alert.setHeight(400);
        alert.show();
        mostrarTablero();
    }

    /**
     * método que avisa el final del juego al usuario
     */
    public void lanzarAvisoFinal(){
        salida.setText("¡Lo sentimos, usted ha perdido!");
        salida.setStyle("-fx-font-weight:bold");
        mostrarTablero();
    }


    //Muestra las minas en el tablero
    private void mostrarTablero(){
        ObservableList<Node> hijos = gridPane.getChildren();
        for(Node n: hijos){
            StackPane s = (StackPane) n ;
            ObservableList<Node> lista = s.getChildren() ;
            for(Node nd : lista) {
                if(nd instanceof Button) {
                    Button b = (Button) nd;
                    b.setVisible(false);
                }
            }
        }
    }

    //método para revisar si se ganó el juego, si ya solo quedan minas sin mostrar
    public void revisarGanador(){
        if((totalCasillas-contador) == noMinas){
            salida.setText("¡¡¡Felicidades, usted ganó!!!");
            salida.setStyle("-fx-font-weight:bold");
            mostrarTablero();
        }
    }

    /**
     * método que carga la vista de inicio nuevamente
     * @param event
     */
    @FXML private void accionAtras(ActionEvent event){
        cargarVistaInicio();
    }

    //método para cargar la vista de inicio
    private void cargarVistaInicio(){
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("VistaInicio.fxml"));
        VBox root = null;
        try {
            root = (VBox) cargador.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Buscaminas");
        Scene escena = new Scene(root) ;
        primaryStage.setScene(escena);
        InicioControlador controladorInicio = cargador.getController() ;
        controladorInicio.setEscenario(primaryStage);
        primaryStage.show();
        escenario.close();
    }

    /**
     * Establece el escenario actual
     * @param escenario escenario actual
     */
    public void setEscenario(Stage escenario){
        this.escenario = escenario ;

    }


}
