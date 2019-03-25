package shakkiBotti9000PC;

import piece.Piece;
/**
 * Data class that saves the information to replicate or undo every move made on board.
 */
public class Move {
	
	private Piece p;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
	private Piece target;
	
	/**
	 * @param p the piece that is going to move
	 * @param x coordinate where to move
	 * @param y coordinate where to move
	 * @param target possible target that is going to be removed from the board 
	 * when the move is executed, should be null if there is no target.
	 */
	public Move(Piece p, int x, int y, Piece target) {
		this.p = p;
		this.oldX = p.getX();
		this.oldY = p.getY();
		this.newX = x;
		this.newY = y;
		this.target = target;
	}

	public Piece getP() {
		return p;
	}

	public void setP(Piece p) {
		this.p = p;
	}
	
	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	public int getNewX() {
		return newX;
	}

	public void setNewX(int newX) {
		this.newX = newX;
	}

	public int getNewY() {
		return newY;
	}

	public void setNewY(int newY) {
		this.newY = newY;
	}

	public Piece getTarget() {
		return target;
	}
	
	public void setTarget(Piece piece) {
		this.target = piece;
	}

	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode * 33 ^ newX;
		hashcode = hashcode * 33 ^ newY;
		hashcode = hashcode * 33 ^ oldX;
		hashcode = hashcode * 33 ^ oldY;
		hashcode = hashcode * 33 ^ p.getValue();
		hashcode = hashcode * 33 ^ p.hashCode();
        return hashcode;
    }
	
	@Override
	public String toString() {
		return p+"("+oldX+","+oldY+") to ("+newX+ ","+ newY+")";
	}
	
}
