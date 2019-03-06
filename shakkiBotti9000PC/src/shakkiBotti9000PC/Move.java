package shakkiBotti9000PC;

import java.util.Comparator;

import piece.Piece;

public class Move implements Comparator<Move> {
	
	private Piece p;
	private Position newPos;
	private Position oldPos;
	private Piece target;
	
	public Move(Piece p, Position pos) {
		this.p = p;
		oldPos = p.getPos();
		this.newPos = pos;
		if (pos.getPiece() != null) {
			this.target = pos.getPiece();
		}
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
	
	public Piece getTarget() {
		return target;
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

	public int compare(Move m1, Move m2) {
		int x = m1.getNewPos().getX()*10+m1.getNewPos().getY();
		int y = m2.getNewPos().getX()*10+m2.getNewPos().getY();
		return x-y;
	}
	
}
