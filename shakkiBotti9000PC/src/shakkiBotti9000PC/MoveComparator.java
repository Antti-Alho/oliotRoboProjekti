package shakkiBotti9000PC;

import java.util.Comparator;
/**
 * Comparator class to compare and sort moves for the tests
 * @author antti
 */
public class MoveComparator implements Comparator<Move>{

	/**
	 * counts numeric value for each position on the chess board 
	 * mainly used for sorting the moves in tests.
	 */
	@Override
	public int compare(Move o1, Move o2) {
		int a = o1.getNewX()*10+o1.getNewY();
		int b = o2.getNewX()*10+o2.getNewY();
		return a-b;
	}

}
