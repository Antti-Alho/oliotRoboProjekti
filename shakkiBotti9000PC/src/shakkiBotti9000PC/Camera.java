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
	private Board board;
	private Webcam webcam;
	private Position[][] newPosition;
	/**
	 * 
	 * The camera constructor takes connection to the webcamera
	 */
	public Camera(Board board) {
		newPosition = new Position[8][8];
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

	/**
	 * Takes picture of a board with a camera
	 */
	public void takePicture() {
		try {
			ImageIO.write(webcam.getImage(), "PNG", new File("board.png"));
			Dimension[] d = webcam.getViewSizes();
			for (int i = 0; i < d.length; i++) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * analyzes picture separating "white" pieces from the picture 
	 */
	private void analyzePicture() {
		try {
			File file= new File("board.png");
			BufferedImage image = ImageIO.read(file);
			boolean find = false;
			int n = 22;
			int m = 22;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					newPosition[j][i] = new Position(i, j);
					find = false;
					for (int j2 =84+i*54+n; j2 <=84+(i+1)*54; j2+=2) {
						for (int k =9+j*54+m; k <9+(j+1)*54; k+=2) {
							int clr =  image.getRGB(j2, k);
							int red = (clr & 0x00ff0000) >> 16;
							int green = (clr & 0x0000ff00) >> 8;
							int blue = clr & 0x000000ff;
							if (red <= 255 && red >= 130 && green <= 110 && green >= 0 && blue <= 90 && blue >= 0) {
								if (find == false) {
									find = true;
									newPosition[j][i].setPiece(new Piece(newPosition[j][i], Piece.WHITE));
								}
								image.setRGB(j2, k, 0xffffffff);
							}
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 
	 * @return new game situation to the use 
	 */
	public Position[][] getEnemymove() {
		Position[][] copyOfOriginal = board.getPositions();
		analyzePicture();
		Position[][] endPositionWhite = movePiece(createWhiteArray(copyOfOriginal), newPosition);
//		System.out.println("endPositionWhite");
//		print(endPositionWhite);
		Position[][] endPositionBlack = createBlackArray(copyOfOriginal);
//		System.out.println("endPositionBlack");
//		print(endPositionBlack);
		Position[][] endPosition = newSituation(endPositionWhite,endPositionBlack);
//		System.out.println("endPosition");
		print(endPosition);
		return endPosition;
	}
	
	/**
	 * creates new list of old positions of whites
	 * @param positions from board
	 * @return whitepieces list
	 */
	private Position[][] createWhiteArray(Position[][] original) {
		Position[][] enemy = new Position[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				enemy[i][j] = new Position(i, j);
				if (original[i][j].getPiece() != null) {
					if (original[i][j].getPiece().getColour() == Piece.WHITE) {
						enemy[i][j].setPiece(original[i][j].getPiece());
					}
				}
			}
		}
		return enemy;
	}
	
	/**
	 * checks the differences of current pieces and old pieces and then saves
	 * new changes to the whitepieces list
	 * @param whitePieces as a old list
	 * @param newPosition  as a new list
	 * @return
	 */
	private Position[][] movePiece(Position[][] whitePieces, Position[][] newPosition) {
		Position[][]newWhitePositions = newPosition;
		int x=0;
		int y=0;
		int z=0;
		int v=0;
		if((whitePieces[7][6].getPiece() == null && newWhitePositions[7][6].getPiece() != null) 
				&&(whitePieces[7][5].getPiece() == null && newWhitePositions[7][5].getPiece() != null)) {
			Piece piece1 = board.positions[7][7].getPiece();
			whitePieces[7][5].setPiece(piece1);
			whitePieces[7][7].setPiece(null);
			
			Piece piece2 = board.positions[7][4].getPiece();
			whitePieces[7][6].setPiece(piece2);
			whitePieces[7][4].setPiece(null);
		}else if ((whitePieces[7][2].getPiece() == null && newWhitePositions[7][2].getPiece() != null) 
				&&(whitePieces[7][3].getPiece() == null && newWhitePositions[7][3].getPiece() != null)) {
			Piece piece1 = board.positions[7][0].getPiece();
			whitePieces[7][3].setPiece(piece1);
			whitePieces[7][0].setPiece(null);
			
			Piece piece2 = board.positions[7][4].getPiece();
			whitePieces[7][2].setPiece(piece2);
			whitePieces[7][4].setPiece(null);
		}else {
			for (int i = 0; i < whitePieces.length; i++) {
				for (int j = 0; j < whitePieces.length; j++) {
					if (whitePieces[i][j].getPiece() == null && newWhitePositions[i][j].getPiece() != null) {
						x = i;
						y = j;
	
					}
					else if (whitePieces[i][j].getPiece() != null && newWhitePositions[i][j].getPiece() == null) {
						z = i;
						v = j;
					}
				}
			}
			Piece piece = board.positions[z][v].getPiece();
			whitePieces[x][y].setPiece(piece);
			whitePieces[z][v].setPiece(null);
		}
		return whitePieces;
	}

	/**
	 * creates list of black pieces positions
	 * @param blacks pieces
	 * @return list of blacks
	 */
	private Position[][] createBlackArray(Position[][] original) {
		Position[][] blacks = new Position[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				blacks [i][j] = new Position(i, j);
				if (original[i][j].getPiece() != null) {
					if (original[i][j].getPiece().getColour() ==Piece.BLACK) {
						blacks[i][j].setPiece(original[i][j].getPiece());
					}
				}
			}
		}
		return blacks;
	}
	
	/**
	 * changes pieces places 
	 * @param enemy
	 * @param blacks
	 * @return new list of pieces positions
	 */
	private Position[][] newSituation(Position[][] enemy, Position[][] blacks) {
		Position[][] positions = board.getPositions();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				positions[i][j].setPiece(null);
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (blacks[i][j].getPiece() != null && enemy[i][j].getPiece() == null) {
					positions[i][j].setPiece(blacks[i][j].getPiece());
				}
				if (enemy[i][j].getPiece() != null) {
					positions[i][j].setPiece(enemy[i][j].getPiece());
					System.out.println(i + " " + j +": "+ positions[i][j].getPieceString());
				//	enemy[i][j].setPiece(null);
				}
			}
		}
		return positions;
	}
	
	private void print(Position[][] positions) {
		
		System.out.println("--------------------");
		System.out.println("a b c d e f g h \n");
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
	}
}
