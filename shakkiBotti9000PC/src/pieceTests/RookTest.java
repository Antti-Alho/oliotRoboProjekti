package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import piece.Rook;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;
import shakkiBotti9000PC.Position;

/**
 * Tests Rook movement
 * @author antti
 *
 */
class RookTest {

	private Rook rook;
	private Board board;
	private Position[][] positions;
	
    @BeforeEach
    public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.rook = (Rook) board.pieceAt(0,0);
        this.board.removePiece(1,0);
    }
	
    @Test
    public void testGetMovesX() {
    	positions = board.getPositions(); 
        
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(rook, 1,0,board.pieceAt(1, 0)));
        movesExpected.add(new Move(rook, 2,0,board.pieceAt(2, 0)));
        movesExpected.add(new Move(rook, 3,0,board.pieceAt(3, 0)));
        movesExpected.add(new Move(rook, 4,0,board.pieceAt(4, 0)));
        movesExpected.add(new Move(rook, 5,0,board.pieceAt(5, 0)));
        movesExpected.add(new Move(rook, 6,0,board.pieceAt(6, 0)));
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = rook.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of possible rook moves when rook can mov on X axis");
    }
    
    @Test
    public void testGetMovesY() {
    	positions = board.getPositions(); 
    	board.move(new Move(rook, 4,3,board.pieceAt(4, 3)));
    	
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(rook, 5,3,board.pieceAt(5, 3)));
        movesExpected.add(new Move(rook, 6,3,board.pieceAt(6, 3)));
        movesExpected.add(new Move(rook, 3,3,board.pieceAt(3, 3)));
        movesExpected.add(new Move(rook, 2,3,board.pieceAt(2, 3)));
        movesExpected.add(new Move(rook, 4,4,board.pieceAt(4, 4)));
        movesExpected.add(new Move(rook, 4,5,board.pieceAt(4, 5)));
        movesExpected.add(new Move(rook, 4,6,board.pieceAt(4, 6)));
        movesExpected.add(new Move(rook, 4,7,board.pieceAt(4, 7)));
        movesExpected.add(new Move(rook, 4,2,board.pieceAt(4, 2)));
        movesExpected.add(new Move(rook, 4,1,board.pieceAt(4, 1)));
        movesExpected.add(new Move(rook, 4,0,board.pieceAt(4, 0)));
        
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = rook.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of possible rook moves when rook can mov on X axis");
    }
    @Disabled
    @Test
    public void testGetMovesCastleKing() {
        ArrayList<Move> moves = new ArrayList<Move>();
        assertEquals(moves, rook.getMoves(board), "List of possible rook moves when king side castle is possible");
    }
    @Disabled
    @Test
    public void testGetMovesCastleQueen() {
        ArrayList<Move> moves = new ArrayList<Move>();
        assertEquals(moves, rook.getMoves(board), "List of rook moves when Queens side castle is possible");
    }
  
}
