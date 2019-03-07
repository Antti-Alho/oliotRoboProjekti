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

class RookTest {

	private Rook rook;
	private Board board;
	private Position[][] positions;
	
    @BeforeEach
    public void initTestSystem() {
        System.out.println("--------------------");
        this.board = new Board();
        this.rook = (Rook) board.getPositions()[0][0].getPiece();
        this.board.removePiece(board.getPositions()[1][0]);
    }
	
    @Test
    public void testGetMovesX() {
    	positions = board.getPositions(); 
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        
    	ArrayList<Move> movesExpected = new ArrayList<Move>();
        movesExpected.add(new Move(rook, board.getPositions()[1][0]));
        movesExpected.add(new Move(rook, board.getPositions()[2][0]));
        movesExpected.add(new Move(rook, board.getPositions()[3][0]));
        movesExpected.add(new Move(rook, board.getPositions()[4][0]));
        movesExpected.add(new Move(rook, board.getPositions()[5][0]));
        movesExpected.add(new Move(rook, board.getPositions()[6][0]));
        Collections.sort(movesExpected, new MoveComparator());
        
        ArrayList<Move> movesReal = rook.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(movesExpected, movesReal, "list of possible rook moves when rook can mov on X axis");
    }
    
    @Test
    public void testGetMovesY() {
    	positions = board.getPositions(); 
    	board.move(new Move(rook, board.getPositions()[4][3]));
    	
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions.length; j++) {
				System.out.print(positions[i][j].getPieceString() + " ");
			}
			System.out.println("  "+(i+1));
		}
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.add(new Move(rook, board.getPositions()[5][3]));
        moves.add(new Move(rook, board.getPositions()[6][3]));
        moves.add(new Move(rook, board.getPositions()[3][3]));
        moves.add(new Move(rook, board.getPositions()[2][3]));
        moves.add(new Move(rook, board.getPositions()[4][4]));
        moves.add(new Move(rook, board.getPositions()[4][5]));
        moves.add(new Move(rook, board.getPositions()[4][6]));
        moves.add(new Move(rook, board.getPositions()[4][7]));
        moves.add(new Move(rook, board.getPositions()[4][2]));
        moves.add(new Move(rook, board.getPositions()[4][1]));
        moves.add(new Move(rook, board.getPositions()[4][0]));
        
        ArrayList<Move> movesReal = rook.getMoves(board);
        Collections.sort(movesReal, new MoveComparator());
        
        assertEquals(moves, rook.getMoves(board), "List of possible rook moves when rook can move on Y axis");
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
