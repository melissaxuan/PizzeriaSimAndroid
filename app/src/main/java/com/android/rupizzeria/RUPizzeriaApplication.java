//package projects.rupizzeria.rupizzeria;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import projects.rupizzeria.rupizzeria.controllers.MainController;
//
//import java.io.IOException;
//
///**
// * Class to load the RUPizzeria GUI.
// * @author Melissa Xuan
// */
//public class RUPizzeriaApplication extends Application {
//    /**
//     * Loads the first scene in the GUI.
//     * @param stage
//     * @throws IOException
//     */
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(RUPizzeriaApplication.class.getResource("RUPizzeria-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 608, 500);
//        MainController mainController = fxmlLoader.getController();
//        mainController.setPrimaryStage(stage, scene);
//        stage.setTitle("RU Pizzeria");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    /**
//     * Main method to launch the GUI.
//     * @param args
//     */
//    public static void main(String[] args) {
//        launch();
//    }
//}