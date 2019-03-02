package piece;

import java.util.ArrayList;

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
	public void setArvo(int value) {
		this.value = value;
		
	}

	@Override
	public ArrayList<Position> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean getColour() {
		return colour;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


}
