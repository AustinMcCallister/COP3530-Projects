import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 – Hash Tables
 * <p>
 * This program takes in a user specified csv file of country data and displays a list of options
 * which the user can use to perform various functions, to include printing the hash table, deleting
 * a country from the table, inserting a new country to the table, searching for a given country and
 * printing its case rate and index, and printing the total number of empty cells and collisions in
 * the hash table
 *
 * @author Austin McCallister (N01487083)
 * @version 11/30/22
 */
public class Project5 {
  /**
   * Instantiates the hash table, loads it with data nodes
   * from a user-defined file, and initiates the main menu loop
   *
   * @param  args  Command line arguments
   */
  public static void main(String[] args) {
    HashTable table = new HashTable(293);

    System.out.print("COP3530 Project 5\n");
    System.out.print("Instructor: Xudong Liu\n\n");
    System.out.print("Hash Tables\n");

    loadData(table);
    runMenu(table);
  }

  /**
   * Takes in the user's desired csv file and instantiates a Node object for each record, inserting them
   * into the hash table
   *
   * @param  table  The hash table
   */
  public static void loadData(HashTable table) {
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
          long population = Long.parseLong(inputString[2]);
          long cases = Long.parseLong(inputString[4]);
          table.insert(name, population, cases);
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
    System.out.println("\n1) Print hash table");
    System.out.println("2) Delete a country of a given name");
    System.out.println("3) Insert a country of a given name");
    System.out.println("4) Search and print a country and its case rate for a given name");
    System.out.println("5) Print numbers of empty cells and collided cells");
    System.out.println("6) Exit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Loops infinitely, displaying menu options and taking in user input to perform various functions,
   * until the user inputs the quit command (6)
   *
   * @param  table  The hash table
   */
  public static void runMenu(HashTable table) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner input = new Scanner(System.in);

    printMenu();
    int choice = 0;
    do {
      try {
        choice = input.nextInt();
        switch (choice) {
          case 1 -> {
            System.out.println();
            table.display();
            printMenu();
          }
          case 2 -> {
            System.out.print("Enter country name: ");
            table.delete(reader.readLine());
            printMenu();
          }
          case 3 -> {
            System.out.print("Enter country name: ");
            String name = reader.readLine();
            System.out.print("Enter country population: ");
            long population = Long.parseLong(reader.readLine());
            System.out.print("Enter country COVID cases: ");
            long cases = Long.parseLong(reader.readLine());
            table.insert(name, population, cases);
            System.out.printf("\n%s is inserted to hash table\n", name);
            printMenu();
          }
          case 4 -> {
            System.out.print("Enter country name: ");
            table.find(reader.readLine());
            printMenu();
          }
          case 5 -> {
            table.printEmptyAndCollidedCells();
            printMenu();
          }
          case 6 -> System.out.print("\nHave a good day!\n");
          default -> System.out.print("Invalid choice, enter 1-6: ");
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      catch (InputMismatchException e) {
        System.out.print("Invalid choice, enter 1-6: ");
        input.next(); // Flush stdin
      }
    } while (choice != 6);
    input.close();
  }
}
