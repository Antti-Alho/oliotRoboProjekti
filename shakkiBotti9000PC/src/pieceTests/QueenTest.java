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

class QueenTest {
	
	private Queen queen;
	private Board board;
	private Position[][] positions;

	@BeforeEach
	public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.queen = (Queen) board.getPositions()[0][3].getPiece();
        this.board.removePiece(board.getPositions()[0][2]);
        this.board.removePiece(board.getPositions()[1][2]);
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
    	  movesExpected.add(new Move(queen, board.getPositions()[0][2]));
    	  movesExpected.add(new Move(queen, board.getPositions()[1][2]));
    	  movesExpected.add(new Move(queen, board.getPositions()[2][1]));
    	  movesExpected.add(new Move(queen, board.getPositions()[3][0]));
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = queen.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "list of possible Queen moves with 2 missing pieces next to it");
    }

	@Test
    public void testQueenOpenMoves() {
    	positions = board.getPositions(); 
    	board.move(new Move(queen, board.getPositions()[4][5]));
    	
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(queen, board.getPositions()[5][5]));
        moves.add(new Move(queen, board.getPositions()[6][5]));
        moves.add(new Move(queen, board.getPositions()[5][6]));
        moves.add(new Move(queen, board.getPositions()[6][7]));
        moves.add(new Move(queen, board.getPositions()[5][4]));
        moves.add(new Move(queen, board.getPositions()[6][3]));
        moves.add(new Move(queen, board.getPositions()[4][6]));
        moves.add(new Move(queen, board.getPositions()[4][7]));
        moves.add(new Move(queen, board.getPositions()[4][4]));
        moves.add(new Move(queen, board.getPositions()[4][3]));
        moves.add(new Move(queen, board.getPositions()[4][2]));
        moves.add(new Move(queen, board.getPositions()[4][1]));
        moves.add(new Move(queen, board.getPositions()[4][0]));
        moves.add(new Move(queen, board.getPositions()[3][6]));
        moves.add(new Move(queen, board.getPositions()[2][7]));
        moves.add(new Move(queen, board.getPositions()[3][4]));
        moves.add(new Move(queen, board.getPositions()[2][3]));
        moves.add(new Move(queen, board.getPositions()[1][2])); // Tästä ruudusta poistettu nappula alustuksessa
        moves.add(new Move(queen, board.getPositions()[3][5]));
        moves.add(new Move(queen, board.getPositions()[2][5]));
        
        
        ArrayList<Move> movesReal = queen.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(moves, queen.getMoves(board), "List of possible queen moves at open field");
    }

}
