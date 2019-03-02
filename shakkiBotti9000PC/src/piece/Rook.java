package piece;

import shakkiBotti9000PC.Position;

public class Rook extends Piece{
	public Rook(Position pos, Boolean colour) {
		super(pos, colour);
		if (colour) {
			super.setArvo(50);
		} else {
			super.setArvo(-50);
		}
	}
	//Pelinappulan nimi ja väri
	@Override
	  public String getName(){
	    String s = "r";
	    if (super.getColour()) {
	      s.toUpperCase();
	    }
	    return s;
	  }

}
