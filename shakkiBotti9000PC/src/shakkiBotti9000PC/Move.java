package shakkiBotti9000PC;

import piece.Piece;

public class Move {
	
	Piece p;
	Position pos;
	
	public Move(Piece p, Position pos) {
		this.p = p;
		this.pos = pos;
	}
	
	public Piece getP() {
		return p;
	}
	public void setP(Piece p) {
		this.p = p;
	}
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
}
