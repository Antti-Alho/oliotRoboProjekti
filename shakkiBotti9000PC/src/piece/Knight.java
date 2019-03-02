package piece;

import shakkiBotti9000PC.Position;

public class Knight extends Piece{
	public Knight(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(30);
		} else {
			super.setArvo(-30);
		}
	}
	@Override
	  public String getName(){
	    String s = "n";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }
	private int[][] eval = {
			{ -5, -4, -3, -3, -3, -3, -5, -5},
			{ -4, -2,  0,  0,  0,  0, -2, -4},
			{ -3,  0,  1,  2,  2,  1,  0, -3},
			{ -3,  1,  2,  4,  4,  2,  1, -3},
			{ -3,  0,  1,  4,  4,  1,  0, -3},
			{ -3,  1,  2,  2,  2,  2,  1, -3},
			{ -4, -2,  0,  1,  1,  0, -2, -4},
			{ -5, -4, -3, -3, -3, -3, -4, -5}
		};

}
