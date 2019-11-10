
import net.datastructures.*;
import java.util.Comparator;


public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }
  
  public void printTree() {
    // Put your code to print AVL tree here
    System.out.println("Print of Tree");

    // Parameters of the tree
    int height_tree = height(root());
    int beginCol = 50;
    int beginLevel = 2 * height_tree;

    // Create List of Nodes
    String[][] treeList = new String[100][100];

    // Put tree into the array
    printNodesInternal(root(), beginLevel, beginCol, treeList);

    // Print the array
    for (int level = height_tree*2; level>=0; level--) {
      for (int columns = 0; columns < treeList[0].length; columns++) {
        if (treeList[level][columns] != null) {
          System.out.print(treeList[level][columns]);
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }

  }

  public void printNodesInternal(Position<Entry<K,V>> current, int level, int col, String[][] treeList) {

    // If the node is null, return
    if (current.getElement() == null) {
      return;
    }

    // First, we put the current element in its position
    treeList[level][col] = (String) current.getElement().getKey();

    // Then we put arrows into the corresponding positions, and print children
    int space = calculateSpace(level / 2);

    if (left(current).getElement() != null) {
      treeList[level - 1][col - (space / 2)] = "/";
      printNodesInternal(left(current), level - 2, col - space, treeList);
    }

    if (right(current).getElement() != null) {
      treeList[level - 1][col + (space / 2)] = "\\";
      printNodesInternal(right(current), level - 2, col + space, treeList);
    }

    return;
  }

  public int calculateSpace(int level) {
     if (level == 1) {return 2;}
     if (level == 2) {return 3;}
    return 6 * (int) Math.pow(2, level-3);
  }

   
}
