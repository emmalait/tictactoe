package tictactoe.main;

import tictactoe.ai.AI;
import java.util.Scanner;
import tictactoe.game.Game;
import tictactoe.game.Move;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Game game = new Game(3, 3, 3);
        AI ai = new AI(game, 'X');
        int row;
        int col;

        System.out.println("=== Tic Tac Toe ===");

         /*game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 0);
        game.switchPlayer();
        game.makeMove(0, 0);
        game.makeMove(2, 1);
        game.makeMove(2, 2);
        game.switchPlayer();
        game.printBoard();*/

        /*game.makeMove(0, 0);
        game.makeMove(0, 1);
        //game.makeMove(1, 1);
        game.switchPlayer();
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        //game.makeMove(2, 2);
        game.switchPlayer();*/

        /*game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(1, 2);
        game.switchPlayer();
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 2);
        game.switchPlayer();*/

        game.getBoard().printBoard();

        
        System.out.println("");

        while (true) {
            if (game.getPlayer() == 'X') {
                System.out.println("Player " + game.getPlayer() + "'s turn ");

//                System.out.println("Enter move coordinates: ");
//                System.out.print("row: ");
//                row = Integer.parseInt(reader.nextLine());
//                System.out.print("column: ");
//                col = Integer.parseInt(reader.nextLine());
//                System.out.println("");
//                game.makeMove(row - 1, col - 1);
                long timeAtStart = System.currentTimeMillis();
                Move move = ai.makeMove();
                long timeAtEnd = System.currentTimeMillis();
                System.out.println("Operation took: " + (timeAtEnd - timeAtStart) + "ms.");
                game.makeMove(move.getRow(), move.getCol());

            } else {
                System.out.println("Player " + game.getPlayer() + "'s turn ");

                System.out.println("Enter move coordinates: ");
                System.out.print("row: ");
                row = Integer.parseInt(reader.nextLine());
                System.out.print("column: ");
                col = Integer.parseInt(reader.nextLine());
                System.out.println("");
                game.makeMove(row - 1, col - 1);

//                Move move = ai.minimax(game.getBoard(), 'O', 'X', true);
//                game.makeMove(move.getRowCoordinate(), move.getColCoordinate());
            }
            game.getBoard().printBoard();
            System.out.println("");

            if (game.getBoard().checkForWin(game.getPlayer(), game.getLatestMove())) {
                System.out.println("Player " + game.getPlayer() + " wins!");
                break;
            }

            if (game.isGameOver()) {
                System.out.println("Draw!");
                break;
            }

            game.switchPlayer();
        }

    }
}
