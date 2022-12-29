/**
 * Describes a heap to be implemented into a min and max heap
 *
 * @author Austin McCallister (N01487083)
 * @version 11/17/22
 */
public interface Heap {
  /**
   * Inserts a node into the heap
   *
   * @param  node  The node to be inserted
   */
  void insert(Node node);

  /**
   * Removes the first node from the heap
   *
   * @return The removed node
   */
  Node remove();

  /**
   * Checks whether the heap is empty
   *
   * @return True if the heap is empty, false otherwise
   */
  boolean isEmpty();
}
