package shakkiBotti9000PC;

import java.util.Comparator;

public class MoveComparator implements Comparator<Move>{

	/**
	 * counts numeric value for each position on the chess board 
	 * mainly used for sorting the moves in tests.
	 */
	@Override
	public int compare(Move o1, Move o2) {
		int a = o1.getNewPos().getX()*10+o1.getNewPos().getY();
		int b = o2.getNewPos().getX()*10+o2.getNewPos().getY();
		return a-b;
	}

}
