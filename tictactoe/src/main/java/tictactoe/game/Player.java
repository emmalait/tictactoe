package tictactoe.game;

import java.util.Scanner;
import tictactoe.ai.AI;

public class Player {

    private char mark;
    private AI ai;
    private boolean aiPlayer;
    private Scanner reader = new Scanner(System.in);

    public Player(char mark) {
        this.mark = mark;
        this.aiPlayer = false;
    }

    public Player(char mark, AI ai) {
        this.mark = mark;
        this.ai = ai;
        this.aiPlayer = true;
    }

    public Move getMove() {
        if (aiPlayer) {
            return ai.makeMove();
        } else {
            System.out.println("Enter move coordinates: ");
            System.out.print("row: ");
            int row = Integer.parseInt(reader.nextLine());
            System.out.print("column: ");
            int col = Integer.parseInt(reader.nextLine());
            System.out.println("");
            return new Move(row - 1, col - 1);
        }
    }

    public char getMark() {
        return mark;
    }

    public AI getAi() {
        return ai;
    }

    public boolean isAiPlayer() {
        return aiPlayer;
    }

}
