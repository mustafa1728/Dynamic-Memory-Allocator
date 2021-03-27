// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

/*public class A2DynamicMem extends A1DynamicMem {

    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees.
    // No changes should be required in the A1DynamicMem functions.
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees.

    public void Defragment() {
        if(type == 2){

            Dictionary build_tree = new BSTree();

            Dictionary temp1,temp2;

            Dictionary cur = freeBlk.getFirst();

            if(cur == null){
                return ;
            }

            while(cur!=null){
                build_tree.Insert(cur.address,cur.size,cur.address);
                cur = cur.getNext();
            }


            temp1 = build_tree.getFirst();

            temp2 = temp1.getNext();

            while(temp2!=null){

                if(temp1.address + temp1.size != temp2.address){
                    temp1 = temp2;
                    temp2 = temp1.getNext();
                    continue;
                }

                Dictionary cur1 = new BSTree(temp1.address,temp1.size,temp1.size);
                Dictionary cur2 = new BSTree(temp2.address,temp2.size,temp2.size);

                Dictionary cur3 = new BSTree(temp1.address,temp1.size,temp1.address);
                Dictionary cur4 = new BSTree(temp2.address,temp2.size,temp2.address);

                freeBlk.Delete(cur1);
                freeBlk.Delete(cur2);
                build_tree.Delete(cur3);
                build_tree.Delete(cur4);

                freeBlk.Insert(cur1.address, cur1.size+cur2.size, cur1.size+ cur2.size );

                temp1 = build_tree.Insert(cur1.address, cur1.size+cur2.size , cur1.address);
                temp2 = temp1.getNext();

            }

        }else if(type == 3){

            Dictionary build_tree = new AVLTree();

            Dictionary temp1,temp2;

            Dictionary cur = freeBlk.getFirst();

            if(cur == null){
                return ;
            }

            while(cur!=null){
                build_tree.Insert(cur.address,cur.size,cur.address);
                cur = cur.getNext();
            }

            temp1 = build_tree.getFirst();

            temp2 = temp1.getNext();

            while(temp2!=null){

                if(temp1.address + temp1.size != temp2.address){
                    temp1 = temp2;
                    temp2 = temp1.getNext();
                    continue;
                }

                Dictionary cur1 = new AVLTree(temp1.address,temp1.size,temp1.size);
                Dictionary cur2 = new AVLTree(temp2.address,temp2.size,temp2.size);

                Dictionary cur3 = new AVLTree(temp1.address,temp1.size,temp1.address);
                Dictionary cur4 = new AVLTree(temp2.address,temp2.size,temp2.address);

                freeBlk.Delete(cur1);
                freeBlk.Delete(cur2);

                freeBlk.Insert(cur1.address, cur1.size+cur2.size, cur1.size+ cur2.size );

                temp1 = build_tree.Insert(cur1.address, cur1.size+cur2.size , cur1.address);
                build_tree.Delete(cur3);
                build_tree.Delete(cur4);
                temp2 = temp1.getNext();

            }

        }

    }

}*/

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

    public void checkSanity(){
        if(!this.freeBlk.sanity() || !this.allocBlk.sanity()){
            System.out.println("This is just insane!");
        }

    }

    public void Defragment() {
        if(type == 2){

            Dictionary build_tree = new BSTree();

            Dictionary temp1,temp2;

            Dictionary cur = freeBlk.getFirst();

            if(cur == null){
                return ;
            }

            while(cur!=null){
                build_tree.Insert(cur.address,cur.size,cur.address);
                cur = cur.getNext();
            }

            temp1 = build_tree.getFirst();

            temp2 = temp1.getNext();

            while(temp2!=null){

                if(temp1.address + temp1.size != temp2.address){
                    temp1 = temp2;
                    temp2 = temp1.getNext();
                    continue;
                }

                Dictionary cur1 = new BSTree(temp1.address,temp1.size,temp1.size);
                Dictionary cur2 = new BSTree(temp2.address,temp2.size,temp2.size);

                Dictionary cur3 = new BSTree(temp1.address,temp1.size,temp1.address);
                Dictionary cur4 = new BSTree(temp2.address,temp2.size,temp2.address);

                freeBlk.Delete(cur1);
                freeBlk.Delete(cur2);
                build_tree.Delete(cur3);
                build_tree.Delete(cur4);

                freeBlk.Insert(cur1.address, cur1.size+cur2.size, cur1.size+ cur2.size );

                temp1 = build_tree.Insert(cur1.address, cur1.size+cur2.size , cur1.address);
                temp2 = temp1.getNext();

            }

        }else if(type == 3){

            Dictionary build_tree = new AVLTree();

            Dictionary temp1,temp2;

            Dictionary cur = freeBlk.getFirst();

            if(cur == null){
                return ;
            }

            while(cur!=null){
                build_tree.Insert(cur.address,cur.size,cur.address);
                cur = cur.getNext();
            }

            temp1 = build_tree.getFirst();

            temp2 = temp1.getNext();

            while(temp2!=null){

                if(temp1.address + temp1.size != temp2.address){
                    temp1 = temp2;
                    temp2 = temp1.getNext();
                    continue;
                }

                Dictionary cur1 = new AVLTree(temp1.address,temp1.size,temp1.size);
                Dictionary cur2 = new AVLTree(temp2.address,temp2.size,temp2.size);

                Dictionary cur3 = new AVLTree(temp1.address,temp1.size,temp1.address);
                Dictionary cur4 = new AVLTree(temp2.address,temp2.size,temp2.address);

                freeBlk.Delete(cur1);
                freeBlk.Delete(cur2);

                freeBlk.Insert(cur1.address, cur1.size+cur2.size, cur1.size+ cur2.size );

                temp1 = build_tree.Insert(cur1.address, cur1.size+cur2.size , cur1.address);
                build_tree.Delete(cur3);
                build_tree.Delete(cur4);
                temp2 = temp1.getNext();

            }

        }

    }

}