
public class Board implements Cloneable {

    int[][] boardList;
    int row_num;
    int col_num;

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

    // Access Methods
    public boolean isFull(){
        boolean empty = true;
        for (int i1 = 0; i1 < row_num; i1++) {
            for (int i2 = 0; i2 < col_num; i2++) {
                if (boardList[i1][i2] == 0) {empty = false; break;}
            }
        }
        return empty;
    }

    public int columnTopIndex(int input_col){
        for(int index = row_num-1; index >= 0; index--) {
            if (boardList[index][input_col] == 0) {return index;}
        }
        return -1;
    }

    public int get(int row, int col) {
        return boardList[row][col];
    }



    // Update Methods
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

    public void update(int rowP, int colP, int val) {
        boardList[rowP][colP] = val;
    }

}
