// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 



    public void Defragment() {
        if(this.type==2){
            BSTree addressList = new BSTree();
            if(this.freeBlk.getFirst() == null){
                return;
            }

            for(Dictionary n = this.freeBlk.getFirst(); n!=null; n = n.getNext()){
                addressList.Insert(n.address, n.size, n.address);
            }
            Dictionary prev = addressList.getFirst();
            
            for(Dictionary n = addressList.getFirst().getNext(); n!=null; n = n.getNext()){
                if(prev.address + prev.size == n.address){

                    freeBlk.Insert(prev.address, prev.size + n.size, prev.size + n.size);
                    BSTree d1 = new BSTree(prev.address, prev.size, prev.size);
                    freeBlk.Delete(d1);
                    BSTree d2 = new BSTree(n.address, n.size, n.size);
                    freeBlk.Delete(d2);

                    n.address = prev.address;
                    n.key = prev.address;
                    n.size = prev.size + n.size;
                    prev = n;

                }
                else{
                    prev = n;
                }   
            }

            addressList = null;
            return ;

        }
        else if(this.type==3){
            AVLTree addressList = new AVLTree();
            if(this.freeBlk.getFirst() == null){
                return;
            }

            for(Dictionary n = this.freeBlk.getFirst(); n!=null; n = n.getNext()){
                addressList.Insert(n.address, n.size, n.address);
            }
            Dictionary prev = addressList.getFirst();
            
            for(Dictionary n = addressList.getFirst().getNext(); n!=null; n = n.getNext()){
                if(prev.address + prev.size == n.address){

                    freeBlk.Insert(prev.address, prev.size + n.size, prev.size + n.size);
                    AVLTree d1 = new AVLTree(prev.address, prev.size, prev.size);
                    freeBlk.Delete(d1);
                    AVLTree d2 = new AVLTree(n.address, n.size, n.size);
                    freeBlk.Delete(d2);

                    n.address = prev.address;
                    n.key = prev.address;
                    n.size = prev.size + n.size;
                    prev = n;

                }
                else{
                    prev = n;
                }   
            }

            addressList = null;
            return ;

        }
        
    }
}