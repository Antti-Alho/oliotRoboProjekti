package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public interface IPiece {
	public Position getPos();
	public void setPos(Position pos);
	public int getValue();
	public void setValue(int value);
	public Boolean getColour();
	ArrayList<Move> getMoves(Board board);
	String getName();
}
