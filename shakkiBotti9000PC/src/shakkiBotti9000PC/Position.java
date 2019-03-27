package shakkiBotti9000PC;

import piece.IPiece;
import piece.Piece;
/**
 * Data class used to handle the situation of the board outside of the board
 * mainly to print the current board situation to the console
 * @author Antti Alho
 */
public class Position{
	
	private int x;
	private int y;
	private Piece piece;

	/**
	 * @param x coordinate of this position on the board
	 * @param y coordinate of this position on the board
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * returns the String representation of this position 
	 * @return String " " or the letter representing the piece on this position
	 */
    public String getPieceString() {
        if (piece == null) {
            return " ";
        }
        if (piece.getColour()) {
        	return piece.getName();
        } else {
			return piece.getName().toUpperCase();
		}
    }
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
