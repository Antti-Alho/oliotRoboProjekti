package piece;

import shakkiBotti9000PC.Position;

public class Pawn extends Piece{
	public Pawn(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(10);
		} else {
			super.setArvo(-10);
		}
	}
	//Pelinappulan nimi ja väri
	@Override
	  public String getName(){
	    String s = "p";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	
	private int[][] eval = {
			{  0,  0,  0,  0,  0,  0,  0,  0},
			{  5,  5,  5,  5,  5,  5,  5,  5},
			{  1,  1,  2,  3,  3,  2,  1,  1},
			{  1,  0,  1,  2,  2,  1,  0,  1},
			{  0,  0,  1,  1,  1,  1,  0,  0},
			{  1, -1, -1,  0,  0, -1, -1,  1},
			{  1,  1,  1, -2, -2,  1,  1,  1},
			{  0,  0,  0,  0,  0,  0,  0,  0}
		};

}
