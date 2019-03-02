package piece;

import shakkiBotti9000PC.Position;

public class Bishop extends Piece{
	public Bishop(Position pos, Boolean colour) {
		super(pos, colour);
		super.setArvo(30);
	}
	//Pelinappulan nimi ja väri
	@Override
	  public String getName(){
	    String s = "b";
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
