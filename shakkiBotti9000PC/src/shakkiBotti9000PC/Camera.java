package shakkiBotti9000PC;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;
import piece.Piece;

public class Camera {

	private Position[][] newPosition = new Position[8][8];
	private Board board;
	private Webcam webcam;
		
	public Camera(Board board) {
		this.board = board;
		Pattern pat = Pattern.compile("Logitech QuickCam 3000 .");
		Matcher m;
		for (Webcam webcams : Webcam.getWebcams()) {
			System.out.println(webcams.getName());
			m = pat.matcher(webcams.getName());
			if (m.matches()) {
				this.webcam = webcams;
				this.webcam.setViewSize(webcam.getViewSizes()[2]);
				this.webcam.open();
			}
		}
	}

	public void takePicture() {

		try {
			ImageIO.write(webcam.getImage(), "PNG", new File("board.png"));
			Dimension[] d = webcam.getViewSizes();
			for (int i = 0; i < d.length; i++) {
//				System.out.println(d[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void analyzePicture() {
		
		
		try {
			 File file= new File("board.png");
			BufferedImage image = ImageIO.read(file);
			boolean find = false;
			int n = 20;
			int m = 20;
			for (int i = 0; i < 8; i++) {
//					System.out.println("----------- " +i);
				for (int j = 0; j < 8; j++) {
					newPosition[j][i] = new Position(i, j);
					find = false;
//						System.out.println("j = "+j);
					for (int j2 =78+i*54+n; j2 <=78+(i+1)*54; j2+=2) {
						for (int k =10+j*54+m; k <10+(j+1)*54; k+=2) {
							
							int clr =  image.getRGB(j2, k);
							int red = (clr & 0x00ff0000) >> 16;
							int green = (clr & 0x0000ff00) >> 8;
							int blue = clr & 0x000000ff;
//							image.setRGB(j2, k, 0x00ff0000);
							if (red <= 255 && red >= 110 && green <= 69 && green >= 0 && blue <= 90 && blue >= 0) {
								if (find == false) {
									//System.out.println("Found in " + i + " " + j);
									find = true;
									newPosition[j][i].setPiece(new Piece(newPosition[j][i], Piece.WHITE));
								}
//								image.setRGB(j2, k, 0x000000ff);
							}
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Position[][] getEnemymove() {
		return newPosition;
	}
	
	public void situation() {
		
			Position[][] positions = board.getPositions();
			Position[][] enemy = getEnemymove();
			
			Position[][] whites = board.getPositions();
			for (int i = 0; i < positions.length; i++) {
				for (int j = 0; j < positions.length; j++) {
					if (positions[i][j].getPiece() != null) {
						if (positions[i][j].getPiece().getColour()==Piece.WHITE && positions[i][j].getPiece() != null) {
							whites[i][j] = positions[i][j];
						}
					}
				}
			}
			int x=0;
			int y=0;
			int z=0;
			int v=0;
			for (int i = 0; i < positions.length; i++) {
				for (int j = 0; j < positions.length; j++) {
					positions[i][j].getPieceString();
					enemy[i][j].getPieceString();
					
					if (whites[i][j].getPiece() == null && enemy[i][j].getPiece() != null) {
						x = i;
						y = j;
//						System.out.println(x +" "+ y);
					}
					else if (whites[i][j].getPiece() != null && enemy[i][j].getPiece() == null) {
						z = i;
						v = j;
						
					}
				}
			}
			positions[x][y].setPiece(positions[z][v].getPiece());
			positions[z][v].setPiece(null);
//			System.out.println(positions[x][y].getPieceString()+ " -"+x + " "+y);
	}
	
}
