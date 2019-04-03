package tictactoe.game;

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);
        TicTacToe game = new TicTacToe(3, 3, 3);
        AI ai = new AI(game, 3, 3, 'X', 'O');
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
        
        game.makeMove(0, 0);
        game.switchPlayer();
        game.makeMove(0, 2);
        game.switchPlayer();
        game.printBoard();
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
                
                Move move = ai.minimax(game.getBoard(), 'X', 'O', true);
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

//                Move move = ai.minimax(game.getBoard(), 'O', 'X', true);
//                game.makeMove(move.getRowCoordinate(), move.getColCoordinate());
            }
            game.printBoard();
            System.out.println("");
            
            if (game.checkForWin(game.getBoard(), game.getPlayer())) {
                System.out.println("Player " + game.getPlayer() + " wins!");
                break;
            }
            
            if(game.isGameOver()) {
                System.out.println("Draw!");
                break;
            }

            game.switchPlayer();
        }

    }
}
