package shakkiBotti9000PC;

import piece.Piece;

public class Move {
	
	private Piece p;
	private Position newPos;
	private Position oldPos;
	
	public Move(Piece p, Position pos) {
		this.p = p;
		oldPos = p.getPos();
		this.newPos = pos;
	}

	public Piece getP() {
		return p;
	}

	public void setP(Piece p) {
		this.p = p;
	}

	public Position getNewPos() {
		return newPos;
	}

	public Position getOldPos() {
		return oldPos;
	}
	
}
