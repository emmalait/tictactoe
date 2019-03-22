
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
        int x;
        int y;
        
        System.out.println("=== Tic Tac Toe ===");
        game.initialiseBoard();
        game.printBoard();
        
        while (true) {
            System.out.println("Player " + game.getPlayer() + "'s turn ");
            System.out.println("Enter move coordinates: ");
            System.out.print("x: ");
            x = Integer.parseInt(reader.nextLine());
            System.out.print("y: ");
            y = Integer.parseInt(reader.nextLine());
            System.out.println("");

            if (!game.makeMove(x - 1, y - 1)) {
                System.out.println("That spot is already taken!");
                System.out.println("");
            } else {
                game.printBoard();
                System.out.println("");

                if (game.checkForWin()) {
                    System.out.println("Player " + game.getPlayer() + " wins!");
                    break;
                }

                game.switchPlayer();
            }
            
        }
    }
    
}
