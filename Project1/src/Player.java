public class Player {

    int totalPoints;
    int movesCount = 0;
    int movesCount_dice = 0;
    String playerName;
    int playerID;
    private Square position;

    // Constructor
    public Player(String playerName, int playerID) {

        this.playerName = playerName;
        this.playerID = playerID;
    }


    // toString
    public String toString() {
        return "[" + playerName + "]" + ": Score" + totalPoints;
    }

    // Change of position
    public Square getPosition() {return this.position;}
    public void setPosition(Square newPosition) {this.position = newPosition;}



}
