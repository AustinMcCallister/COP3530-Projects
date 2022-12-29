/**
 * Describes a double-ended singly-linked list, which can add and remove Node objects,
 * and print their information to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 11/28/22
 */
public class LinkedList {
  private Node head;
  private Node tail;

  /**
   * Describes a singly linked Node object which stores country data
   *
   * @author Austin McCallister (N01487083)
   * @version 11/27/22
   */
  private class Node {
    String name;
    long population;
    long cases;
    Node nextNode;

    /**
     * Constructor for the node
     *
     * @param  name  The name of the country
     * @param  population  The population of the country
     * @param  cases  The number of COVID cases in the country
     */
    public Node(String name, long population, long cases) {
      this.name = name;
      this.population = population;
      this.cases = cases;
    }

    /**
     * Prints node information to the console
     */
    public void printNode() {
      System.out.printf("%-30s %-20.3f\n", name, (double)cases/population*100000);
    }
  }

  /**
   * Constructor for the linked list
   */
  public LinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Returns the head node of the linked list
   *
   * @return The head of the linked list
   */
  public Node getHead() {
    return head;
  }

  /**
   * Returns the tail node of the linked list
   *
   * @return The tail of the linked list
   */
  public Node getTail() {
    return tail;
  }

  /**
   * Inserts a new country into the linked list
   *
   * @param  country  The name of the country
   * @param  population  The population of the country
   * @param  cases  The number of COVID cases in the country
   */
  public void insert(String country, long population, long cases) {
    Node node = new Node(country, population, cases);
    if (isEmpty()) {
      head = node;
    }
    else {
      tail.nextNode = node;
    }
    tail = node;
  }

  /**
   * Searches for a data node containing the given country name and deletes it if found
   *
   * @param  country  The name of the country
   */
  public void delete(String country) {
    if (head.name.compareTo(country) == 0) {
      head = head.nextNode;
      System.out.printf("\n%s is deleted from hash table\n", country);
      return;
    }
    Node current = head;
    Node previous = current;
    while (current != null) {
      if (current.name.compareTo(country) == 0) {
        if (current == tail) {
          previous.nextNode = null;
          tail = previous;
        }
        else {
          previous.nextNode = current.nextNode;
        }
        System.out.printf("\n%s is deleted from hash table\n", country);
        return;
      }
      previous = current;
      current = current.nextNode;
    }
    System.out.printf("\n%s is not a country\n", country);
  }

  /**
   * Searches for a data node containing the given country name, returning the case rate
   * if found or -1 otherwise
   *
   * @param  country  The name of the country
   * @return The case rate if found, or -1 otherwise
   */
  public double find(String country) {
    Node current = head;
    while (current != null) {
      if (current.name.compareTo(country) == 0) {
        return ((double)current.cases / current.population * 100000);
      }
      current = current.nextNode;
    }
    return -1;
  }

  /**
   * Prints the contents of the linked list to console
   */
  public void print() {
    if (isEmpty()) {
      System.out.println("Empty");
    }
    else {
      Node current = head;
      boolean printed = false;
      while (current != null) {
        if (printed) {
          System.out.print("     ");
        }
        current.printNode();
        printed = true;
        current = current.nextNode;
      }
    }
  }

  /**
   * Checks the stack to determine whether it is empty
   *
   * @return True if the linked list is empty
   */
  public boolean isEmpty() {
    return (head == null);
  }
}
