package tictactoe.game;

import java.util.Scanner;

public class UI {

    private Game game;
    private Scanner reader = new Scanner(System.in);

    public UI(Game game) {
        this.game = game;
    }

    public void printStart() {
        System.out.println("=== Tic Tac Toe ===");

        boolean isHumanMode = true;

        System.out.println("--- Setup ---");

        while (true) {
            System.out.println("Choose mode:");
            System.out.println("1 - Human vs. AI");
            System.out.println("2 - AI vs. AI");
            System.out.print("Mode: ");
            int mode = Integer.parseInt(reader.nextLine());

            if (mode == 1) {
                break;
            } else if (mode == 2) {
                isHumanMode = false;
                break;
            } else {
                System.out.println("Enter 1 or 2!");
            }
        }

        System.out.print("Board rows: ");
        int rows = Integer.parseInt(reader.nextLine());
        System.out.print("Board columns: ");
        int cols = Integer.parseInt(reader.nextLine());

        int winningStreak = 3;
        
        while (true) {
            System.out.print("Winning streak: ");
            winningStreak = Integer.parseInt(reader.nextLine());
            
            if (winningStreak > rows && winningStreak > cols) {
                System.out.println("Winning streak has to be smaller than board rows or columns.");
            } else {
                break;
            }
        }
        
        game.setup(rows, cols, winningStreak, isHumanMode);
    }

    public void printBoard() {
        System.out.println("");
        game.getBoard().printBoard();
        System.out.println("");
    }

    public void printTurn() {
        Player player = game.getCurrentPlayer();

        while (true) {
            System.out.println("Player " + player.getMark() + "'s turn");
            Move move = player.getMove();
            if (game.makeMove(move)) {
                break;
            }
        }
    }

    public void printWin(Player player) {
        System.out.println("Player " + player.getMark() + " wins!");
    }

    public void printDraw() {
        System.out.println("Draw!");
    }

}
