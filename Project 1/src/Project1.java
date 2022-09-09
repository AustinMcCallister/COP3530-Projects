import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 1 – Array Searches and Sorts
 * <p>
 * This program takes in a user specified csv file of country data and displays a list of options
 * which the user can use to perform various sorting and searching methods on the data held within
 * the csv file, including three types of simple sorting algorithms and two simple searching
 * algorithms, as well as calculating a Kendall's Tau matrix and printing data to the console
 *
 * @author Austin McCallister (N01487083)
 * @version 09/09/22
 */
public class Project1 {
  /**
   * Instantiates the Country object array, loads the array with Country objects, and initiates
   * the main menu loop
   *
   * @param  args  Command line arguments
   */
  public static void main(String[] args) {
    Country[] countries = new Country[145];

    System.out.print("COP3530 Project 1\n");
    System.out.print("Instructor: Xudong Liu\n\n");
    System.out.print("Array Searches and Sorts\n");

    int numElems = loadArray(countries);
    runMenu(countries, numElems);
  }

  /**
   * Takes in the user's desired csv file and instantiates a Country object for each record, inserting them
   * sequentially into the Country array
   *
   * @param  array  The array to load country objects into
   * @return The number of elements in the array
   */
  public static int loadArray(Country[] array) {
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.print("Enter the file name: ");
      String filename = input.nextLine();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
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
          array[i] = new Country(name, capitol, population, GDP, covidCases, covidDeaths, area);
          i++;
        }
        System.out.printf("\nThere were %d records read.\n\n", i);
        reader.close();
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
    System.out.println("1. Print a countries report");
    System.out.println("2. Sort by Name");
    System.out.println("3. Sort by Case Fatality Rate");
    System.out.println("4. Sort by GDP per capita");
    System.out.println("5. Find and print a given country");
    System.out.println("6. Print Kendall’s tau matrix");
    System.out.println("7. Quit");
    System.out.print("Enter your choice: ");
  }

  /**
   * Loops infinitely, displaying menu options and taking in user input to perform various functions,
   * until the user inputs the quit command (7)
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   */
  public static void runMenu(Country[] array, int numElems) {
    Scanner input = new Scanner(System.in);

    printMenu();
    int choice = 0;
    boolean sortedByName = false;
    do {
      try {
        choice = input.nextInt();
        switch (choice) {
          case 1 -> {
            printReport(array);
            printMenu();
          }
          case 2 -> {
            insertionSort(array, numElems);
            System.out.print("\nCountries sorted by Name.\n\n");
            sortedByName = true;
            printMenu();
          }
          case 3 -> {
            selectionSort(array, numElems, 3);
            System.out.print("\nCountries sorted by Case Fatality Rate.\n\n");
            sortedByName = false;
            printMenu();
          }
          case 4 -> {
            bubbleSort(array, numElems);
            System.out.print("\nCountries sorted by GDP per capita.\n\n");
            sortedByName = false;
            printMenu();
          }
          case 5 -> {
            printInfo(array, numElems, sortedByName);
            printMenu();
          }
          case 6 -> {
            kendallMatrix(array, numElems);
            printMenu();
          }
          case 7 -> System.out.print("\nHave a good day!\n");
          default -> System.out.print("Invalid choice! Enter 1-7: ");
        }
      }
      catch (InputMismatchException e) {
        System.out.print("Invalid choice! Enter 1-7: ");
        input.next(); // Flush stdin
      }
    } while (choice != 7);
  }

  /**
   * Performs an insertion sort on the array to sort it by country name
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   */
  public static void insertionSort(Country[] array , int numElems) {
    // For each item in the array, starting from the second item
    for (int i = 1; i < numElems; i++) {
      // Save the current upper bound value in a temp variable
      Country temp = array[i];
      // Set lower bound to the previous index
      int j = (i - 1);
      // While lower bound is >= 0 and the value at lower bound is > upper bound
      while (j >= 0 && array[j].getName().compareTo(temp.getName()) > 0) {
        // Move the value at the lower bound up one place in the array
        array[j + 1] = array[j];
        j--;
      }
      // Insert the temp value into its space
      array[j + 1] = temp;
    }
  }

  /**
   * Performs a selection sort on the array, to sort it by the field designated
   * via the field variable, to include GDP per capita, case fatality rate,
   * Covid case rate, Covid death rate, and population density
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   * @param  field  The type of field to sort by
   */
  public static void selectionSort(Country[] array, int numElems, int field) {
    // For each item in the array, until the second to last
    for (int i = 0; i < (numElems - 1); i++) {
      // Set the index of the lowest item to the index of the lower bound
      int lowest = i;
      // For each item in the array, starting from the second item
      for (int j = (i + 1); j < numElems; j++) {
        double a = 0;
        double b = 0;
        switch (field) {
          case 2 -> { // Sort by GDP per capita
            a = array[j].getGDPPC();
            b = array[lowest].getGDPPC();
          }
          case 3 -> { // Sort by case fatality rate
            a = array[j].getCFR();
            b = array[lowest].getCFR();
          }
          case 4 -> { // Sort by Covid case rate
            a = array[j].getCaseRate();
            b = array[lowest].getCaseRate();
          }
          case 5 -> { // Sort by Covid death rate
            a = array[j].getDeathRate();
            b = array[lowest].getDeathRate();
          }
          case 6 -> { // Sort by population density
            a = array[j].getPopDensity();
            b = array[lowest].getPopDensity();
          }
        }
        // If the value at the lower bound is less than the value of the currently recorded lowest value
        if (a < b) {
          // Set the index of the lowest item to the lower bound
          lowest = j;
        }
      }
      // If the lowest value isn't the starting value
      if (lowest != i) {
        // Save the lowest value in a temp variable
        Country temp = array[lowest];
        // Set the lowest value to the starting value
        array[lowest] = array[i];
        // Set the starting value to the lowest value, saved in the temp variable
        array[i] = temp;
      }
    }
  }

  /**
   * Performs a bubble sort on the array to sort it by GDP per capita
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   */
  public static void bubbleSort(Country[] array, int numElems) {
    // Lower bound starts at 0 and increases until the second to last item
    for (int i = 0; i < (numElems - 1); i++) {
      // Upper bound starts at the last item and decreases until it is lower than lower bound
      for (int j = (numElems - 1); j > i; j--) {
        // If the value at the upper bound is less than the value of the previous item
        if (array[j].getGDPPC() < array[j - 1].getGDPPC()) {
          // Save the current upper bound value in a temp variable
          Country temp = array[j];
          // Set the value at the lower bound to the value of the previous item
          array[j] = array[j - 1];
          // Set the previous item to the value saved in the temp variable
          array[j - 1] = temp;
        }
      }
    }
  }

  /**
   * Takes in user input to search for a specified country, and finds and prints that country's information
   * to console, using a binary search if the array is sorted by name, and using sequential search otherwise
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   * @param  sortedByName  Whether the array was sorted alphabetically by name or not
   */
  public static void printInfo(Country[] array, int numElems, boolean sortedByName) {
    Scanner input = new Scanner(System.in);

    System.out.print("\nEnter country name: ");
    String search = input.nextLine();
    if (sortedByName) {
      System.out.print("\nBinary search is used\n\n");
      // Establish upper and lower bound indexes and declare the midpoint index
      int lowerBound = 0;
      int upperBound = (numElems - 1);
      int mid;
      while (lowerBound <= upperBound) {
        // Set midpoint index to the average of the upper and lower bounds, rounding down
        mid = ((lowerBound + upperBound) / 2);
        // If the value at the midpoint is equal to the search term
        if (array[mid].getName().toLowerCase().compareTo(search.toLowerCase()) == 0) {
          array[mid].print();
          return;
        }
        // If the value at the midpoint is greater than the search term
        else if (array[mid].getName().toLowerCase().compareTo(search.toLowerCase()) > 0) {
          // Target is in the lower half, move upper bound to the index prior to the midpoint
          upperBound = (mid - 1);
        }
        // The value at the midpoint is less than the search term
        else {
          // Target is in the upper half, move lower bound to the index successive to the midpoint
          lowerBound = (mid + 1);
        }
      }
      // If the loop terminates naturally, the item was not found
      System.out.printf("Error: country %s not found\n\n", search);
    }
    else {
      System.out.print("\nSequential search is used\n\n");
      // For each item in the array
      for (Country country : array) {
        if (country != null) { // Do not attempt to work on null pointers
          // Compare the name with the search term
          if (country.getName().toLowerCase().compareTo(search.toLowerCase()) == 0) {
            country.print();
            return;
          }
        }
      }
      // If the loop terminates naturally, the item was not found
      System.out.printf("Error: country %s not found\n\n", search);
    }
  }

  /**
   * Instantiates the four arrays which will be used to calculate the Kendall's tau matrix,
   * sorts each array by its respective field, and outputs the resulting matrix to console
   *
   * @param  array  The array of country objects
   * @param  numElems  The number of elements in the array
   */
  public static void kendallMatrix(Country[] array, int numElems) {
    Country[] GDPPCArray = array.clone();
    Country[] CaseRateArray = array.clone();
    Country[] DeathRateArray = array.clone();
    Country[] PopDensityArray = array.clone();

    selectionSort(GDPPCArray, numElems, 2);
    selectionSort(CaseRateArray, numElems, 4);
    selectionSort(DeathRateArray, numElems, 5);
    selectionSort(PopDensityArray, numElems, 6);

    double x1 = kendallCompare(CaseRateArray, GDPPCArray, numElems);
    double x2 = kendallCompare(CaseRateArray, PopDensityArray, numElems);
    double x3 = kendallCompare(DeathRateArray, GDPPCArray, numElems);
    double x4 = kendallCompare(DeathRateArray, PopDensityArray, numElems);

    System.out.print("\n---------------------------------------------\n");
    System.out.print("|                  |   GDPPC   | PopDensity |\n");
    System.out.print("---------------------------------------------\n");
    System.out.printf("| COVID Case Rate  |%8.4f   |%9.4f   |\n", x1, x2);
    System.out.print("---------------------------------------------\n");
    System.out.printf("| COVID Death Rate |%8.4f   |%9.4f   |\n", x3, x4);
    System.out.print("---------------------------------------------\n\n");
  }

  /**
   * Takes in two sorted arrays as input and calculates the Kendall's tau correlation coefficient between them
   *
   * @param  a  The first array to compare
   * @param  b  The second array to compare
   * @param  numElems  The number of elements in the array
   */
  public static double kendallCompare(Country[] a, Country[] b, int numElems) {
    int agree = 0;
    int total = ((numElems * (numElems - 1)) / 2);
    // For each country in the array up to the second to last element
    for (int i = 0; i < (numElems - 1); i++) {
      String first = a[i].getName();
      // Compare with each successive element up to the last element
      for (int j = (i + 1); j < numElems; j++) {
        String second = a[j].getName();
        // Iterate through the second array until the first string is located
        for (int k = 0; k < numElems; k++) {
          // Once found, check remaining elements to see if the second string is "greater" than the first
          if (b[k].getName().compareTo(first) == 0) {
            for (int l = (k + 1); l < numElems; l++) {
              if (b[l].getName().compareTo(second) == 0) {
                // The two pairs agree in the ranking
                agree++;
                break;
              }
            }
            // The first pair was found, so terminate loop
            break;
          }
        }
      }
    }
    int disagree = (total - agree);
    return ((agree - disagree) / ((numElems * (numElems - 1.0)) / 2.0));
  }

  /**
   * Prints a report of all countries in the array by the currently sorted order
   *
   * @param  array  The array of country objects
   */
  public static void printReport(Country[] array) {
    System.out.print("\nName                              Capitol         GDPPC       CFR       CaseRate   DeathRate  PopDensity\n");
    System.out.println("--------------------------------------------------------------------------------------------------------");
    for (Country country : array) {
      if (country != null) { // Do not attempt to print from null pointers
        System.out.printf("%-33s %-15s %-11.3f %-9.6f %-10.3f %-10.3f %-10.3f\n",
            country.getName(),
            country.getCapitol(),
            country.getGDPPC(),
            country.getCFR(),
            country.getCaseRate(),
            country.getDeathRate(),
            country.getPopDensity());
      }
    }
    System.out.println();
  }
}
