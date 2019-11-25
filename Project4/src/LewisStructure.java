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
    private Node<E> root;
    private Node<E> cursor;

    // public methods
    public void Root(E element) {
        this.root = new Node<E> (element, null, null, null, null);
        this.cursor = this.root;
    }

    public E getRoot() { return root.getElement(); }
    public E getCursor() { return cursor.getElement(); }


    public void Add(E element, String d) {
        String direction = d.toUpperCase();
        switch (direction) {
            case "NORTH":
                cursor.setNorth(new Node<E>(element, null, null, cursor, null));
                cursor = cursor.north;
                break;
            case "EAST":
                cursor.setEast(new Node<E>(element, null, null, null, cursor));
                cursor = cursor.east;
                break;
            case "SOUTH":
                cursor.setSouth(new Node<E>(element, cursor, null, null, null));
                cursor = cursor.south;
                break;
            case "WEST":
                cursor.setWest(new Node<E>(element, null, cursor, null, null));
                cursor = cursor.west;
                break;
        }
        size++;
    }

    public void Move(String d) {
        String direction = d.toUpperCase();

        switch (direction) {
            case "NORTH": cursor = cursor.north; break;
            case "EAST": cursor = cursor.east; break;
            case "SOUTH": cursor = cursor.south; break;
            case "WEST": cursor = cursor.west; break;
        }
    }

    public void Print() { recursivePrint(root, "none"); }

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
