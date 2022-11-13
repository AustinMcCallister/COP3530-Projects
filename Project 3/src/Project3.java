import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 – Linked Lists
 * <p>
 * This program takes in a user specified csv file of country data and displays a list of options
 * which the user can use to perform various functions, to include adding and removing countries
 * to a stack or priority queue, and printing the data from the stack or priority queue to console
 *
 * @author Austin McCallister (N01487083)
 * @version 10/26/22
 */
public class Project3 {
  /**
   * Instantiates the stack and priority queue, loads them with Country objects
   * from a user-defined file, and initiates the main menu loop
   *
   * @param  args  Command line arguments
   */
  public static void main(String[] args) {
    Stack stack = new Stack();

    System.out.print("COP3530 Project 3\n");
    System.out.print("Instructor: Xudong Liu\n\n");
    System.out.print("Linked Lists\n");

    loadData(stack);
    runMenu(stack);
  }

  /**
   * Takes in the user's desired csv file and instantiates a Country object for each record, inserting them
   * sequentially into the Country stack if their death rate is within the required range
   *
   * @param  stack  The stack to load country objects into
   */
  public static void loadData(Stack stack) {
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.print("Enter the file name: ");
      String filename = input.nextLine();
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        reader.readLine(); // Remove header line from input
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
          String[] inputString = line.split(",");
          String name = inputString[0];
          String capitol = inputString[1];
          double population = Double.parseDouble(inputString[2]);
          double GDP = Double.parseDouble(inputString[3]);
          double covidCases = Double.parseDouble(inputString[4]);
          double covidDeaths = Double.parseDouble(inputString[5]);
          double area = Double.parseDouble(inputString[6]);
          double deathRate = ((covidDeaths / population) * 100000);
          if (deathRate >= 20 && deathRate < 350) {
            stack.push(new Country(name, capitol, population, GDP, covidCases, covidDeaths, area));
            i++;
          }
        }
        System.out.printf("\nThere were %d records read.\n", i);
        return;
      }
      catch (FileNotFoundException e) {
        System.out.println("File does not exist!");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Prints the main menu options to the console
   */
  public static void printMenu() {
    System.out.println("\n1. Enter a DR interval for deletions on priority queue");
    System.out.println("2. Print priority queue");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Prints the stack from top to bottom, and subsequently pops all items into priority queue. Loops infinitely,
   * displaying menu options and taking in user input to perform various functions until the user inputs
   * the quit command (3)
   *
   * @param  stack  The array of country objects
   */
  public static void runMenu(Stack stack) {
    Scanner input = new Scanner(System.in);
    PriorityQ queue = new PriorityQ();

    System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
    System.out.println("--------------------------------------------------------------------------------------------------------");
    stack.printStack();

    while (!stack.isEmpty()) {
      queue.insert(stack.pop());
    }

    System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
    System.out.println("--------------------------------------------------------------------------------------------------------");
    queue.printPriorityQ();

    printMenu();
    int choice = 0;
    do {
      try {
        choice = input.nextInt();
        switch (choice) {
          case 1 -> {
            Scanner input2 = new Scanner(System.in);
            System.out.print("Enter DR interval like [x,y]: ");
            while (true) {
              String line = input2.nextLine();
              line = line.replace("[", "");
              line = line.replace("]", "");
              String[] interval = line.split(",");
              try {
                int x = Integer.parseInt(interval[0]);
                int y = Integer.parseInt(interval[1]);
                if (x <= y) {
                  queue.intervalDelete(x, y);
                  System.out.printf("\nCountries of priority queue with DRs in [%d,%d] are deleted\n\n", x, y);
                  break;
                }
                else {
                  System.out.print("Invalid interval, first number must be no bigger than the second: ");
                }
              }
              catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.print("Invalid interval, enter numbers: ");
              }
            }
            printMenu();
          }
          case 2 -> {
            System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            queue.printPriorityQ();
            printMenu();
          }
          case 3 -> System.out.print("\nHave a good day!\n");
          default -> System.out.print("Invalid choice, enter 1-3: ");
        }
      }
      catch (InputMismatchException e) {
        System.out.print("Invalid choice, enter 1-3: ");
        input.next(); // Flush stdin
      }
    } while (choice != 3);
    input.close();
  }
}
