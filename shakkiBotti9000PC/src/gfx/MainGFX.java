package gfx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGFX extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ShakkiBotti9000");

        initRootLayout();

        showChessGUI();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGFX.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the chess GUI inside the root layout.
     */
    public void showChessGUI() {
        try {
            // Load chessGUI
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGFX.class.getResource("view/ChessGUI.fxml"));
            BorderPane chessGUI = (BorderPane) loader.load();
            
            // Set chessGUI into the center of root layout.
            rootLayout.setCenter(chessGUI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void Nobb() {
    	System.out.println("aaaach");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}