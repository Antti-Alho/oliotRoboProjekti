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
	
    @BeforeEach
    public void initTestSystem() {
        System.out.println("----------");
        this.board = new Board();
        this.pawn = (Pawn) board.getPositions()[1][4].getPiece();
    }
	
    @Test
    public void testGetMovesFirstMoveWhite() {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(pawn, new Position(2, 4)));
        moves.add(new Move(pawn, new Position(3, 4)));
        assertEquals(moves, pawn.getMoves(board), "List of Pawn start moves test");
    }
    
    @Test
    public void testGetMovesNotFirstMoveWhite() {
    	board.move(new Move(pawn, new Position(2, 4)));
    	ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(pawn, new Position(3, 4)));
        assertEquals(moves, pawn.getMoves(board), "List of Pawn moves when pawn has allready moved onece");
    }
    @Test
    public void testGetMovesEatWhite() {
    	Pawn enemyPawn1 = new Pawn(new Position(2, 3), Piece.BLACK);
    	Pawn enemyPawn2 = new Pawn(new Position(2, 5), Piece.BLACK);
    	ArrayList<Move> moves = new ArrayList<Move>();
    	moves.add(new Move(pawn, new Position(2, 3)));
    	moves.add(new Move(pawn, new Position(2, 5)));
    	moves.add(new Move(pawn, new Position(2, 4)));
    	moves.add(new Move(pawn, new Position(3, 4)));
        assertEquals(moves, pawn.getMoves(board), "list of pawn moves when pawn can eat");
    }
    
    @Disabled
    @Test
    public void testGetMovesEnPessantWhite() {
    	
    }

}
