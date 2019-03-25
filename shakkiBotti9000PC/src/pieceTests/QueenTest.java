package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import piece.Queen;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;
import shakkiBotti9000PC.Position;

/**
 * Tests Queen movement
 * @author antti
 *
 */
class QueenTest {
	
	private Queen queen;
	private Board board;
	private Position[][] positions;

	@BeforeEach
	public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.queen = (Queen) board.getPositions()[0][3].getPiece();
        this.board.removePiece(0,2);
        this.board.removePiece(1,2);
    }
	
	@Test
    public void testGetQueenFirstMove() {
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	  movesExpected.add(new Move(queen, 0, 2,board.pieceAt(0, 2)));
    	  movesExpected.add(new Move(queen, 1, 2,board.pieceAt(1, 2)));
    	  movesExpected.add(new Move(queen, 2, 1,board.pieceAt(2, 1)));
    	  movesExpected.add(new Move(queen, 3, 0,board.pieceAt(3, 0)));
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = queen.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of possible Queen moves with 2 missing pieces next to it");
    }

	@Test
    public void testQueenOpenMoves() {
    	positions = board.getPositions(); 
    	board.move(new Move(queen, 4,5,board.pieceAt(4, 5)));
    	
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(queen, 5,5,board.pieceAt(5, 5)));
        movesExpected.add(new Move(queen, 6,5,board.pieceAt(6, 5))); 	// Queen eats
        movesExpected.add(new Move(queen, 5,6,board.pieceAt(5, 6)));
        movesExpected.add(new Move(queen, 6,7,board.pieceAt(6, 7))); 	// Queen eats
        movesExpected.add(new Move(queen, 5,4,board.pieceAt(5, 4)));
        movesExpected.add(new Move(queen, 6,3,board.pieceAt(6, 3)));		// Queen eats
        movesExpected.add(new Move(queen, 4,6,board.pieceAt(4, 6)));
        movesExpected.add(new Move(queen, 4,7,board.pieceAt(4, 7)));
        movesExpected.add(new Move(queen, 4,4,board.pieceAt(4, 4)));
        movesExpected.add(new Move(queen, 4,3,board.pieceAt(4, 3)));
        movesExpected.add(new Move(queen, 4,2,board.pieceAt(4, 2)));
        movesExpected.add(new Move(queen, 4,1,board.pieceAt(4, 1)));
        movesExpected.add(new Move(queen, 4,0,board.pieceAt(4, 0)));
        movesExpected.add(new Move(queen, 3,6,board.pieceAt(3, 6)));
        movesExpected.add(new Move(queen, 2,7,board.pieceAt(2, 7)));
        movesExpected.add(new Move(queen, 3,4,board.pieceAt(4, 4)));
        movesExpected.add(new Move(queen, 2,3,board.pieceAt(2, 3)));
        movesExpected.add(new Move(queen, 1,2,board.pieceAt(1, 2))); 	// Open square, pawn was removed in TestSystemInit
        movesExpected.add(new Move(queen, 3,5,board.pieceAt(3, 5)));
        movesExpected.add(new Move(queen, 2,5,board.pieceAt(2, 5)));
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = queen.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of possible queen moves at open field");
    }

}
