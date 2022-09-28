/**
 * Describes a stack implemented with an array, which can add and remove Country objects,
 * and print their information to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 09/28/22
 */
public class Stack {
  private final Country[] stackArray;
  private int top;

  /**
   * Constructor for the stack based on the given size
   *
   * @param  size  The size of the stack
   */
  public Stack(int size) {
    this.stackArray = new Country[size];
    this.top = -1;
  }

  /**
   * Pushes a Country object to the top of the stack
   *
   * @param  country  The Country object to push to the top of the stack
   */
  public void push(Country country) {
    if (!isFull()) {
      stackArray[++top] = country;
    }
    else {
      System.out.println("Error: Stack is full");
    }
  }

  /**
   * Removes a Country object from the top of the stack and returns it
   *
   * @return The removed Country object
   */
  public Country pop() {
    if (!isEmpty()) {
      return stackArray[top--];
    }
    else {
      System.out.println("Error: Stack is empty");
      return null;
    }
  }

  /**
   * Checks the stack to determine whether it is empty
   *
   * @return True if the stack is empty
   */
  public boolean isEmpty() {
    return (top == -1);
  }

  /**
   * Checks the stack to determine whether it is full
   *
   * @return True if the stack is full
   */
  public boolean isFull() {
    return (top == (stackArray.length - 1));
  }

  /**
   * Prints the stack to the console from top to bottom
   */
  public void printStack() {
    System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
    System.out.println("--------------------------------------------------------------------------------------------------------");
    for (int i = top; i >= 0; i--) {
      System.out.printf("%-33s %-15s %-11.3f %-9.6f %-10.3f %-10.3f %-10.3f\n",
          stackArray[i].getName(),
          stackArray[i].getCapitol(),
          stackArray[i].getGDPPC(),
          stackArray[i].getCFR(),
          stackArray[i].getCaseRate(),
          stackArray[i].getDeathRate(),
          stackArray[i].getPopDensity());
    }
  }
}
