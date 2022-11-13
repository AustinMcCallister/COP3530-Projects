/**
 * Describes a stack implemented with an array, which can add and remove Country objects,
 * and print their information to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 10/26/22
 */
public class Stack {
  private Node head;
  private Node tail;

  /**
   * Describes a singly linked node object which stores Country objects
   *
   * @author Austin McCallister (N01487083)
   * @version 10/18/22
   */
  private static class Node {
    private final Country country;
    private Node next;

    /**
     * Constructor for the node
     *
     * @param  country  The country object to be added to the node
     */
    public Node(Country country) {
      this.country = country;
    }

    /**
     * Returns the country object contained in the node
     *
     * @return The removed Country object
     */
    public Country getData() {
      return country;
    }
  }

  /**
   * Constructor for the stack
   */
  public Stack() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Pushes a Country object to the top of the stack
   *
   * @param  country  The Country object to push to the top of the stack
   */
  public void push(Country country) {
    Node node = new Node(country);
    if (isEmpty()) {
      tail = node;
    }
    node.next = head;
    head = node;
  }

  /**
   * Removes a Country object from the top of the stack and returns it
   *
   * @return The removed Country object
   */
  public Country pop() {
    if (isEmpty()) {
      return null;
    }
    Node temp = head;
    head = head.next;
    return temp.getData();
  }

  /**
   * Checks the stack to determine whether it is empty
   *
   * @return True if the stack is empty
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Calls the printFrom() method
   */
  public void printStack() {
    printFrom(head);
  }

  /**
   * Recursively prints the stack to the console from top to bottom
   *
   * @param  node  The node to start printing from
   */
  private void printFrom(Node node) {
    if (node == null) {
      return;
    }
    System.out.printf("%-33s %-15s %-11.3f %-9.6f %-10.3f %-10.3f %-10.3f\n",
        node.getData().getName(),
        node.getData().getCapitol(),
        node.getData().getGDPPC(),
        node.getData().getCFR(),
        node.getData().getCaseRate(),
        node.getData().getDeathRate(),
        node.getData().getPopDensity());
    printFrom(node.next);
  }
}
