
/* 
  Name: Wen Guo
  Email: w5guo@ucsd.edu
  PID: A17630856
  Sources Used: PA1 Write-up
   
  This file have a class extends RPSAbstract, method use
  to determin winner, and main method
*/
import java.util.Scanner;

/**
 * this is RPS class extend RPSAbstract has a constructor
 */
public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED = 
        "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     * 
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * to determine the winner for one game
     * 
     * @param playerMove move that user enter
     * @param cpuMove    move that computer generated
     * @return -1 for invalid input, 0 for tie,
     *         1 for player win, and 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        // check if playermove is valid
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)) {
            return INVALID_INPUT_OUTCOME;
        } else {
            // variable to store index of playerMove and
            // cpuMove in possibleMoves
            int pIndex = ZERO;
            int cIndex = ZERO;
            int length = possibleMoves.length;
            // loop for possibleMoves to find the index of
            // playerMove and cpuMove
            for (int i = ZERO; i < length; i++) {
                if (possibleMoves[i].equals(playerMove)) {
                    pIndex = i;
                }
                if (possibleMoves[i].equals(cpuMove)) {
                    cIndex = i;
                }
            }
            // index of cpuMove is not the 1st and last element
            // in possibleMoves
            if (cIndex != ZERO && cIndex != length - ONE) {
                // index of cpuMove is next to playerMove
                if (cIndex == pIndex + ONE) {
                    return PLAYER_WIN_OUTCOME;
                    // index of playerMove is next to cpuMove
                } else if (cIndex == pIndex - ONE) {
                    return CPU_WIN_OUTCOME;
                } else {
                    return TIE_OUTCOME;
                }
                // index of cpuMove is the 1st element in possibleMoves
            } else if (cIndex == ZERO) {
                // index of playerMove is the last element
                if (pIndex == length - ONE) {
                    return PLAYER_WIN_OUTCOME;
                    // index of playerMove is the 2nd element
                } else if (pIndex == ONE) {
                    return CPU_WIN_OUTCOME;
                } else {
                    return TIE_OUTCOME;
                }
                // index of cpuMove is the last element in possibleMoves
            } else {
                // cpuMove is next to the playerMove
                if (pIndex == cIndex - ONE) {
                    return PLAYER_WIN_OUTCOME;
                    // index of playerMove is the 1st element
                } else if (pIndex == ZERO) {
                    return CPU_WIN_OUTCOME;
                } else {
                    return TIE_OUTCOME;
                }
            }
        }
    }

    /**
     * main method that reads user input, generates CPU moves,
     * and plays the game. This method is partially completed
     * and you will fill in the rest.
     * 
     * @param args parameter for command line
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        // System.out.println(GAME_NOT_IMPLEMENTED);

        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.

        // set up the game
        game.numGames = 0;
        game.numPlayerWins = 0;
        game.numCPUWins = 0;
        game.playerMoves = new String[MAX_GAMES];
        game.cpuMoves = new String[MAX_GAMES];
        String userInput = null;
        String cMove = null;

        // print prompt massage
        System.out.println(PROMPT_MOVE);
        // read from user
        userInput = in.next();

        // check if user wants to quit
        while (!userInput.equals(QUIT)) {

            // generate move for computer
            cMove = game.genCPUMove();
            // play game
            game.playRPS(userInput, cMove);

            System.out.println(PROMPT_MOVE);
            userInput = in.next();

        }
        // display status
        game.displayStats();
        // close scanner
        in.close();
    }
}
