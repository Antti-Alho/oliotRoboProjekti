package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * contains generic piece functionality
 * Superclass for every piece type 
 * @author antti
 */
public class Piece implements IPiece{
	public static Boolean BLACK = true;
	public static Boolean WHITE = false;
	private Boolean colour;
	private int x;
	private int y;
	private int value;
	
	public Piece( Boolean colour, int x, int y) {
		this.x = x;
		this.y = y;
		this.colour = colour;
	}

	/**
	 * Returns the x coordinate of this piece
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of this piece
	 * @param x new x coordinate for the piece
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	* Returns the Y coordinate of this piece
	* @return y coordinate
	*/
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of this piece
	 * @param y new y coordinate for this piece
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns the current value of this piece to the minMax algorithm
	 * generic pies does not have position evaluation
	 * @return returns the current value of this piece
	 */
	@Override
	public int getValue() {
		return this.value;
	}
	/**
	 * Sets the value of this piece
	 * this does not effect the position evaluation of this 
	 * piece that is added to this value when get value is called
	 * @param value new value of the piece
	 */
	@Override
	public void setValue(int value) {
		this.value = value;	
	}
	
	@Override
	public Boolean getColour() {
		return colour;
	}
	/**
	 * Generic piece does not have any moves
	 * @return ArrayList empty arrayList of moves.
	 */
	@Override
	public ArrayList<Move> getMoves(Board board) {
		return new ArrayList<Move>();
	}

	/**
	 * Returns the string that represents this piece in the command line UI
	 * returns x that is supposed to represent any piece on the board
	 * this method is used mainly in camera calibration.
	 * @return string representation of the generic piece
	 */
	@Override
	public String getName(){
	    String s = "x";
	    if (getColour()) {
	    	s.toUpperCase();
	    }
	    return s;
	}

}
