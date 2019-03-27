package piece;


import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * King specific functionality
 * @author antti
 *
 */
public class King extends Piece{
	
	/**
	 * Creates King of given colour in given coordinates 
	 * @param colour boolean value witch represents the colour of the piece use Piece.BLACK or Piece.WHITE for clarity
	 * @param x coordinate of the piece to be created
	 * @param y coordinate of the piece to be created
	 */
	public King(Boolean colour, int x, int y) {
		super(colour, x, y);
		if (colour) {
			super.setValue(9000);
		} else {
			super.setValue(-900);
		}
	}

	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this includes the position evaluation that is read from the evaluation table.
	 * @return current value of the piece on the board
	 */
	@Override
	public int getValue() {
		return (eval[super.getX()][super.getY()] + super.getValue());
	}
	private int[][] eval = {
			{  3,  1,  0,  0,  0,  1,  2,  3},
		    {  2,  2,  0,  0,  0,  0,  2,  2},
		    { -3, -1, -2, -1, -1, -2, -1, -3},
		    { -3, -1, -3, -5, -5, -3, -1, -3},
		    { -3, -1, -3, -4, -4, -3, -1, -3},
		    { -3, -1, -2, -3, -3, -2, -1, -3},
		    { -3, -3, -5, -5, -5, -5, -3, -3},
		    { -5, -3, -3, -3, -3, -3, -3, -5}
	};
	
	/**
	 * returns an ArrayList of all possible moves the piece can currently take.
	 * @param board current board in play
	 * @return ArrayList moves the piece can currently take
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		ArrayList<Move> newLegalMoves = new ArrayList<Move>();
		int x = this.getX();
		int y = this.getY();
		
		int[] is = {x+1,x-1,x-1,x+1,x+1,x-1,x,x};
		int[] js = {y+1,y-1,y+1,y-1,y,y,y+1,y-1};
		for (int i = 0; i < is.length; i++) {
			if (isLegal(is[i], js[i], board)) {
			newLegalMoves.add(new Move(this, is[i], js[i], board.pieceAt(is[i], js[i])));
			}
		}
		return newLegalMoves;
	}

	/**
	 * Returns true if king can move to this position
	 * @param x x coordinate of king
	 * @param y y coordinate of king
	 * @param board current board in play
	 * @return True if king can move to this position. False if king can't move.
	 */
	private boolean isLegal(int x, int y, Board board) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (board.pieceAt(x, y) == null) {
				return true;
			} else if (board.pieceAt(x, y).getColour() != this.getColour()){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * Returns the string that represents this piece for the command line UI
	 * @return string representation of the piece.
	 */
	@Override
	  public String getName(){
	    String s = "k";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	



}
