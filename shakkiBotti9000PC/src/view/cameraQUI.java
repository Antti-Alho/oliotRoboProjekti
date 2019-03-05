package view;
import javafx.application.Application;
import static javafx.application.Application.launch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class cameraQUI extends Application {

    @Override
    public void start(Stage stage) {

        Image kuvatiedosto = new Image("file:board.png");
        ImageView kuva = new ImageView(kuvatiedosto);

        Pane ruutu = new Pane();
        ruutu.getChildren().add(kuva);

        stage.setScene(new Scene(ruutu));
        stage.show();

    }

    public static void main(String[] args) throws IOException {
		File file= new File("board.png");
		BufferedImage image = ImageIO.read(file);
		boolean find = false;
		// Getting pixel color by position x and y 
		for (int i = 180; i <= 220; i += 2) {
			for (int j = 300; j <= 340; j += 2) {
				
				
				
				int clr =  image.getRGB(i, j);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;
				if (red <= 28 && red >= 7 && green <= 93 && green >= 11 && blue <= 158 && blue >= 70) {
					System.out.println("Red Color value = "+ red);
					System.out.println("Green Color value = "+ green);
					System.out.println("Blue Color value = "+ blue);
					System.out.println("Coords = " + i + " " +j);
					i = 220;
					j = 339;
					find = true;
				if (i == 220 && j == 340 && find == false) {
					System.out.println("Ei nappulaa");
				}
					
				}
			}
			
		}
		
		
		
		
		
   // 	launch(args);
    }

}