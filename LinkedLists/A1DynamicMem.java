// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        
        Dictionary n = freeBlk.Find(blockSize, false);
        if (n == null || blockSize <= 0){
            return -1;
        }
        freeBlk.Delete(n);
        if (n.size>blockSize){
            freeBlk.Insert(n.address + blockSize, n.size - blockSize, n.size - blockSize);
        }
        allocBlk.Insert(n.address, blockSize, n.address);
        return (n.address);
    } 
    
    public int Free(int startAddr) {
        Dictionary n = allocBlk.Find(startAddr, true);
        if (n == null){
            return -1;
        }
        allocBlk.Delete(n);
        freeBlk.Insert(n.address, n.size, n.size);
        return 0;
    }
}