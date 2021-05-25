# Linked Lists

Using linked lists to implement the dictionary in the allocator.

### A1List

The following functions are implemented: 

(Note, n is the no. of operations of A1List that already have been performed on it)
1. **Insert**:
This inserts a node containing the (address, size, key) triplet right after the node it is called at. Works in O(1) time.
2. **Delete**: 
Searches for the (address, size, key) triplet of the input dictionary in the list (starting from the head and going towards the tail) and deletes if found. return true if found and false otherwise.
Works in O(n) time.
3. **Find**: Searches for a particular key in the A1List. Starts from the head and goes towards the tail. Works in O(n).
4. **getFirst**: returns the first element right after the head sentinel. Works in O(n) time.
5. **getNext**: returns the element right after the current element in the A1List. returns null if the next is the tail sentinel. Works in O(1) time.
6. **sanity**: Checks whether an A1List is working correctly. It works in O(n) time. Checks the following invariants:
   1. **Non-Cyclic**: using the hare and tortoise method, [(wikipedia)](https://en.wikipedia.org/wiki/Cycle_detection) the A1List is checked for the presence of a cycle that would lead to infinite loops in normal operations of the list.
   2. **Sentinel Nodes**: It checks whether the sentinel nodes are the only nodes with next or previous == null. Thus, it checks for any breaks (holes) in the list.
   3. **this.next.prev and this.prev.next**: Both of them should be equal to this for all of the nodes in the A1List. 



### A1DynamicMem

The following functions are implemented: 

(Note, n is the no. of operations of A1DynamicMem that already have been performed on it)

1. **Allocate**:
This finds the first free block of size >= a given block size and returns it, after updating the freeBlk and allocBlk. Works in O(1) time.
2. **Free**: 
Searches for the address and then removes it from the allocBlk, adding it to the freeBlk. Works in O(n) time.

