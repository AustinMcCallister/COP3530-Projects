import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 4 – Binary Search Trees
 * <p>
 * This program takes in a user specified csv file of country data and displays a list of options
 * which the user can use to perform various functions, to include printing the binary search tree
 * in several orders, inserting a new country to the tree, delete a country, search for a country,
 * and print the top and bottom x countries by GDPPC
 *
 * @author Austin McCallister (N01487083)
 * @version 11/16/22
 */
public class Project4 {
  /**
   * Instantiates the binary search tree, loads it with data nodes
   * from a user-defined file, and initiates the main menu loop
   *
   * @param  args  Command line arguments
   */
  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();

    System.out.print("COP3530 Project 4\n");
    System.out.print("Instructor: Xudong Liu\n\n");
    System.out.print("Binary Search Trees\n");

    loadData(tree);
    runMenu(tree);
  }

  /**
   * Takes in the user's desired csv file and instantiates a Node object for each record, inserting them
   * sequentially into the binary search tree
   *
   * @param  tree  The binary search tree to load nodes into
   */
  public static void loadData(BinarySearchTree tree) {
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
          double population = Double.parseDouble(inputString[2]);
          double GDP = Double.parseDouble(inputString[3]);
          tree.insert(name, (GDP / population));
          i++;
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
    System.out.println("\n1) Print tree inorder");
    System.out.println("2) Print tree preorder");
    System.out.println("3) Print tree postorder");
    System.out.println("4) Insert a country with name and GDP per capita");
    System.out.println("5) Delete a country for a given name");
    System.out.println("6) Search and print a country and its path for a given name");
    System.out.println("7) Print bottom countries regarding GDPPC");
    System.out.println("8) Print top countries regarding GDPPC");
    System.out.println("9) Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Loops infinitely, displaying menu options and taking in user input to perform various functions,
   * until the user inputs the quit command (9)
   *
   * @param  tree  The binary search tree
   */
  public static void runMenu(BinarySearchTree tree) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner input = new Scanner(System.in);

    printMenu();
    int choice = 0;
    do {
      try {
        choice = input.nextInt();
        switch (choice) {
          case 1 -> {
            tree.printInorder();
            printMenu();
          }
          case 2 -> {
            tree.printPreorder();
            printMenu();
          }
          case 3 -> {
            tree.printPostorder();
            printMenu();
          }
          case 4 -> {
            System.out.print("Enter country name: ");
            String name = reader.readLine();
            System.out.print("Enter country GDP per capita: ");
            double GDPPC = input.nextDouble();
            tree.insert(name, GDPPC);
            System.out.printf("\n%s with GDP per capita of %.3f is inserted.\n", name, GDPPC);
            printMenu();
          }
          case 5 -> {
            System.out.print("Enter country name: ");
            tree.delete(reader.readLine());
            printMenu();
          }
          case 6 -> {
            System.out.print("Enter country name: ");
            tree.find(reader.readLine());
            printMenu();
          }
          case 7 -> {
            System.out.print("Enter the number of countries: ");
            int numCountries = input.nextInt();
            System.out.printf("\nBottom %d countries regarding GDPPC:\n", numCountries);
            System.out.println("\nName                              GDP Per Capita");
            System.out.println("------------------------------------------------");
            tree.printBottomCountries(numCountries);
            printMenu();
          }
          case 8 -> {
            System.out.print("Enter the number of countries: ");
            int numCountries = input.nextInt();
            System.out.printf("\nTop %d countries regarding GDPPC:\n", numCountries);
            System.out.println("\nName                              GDP Per Capita");
            System.out.println("------------------------------------------------");
            tree.printTopCountries(numCountries);
            printMenu();
          }
          case 9 -> System.out.print("\nHave a good day!\n");
          default -> System.out.print("Invalid choice, enter 1-9: ");
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      catch (InputMismatchException e) {
        System.out.print("Invalid choice, enter 1-9: ");
        input.next(); // Flush stdin
      }
    } while (choice != 9);
    input.close();
  }
}