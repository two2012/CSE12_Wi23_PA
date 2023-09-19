/* 
  Name: Wen Guo
  Email: w5guo@ucsd.edu
  PID: A17630856
  Sources Used: PA1 Write-up
   
  This file is abstract class implements RPSInterface use
  to check valid move, play game, generate moves, and 
  display massages for game
*/

import java.util.Random;

/**
 * 
 * This file is abstract class implements RPSInterface use
 * to check valid move, play game, generate moves, and
 * display massages for game
 **/
public abstract class RPSAbstract implements RPSInterface {

    protected static final int SEED = 12;
    protected final Random rand = new Random(SEED);

    // The moves allowed in this version of RPS
    protected String[] possibleMoves;

    // The number of games, player wins, CPU wins and ties
    protected int numGames;
    protected int numPlayerWins;
    protected int numCPUWins;
    protected int numTies;

    // The complete history of the moves
    protected String[] playerMoves;
    protected String[] cpuMoves;

    // The default moves. Use for the basic implementation of the game.
    protected static final String[] DEFAULT_MOVES = 
        { "scissors", "paper","rock" };

    /*
     * Important: Use the following constants to avoid output matching issues
     * and magic numbers!
     */

    // Messages for the game.
    protected static final String INVALID_INPUT = 
        "That is not a valid move. Please try again.";
    protected static final String PLAYER_WIN = "You win.";
    protected static final String CPU_WIN = "I win.";
    protected static final String TIE = "It's a tie.";
    protected static final String CPU_MOVE = "I chose %s. ";
    protected static final String THANKS = 
        "Thanks for playing!\nOur most recent games were: ";
    protected static final String PROMPT_MOVE = 
        "Let's play! What's your move? (Type the move or q to quit)";

    protected static final String OVERALL_STATS = "Our overall stats are:\n" +
            "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    protected static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";

    // Game outcome constants.
    protected static final int CPU_WIN_OUTCOME = 2;
    protected static final int PLAYER_WIN_OUTCOME = 1;
    protected static final int TIE_OUTCOME = 0;
    protected static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.
    protected static final int MAX_GAMES = 100;
    protected static final int MIN_POSSIBLE_MOVES = 3;
    protected static final int NUM_ROUNDS_TO_DISPLAY = 10;
    protected static final int PERCENTAGE_RESIZE = 100;
    protected static final String QUIT = "q";

    // avoid magic number
    protected static final int ZERO = 0;
    protected static final int ONE = 1;

    /**
     * check if the move is a valid move
     * 
     * @param move the move needs be check
     * @return return true for valid move otherwise return fasle
     */
    public boolean isValidMove(String move) {

        // TODO
        // Use a loop here
        for (String dMove : possibleMoves) {
            // check if user move matchs with default move
            if (dMove.equals(move)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Play one game of RPS using the moves provided.
     * and determine the game outcome, print messages
     * record the game status
     * 
     * @param playerMove the move from user
     * @param cpuMove    the move from computer
     */
    public void playRPS(String playerMove, String cpuMove) {
        // TODO
        // Add appropriate Javadoc method header

        // Use determineWinner to determine who won
        int result = determineWinner(playerMove, cpuMove);
        // Record the moves made
        // check if outcome invalid
        if (result != INVALID_INPUT_OUTCOME) {
            // increase total number of games
            numGames++;
            // store moves of player and computer
            playerMoves[numGames - ONE] = playerMove;
            cpuMoves[numGames - ONE] = cpuMove;

            // Add one to the appropriate statistics
            // and print out the appropriate massages
            if (result == TIE_OUTCOME) {
                // increase tie by 1
                numTies++;
                // print massages
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(TIE);
            } else if (result == PLAYER_WIN_OUTCOME) {
                // increase playerWins by 1
                numPlayerWins++;
                // print massage
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(PLAYER_WIN);
            } else if (result == CPU_WIN_OUTCOME) {
                // increase cpuWins by 1
                numCPUWins++;
                // print massage
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(CPU_WIN);
            }

        } else {
            System.out.println(INVALID_INPUT);
        }
    }

    // The following methods have been already
    // implemented. Do not change them.

    /**
     * Generates next cpu move
     *
     * @return representing the move, depending on random int
     */
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    public void displayStats() {
        float cpuWinPercent = (float) numCPUWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float) numPlayerWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float) numTies /
                (float) numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY)
                ? 0
                : numGames - NUM_ROUNDS_TO_DISPLAY;

        // Print system and user moves
        for (int i = numGames - 1; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES,
                    this.cpuMoves[i],
                    this.playerMoves[i]);
        }

        System.out.printf(OVERALL_STATS,
                cpuWinPercent,
                playerWinPercent,
                tiedPercent);
    }
}
