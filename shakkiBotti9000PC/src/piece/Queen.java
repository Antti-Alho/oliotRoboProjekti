package piece;

import shakkiBotti9000PC.Position;

public class Queen extends Piece{
	public Queen(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(100);
		} else {
			super.setArvo(-100);
		}
	}
	//Pelinappulan nimi ja väri
	@Override
	  public String getName(){
	    String s = "q";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	private int[][] eval = {
			{ -2, -1, -1, -1, -1, -1, -1, -2},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -1,  0,  0,  1,  1,  0,  0, -1},
			{ -1,  0,  0,  1,  1,  0,  0, -1},
			{ -1,  0,  1,  1,  1,  1,  0, -1},
			{ -1,  1,  1,  1,  1,  1,  1, -1},
			{ -1,  0,  0,  0,  0,  0,  0, -1},
			{ -2, -1, -1, -1, -1, -1, -1, -2}
		};

}
