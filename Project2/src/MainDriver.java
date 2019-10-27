public class MainDriver {

    static final int R=1, B=2;
    static final int NUM_COLUMNS = 4;
    static final int NUM_ROWS=4;
    static final int NUM_TO_WIN=4;


    public static void main(String[] args) throws CloneNotSupportedException {

        int[][] boardList = {{0, 0, 0, 0},
                             {1, 1, 0, 1},
                             {0, 2, 1, 2},
                             {1, 0, 2, 1}};

        Board myBoard = new Board(boardList);
        myBoard.move(1, 1);
        myBoard.move(3, 2);
        myBoard.move(0, 1);
        myBoard.printBoard();

        System.out.println(checkBoard(myBoard, 1));
    }


    /**
     * Method 1     :  PLAY
     * Description  :
     */
    public static void play(Board board, int player) throws CloneNotSupportedException {

        // Base Case


        // Recursive Body
        for (int curCol = 0; curCol < NUM_COLUMNS; curCol++) {                  // Loop over columns (next move)

            if (! board.isFull(curCol) ) {                                            // Check if the column is full
                Board newBoard = (Board) board.clone();     // Clone the board
                newBoard.move(curCol, player);              // Make a move at this column
//                Play(newBoard, 3-player);
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
