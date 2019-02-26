package piece;

import java.util.ArrayList;

import shakkiBotti9000PC.Position;

public interface IPiece {
	public abstract Position getPos();
	public abstract void setPos(Position pos);
	public abstract int getValue();
	public abstract void setArvo(int arvo);
	public abstract ArrayList<Position> getMoves();
}
