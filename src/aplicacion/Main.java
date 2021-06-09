package aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * clase que carga la vista de inicio
 */
public class Main extends Application {


    /**
     * método que carga y muestra la vista de inicio
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("VistaInicio.fxml"));
        VBox root = (VBox) cargador.load() ;
        primaryStage.setTitle("Buscaminas");
        Scene escena = new Scene(root) ;
        primaryStage.setScene(escena);
        InicioControlador controladorInicio = cargador.getController() ;
        controladorInicio.setEscenario(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
