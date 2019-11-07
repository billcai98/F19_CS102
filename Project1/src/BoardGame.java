
/*

    Haoyang Cai
    N18718711; hc2283@nyu.edu
    Oct 2nd 2019
    CS-UA-102 Assignment 1: Board Game

 */
import java.util.ArrayList;
import java.util.Random;

public class BoardGame {


    public static void main(String[] args) {

        PlayerStats[] statsArray = new PlayerStats[4];
        int[] boardSetup = new int[]{0, 5, 10 , 8, 10, 7, 5, 9, 10, 6, 7, 10, 6, 5,   // setup the board
                8, 9, 5, 10, 5, 9, 6, 8, 7, 10, 6, 8, 0};

        //-----------------------------------------Player = 1, 2, 3, 4----------------------------------------------//
        for(int nPlayer = 1; nPlayer <= 4; nPlayer++) {

            // Game Settings
            int numPlayer = nPlayer;
            int runTimes = 1000;
            PlayerStats stats = new PlayerStats(numPlayer);

            // Repeat Games for "runTimes" times
            for (int i = 1; i <= runTimes; i++) {

                // Some tools we use to track the game
                Player[] playerList = new Player[numPlayer];

                // Generate the board
                DoublyLinkedList<Square> board = newBoard(boardSetup);

                // Create players and put into the board
                addPlayer(board, numPlayer, playerList);

                // ---- Start Game ----
                boolean isAnotherRound = true;
                int counter = 0;
                while(isAnotherRound) {
                    counter++;
                    isAnotherRound = newRound(board, playerList);
                }

                // ---- Conclude: Add Wins to the Winner ----
                Player winner = board.last().currentPlayers.get(0);         // Get Winner
                int winnerID = winner.playerID;                             // Get Winner ID
                int movesTaken = winner.movesCount;                         // Get Winner Moves
                stats.addWin(winnerID, movesTaken);

                // Print Result of Round 1, 101, 201...
                if((i % 100) == 1) printBoard(board, numPlayer, i);


            }

            // Simulation ends for n players, record stats
            statsArray[nPlayer-1] = stats;
        }
        //---------------------------------------------------------------------------------------------------------//


        // Print the Result
        printResult(statsArray);


    }


    // Generate new board
    public static DoublyLinkedList<Square> newBoard(int[] boardSetup) {

        DoublyLinkedList<Square> newBoard = new DoublyLinkedList<Square>(); // initialize the board

        for (int i = 0; i < boardSetup.length; i ++) {                  // put squares into the board
            Square tempSquare = new Square(boardSetup[i], i);
            newBoard.addLast(tempSquare);

            //System.out.println(tempSquare);
        }

        return newBoard;
    }

    // Roll the dice
    public static int rollDice( ) {

        Random rd = new Random();
        int dice = rd.nextInt(6) + 1;

        return dice;
    }

    // Add player to the board
    public static void addPlayer(DoublyLinkedList<Square> board, int numPlayer, Player[] playerList) {

        for (int i = 0; i < numPlayer; i++) {

            Player temp = new Player("Player"+(i+1), i+1);
            playerList[i] = temp;                                   // add Player to out tracking list
            temp.setPosition(board.first());                        // Tell player its position
            board.first().currentPlayers.add(temp);                 // Set player its position

//            System.out.println("I am" + temp);
//            System.out.println("My position is: " + temp.getPosition().getPosition());
        }
    }

    // New Round of Game
    public static boolean newRound(DoublyLinkedList<Square> board, Player[] playerList) {

        // control variables
        int numPlayer = playerList.length;

        for (int movingPlayerID = 1; movingPlayerID <= numPlayer; movingPlayerID++) {         // each player will move once

            // initialization
            Player movingPlayer = playerList[movingPlayerID-1];
            int numSteps = rollDice();
            Square currentSquare = movingPlayer.getPosition();
            int currentPosition = currentSquare.getPosition();
            int newPosition = currentPosition + numSteps;

            // Count Step
            movingPlayer.movesCount+=1;

            // Check if the new position is with the boundary
            boolean isInBound = false;
            if (newPosition < board.last().getPosition()) isInBound = true;

            // Start Moving
            if (isInBound) {                                                // **Case 1**: New Position is not beyond
//                Square newSquare = board.getNthElement(newPosition);
                Square newSquare = board.findNthAfterTarget(currentSquare, numSteps);


                if (newSquare.isEmpty()) {                                  // Case 1.1: new Square is {Inbound & Empty}

                    newSquare.currentPlayers.add(movingPlayer);         // Add player to new square
                    currentSquare.currentPlayers.remove(movingPlayer);  // Remove player from the current square
                    movingPlayer.setPosition(newSquare);                // Tell current player it has been moved
                    movingPlayer.totalPoints += newSquare.value;        // Player scores !

                } else {                                                    // Case 1.2: new Square is {Inbound & Occupied)
                    // First, we need to move the previous players back
                    Square backSquare;
                    if (newPosition - 7 < 0) {                              // Case 1.2.1: backSquare is {OutOfBound}
                        backSquare = board.first();
                    } else {                                                // Case 1.2.2: backSquare is {InBound}
                        backSquare = board.findNthAfterTarget(newSquare, -7);
                    }

                    for (int index = 0; index < newSquare.currentPlayers.size(); index++) {         // Loop over previous players on new Square.
                        // Add previous players to backSquare
                        Player backPlayer = newSquare.currentPlayers.get(index);
                        backSquare.currentPlayers.add(backPlayer);
                        backPlayer.setPosition(backSquare);
                    }
                    newSquare.currentPlayers.clear();                                              // Clear the "new board" for the movingPlayer to move in

                    // Second, we add the movingPlayer to the newSquare
                    newSquare.currentPlayers.add(movingPlayer);                                    // Add player to new square
                    currentSquare.currentPlayers.remove(movingPlayer);                             // Remove player from the current square
                    movingPlayer.setPosition(newSquare);                                           // Tell current player it has been moved
                    movingPlayer.totalPoints += newSquare.value;                                   // Player scores !

                }

            } else {                                                         // Case 2: new Square is {OutOfBound}

                if (movingPlayer.totalPoints >= 44) {

                    board.last().currentPlayers.add(movingPlayer);                   // Put player to the end
                    currentSquare.currentPlayers.remove(movingPlayer);               // Remove player from current square
                    movingPlayer.setPosition(board.last());

                    return false;                                                    // Game ends

                } else {                                                     // Case 2.2: Not Enough Score --> Go Back to Start
                    movingPlayer.totalPoints = 0;                                    // Reset Points to Zero
                    board.first().currentPlayers.add(movingPlayer);                  // Add player to new square
                    currentSquare.currentPlayers.remove(movingPlayer);               // Remove player from the current square
                    movingPlayer.setPosition(board.first());                         // Tell current player: he/she has been moved
                }

            }
        }

    return true;
    }

    // Print Result
    public static void printResult(PlayerStats[] statsArray) {

        System.out.println("-----------------------------------------------------------------------------------------------  ");
        System.out.println("                                        Result Table                                            ");
        System.out.println("-----------------------------------------------------------------------------------------------  ");
        System.out.println("|                | Player A average | Player B average | Player C average | Player D average | ");
        System.out.println("| Player in game | moves/% winning  | moves/% winning  | moves/% winning  | moves/% winning  | ");
        System.out.println("-----------------------------------------------------------------------------------------------  ");

//        String[] rows = new String[statsArray.length];

        for (int i = 0; i < statsArray.length; i++) {
            String rows = "";

            switch(i) {
                case 0: rows = "|A               "; break;
                case 1: rows = "|A,B             "; break;
                case 2: rows = "|A,B,C           "; break;
                case 3: rows = "|A,B,C,D         "; break;
            }

            PlayerStats stats = statsArray[i];
            for (int p = 1; p <= i+1; p++) {
                float winPercent = stats.getWinPercentage(p) * 100;
                rows += String.format("|  %-5.2f, %-6.2f%%%2s", stats.getWinnerAveMoves(p), winPercent, " ");
            }

            for(int p = 0; p < statsArray.length - i; p++) {rows += "|                  ";}
            System.out.println(rows);

        }
        System.out.println();
    }

    // Print Board Methods
    public static void printBoard(DoublyLinkedList<Square> board, int numPlayer, int round) {

        System.out.println("-------------------------------------------[   Number Of Players: "+ numPlayer +" -- Round " + round +"    ]-------------------------------------------");

        Square s = board.first();

        for(int i = 0; i < board.size(); i++){
            if(s.currentPlayers.isEmpty()) {

                if (i == 0) {
                    System.out.print("[*** START ***] -->\n");
                } else if ( i == board.size()-1 ){
                    System.out.print("\n[*** END ***]");
                } else {System.out.print("(    ''''''''    )");}

            } else {
                if (i == 0) {System.out.print("[*** START: ");} else if (i==board.size()-1){
                    System.out.print("\n[!!*** WINNER: ");
                } else {System.out.print("( ");}
                String plrs = "";
                for (int p = 0; p < s.currentPlayers.size(); p++) {
                    plrs += "P" + s.currentPlayers.get(p).playerID;
                    if (p < s.currentPlayers.size() - 1 ) plrs += ", ";
                }

                if (i == 0) {System.out.print(plrs+" ***]-->\n");} else if (i == board.size()-1) {
                    System.out.print(plrs+" ***!!]");
                } else {

                    switch(s.currentPlayers.size()){
                        case 1: plrs = "      "+plrs+"      "; break;
                        case 2: plrs = "    "+plrs+"    "; break;
                        case 3: plrs = "  "+plrs+"  "; break;
                        case 4: break;
                    }

                    System.out.print(plrs+" )");
                }
            }

            if (i < board.size() - 1) {
                s = board.findNthAfterTarget(s, 1);
                if(!(i == 0)) System.out.print("-->");
            }

            if (i == 6 || i == 12 || i == 18 || i ==24) System.out.println();
        }
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\n");
    }


}
