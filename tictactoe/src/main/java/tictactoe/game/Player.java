package tictactoe.game;

import tictactoe.ai.AI;

/**
 * lass for implementing the player object in the game.
 */
public class Player {

    private char mark;
    private AI ai;
    private boolean aiPlayer;
    private UI ui;

    public Player(char mark, UI ui) {
        this.mark = mark;
        this.aiPlayer = false;
        this.ui = ui;
    }

    public Player(char mark, AI ai) {
        this.mark = mark;
        this.ai = ai;
        this.aiPlayer = true;
    }

    /**
     * Method retrieves a move from the player: for an AI player, move is retrieved
     * through AI class's method; for a human player, move is retrieved from the
     * user.
     * @return Retrieved move
     */
    public Move getMove() {
        if (aiPlayer) {
            return ai.makeMove();
        } else {
            return ui.getMoveFromUser();
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
