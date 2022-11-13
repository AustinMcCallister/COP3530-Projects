/**
 * Describes a priority queue implemented with a sorted doubly linked list, which can add and remove Country objects,
 * remove objects within given interval, and print their information to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 10/18/22
 */
public class PriorityQ {
  private Node head;
  private Node tail;

  /**
   * Describes a doubly linked node object which stores Country objects
   *
   * @author Austin McCallister (N01487083)
   * @version 10/18/22
   */
  private static class Node {
    private final Country country;
    private Node next;
    private Node previous;

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
   * Constructor for the priority queue
   */
  public PriorityQ() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Inserts a Country object into the priority queue sorted in ascending order
   *
   * @param  country  The Country object to insert into the priority queue
   */
  public void insert(Country country) {
    Node node = new Node(country);
    if (isEmpty()) {
      head = node;
      tail = node;
      return;
    }
    Node current = head;
    while (current != null) {
      if (node.getData().getDeathRate() < current.getData().getDeathRate()) {
        if (current == head) {
          head = node;
        }
        if (current.previous != null) {
          current.previous.next = node;
          node.previous = current.previous;
        }
        node.next = current;
        current.previous = node;
        return;
      }
      current = current.next;
    }
    node.previous = tail;
    tail.next = node;
    tail = node;
  }

  /**
   * Removes a Country object from the priority queue and returns it
   *
   * @return The removed Country object
   */
  public Country remove() {
    if (isEmpty()) {
      return null;
    }
    Node temp = head;
    head = head.next;
    return temp.getData();
  }

  /**
   * Removes a range of Country objects from the priority queue and returns true or false
   * depending on if objects were removed
   *
   * @param  x  The lower bound of the interval
   * @param  y  The upper bound of the interval
   * @return True if objects were deleted, false otherwise
   */
  public boolean intervalDelete(int x, int y) {
    Node current = head;
    boolean deleted = false;
    while (current != null) {
      if (current.getData().getDeathRate() >= x && current.getData().getDeathRate() <= y) {
        if (current.previous == null) {
          head = current.next;
        }
        else {
          current.previous.next = current.next;
        }
        if (current.next != null) {
          current.next.previous = current.previous;
        }
        if (current == tail) {
          tail = null;
        }
        deleted = true;
      }
      else {
        tail = current;
      }
      current = current.next;
    }
    return deleted;
  }

  /**
   * Checks the priority queue to determine whether it is empty
   *
   * @return True if the priority queue is empty
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Calls the printFrom() method
   */
  public void printPriorityQ() {
    printFrom(head);
  }

  /**
   * Recursively prints the priority queue to the console from beginning to end
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
