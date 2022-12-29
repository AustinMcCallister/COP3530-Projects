/**
 * Describes a max heap implemented with an array which can insert and remove country nodes,
 * and check whether it is empty
 *
 * @author Austin McCallister (N01487083)
 * @version 11/17/22
 */
public class MaxHeap implements Heap {
  private final Node[] heapArray;
  private int currentSize = 0;

  /**
   * Constructor for the max heap
   *
   * @param  size  The size of the heap array
   */
  public MaxHeap(int size) {
    heapArray = new Node[size];
  }

  /**
   * Inserts a node into the max heap
   *
   * @param  node  The node to be inserted
   */
  @Override
  public void insert(Node node) {
    if (currentSize == heapArray.length) {
      return;
    }
    heapArray[currentSize] = node;
    trickleUp(currentSize++);
  }

  /**
   * Trickles up the node at the given index
   *
   * @param  index  The index of the node to be trickled up
   */
  private void trickleUp(int index) {
    int parent = ((index - 1) / 2);
    Node bottom = heapArray[index];

    while (index > 0 && heapArray[parent].getGDPPC() <= bottom.getGDPPC()) {
      heapArray[index] = heapArray[parent];
      index = parent;
      parent = ((parent - 1) / 2);
    }
    heapArray[index] = bottom;
  }

  /**
   * Removes the first node from the max heap
   *
   * @return The removed node
   */
  @Override
  public Node remove() {
    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];
    trickleDown();
    return root;
  }

  /**
   * Trickles down the top of the heap after executing a removal
   */
  private void trickleDown() {
    int index = 0;
    int largerChild;
    Node top = heapArray[index];

    while (index < (currentSize / 2)) {
      int leftChild = ((2 * index) + 1);
      int rightChild = (leftChild + 1);
      if (rightChild < currentSize && heapArray[leftChild].getGDPPC() <= heapArray[rightChild].getGDPPC()) {
        largerChild = rightChild;
      }
      else {
        largerChild = leftChild;
      }
      if (top.getGDPPC() >= heapArray[largerChild].getGDPPC()) {
        break;
      }
      heapArray[index] = heapArray[largerChild];
      index = largerChild;
    }
    heapArray[index] = top;
  }

  /**
   * Checks whether the heap is empty
   *
   * @return True if the heap is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return (currentSize == 0);
  }
}
