package tictactoe.tictactoe;

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        int row;
        int col;

        System.out.println("=== Tic Tac Toe ===");
        game.initialiseBoard();
        /*game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 0);
        game.switchPlayer();
        game.makeMove(0, 0);
        game.makeMove(2, 1);
        game.makeMove(2, 2);
        game.switchPlayer();
        game.printBoard();*/
        game.printBoard();

        while (true) {
            if (game.getPlayer() == 'X') {
                System.out.println("Player " + game.getPlayer() + "'s turn ");
                char[][] newBoard = game.getBoard();
                Move move = game.minimax(newBoard, 'X', 'O', true);
                game.makeMove(move.getRowCoordinate(), move.getColCoordinate());
            } else {
                System.out.println("Player " + game.getPlayer() + "'s turn ");
                System.out.println("Enter move coordinates: ");
                System.out.print("row: ");
                row = Integer.parseInt(reader.nextLine());
                System.out.print("column: ");
                col = Integer.parseInt(reader.nextLine());
                System.out.println("");
                game.makeMove(row - 1, col - 1);
            }
            game.printBoard();
            System.out.println("");
            if (game.checkForWin(game.getBoard(), game.getPlayer())) {
                System.out.println("Player " + game.getPlayer() + " wins!");
                break;
            }

            game.switchPlayer();
        }

    }
}
