package shakkiBotti9000PC;

import java.util.ArrayList;
import piece.Piece;

public class ChessAI {
	
	public Move calculateBestMove(int depth, Board board, Boolean isMaximizingPlayer) {
		ArrayList<Move> moves = board.getLegalMoves();
		int bestMoveVal = -9999;
		int moveValue;
		Move bestMove = moves.get(0);
		for (Move move : moves) {
			board.move(move);
			moveValue = minMax(depth, board, -10000, 10000, isMaximizingPlayer);
			board.undo();
			if(moveValue >= bestMoveVal) {
				bestMoveVal = moveValue;
				bestMove = move;
			}
		}
		return bestMove;
	}
	
	public int minMax(int depth, Board board, int alpha, int beta, Boolean isMaximizingPlayer) {
		ArrayList<Move> bestMoves = board.getLegalMoves();
		if (depth == 0) {
			return -eval(board.getPieces());
		}
		
		if (isMaximizingPlayer) {
			int bestMoveVal = -9999;
			for (Move move : bestMoves) {
				board.move(move);
				bestMoveVal = Math.max(bestMoveVal, minMax(depth - 1, board, alpha, beta, !isMaximizingPlayer));
				board.undo();
	            alpha = Math.max(alpha, bestMoveVal);
	            if (beta <= alpha) {
	                return bestMoveVal;
	            }
			}
			return bestMoveVal;
		} else {
			int bestMoveVal = 9999;
			for (Move move : bestMoves) {
				board.move(move);
				bestMoveVal = Math.min(bestMoveVal, minMax(depth - 1, board, alpha, beta, !isMaximizingPlayer));
				board.undo();
	            beta = Math.min(beta, bestMoveVal);
	            if (beta <= alpha) {
	                return bestMoveVal;
	            }
			}
			return eval(board.getPieces());
		}
	}
	private int eval(ArrayList<Piece> pieces) {
		int totalEvaluation = 0;

		for (Piece piece : pieces) {
			totalEvaluation = totalEvaluation + piece.getValue();
		}
	    return totalEvaluation;
	}

}
