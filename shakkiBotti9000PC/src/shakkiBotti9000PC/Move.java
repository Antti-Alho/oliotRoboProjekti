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
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode * 33 ^ newPos.getX();
		hashcode = hashcode * 33 ^ newPos.getY();
		hashcode = hashcode * 33 ^ oldPos.getX();
		hashcode = hashcode * 33 ^ oldPos.getY();
		hashcode = hashcode * 33 ^ p.getValue();
		hashcode = hashcode * 33 ^ p.hashCode();
        return hashcode;
    }
	
	@Override
	public String toString() {
		return "("+oldPos.getX()+","+oldPos.getY()+") to ("+newPos.getX()+ ","+ newPos.getY()+")";
	}
	
}
