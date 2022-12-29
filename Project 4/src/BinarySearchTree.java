/**
 * Describes a binary search tree which can insert and delete nodes, find a node
 * for a given country name, print itself in three orders, and print the top and bottom
 * 'c' countries by GDP per capita for a user supplied 'c'
 *
 * @author Austin McCallister (N01487083)
 * @version 11/18/22
 */
public class BinarySearchTree {
  private Node root;
  private int numElements;

  /**
   * Constructor for the binary search tree
   */
  public BinarySearchTree() {
    this.root = null;
    this.numElements = 0;
  }

  /**
   * Inserts a node into the binary search tree
   *
   * @param  name  The name of the country
   * @param  GDPPC  The GDP per capita of the country
   */
  public void insert(String name, double GDPPC) {
    Node node = new Node(name, GDPPC);
    numElements++;
    if (root == null) {
      root = node;
    }
    else {
      Node current = root;
      Node parent;
      while (true) {
        parent = current;
        if (name.toLowerCase().compareTo(current.getName().toLowerCase()) < 0) {
          current = current.getLeft();
          if (current == null) {
            parent.setLeft(node);
            return;
          }
        }
        else {
          current = current.getRight();
          if (current == null) {
            parent.setRight(node);
            return;
          }
        }
      }
    }
  }

  /**
   * Finds a node with a given country name and returns its GDP per capita,
   * as well as printing its path to the console
   *
   * @param  name  The name of the country node to find
   * @return The GDP per capita of the found node
   */
  public double find(String name) {
    if (root == null) {
      return -1;
    }
    Node current = root;
    StringBuilder path = new StringBuilder();
    while (name.toLowerCase().compareTo(current.getName().toLowerCase()) != 0) {
      path.append(current.getName());
      if (name.toLowerCase().compareTo(current.getName().toLowerCase()) < 0) {
        current = current.getLeft();
      }
      else {
        current = current.getRight();
      }
      if (current == null) {
        System.out.printf("\n%s is not found.\n", name);
        return -1;
      }
      path.append(" " + "->" + " ");
    }
    path.append(current.getName());
    System.out.printf("\n%s is found with GDP per capita of %.3f.\n", current.getName(), current.getGDPPC());
    System.out.printf("Path to %s is %s.\n", current.getName(), path);
    return current.getGDPPC();
  }

  /**
   * Deletes a node with a given name
   *
   * @param  name  The name of the country node to delete
   */
  public void delete(String name) {
    if (root == null) {
      return;
    }
    Node current = root;
    Node parent = root;
    boolean isLeftChild = false;
    while (name.compareTo(current.getName()) != 0) {
      parent = current;
      if (name.compareTo(current.getName()) < 0) {
        current = current.getLeft();
        isLeftChild = true;
      }
      else {
        current = current.getRight();
        isLeftChild = false;
      }
      if (current == null) {
        System.out.printf("\n%s is not found.\n", name);
        return;
      }
    }
    if (current.getLeft() == null && current.getRight() == null) {
      if (current == root) {
        root = null;
      }
      else if (isLeftChild) {
        parent.setLeft(null);
      }
      else {
        parent.setRight(null);
      }
    }
    else if (current.getRight() == null) {
      if (current == root) {
        root = current.getLeft();
      }
      else if (isLeftChild) {
        parent.setLeft(current.getLeft());
      }
      else {
        parent.setRight(current.getLeft());
      }
    }
    else if (current.getLeft() == null) {
      if (current == root) {
        root = current.getRight();
      }
      else if (isLeftChild) {
        parent.setLeft(current.getRight());
      }
      else {
        parent.setRight(current.getRight());
      }
    }
    else {
      Node successor = getSuccessor(current);
      if (current == root) {
        root = successor;
      }
      else if (isLeftChild) {
        parent.setLeft(successor);
      }
      else {
        parent.setRight(successor);
      }
      successor.setLeft(current.getLeft());
    }
    System.out.printf("\n%s is deleted from binary search tree.\n", name);
  }

  /**
   * Finds and returns the inorder successor of the given node
   *
   * @param  node  The node whose successor will be found
   * @return The successor of the given node
   */
  private Node getSuccessor(Node node) {
    Node successorParent = node;
    Node successor = node;
    Node current = node.getRight();
    while (current != null) {
      successorParent = successor;
      successor = current;
      current = current.getLeft();
    }
    if (successor != node.getRight()) {
      successorParent.setLeft(successor.getRight());
      successor.setRight(node.getRight());
    }
    return successor;
  }

  /**
   * Prints the binary search tree inorder
   */
  public void printInorder() {
    System.out.println("\nName                              GDP Per Capita");
    System.out.println("------------------------------------------------");
    print(root, "inorder");
  }

  /**
   * Prints the binary search tree preorder
   */
  public void printPreorder() {
    System.out.println("\nName                              GDP Per Capita");
    System.out.println("------------------------------------------------");
    print(root, "preorder");
  }

  /**
   * Prints the binary search tree postorder
   */
  public void printPostorder() {
    System.out.println("\nName                              GDP Per Capita");
    System.out.println("------------------------------------------------");
    print(root, "postorder");
  }

  /**
   * Recursively prints the binary search tree depending on the order chosen
   *
   * @param  node  The root of the binary search tree
   * @param  type  The order type to be printed
   */
  private void print(Node node, String type) {
    if (node == null) {
      return;
    }
    switch (type) {
      case "inorder" -> {
        print(node.getLeft(), "inorder");
        node.print();
        print(node.getRight(), "inorder");
      }
      case "preorder" -> {
        node.print();
        print(node.getLeft(), "preorder");
        print(node.getRight(), "preorder");
      }
      case "postorder" -> {
        print(node.getLeft(), "postorder");
        print(node.getRight(), "postorder");
        node.print();
      }
    }
  }

  /**
   * Prints the bottom 'c' countries by GDP per capita to console for a given 'c', using a min heap
   *
   * @param  c  The number of countries to be printed
   */
  public void printBottomCountries(int c) {
    MinHeap heap = new MinHeap(numElements);
    loadHeap(heap, root);
    while (!heap.isEmpty() && c > 0) {
      heap.remove().print();
      c--;
    }
  }

  /**
   * Prints the top 'c' countries by GDP per capita to console for a given 'c', using a max heap
   *
   * @param  c  The number of countries to be printed
   */
  public void printTopCountries(int c) {
    MaxHeap heap = new MaxHeap(numElements);
    loadHeap(heap, root);
    while (!heap.isEmpty() && c > 0) {
      heap.remove().print();
      c--;
    }
  }

  /**
   * Recursively loads the given heap with nodes from the binary search tree
   *
   * @param  heap  The heap to be loaded
   * @param  node  The root of the binary search tree
   */
  private void loadHeap(Heap heap, Node node) {
    if (node == null) {
      return;
    }
    loadHeap(heap, node.getLeft());
    heap.insert(node);
    loadHeap(heap, node.getRight());
  }
}
