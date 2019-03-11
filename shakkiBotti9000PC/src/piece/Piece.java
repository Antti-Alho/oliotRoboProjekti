package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;	
	}
	
	@Override
	public Boolean getColour() {
		return colour;
	}

	@Override
	public ArrayList<Move> getMoves(Board board) {
		return null;
	}

	/**
	 * Returns the string that represents this piece in the command line UI
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
