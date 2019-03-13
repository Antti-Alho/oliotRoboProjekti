package gfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gfx.view.*;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGFX extends Application implements Initializable {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @FXML
    public Label label11;
    @FXML
    public Label label13;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ShakkiBotti9000");
        

        initRootLayout();
        showBoardGFX();
        
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
           
       //   primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the chess GUI inside the root layout.
     */
    
    public void showBoardGFX() {
        try {
            // Load chessGUI
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGFX.class.getResource("view/BoardGFX.fxml"));
            AnchorPane boardGFX = (AnchorPane) loader.load();
            
            // Set chessGUI into the center of root layout.
            rootLayout.setCenter(boardGFX);
            nobb();
            
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

  @FXML 
  	protected void nobb() {
	  System.out.println(label13);
	  label13.setText("1257125");
	  System.out.println("lasfokgjpjapksak");
    }
    
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}