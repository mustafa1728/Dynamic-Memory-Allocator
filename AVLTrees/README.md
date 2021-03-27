# AVL Tree

Using AVL Trees to implement the dictionary in the allocator.

### AVLTree

The following functions are implemented: 

(Note, n is the no. of elements in the BSTree, and h is its height, with h = O(log n))
1. **Insert**:
This inserts a node containing the (address, size, key) triplet right after the node it is called at. If insert causes a height imbalance, it rebalances the tree by performing rotations.
Works in O(h) = O(log n) time.
2. **Delete**: 
Searches for the (address, size, key) triplet of the input dictionary in the entire tree and deletes if found. return true if found and false otherwise. Note, that when a swap is needed, it restores the (address, size, key) of the original node, after swapping. If delete causes a height imbalance, it rebalances the tree by performing rotations.
Works in O(h) = O(log n) time.
3. **Find**: Searches for a particular key in the entire tree. Starts from the root and goes towards the leaves. Works in O(h) = O(log n).
4. **getFirst**: returns the minimum element in the entire tree. Works in O(h) = O(log n) time.
5. **getNext**: returns the element right after the current element in the inorder traversal of the tree (i.e. its successor). returns null if this is the sentinel. Works in O(h) = O(log n) time.
6. **sanity**: Checks whether an AVLTree is working correctly. It works in O(n) time. Checks the following invariants:
   1. **Non-Cyclic**: using the hare and tortoise method, [(wikipedia)](https://en.wikipedia.org/wiki/Cycle_detection) it checks for a cycle in the path from the current node to the root. If there is no such cycle, then it checks for a cycle in the remaining nodes starting from the root. 
   2. **Sentinel root**: It checks whether the sentinel root has left == null and has the triplet (-1, -1, -1).
   3. **BST property**: It should follow the property that left <= this < right for any node in the tree.
   4. **this.child.parent**: it should be equal to this for all of the nodes in the AVLtree. 
   5. **Height balance**: The height of any two subtrees of any node should not differ by more than 1. This is to ensure that the height of the tree is bounded by O(log n). Other than this, the other checks were the same for BSTree and are simply inherited here.


### A3.2

As discussed in linked lists, the worst case running time complexities are:
(Note: in BST, h = O(n))
1. Insert: O(h) = O(n)
2. Delete: O(h) = O(n)
3. Find: O(h) = O(n)
4. getFirst: O(h) = O(n)
5. getNext: O(h) = O(n) 
6. Sanity: O(n)


### A3.3

As discussed in linked lists, the worst case running time complexities are:
(Note: in AVL trees, because of the height balancing, h = O(log n))
1. Insert: O(h) = O(log n)
2. Delete: O(h) = O(log n)
3. Find: O(h) = O(log n)
4. getFirst: O(h) = O(log n)
5. getNext: O(h) = O(log n) 
6. Sanity: O(n)

