public class MainDriver {

    static final int X=1, O=2;

    public static void main(String[] args) throws CloneNotSupportedException {

        int[][] boardList = {{1, 2, 3},
                             {3, 4, 5},
                             {5, 6, 7},
                             {8, 9, 10}};

        Board myBoard = new Board(boardList);


    }

    public static void play(Board boart, int clr) {
    }


    public static int checkBoard(Board board, int clr) {
        return 1;
    }

}
