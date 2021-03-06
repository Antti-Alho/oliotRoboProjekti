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

/**
 * Takes pictures and parses the new positions for the pieces from the images.
 * @author Heidi Joensuu
 */
public class Camera {
	private Board board;
	private Webcam webcam;
	private Position[][] newPosition;
	
	/**
	 * The camera constructor creates connection to the webcam
	 * change the name of the camera if your camera is not Logitech QuickCam 3000
	 * or if you have multiple of the same cameras connected
	 * @param board current board in play
	 */
	public Camera(Board board) {
		newPosition = new Position[8][8];
		this.board = board;
		Pattern pat = Pattern.compile("Logitech QuickCam 3000 1");
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
	 * Takes picture of the board with the webcam
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
	 * analyses picture separating "white" pieces from the picture 
	 */
	private void analysePicture() {
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
							if (red <= 255 && red >= 115 && green <= 100 && green >= 0 && blue <= 110 && blue >= 0) {
								if (find == false) {
									find = true;
									newPosition[j][i].setPiece(new Piece(Piece.WHITE,i,j));
								}
								image.setRGB(j2, k, 0x0000ff00);
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
	 *  Does the changes to the board that were found in the picture
	 */
	public void enemyMove() {
		Position[][] copyOfOriginal = board.getPositions();
		analysePicture();
		Position[][] endPositionWhite = movePiece(createWhiteArray(copyOfOriginal), newPosition);
		Position[][] movePiece;
	}
	
	/**
	 * creates new list of the positions of white pieces on the board
	 * @param original positions from board
	 * @return white piece list
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
	 * checks the differences of current pieces and old pieces and then creates 
	 * an array of positions with the updated white piece positions
	 * @param whitePieces old positions of white pieces
	 * @param newPosition new positions of generic white pieces
	 * @return Position[][] Real pieces from white pieces in the positions of generic pieces from new positions
	 */
	private Position[][] movePiece(Position[][] whitePieces, Position[][] newPosition) {
		Position[][]newWhitePositions = newPosition;
		int x=0;
		int y=0;
		int z=0;
		int v=0;
		if((whitePieces[7][6].getPiece() == null && newWhitePositions[7][6].getPiece() != null) 
				&&(whitePieces[7][5].getPiece() == null && newWhitePositions[7][5].getPiece() != null)) {
			board.move(new Move(board.pieceAt(7, 7), 7,5,board.pieceAt(7,5)));
			board.move(new Move(board.pieceAt(7, 4),7,6,board.pieceAt(7,6)));			
		}else if ((whitePieces[7][2].getPiece() == null && newWhitePositions[7][2].getPiece() != null) 
				&&(whitePieces[7][3].getPiece() == null && newWhitePositions[7][3].getPiece() != null)) {
			board.move(new Move(board.pieceAt(7, 0), 7,3,board.pieceAt(7,3)));
			board.move(new Move(board.pieceAt(7, 4),7,2,board.pieceAt(7,2)));
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
			board.move(new Move(board.pieceAt(z, v),x,y,board.pieceAt(x, y)));
		}
		return whitePieces;
	}
}
