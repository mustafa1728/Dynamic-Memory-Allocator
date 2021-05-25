# Binary Search Tree

Using Binary Search Trees to implement the dictionary in the allocator.


### BSTree

The following functions are implemented: 

(Note, n is the no. of elements in the BSTree, and h is its height)
1. **Insert**:
This inserts a node containing the (address, size, key) triplet right after the node it is called at. Works in O(h) time.
2. **Delete**: 
Searches for the (address, size, key) triplet of the input dictionary in the entire tree and deletes if found. return true if found and false otherwise. Note, that when a swap is needed, it restores the (address, size, key) of the original node, after swapping.
Works in O(h) time.
3. **Find**: Searches for a particular key in the entire tree. Starts from the root and goes towards the leaves. Works in O(h).
4. **getFirst**: returns the minimum element in the entire tree. Works in O(h) time.
5. **getNext**: returns the element right after the current element in the inorder traversal of the tree (i.e. its successor). returns null if this is the sentinel. Works in O(h) time.
6. **sanity**: Checks whether a BSTree is working correctly. It works in O(n) time. Checks the following invariants:
   1. **Non-Cyclic**: using the hare and tortoise method, [(wikipedia)](https://en.wikipedia.org/wiki/Cycle_detection) the BSTree is checked for the presence of a cycle that would lead to infinite loops in normal operations of the list.
   2. **Sentinel Nodes**: It checks whether the sentinel nodes are the only nodes with next or previous == null. Thus, it checks for any breaks (holes) in the list.
   3. **this.next.prev and this.prev.next**: Both of them should be equal to this for all of the nodes in the A1List. 



### A2DynamicMem

The following functions are implemented: 

(Note, n is the no. of elements in the BSTree, and h is its height)

1. **Defragment**:
This makes a new BSTree having the blocks of freeBlk, with the keys as their addresses. Performing inorder traversal in the new tree, we check if two memory blocks are contiguous, and if they are, we merge them and proceed. 
Works in O(nh) time.

