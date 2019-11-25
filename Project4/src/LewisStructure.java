public class LewisStructure<E> {

    private static class Node<E> {

        private E element;               // reference to the element stored at this node

        private Node<E> north;            // reference to the North node in the list
        private Node<E> east;            // reference to the East node in the list
        private Node<E> south;            // reference to the South node in the list
        private Node<E> west;            // reference to the West node in the list

        public Node(E e, Node<E> north, Node<E> east,  Node<E> south,  Node<E> west) {
            element = e;
            this.north = north;
            this.south = south;
            this.east = east;
            this.west = west;
        }

        // public accessor methods
        public E getElement() { return element; }

        public Node<E> getNorth() { return north; }
        public Node<E> getEast() { return east; }
        public Node<E> getSouth() { return south; }
        public Node<E> getWest() { return west; }

        public void setNorth(Node<E> north) { this.north = north; }
        public void setEast(Node<E> east) { this.east = east; }
        public void setSouth(Node<E> south) { this.south = south; }
        public void setWest(Node<E> west) { this.west = west; }

    } //----------- end of nested Node class -----------

    private int size = 0;
    private int[] size4D = {0, 0, 0, 0};
    private Node<E> root;
    private Node<E> cursor;

    // public methods
    public void Root(E element) {
        this.root = new Node<E> (element, null, null, null, null);
        this.cursor = this.root;
        size4D[0] += 1; size4D[1] += 1; size4D[2] += 1; size4D[3] += 1;
        size++;
    }

    public E getRoot() { return root.getElement(); }
    public E getCursor() { return cursor.getElement(); }
    public int getSize() { return size; }

    /***
     *      Add: add element to direction
     */
    public void Add(E element, String d) {
        String direction = d.toUpperCase();
        switch (direction) {
            case "NORTH":
                cursor.setNorth(new Node<E>(element, null, null, cursor, null));
                cursor = cursor.north;
                size4D[0]+=1;
                break;
            case "EAST":
                cursor.setEast(new Node<E>(element, null, null, null, cursor));
                cursor = cursor.east;
                size4D[1]+=1;
                break;
            case "SOUTH":
                cursor.setSouth(new Node<E>(element, cursor, null, null, null));
                cursor = cursor.south;
                size4D[2]+=1;
                break;
            case "WEST":
                cursor.setWest(new Node<E>(element, null, cursor, null, null));
                cursor = cursor.west;
                size4D[3]+=1;
                break;
        }
        size++;
    }

    /***
     *      Move: move cursor to the designated direction
     */
    public void Move(String d) {
        String direction = d.toUpperCase();

        switch (direction) {
            case "NORTH": cursor = cursor.north; break;
            case "EAST": cursor = cursor.east; break;
            case "SOUTH": cursor = cursor.south; break;
            case "WEST": cursor = cursor.west; break;
        }
    }

    /***
     *     Print: Full Version
     */
    public void Print() {
        // Initialize the list to print
        String[][] list = new String[(size4D[0]+size4D[2])*2][(size4D[1]+size4D[3])*2 + 2];

        // Print Boundary
        for (int i = 0; i<list[0].length+2; i++){System.out.print("---");}
        System.out.println();

        // Walk through the structure and generate the list to print
        int[] root_pos = {size4D[0]*2, size4D[3]*2};
        recursivePrint(root, "none", list, root_pos);

        // Display the list
        for(int row = 0; row < list.length; row++) {
            System.out.print("***");
            for (int col = 0; col < list[0].length; col++) {
                if (list[row][col] != null) {
                    System.out.print(list[row][col]);
                } else { System.out.print("   "); }
            }
            System.out.println("***");
        }

        // Print Boundary
        for (int i = 0; i<list[0].length+2; i++){System.out.print("---");}
        System.out.println();
    }

    private boolean recursivePrint(Node<E> center, String lastDirection, String[][] list, int[] pos) {
        // Base Case
        if (center == null) { return false; }

        // Initialization
        lastDirection = lastDirection.toUpperCase();
        int[] newPos = {pos[0], pos[1]};
        int p0 = pos[0];
        int p1 = pos[1];
        boolean[] haveElement = {false, false, false, false};

        // Begins walk
        if (!lastDirection.equals("NORTH")) {
            newPos[0] = p0 - 2; newPos[1] = p1;
            haveElement[0] = recursivePrint(center.north, "South", list, newPos);
        }
        if (!lastDirection.equals("EAST")) {
            newPos[0] = p0; newPos[1] = p1 + 2;
            haveElement[1] = recursivePrint(center.east, "West", list, newPos);
        }
        if (!lastDirection.equals("SOUTH")) {
            newPos[0] = p0 + 2; newPos[1] = p1;
            haveElement[2] = recursivePrint(center.south, "North", list, newPos);
        }
        if (!lastDirection.equals("WEST")) {
            newPos[0] = p0; newPos[1] = p1 - 2;
            haveElement[3] = recursivePrint(center.west, "East", list, newPos);
        }

        // Put element into its position
        list[pos[0]][pos[1]] = " "+(String)center.getElement()+" ";

        // Now we fill in the bars
        if (haveElement[0]) {list[pos[0]-1][pos[1]] = " | ";}
        if (haveElement[2]) {list[pos[0]+1][pos[1]] = " | ";}
        if (haveElement[1]) {list[pos[0]][pos[1]+1] = "---";}
        if (haveElement[3]) {list[pos[0]][pos[1]-1] = "---";}

        return true;
    }

    /***
     *     Print: Simple Version
     */
    public void Print1() { recursivePrint(root, "none"); }

    private void recursivePrint(Node<E> center, String lastDirection) {
        if (center == null) { return; }

        lastDirection = lastDirection.toUpperCase();

        if (!lastDirection.equals("NORTH")) { recursivePrint(center.north, "South"); }
        if (!lastDirection.equals("EAST")) { recursivePrint(center.east, "West"); }
        if (!lastDirection.equals("SOUTH")) { recursivePrint(center.south, "North"); }
        if (!lastDirection.equals("WEST")) { recursivePrint(center.west, "East"); }

        System.out.print(center.getElement()+" ");
    }






}
