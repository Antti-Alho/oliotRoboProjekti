package shakkiBotti9000PC;

import piece.IPiece;
import piece.Piece;

public class Position{
	
	private int x;
	private int y;
	private Piece piece;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
