package view;

import javafx.application.Application;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import piece.Piece;
import shakkiBotti9000PC.Position;

public class cameraQUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {

    	Position[][] newPosition = new Position[8][8];

        Pane ruutu = new Pane();
        stage.setScene(new Scene(ruutu));
        stage.show();
        
        File file= new File("board.png");
		BufferedImage image = ImageIO.read(file);
		boolean find = false;
		int n = 22;
		int m = 22;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				newPosition[j][i] = new Position(i, j);
				find = false;
				for (int j2 =77+i*54+n; j2 <=77+(i+1)*54; j2+=2) {
					for (int k =9+j*54+m; k <9+(j+1)*54; k+=2) {
						int clr =  image.getRGB(j2, k);
						int red = (clr & 0x00ff0000) >> 16;
						int green = (clr & 0x0000ff00) >> 8;
						int blue = clr & 0x000000ff;
						if (red <= 255 && red >= 115 && green <= 100 && green >= 0 && blue <= 110 && blue >= 0) {
							if (find == false) {
								System.out.println("Found in " + j + " " + i);
								find = true;
								newPosition[j][i].setPiece(new Piece(Piece.WHITE,i,j));
							}
							image.setRGB(j2, k, 0x0000ff00);
						}
					}
				}
			}
		}
		ImageIO.write(image,  "PNG", new File("board.png"));
		Image kuvatiedosto = new Image("file:board.png");
        ImageView kuva = new ImageView(kuvatiedosto);
        ruutu.getChildren().add(kuva);
    }

    public static void main(String[] args) throws IOException {
    	launch(args);
    }

}
