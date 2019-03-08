package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import piece.King;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.MoveComparator;
import shakkiBotti9000PC.Position;

class KingTest {
	
	private King king;
	private Board board;
	private Position[][] positions;

	@BeforeEach
    public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.king = (King) board.getPositions()[0][4].getPiece();
        this.board.removePiece(board.getPositions()[0][5]);
        this.board.removePiece(board.getPositions()[1][4]);
    }

    @Test
    public void testGetKingFirstMove() {
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
    	  movesExpected.add(new Move(king, board.getPositions()[0][5]));
    	  movesExpected.add(new Move(king, board.getPositions()[1][4]));
        
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = king.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "list of possible king moves with 2 missing pieces next to it");
    }

    @Test
    public void testKingOpenMoves() {
    	positions = board.getPositions(); 
    	board.move(new Move(king, board.getPositions()[4][3]));
    	
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(king, board.getPositions()[5][3]));
        movesExpected.add(new Move(king, board.getPositions()[5][4]));
        movesExpected.add(new Move(king, board.getPositions()[5][2]));
        movesExpected.add(new Move(king, board.getPositions()[4][4]));
        movesExpected.add(new Move(king, board.getPositions()[4][2]));
        movesExpected.add(new Move(king, board.getPositions()[3][3]));
        movesExpected.add(new Move(king, board.getPositions()[3][4]));
        movesExpected.add(new Move(king, board.getPositions()[3][2]));
       
        Collections.sort(movesExpected, new MoveComparator());
        ArrayList<Move> movesReal = king.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected.toString(), movesReal.toString(), "List of possible king moves at open field");
    }

}
