package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Knight extends Piece{
	public Knight(Boolean colour, int x, int y) {
		super(colour, x, y);
		if (colour) {
			super.setValue(30);
		} else {
			super.setValue(-30);
		}
	}
	
	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this includes the position evaluation that is read from the evaluation table.
	 */
	@Override
	public int getValue() {
		return eval[super.getX()][super.getY()] + super.getValue();
	}
	
	/**
	 * returns an ArrayList of all possible moves the piece can currently take.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getX();
		int y = this.getY();
		
		int[] is = {x+2,x-2,x+2,x-2,x+1,x-1,x+1,x-1};
		int[] js = {y+1,y-1,y-1,y+1,y+2,y-2,y-2,y+2};
		for (int i = 0; i < is.length; i++) {
			if (isLegal(is[i], js[i], board)) {
			newLegalMoves.add(new Move(this, is[i], js[i], board.pieceAt(is[i], js[i])));
			}
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
	    String s = "n";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	}
	
	private int[][] eval = {
			{ -5,  0, -3, -3, -3, -3,  0, -5},
			{ -4, -2,  0,  0,  0,  0, -2, -4},
			{ -3,  0,  1,  2,  2,  1,  0, -3},
			{ -3,  1,  2,  4,  4,  2,  1, -3},
			{ -3,  0,  2,  4,  4,  2,  0, -3},
			{ -3,  1,  1,  2,  2,  1,  1, -3},
			{ -4, -2,  0,  1,  1,  0, -2, -4},
			{ -5, -4, -3, -3, -3, -3, -4, -5}
	};

}
