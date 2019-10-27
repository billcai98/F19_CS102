
public class Board implements Cloneable {

    int[][] boardList;
    int row;
    int col;

    // Board Constructor
    Board(int[][] boardList) {

        this.row = boardList.length;
        this.col = boardList[0].length;

        this.boardList = new int[row][col];
        for (int i = 0; i<row; i++) {
            for (int j = 0; j<col; j++) {
                this.boardList[i][j] = boardList[i][j];
            }
        }
    }

    // Print Method
    public void printBoard() {
        for (int i = 0; i<row; i++) {
            for (int j = 0; j<col; j++) {
                System.out.print(boardList[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Public Methods
    public boolean isFull(){
        boolean empty = true;
        for (int i1 = 0; i1 < row; i1++) {
            for (int i2 = 0; i2 < col; i2++) {
                if (boardList[i1][i2] == 0) {empty = false; break;}
            }
        }
        return empty;
    }


    // Update Methods
    public Object clone() throws CloneNotSupportedException {

        Board cloneBoard = (Board) super.clone();
        int[][] cloneList = new int[row][col];
        for (int i = 0; i<row; i++) {
            for (int j = 0; j<col; j++) {
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
