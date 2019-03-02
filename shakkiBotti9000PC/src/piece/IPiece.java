package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

public interface IPiece {
	public abstract Position getPos();
	public abstract void setPos(Position pos);
	public abstract int getValue();
	public abstract void setValue(int arvo);
	public abstract ArrayList<Move> getMoves(Board board);
}
