package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Knight extends Piece{
	public Knight(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setValue(30);
		} else {
			super.setValue(-30);
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
	@Override
	  public String getName(){
	    String s = "n";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	private int[][] eval = {
			{ -5, -4, -3, -3, -3, -3, -5, -5},
			{ -4, -2,  0,  0,  0,  0, -2, -4},
			{ -3,  0,  1,  2,  2,  1,  0, -3},
			{ -3,  1,  2,  4,  4,  2,  1, -3},
			{ -3,  0,  2,  4,  4,  2,  0, -3},
			{ -3,  1,  1,  2,  2,  1,  1, -3},
			{ -4, -2,  0,  1,  1,  0, -2, -4},
			{ -5, -4, -3, -3, -3, -3, -4, -5}
	};

}
