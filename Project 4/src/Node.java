/**
 * Describes a Node object for use within a binary search tree which stores country data
 * to include country name and GDP per capita as well as containing references to its children
 *
 * @author Austin McCallister (N01487083)
 * @version 11/17/22
 */
public class Node {
  private final String name;
  private final double GDPPC;
  private Node left;
  private Node right;

  /**
   * Constructor for the Node
   *
   * @param  name  The name of the country
   * @param  GDPPC  The GDP per capita of the country
   */
  public Node(String name, double GDPPC) {
    this.name = name;
    this.GDPPC = GDPPC;
  }

  /**
   * Returns country name
   *
   * @return The name of the country
   */
  public String getName() {
    return name;
  }

  /**
   * Gets country GDP per capita
   *
   * @return The GDP per capita of the country
   */
  public double getGDPPC() {
    return GDPPC;
  }

  /**
   * Gets the reference to the left child
   *
   * @return The left child reference
   */
  public Node getLeft() {
    return left;
  }

  /**
   * Gets the reference to the right child
   *
   * @return The right child reference
   */
  public Node getRight() {
    return right;
  }

  /**
   * Sets the left child reference
   *
   * @param  node  The node to be the left child
   */
  public void setLeft(Node node) {
    left = node;
  }

  /**
   * Sets the right child reference
   *
   * @param  node  The node to be the right child
   */
  public void setRight(Node node) {
    right = node;
  }

  /**
   * Prints formatted node data to console
   */
  public void print() {
    System.out.printf("%-33s %.3f\n", name, GDPPC);
  }
}
