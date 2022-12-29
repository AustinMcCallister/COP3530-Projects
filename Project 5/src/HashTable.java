/**
 * Describes a hash table implemented with an array of linked lists, which can insert and delete nodes,
 * find specific nodes, print the linked list to the console, and print the number of empty cells and
 * collisions
 *
 * @author Austin McCallister (N01487083)
 * @version 11/28/22
 */
public class HashTable {
  private final LinkedList[] hashArray;

  /**
   * Constructor for the linked list
   *
   * @param  size  The size of the hash table
   */
  public HashTable(int size) {
    hashArray = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      hashArray[i] = new LinkedList();
    }
  }

  /**
   * Inserts a data node into the linked list at the hashed index
   *
   * @param  country  The name of the country
   * @param  population  The population of the country
   * @param  cases  The number of COVID cases in the country
   */
  public void insert(String country, long population, long cases) {
    int hash = hash(country);
    hashArray[hash].insert(country, population, cases);
  }

  /**
   * Sums the unicode values of the characters in the name of the given country and calculates
   * a hash index value
   *
   * @param  country  The name of the country
   * @return The hashed index for the country name
   */
  private int hash(String country) {
    char[] array = country.toCharArray();
    int total = 0;
    for (char c : array) {
      total += c;
    }
    return (total % hashArray.length);
  }

  /**
   * Searches for the given country in the hash table, and returns the index if found, or -1 if not
   *
   * @param  country  The name of the country
   * @return The index of the country if found, -1 otherwise
   */
  public int find(String country) {
    int hash = hash(country);
    double caseRate = hashArray[hash].find(country);
    if (caseRate != -1) {
      System.out.printf("\n%s is found at index %d with case rate of %.3f\n", country, hash, caseRate);
      return hash;
    }
    else {
      System.out.printf("\n%s is not a country\n", country);
      return -1;
    }
  }

  /**
   * Deletes a node from the hash table with a given country name
   *
   * @param  country  The name of the country
   */
  public void delete(String country) {
    int hash = hash(country);
    hashArray[hash].delete(country);
  }

  /**
   * Prints the contents of the hash table to console
   */
  public void display() {
    for (int i = 0; i < hashArray.length; i++) {
      System.out.printf("%-5s", i + ".");
      hashArray[i].print();
    }
  }

  /**
   * Prints the total number of empty cells and collisions in the hash table
   */
  public void printEmptyAndCollidedCells() {
    int empty = 0;
    int collisions = 0;
    for (LinkedList linkedList : hashArray) {
      if (linkedList.getHead() == null) {
        empty++;
      }
      else {
        if (linkedList.getHead() != linkedList.getTail()) {
          collisions++;
        }
      }
    }
    System.out.printf("\nThere are %d empty cells and %d collisions in the hash table\n", empty, collisions);
  }
}
