package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * The IPiece interface provides everything about the piece
 * that the Board needs to know about the pieces.
 */
public interface IPiece {
	/**
	 * @return the current value of the piece to the minMax algorithm
	 */
	abstract int getValue();
	
	/**
	 * every Piece value must be set in the constructor of the subclasses of piece using this method
	 * @param value new value of the piece
	 */
	abstract void setValue(int value);
	
	/**
	 * returns the colour of the piece true if black false if white
	 * @return colour of this piece
	 */
	abstract Boolean getColour();
	
	/**
	 * returns all possible moves the piece can currently take
	 * @param board current board situation
	 * @return ArrayList of moves the piece can currently take
	 */
	abstract ArrayList<Move> getMoves(Board board);
	
	/**
	 * Returns the string representation of the piece so that when you are 
	 * printing pieces from the boards list we know which piece is which.
	 * @return string representation of the piece
	 */
	abstract String getName();
}
