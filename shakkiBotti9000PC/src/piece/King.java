package piece;


import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class King extends Piece{
	public King(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setValue(900);
		} else {
			super.setValue(-900);
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
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		while (x <= 8 && y <= 8 ) {
			x++;
			y++;
			newLegalMoves.add(new Move(this,new Position(x, y)));
			if (board.containsPiece(new Position(x, y))) {
				break;
			}
		}
		
		return null;
	}

	/**
	 * Returns the string that represents this piece in the command line UI
	 */
	@Override
	  public String getName(){
	    String s = "k";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	

	private int[][] endEval = {
		{ -5, -4, -3, -2, -2, -3, -4, -5},
		{ -3, -2, -1,  0,  0, -1, -2, -3},
		{ -3, -1,  2,  3,  3,  2, -1, -3},
		{ -3, -1,  3,  4,  4,  3, -1, -3},
		{ -3, -1,  3,  4,  4,  3, -1, -3},
		{ -3, -1,  2,  3,  3,  2, -1, -3},
		{ -3, -3,  0,  0,  0,  0, -3, -3},
		{ -5, -3, -3, -3, -3, -3, -1, -5}
	};
	
	private int[][] eval = {
		{ -5, -4, -3, -2, -2, -3, -4, -5},
		{ -3, -2, -1,  0,  0, -1, -2, -3},
		{ -3, -1,  2,  3,  3,  2, -1, -3},
		{ -3, -1,  3,  4,  4,  3, -1, -3},
		{ -3, -1,  3,  4,  4,  3, -1, -3},
		{ -3, -1,  2,  3,  3,  2, -1, -3},
		{ -3, -3,  0,  0,  0,  0, -3, -3},
		{ -5, -3, -3, -3, -3, -3, -3, -5}
	};

}
