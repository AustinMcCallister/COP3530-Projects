import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 2 – Stacks and Priority Queues
 * <p>
 * This program takes in a user specified csv file of country data and displays a list of options
 * which the user can use to perform various functions, to include adding and removing countries
 * to a stack or priority queue, and printing the data from the stack or priority queue to console
 *
 * @author Austin McCallister (N01487083)
 * @version 09/28/22
 */
public class Project2 {
  /**
   * Instantiates the stack and priority queue, loads them with Country objects
   * from a user-defined file, and initiates the main menu loop
   *
   * @param  args  Command line arguments
   */
  public static void main(String[] args) {
    Stack stack = new Stack(145);
    PriorityQ queue = new PriorityQ(145);

    System.out.print("COP3530 Project 2\n");
    System.out.print("Instructor: Xudong Liu\n\n");
    System.out.print("Stacks and Priority Queues\n");

    loadData(stack, queue);
    runMenu(stack, queue);
  }

  /**
   * Takes in the user's desired csv file and instantiates a Country object for each record, inserting them
   * sequentially into the Country stack
   *
   * @param  stack  The stack to load country objects into
   * @param  queue  The priority queue to load country objects into
   */
  public static int loadData(Stack stack, PriorityQ queue) {
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
          stack.push(new Country(name, capitol, population, GDP, covidCases, covidDeaths, area));
          queue.insert(new Country(name, capitol, population, GDP, covidCases, covidDeaths, area));
          i++;
        }
        System.out.printf("\nThere were %d records read.\n", i);
        return i;
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
    System.out.println("\n1) Print stack");
    System.out.println("2) Pop a country object from stack");
    System.out.println("3) Push a country object onto stack");
    System.out.println("4) Print priority queue");
    System.out.println("5) Remove a country object from priority queue");
    System.out.println("6) Insert a country object into priority queue");
    System.out.println("7) Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Loops infinitely, displaying menu options and taking in user input to perform various functions,
   * until the user inputs the quit command (7)
   *
   * @param  stack  The array of country objects
   * @param  queue  The number of elements in the array
   */
  public static void runMenu(Stack stack, PriorityQ queue) {
    Scanner input = new Scanner(System.in);

    printMenu();
    int choice = 0;
    do {
      try {
        choice = input.nextInt();
        switch (choice) {
          case 1 -> {
            stack.printStack();
            printMenu();
          }
          case 2 -> {
            System.out.println("\nOne country is popped from stack.");
            stack.pop();
            printMenu();
          }
          case 3 -> {
            if (!stack.isFull()) {
              Country country = generateCountry();
              if (country != null) {
                System.out.println("\nOne country is pushed onto stack.");
                stack.push(country);
              }
            }
            else {
              System.out.println("Error: Stack is full");
            }
            printMenu();
          }
          case 4 -> {
            queue.printQueue();
            printMenu();
          }
          case 5 -> {
            System.out.println("\nOne country is removed from priority queue.");
            queue.remove();
            printMenu();
          }
          case 6 -> {
            if (!queue.isFull()) {
              Country country = generateCountry();
              if (country != null) {
                System.out.println("\nOne country is inserted onto priority queue.");
                queue.insert(country);
              }
            }
            else {
              System.out.println("Error: Stack is full");
            }
            printMenu();
          }
          case 7 -> System.out.print("\nHave a good day!\n");
          default -> System.out.print("Invalid choice. Enter your choice: ");
        }
      }
      catch (InputMismatchException e) {
        System.out.print("Invalid choice. Enter your choice: ");
        input.next(); // Flush stdin
      }
    } while (choice != 7);
    input.close();
  }

  /**
   * Prompts the user for specified information and instantiates and returns a Country object
   *
   * @return The created Country object
   */
  public static Country generateCountry() {
    Scanner input = new Scanner(System.in);

    try {
      System.out.print("\nEnter name: ");
      String name = input.nextLine();
      System.out.print("Enter capitol: ");
      String capitol = input.nextLine();
      System.out.print("Enter population: ");
      double population = input.nextDouble();
      System.out.print("Enter GDP (USD): ");
      double GDP = input.nextDouble();
      System.out.print("Enter COVID cases: ");
      double covidCases = input.nextDouble();
      System.out.print("Enter COVID deaths: ");
      double covidDeaths = input.nextDouble();
      System.out.print("Enter Area (km²): ");
      double area = input.nextDouble();
      return new Country(name, capitol, population, GDP, covidCases, covidDeaths, area);
    }
    catch (InputMismatchException e) {
      System.out.println("Input Mismatch Error: " + e.getMessage());
      return null;
    }
  }
}
