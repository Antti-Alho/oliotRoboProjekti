package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public class Piece implements IPiece{
	public static Boolean BLACK = true;
	public static Boolean WHITE = false;
	private Boolean colour;
	private Position pos;
	private int value;
	
	public Piece(Position pos, Boolean colour) {
		this.pos = pos;
		this.colour = colour;
	}

	@Override
	public Position getPos() {
		return pos;
	}

	@Override
	public void setPos(Position pos) {
		this.pos = pos;
		
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
	    String s = "b";
	    if (getColour()) {
	    	s.toUpperCase();
	    }
	    return s;
	}

}
