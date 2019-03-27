package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * Pawn specific functionality
 * @author antti
 *
 */
public class Pawn extends Piece{
	
	private int firstX;
	private int firstY;
	

	/**
	 * Creates Pawn of given colour in given coordinates 
	 * @param colour boolean value witch represents the colour of the piece use Piece.BLACK or Piece.WHITE for clarity
	 * @param x coordinate of the piece to be created
	 * @param y coordinate of the piece to be created
	 */
	public Pawn(Boolean colour, int x, int y) {
		super(colour, x, y);
		this.firstX = x;
		this.firstY = y;
		if (colour) {
			super.setValue(10);
		} else {
			super.setValue(-10);
		}
	}
  
	/**
	 * Returns the string that represents this piece for the command line UI
	 * @return string representation of the piece.
	 */
	@Override
	  public String getName(){
	    String s = "p";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	
	/**
	 * return the current value of the piece to the minMax algorithm,
	 * this includes the position evaluation that is read from the evaluation table.
	 * @return current value of the piece on the board
	 */
	@Override
	public int getValue() {
		return eval[super.getX()][super.getY()] + super.getValue();
	}
	
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
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (!super.getColour()) {
				if(x >= 1) {
					if (board.pieceAt(x-1,y) == null) {
						newLegalMoves.add(new Move(this,x-1,y,board.pieceAt(x-1,y)));
					}
				}
				if (y != 7) {
					if (board.canEat(this, x-1,y+1)){
						newLegalMoves.add(new Move(this,x-1,y+1,board.pieceAt(x-1,y+1)));
					} 
				}
				if (y != 0) {
					if (board.canEat(this, x-1,y-1)) {
						newLegalMoves.add(new Move(this,x-1,y-1,board.pieceAt(x-1,y-1)));
					}
				}
				if(x >= 2) {
					if (board.pieceAt(x-2,y) == null && this.getX() == firstX && this.getY() == firstY && board.pieceAt(x-1,y) == null) {
						newLegalMoves.add(new Move(this,x-2,y,board.pieceAt(x-2,y)));
					}
				}
			} else {
				if(x <= 6) {
					if (board.pieceAt(x+1,y) == null) {
						newLegalMoves.add(new Move(this,x+1,y,board.pieceAt(x+1,y)));
					}
				}
				if (y != 7) {
					if (board.canEat(this, x+1,y+1)){
						newLegalMoves.add(new Move(this,x+1,y+1,board.pieceAt(x+1,y+1)));
					} 
				}
				if(y != 0) {
					if (board.canEat(this, x+1,y-1)) {
						newLegalMoves.add(new Move(this,x+1,y-1,board.pieceAt(x+1,y-1)));
					}
				}
				if(x <= 5) {
					if (board.pieceAt(x+2,y) == null && this.getX() == firstX && this.getY() == firstY && board.pieceAt(x+1,y) == null) {
						newLegalMoves.add(new Move(this,x+2,y,board.pieceAt(x+2,y)));
					}
				}
			}
		}
		
		return newLegalMoves;
	}

	/*
	 * evaluation array witch tells how much the piece if worth in each position.
	 */
	private int[][] eval = {
			{  0,  0,  0,  0,  0,  0,  0,  0},
			{  3,  3,  3,  3,  3,  3,  3,  3},
			{  1,  1,  2,  3,  3,  2,  1,  1},
			{  1,  0,  3,  2,  2,  3,  0,  1},
			{  0,  0,  1,  1,  1,  1,  0,  0},
			{  1, -1, -1,  0,  0, -1, -1,  1},
			{  1,  1,  1, -2, -2,  1,  1,  1},
			{  0,  0,  0,  0,  0,  0,  0,  0}
		};
}
