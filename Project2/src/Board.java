
public class Board implements Cloneable {

    private int[][] boardList;
    private int row_num;
    private int col_num;

    // Board Constructor
    Board(int[][] boardList) {

        this.row_num = boardList.length;
        this.col_num = boardList[0].length;

        this.boardList = new int[row_num][col_num];
        for (int i = 0; i < row_num; i++) {
            for (int j = 0; j < col_num; j++) {
                this.boardList[i][j] = boardList[i][j];
            }
        }
    }

    // Print Method
    public void printBoard() {
        for (int i = 0; i < row_num; i++) {
            for (int j = 0; j < col_num; j++) {
                System.out.print(boardList[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Accessor Methods
     */
    // isFull() -- return if the board is full
    public boolean isFull(){
        boolean full = true;
        for (int i1 = 0; i1 < row_num; i1++) {
            for (int i2 = 0; i2 < col_num; i2++) {
                if (boardList[i1][i2] == 0) {full = false; break;}
            }
        }
        return full;
    }

    // isFull(col) -- return if the col is full
    public boolean isFull(int col) {
        boolean full = true;
        for (int i1 = 0; i1 < row_num; i1++) {
            if (boardList[i1][col] == 0) {full = false; break;}
        }
        return full;
    }

    // columnTopIndex(col) -- return index of the top of this column; return -1 if the column if full
    public int columnTopIndex(int input_col){
        for(int index = row_num-1; index >= 0; index--) {
            if (boardList[index][input_col] == 0) {return index;}
        }
        return -1;
    }

    // get(row, col) -- return value at position (row, col)
    public int get(int row, int col) {
        return boardList[row][col];
    }




    /**
     * Update Methods
     */
    // clone() -- return a deep copy of current board
    public Object clone() throws CloneNotSupportedException {
        Board cloneBoard = (Board) super.clone();
        int[][] cloneList = new int[row_num][col_num];
        for (int i = 0; i<row_num; i++) {
            for (int j = 0; j<col_num; j++) {
                cloneList[i][j] = boardList[i][j];
            }
        }
        cloneBoard.boardList = cloneList;
        return cloneBoard;
    }

    // update(row, col, val) -- add "val" to given position
    public void update(int rowP, int colP, int val) {
        boardList[rowP][colP] = val;
    }

    // move(row, col, ply) -- make a move at given "col_to_move"
    public void move(int col_to_move, int plyr) {
        if (plyr == 1 || plyr == 2){
            int row_to_move = this.columnTopIndex(col_to_move);
            this.update(row_to_move, col_to_move, plyr);
        } else {
            System.out.println("NOT A PLAYER ! !");
        }
    }

}
