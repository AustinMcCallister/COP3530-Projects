/**
 * Describes a priority queue implemented with a sorted array, which can add and remove Country objects,
 * and print their information to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 09/28/22
 */
public class PriorityQ {
  private final Country[] queueArray;
  private int rear;

  /**
   * Constructor for the priority queue based on the given size
   *
   * @param  size  The size of the priority queue
   */
  public PriorityQ(int size) {
    this.queueArray = new Country[size];
    this.rear = -1;
  }

  /**
   * Inserts a Country object into the priority queue sorted in descending order
   *
   * @param  country  The Country object to insert into the priority queue
   */
  public void insert(Country country) {
    if (!isFull()) {
      queueArray[++rear] = country;
      for (int i = 1; i < (rear + 1); i++) {
        Country temp = queueArray[i];
        int j = (i - 1);
        while (j >= 0 && queueArray[j].getCFR() < temp.getCFR()) {
          queueArray[j + 1] = queueArray[j];
          j--;
        }
        queueArray[j + 1] = temp;
      }
    }
    else {
      System.out.println("Error: Priority Queue is full");
    }
  }

  /**
   * Removes a Country object from the priority queue and returns it
   *
   * @return The removed Country object
   */
  public Country remove() {
    if (!isEmpty()) {
      return queueArray[rear--];
    }
    else {
      System.out.println("Error: Priority Queue is empty");
      return null;
    }
  }

  /**
   * Checks the priority queue to determine whether it is empty
   *
   * @return True if the priority queue is empty
   */
  public boolean isEmpty() {
    return (rear == -1);
  }

  /**
   * Checks the priority queue to determine whether it is full
   *
   * @return True if the priority queue is full
   */
  public boolean isFull() {
    return (rear == (queueArray.length - 1));
  }

  /**
   * Prints the priority queue to the console from beginning to end
   */
  public void printQueue() {
    System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
    System.out.println("--------------------------------------------------------------------------------------------------------");
    for (int i = rear; i >= 0; i--) {
      System.out.printf("%-33s %-15s %-11.3f %-9.6f %-10.3f %-10.3f %-10.3f\n",
          queueArray[i].getName(),
          queueArray[i].getCapitol(),
          queueArray[i].getGDPPC(),
          queueArray[i].getCFR(),
          queueArray[i].getCaseRate(),
          queueArray[i].getDeathRate(),
          queueArray[i].getPopDensity());
    }
  }
}
