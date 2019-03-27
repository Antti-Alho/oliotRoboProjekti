package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * Knight specific functionality
 * @author antti
 *
 */
public class Knight extends Piece{
	
	/**
	 * Creates Knight of given colour in given coordinates 
	 * @param colour boolean value witch represents the colour of the piece use Piece.BLACK or Piece.WHITE for clarity
	 * @param x x coordinate of the piece to be created
	 * @param y y coordinate of the piece to be created
	 */
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
	 * @return returns the current value of the piece
	 */
	@Override
	public int getValue() {
		return eval[super.getX()][super.getY()] + super.getValue();
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
	
	/**
	 * returns an ArrayList of all possible moves the piece can currently take.
	 * @param board current board in play
	 * @return ArrayList list of moves the piece can currently take
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
	/**
	 * Returns true if king can move to this position
	 * @param x new x coordinate of king
	 * @param y new y coordinate of king
	 * @param board current board situation
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
	    String s = "n";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	}

}
