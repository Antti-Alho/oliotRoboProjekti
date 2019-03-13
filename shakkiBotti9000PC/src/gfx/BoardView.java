package gfx;

import controller.BoardController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Position;

public class BoardView extends Application {
	
	private GridPane gridPane = new GridPane();
	BoardController bc;
	Scene scene;
	Stage stage;
	
	@Override 
	public void start(Stage stage) {
		this.bc = new BoardController(this);
		this.stage = stage;
		//Creating a Grid Pane 
		//Setting size for the pane  
		gridPane.setMinSize(520, 520); 
  
		Position[][] pos = bc.getPositions();
  
		//Setting the Grid alignment 
		gridPane.setAlignment(Pos.CENTER); 
   
		//Arranging all the nodes in the grid 
		boolean vari = true; 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Pane pane = new Pane();
				System.out.println(StringToImg(pos[i][j].getPieceString()));
				pane.setPrefSize(65, 65);
				if (StringToImg(pos[j][i].getPieceString()) != null){
					System.out.println("asd");
					pane.getChildren().addAll(StringToImg(pos[j][i].getPieceString()));
				}
				if (vari) pane.setStyle("-fx-background-color: WHITE;");
				else pane.setStyle("-fx-background-color: #b35900;");
				gridPane.add(pane,i, j);
				vari = !vari;
			}
	  
			vari = !vari;
		}
		//Creating a scene object 
		Scene scene = new Scene(gridPane);  
  
		//Setting title to the Stage 
		stage.setTitle("Shakkibotti 9000"); 
     
		//Adding scene to the stage 
		stage.setScene(scene); 
     
		//Displaying the contents of the stage 
		stage.show(); 
	} 
	public static void main(String[] args) {
		launch(args);
	}
   
	private ImageView StringToImg(String x) {
		switch (x) {
		case "p":
			return new ImageView(new Image("resources/blackpawn.png"));
	   
		case "P":
			return new ImageView(new Image("resources/whitepawn.png"));
	   
		case "r":
			return new ImageView(new Image("resources/blackrook.png"));
	   
		case "R":
			return new ImageView(new Image("resources/whiterook.png"));
	   
		case "b":
			return new ImageView(new Image("resources/blackbishop.png"));
	   
		case "B":
			return new ImageView(new Image("resources/whitebishop.png"));
	   
		case "k":
			return new ImageView(new Image("resources/blackking.png"));
	   
		case "K":
			return new ImageView(new Image("resources/whiteking.png"));
	   
		case "q":
			return new ImageView(new Image("resources/blackqueen.png"));	   
	   
		case "Q":
			return new ImageView(new Image("resources/whitequeen.png"));
		
		case "n":
			return new ImageView(new Image("resources/blackknight.png"));	   
	   
		case "N":
			return new ImageView(new Image("resources/whiteknight.png"));
		}
		return null;	  
	}
   
	public void update() {	   
		Position[][] pos = bc.getPositions();
		boolean vari = true; 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Pane pane = new Pane();
				System.out.println(StringToImg(pos[i][j].getPieceString()));
				pane.setPrefSize(65, 65);
				if (StringToImg(pos[j][i].getPieceString()) != null){
					pane.getChildren().addAll(StringToImg(pos[j][i].getPieceString()));
				}
				if (vari) pane.setStyle("-fx-background-color: WHITE;");
				else pane.setStyle("-fx-background-color: #b35900;");
				gridPane.add(pane,i, j);
				vari = !vari;
			}
			vari = !vari;
		}
		//Creating a scene object 
		scene = new Scene(gridPane);  
		//Setting title to the Stage 
		stage.setTitle("Shakkibotti 9000"); 
     
		//Adding scene to the stage 
		stage.setScene(scene); 
     
		//Displaying the contents of the stage 
		stage.show();   
	} 
} 
	