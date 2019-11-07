import java.util.ArrayList;

public class Square {

    // Member Variables
    private int position;
    int value;
    ArrayList<Player> currentPlayers = new ArrayList<Player>();

    // Constructor
    public Square(int value, int position) {

        this.value = value;
        this.position = position;

    }

    // Check Emptiness
    public boolean isEmpty() {
        return currentPlayers.isEmpty();
    }

    // Position getter
    public int getPosition() {return this.position;}

    // toString Method
    public String toString() {
        String returnString = "";
        // Square Info
        if (position == 0)
            returnString += "\nSQUARE START"+  ": ";
        else if (position == 26)
            returnString += "\nSQUARE END"+  ": ";
        else
            returnString += "\nSQUARE "+ this.position + ": ";
        returnString += "Value: " + value + "\n";

        // Current Player List
        returnString += "Player List: \n";
        if (this.isEmpty()){
            returnString += "" + "******EMPTY******" +  " \n";
        } else {
            for (int i = 0; i < currentPlayers.size(); i++)
                returnString += "/---------" +currentPlayers.get(i).toString() + " ---------/ \n";
        }

        return returnString;
    }


}
