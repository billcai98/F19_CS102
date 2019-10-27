public class MainDriver {

    private static final int NUM_COLUMNS = 4;
    private static final int NUM_ROWS = 4;
    private static final int NUM_TO_WIN = 4;
    private static int[] stats;
    private static int reCount;


    public static void main(String[] args) throws CloneNotSupportedException {

        for (int initial_col = 0; initial_col < NUM_COLUMNS; initial_col++) {
            // Reset Counters
            stats = new int[2];
            reCount = 0;

            // Make New Board
            int[][] boardList = new int[NUM_ROWS][NUM_COLUMNS];
            Board myBoard = new Board(boardList);

            // First Move + Play Game
            myBoard.move(initial_col, 1);
            play(myBoard, 2);

            // Print Results
            System.out.println("COLUMN " + (initial_col+1) );
            System.out.println("P1 Net Wins: " + (stats[0]-stats[1]));
            System.out.println("BLUE Wins: " + stats[0] + " RED Wins: " + stats[1]);
            System.out.println("Total Recursive Calls: " + reCount);
            System.out.println();
        }


    }


    /**
     * Method 1     :  PLAY
     * Description  :
     */
    public static void play(Board board, int player) throws CloneNotSupportedException {
        // Recursion Counter
        reCount ++;

        // Base Case
        int result = checkBoard(board, 3-player);
        if (result < 3) {
            if (result == 1) { stats[0] += 1; }         // Case 1: Player 1 (Red) wins in last round
            else if (result == 2) { stats[1] += 1; }    // Case 2: Player 2 (Blue) wins in last round
            return;
        }

        // Recursive Body
        for (int curCol = 0; curCol < NUM_COLUMNS; curCol++) {                  // Loop over columns (next move)

            if (! board.isFull(curCol) ) {                              // Check if the column is full
                Board newBoard = (Board) board.clone();         // Clone the board
                newBoard.move(curCol, player);                  // Make a move in this column
                play(newBoard, 3-player);               // Send in the next player. Player next game
            }

        }
    }


    /**
     * Method 2     :  CHECK BOARD
     * Description  :  Return 0 if the board is full, 1 if R wins, 2 if B wins, 3 if game continues
     */
    public static int checkBoard(Board board, int last_plr) {
        // First--Check Rows
        for (int row = 0; row < NUM_ROWS; row++) {
            int consecutive = 0;                                            // New Row, Set Counter to 0
            for (int col = 0; col < NUM_COLUMNS; col++) {                   // Loop over columns
                if (board.get(row, col) == last_plr) {
                    consecutive++;
                    if (consecutive == NUM_TO_WIN) {return last_plr;}       // If win, return "last player"
                } else {
                    consecutive = 0;                                        // If not equal, then consecutive ends. Reset Counter
                }
            }
        }

        // Second--Check Columns
        for (int col = 0; col < NUM_COLUMNS; col++) {
            int consecutive = 0;                                            // New Column, Set Counter to 0
            for (int row = 0; row < NUM_ROWS; row++) {                      // Loop over rows
                if (board.get(row, col) == last_plr) {
                    consecutive++;                                          // If matched, counter++
                    if (consecutive == NUM_TO_WIN) {return last_plr;}       // If win, return "last player"
                } else {
                    consecutive = 0;
                }
            }
        }

        // Third--Check Diagonals
        int consecutive = 0;
        for (int pivot = 0; pivot < NUM_COLUMNS; pivot++ ) {
            if (board.get(pivot, pivot) == last_plr) {
                consecutive++;
                if (consecutive == NUM_TO_WIN)  {return last_plr;}
            }  else {
                consecutive = 0;
            }
        }

        consecutive = 0;
        for (int pivot = 0; pivot < NUM_COLUMNS; pivot++ ) {
            if (board.get(NUM_COLUMNS-1-pivot, pivot) == last_plr) {
                consecutive++;
                if (consecutive == NUM_TO_WIN)  {return last_plr;}
            }  else {
                consecutive = 0;
            }
        }

        // Last--Check "full" or "continue"
        if (board.isFull()) {return 0;}
        return 3;
    }
}
