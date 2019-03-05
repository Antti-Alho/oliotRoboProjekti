package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Rook extends Piece{
  
	public Rook(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setValue(50);
		} else {
			super.setValue(-50);
		}
	}
	
	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this including the position evaluation that is read from the evaluation table.
	 */
	@Override
	public int getValue() {
		return eval[super.getPos().getX()][super.getPos().getY()] + super.getValue();
	}
	
	/**
	 * returns an ArrayList of moves the piece can currently take:
	 * @param Board the board where this piece is.
	 * @return ArrayList<Move> of moves the piece can currentru take.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		for (int i = x; i <= 8; i++) {
			Position newPos = new Position(i, y);
			if (board.canEatOrMove(this,newPos)) {
				newLegalMoves.add(new Move(this, newPos));
			}
		}
		for (int i = x; i >= 0; i--) {
			Position newPos = new Position(i, y);
			if (board.canEatOrMove(this,newPos)) {
				newLegalMoves.add(new Move(this, newPos));
			}
		}
		for (int i = y; i <= 8; i++) {
			Position newPos = new Position(x, i);
			if (board.canEatOrMove(this,newPos)) {
				newLegalMoves.add(new Move(this, newPos));
			}
		}
		for (int i = y; i >= 0; i--) {
			Position newPos = new Position(x, i);
			if (board.canEatOrMove(this,newPos)) {
				newLegalMoves.add(new Move(this, newPos));
			}
		}
		
		return newLegalMoves;
	}
  
	/**
	 * Returns the string that represents this piece in the command line UI
	 */
	@Override
	  public String getName(){
	    String s = "r";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	
	private int[][] eval = {
		{  0,  0,  0,  0,  0,  0,  0,  0},
		{  1,  2,  2,  2,  2,  2,  2,  1},
		{ -1,  0,  0,  0,  0,  0,  0, -1},
		{ -1,  0,  0,  0,  0,  0,  0, -1},
		{ -1,  0,  0,  0,  0,  0,  0, -1},
		{ -1,  0,  0,  0,  0,  0,  0, -1},
		{ -1,  0,  0,  0,  0,  0,  0, -1},
		{  0,  0,  0,  1,  1,  0,  0,  0}
	};

}
