package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import piece.King;
import shakkiBotti9000PC.Board;
import shakkiBotti9000PC.ChessAI;
import shakkiBotti9000PC.Move;

public class BoardController {
	
	public static void main(String[] args) {
		Board board = new Board();
		King white = (King) board.getPositions()[7][4].getPiece();
		King black = (King) board.getPositions()[0][4].getPiece();
		Scanner s = new Scanner(System.in);
		ChessAI ai = new ChessAI(board);
		int depth = 3;
		Socket socket = null;
		boolean vuoro = false;
		boolean peli = true;
		

		try {
			socket = new Socket("10.0.1.1", 1111);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
				while (board.getPieces().contains(white) && board.getPieces().contains(black)) {
					vuoro = in.readBoolean();
					if (peli==true) {
						System.out.println("-----------------------");
						System.out.println(" 0 1 2 3 4 5 6 7");
						for (int i = 0; i < 8; i++) {
							
							for (int j = 0; j < 8; j++) {
								System.out.print(" "+board.getPositions()[i][j].getPieceString());
							}
							System.out.println(" "+i);
						}
						System.out.println("");
						System.out.println("-----------------------");
						System.out.print("Anna mistä x:");
						int x = Integer.parseInt(s.nextLine());
						System.out.print("Anna mistä y:");
						int y = Integer.parseInt(s.nextLine());
						System.out.print("Anna mihin x:");
						int x2 = Integer.parseInt(s.nextLine());
						System.out.print("Anna mihin y:");
						int y2 = Integer.parseInt(s.nextLine());
						
						board.move(new Move(board.getPositions()[x][y].getPiece(), board.getPositions()[x2][y2]));
						Move m = ai.calculateBestMove(depth);
						board.move(m);
						//fromX, toX, fromY, toY, target
						out.writeInt(m.getP().getPos().getX());
						out.flush();
						out.writeInt(m.getP().getPos().getY());
						out.flush();
						out.writeInt(m.getNewPos().getX());
						out.flush();
						out.writeInt(m.getNewPos().getY());
						out.flush();
						if (m.getTarget() == null) {
							out.writeInt(-1);
							out.flush();
						} else {
							out.writeInt(1);
							out.flush();
						}
						
					}
					vuoro = false;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
/**
		while (board.getPieces().contains(white) && board.getPieces().contains(black)) {
			System.out.println("-----------------------");
			System.out.println(" 0 1 2 3 4 5 6 7");
			for (int i = 0; i < 8; i++) {
				
				for (int j = 0; j < 8; j++) {
					System.out.print(" "+board.getPositions()[i][j].getPieceString());
				}
				System.out.println(" "+i);
			}
			System.out.println("");
			System.out.println("-----------------------");
			System.out.print("Anna mistä x:");
			int x = Integer.parseInt(s.nextLine());
			System.out.print("Anna mistä y:");
			int y = Integer.parseInt(s.nextLine());
			System.out.print("Anna mihin x:");
			int x2 = Integer.parseInt(s.nextLine());
			System.out.print("Anna mihin y:");
			int y2 = Integer.parseInt(s.nextLine());
			
			board.move(new Move(board.getPositions()[x][y].getPiece(), board.getPositions()[x2][y2]));
			Move m = ai.calculateBestMove(depth);
			board.move(m);
*/
		}
		
	}
	
}