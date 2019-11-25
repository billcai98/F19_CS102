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

    public Node<E> root;
    private Node<E> cursor;

    // public methods
    public void Root(E element) {
        this.root = new Node<E> (element, null, null, null, null);
        this.cursor = this.root;
    }

    public void Add(E element, String d) {
        String direction = d.toUpperCase();

        switch (direction) {
            case "NORTH":
                break;
            case "EAST":
                break;
            case "SOUTH":
                break;
            case "WEST":
                break;
        }

        System.out.println("WRONG DIRECTION!");
    }

    public void Move(String direction) {}

    public void Print() {}


}
