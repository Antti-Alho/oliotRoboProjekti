package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public interface IPiece {
	public int getValue();
	public void setValue(int value);
	public Boolean getColour();
	abstract ArrayList<Move> getMoves(Board board);
	abstract String getName();
}
