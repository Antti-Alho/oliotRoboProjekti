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
	 * this includes the position evaluation that is read from the evaluation table.
	 */
	@Override
	public int getValue() {
		return eval[super.getPos().getX()][super.getPos().getY()] + super.getValue();
	}
	
	/**
	 * returns an ArrayList of all possible moves the piece can currently take.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		
		int i = x+1;
		int j = y+1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}

		
		i = x-1;
		j = y-1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x-1;
		j = y+1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x+1;
		j = y-1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x+1;
		j = y;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x-1;
		j = y;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x;
		j = y+1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		
		i = x;
		j = y-1;
		if (isLegal(i, j, board)) {
			newLegalMoves.add(new Move(this, board.getPositions()[i][j]));
		}
		return newLegalMoves;
	}

	
	private boolean isLegal(int i, int j, Board board) {
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (board.getPositions()[i][j].getPiece() == null) {
				return true;
			} else if (board.getPositions()[i][j].getPiece().getColour() != this.getColour()){
				return true;
			}
			return false;
		}
		return false;
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
