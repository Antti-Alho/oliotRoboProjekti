package pieceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import piece.Piece;
import piece.Queen;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.Move;
import shakkiBotti9000PC.Position;

/**
 * Tests for board class
 * @author antti
 */
class BoardTest {
	Board board;
	Position[][] positions;
	@BeforeEach
	void setUp() throws Exception {
		board = new Board();
		positions = board.getPositions();
	}

	@Test
	void testCanEat() {
		assertEquals(true, board.canEat(board.pieceAt(0, 0), board.pieceAt(7, 7).getX(),board.pieceAt(7, 7).getY()), "White piece can eat black piece");
		assertEquals(false, board.canEat(board.getPieces().get(0), board.getPieces().get(1).getX(),board.getPieces().get(1).getY()), "white piece can't eat white piece");
	}

	@Test
	void testMove() {
		Queen queen = (Queen) positions[0][3].getPiece();
		Queen queen2 = (Queen) positions[7][3].getPiece();
		Move move1 = new Move(queen, 4,4,board.pieceAt(4, 4));
		board.move(move1);
		Move move2 = new Move(queen2, 0,0,board.pieceAt(0, 0));
		Piece piece = positions[0][0].getPiece();
		board.move(move2);
		
		assertEquals(queen, board.getPositions()[4][4].getPiece(), "board executes given move");
		assertEquals(queen2, board.getPositions()[0][0].getPiece(), "If move eats another piece the other piece disappears");
		assertEquals(false, board.getPieces().contains(piece), "removed piece is removed from the piece list");
	}

	@Test 
	void testUndo() {
		Piece queen = positions[0][4].getPiece();
		Piece pawn = positions[6][0].getPiece();
		Move move1 = new Move(pawn, 4,4,board.pieceAt(4, 4));
		board.move(move1);
		Move move2 = new Move(queen, 4,4,board.pieceAt(4, 4));
		board.move(move2);
		assertEquals(queen, board.getPositions()[4][4].getPiece(), "queen has eaten pawn before undo");
		board.undo();
		assertEquals(pawn, board.getPositions()[4][4].getPiece(), "piece is not int the spot it moved to after undo");
		assertEquals(queen, board.getPositions()[0][4].getPiece(), "piece is in the spot is was before undo");
		board.undo();
		assertEquals(null, board.getPositions()[4][4].getPiece(), "piece is not int the spot it moved to after undo");
		assertEquals(true, board.getPieces().contains(pawn), "piece that was eaten is returned to the piece list after undo");
	}

	@Test
	void testContainsPiece() {
		Piece queen = positions[0][3].getPiece();
		assertEquals(null, board.getPositions()[4][4].getPiece(), "empty position on boeard does not contain piece");
		assertEquals(queen, board.getPositions()[0][3].getPiece(), "position with a piece contains piece");
	}


	@Test
	void testRemovePiece() {
		Piece piece = positions[0][4].getPiece();
		assertEquals(piece, board.getPositions()[0][4].getPiece(), "piece exists before it is removed");
		board.removePiece(0,4);
		assertEquals(null, board.getPositions()[0][4].getPiece(), "removed piece does not exist");
	}

}
