package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

class PawnTest {

	private Pawn pawn;
	private Board board;
	private Position[][] positions;
	
    @BeforeEach
    public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.pawn = (Pawn) board.getPositions()[1][4].getPiece();
    }
	
    @Test
    public void testGetMovesFirstMoveWhite() {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(pawn, board.getPositions()[2][4]));
        moves.add(new Move(pawn, board.getPositions()[3][4]));
        assertEquals(moves, pawn.getMoves(board), "List of Pawn start moves test");
    }
    
    @Test
    public void testGetMovesNotFirstMoveWhite() {
    	board.move(new Move(pawn, board.getPositions()[2][4]));
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
    	ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(pawn, board.getPositions()[3][4]));
        assertEquals(moves, pawn.getMoves(board), "List of Pawn moves when pawn has allready moved onece");
    }
    
    @Test
    public void testGetMovesEatWhite() {
    	
    	
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
    	
    	
    	board.move(new Move(board.getPositions()[6][3].getPiece(), board.getPositions()[2][3]));
    	board.move(new Move(board.getPositions()[6][5].getPiece(), board.getPositions()[2][5]));
    	ArrayList<Move> moves = new ArrayList<Move>();
    	moves.add(new Move(pawn, new Position(2, 3)));
    	moves.add(new Move(pawn, new Position(2, 5)));
    	moves.add(new Move(pawn, new Position(2, 4)));
    	moves.add(new Move(pawn, new Position(3, 4)));
    	
    	
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
    	
    	
        assertEquals(moves, pawn.getMoves(board), "list of pawn moves when pawn can eat");
    }
    
    @Disabled
    @Test
    public void testGetMovesEnPessantWhite() {
    	// 
    }

}
