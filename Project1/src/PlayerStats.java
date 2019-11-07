public class PlayerStats {

    private int numPlayer;

    private int[] numWins;
    private int allPlayerWins = 0;
    private int[] winnerTotalMoves;
    private int[] winnerAveMoves;

    public PlayerStats(int numPlayer){

        this.numPlayer = numPlayer;

        this.numWins = new int[numPlayer];
        this.winnerTotalMoves = new int[numPlayer];
        this.winnerAveMoves = new int[numPlayer];

    }

    // Update Methods
    public void addWin(int winnerID, int movesTaken) {
        numWins[winnerID - 1] += 1;
        allPlayerWins += 1;
        winnerTotalMoves[winnerID - 1] += movesTaken;
    }

    // Statistic Access Methods
    public int getWins(int playerID) { return numWins[playerID-1]; }

    public int[] getWins() { return numWins; }

    public float getWinPercentage(int playerID) {return (float)numWins[playerID-1]/allPlayerWins;}

    public float getWinnerAveMoves(int playerID) {return (float)winnerTotalMoves[playerID-1] / numWins[playerID-1];}


    // Test Methods
    public void printWins() {
        for (int n = 0; n < numPlayer; n++) {
            System.out.println("Player" + (n+1) + " Total Wins: " + numWins[n] + " , Win Percentage: " +getWinPercentage(n+1));
        }
    }

    public void printAverageMoves() {
        for (int n = 0; n < numPlayer; n++) {
            if (numWins[n] != 0) {
                System.out.println("Player" + (n + 1) + " Average Moves: " + (float)winnerTotalMoves[n] / numWins[n]);
            } else {
                System.out.println("No Wins!");
            }
        }
    }


}
