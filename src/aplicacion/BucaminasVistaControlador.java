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

public class BucaminasVistaControlador {

    @FXML
    private GridPane gridPane ;
    @FXML
    private Button boton ;
    @FXML
    private Label salida ;

    private Posicion[][] tablero ;

    private Stage escenario ;
    private int noMinas ;
    private int contador ;
    private int totalCasillas ;

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

    public void lanzarFinal(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setContentText("Lo sentimos, usted ha perdido");
        alert.setTitle("Buscaminas");
        alert.setHeaderText("");
       // alert.setWidth(600);
        //alert.setHeight(400);
        alert.show();
        mostrarTablero();
    }

    public void lanzarAvisoFinal(){
        salida.setText("¡Lo sentimos, usted ha perdido!");
        salida.setStyle("-fx-font-weight:bold");
        mostrarTablero();
    }

    public void mostrarTablero(){
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

    public void revisarGanador(){
        if((totalCasillas-contador) == noMinas){
            salida.setText("¡¡¡Felicidades, usted ganó!!!");
            salida.setStyle("-fx-font-weight:bold");
            mostrarTablero();
        }
    }


    @FXML private void accionAtras(ActionEvent event){
        cargarVistaInicio();
    }


    public void cargarVistaInicio(){
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


    public void setEscenario(Stage escenario){
        this.escenario = escenario ;

    }


}
