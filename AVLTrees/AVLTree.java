// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    // here height 0 corresponds to a leaf node and a null node has height of -1

    private AVLTree getRoot(){
        if(this.parent == null){
            return this.right;
        }
        else{
            return this.parent.getRoot();
        }
    }

    // updates the height and also checks for height imbalance
    private boolean checkUpdateCurrHeight(){
        int leftHeight = -1;
        int rightHeight = -1;
        if (this.left!=null){
            leftHeight = this.left.height;
        }
        if(this.right!=null){
            rightHeight = this.right.height;
        }

        if(rightHeight > leftHeight){
            if(rightHeight > leftHeight + 1){
                return false;
            }
            this.height = rightHeight + 1;
            return true;
        }
        else{
            if(leftHeight > rightHeight + 1){
                return false;
            }
            this.height = leftHeight + 1;
            return true;
        }
    }

    private AVLTree rebalance(AVLTree firstMismatch, AVLTree child, AVLTree grandChild){
        AVLTree newroot;
        if(child == firstMismatch.left){
            if(grandChild == child.left){
                //              f                                 c
                //          c               =>                 g    f
                //      g
                child.parent = firstMismatch.parent;
                if(firstMismatch.parent.left == firstMismatch){
                    firstMismatch.parent.left = child;
                }
                else{
                    firstMismatch.parent.right = child;
                }

                firstMismatch.left = child.right;
                if(firstMismatch.left!=null){
                    firstMismatch.left.parent = firstMismatch;
                }

                child.right = firstMismatch;
                firstMismatch.parent = child;

                newroot = child;

            }
            else{
                //              f                                 g
                //          c               =>                 c    f
                //             g

                grandChild.parent = firstMismatch.parent;
                if(firstMismatch.parent.left == firstMismatch){
                    firstMismatch.parent.left = grandChild;
                }
                else{
                    firstMismatch.parent.right = grandChild;
                }

                firstMismatch.left = grandChild.right;
                if(firstMismatch.left!=null){
                    firstMismatch.left.parent = firstMismatch;
                }

                child.right = grandChild.left;
                if(grandChild.left!=null){
                    grandChild.left.parent = child;
                }

                grandChild.left = child;
                child.parent = grandChild;

                grandChild.right = firstMismatch;
                firstMismatch.parent = grandChild;

                newroot = grandChild;
            }
        }
        else{

            if(grandChild == child.left){
                
                //              f                                 g
                //                  c              =>          f     c
                //               g

                grandChild.parent = firstMismatch.parent;
                if(firstMismatch.parent.left == firstMismatch){
                    firstMismatch.parent.left = grandChild;
                }
                else{
                    firstMismatch.parent.right = grandChild;
                }

                firstMismatch.right = grandChild.left;
                if(firstMismatch.right!=null){
                    firstMismatch.right.parent = firstMismatch;
                }

                child.left = grandChild.right;
                if(grandChild.right!=null){
                    grandChild.right.parent = child;
                }

                grandChild.right = child;
                child.parent = grandChild;

                grandChild.left = firstMismatch;
                firstMismatch.parent = grandChild;

                newroot = grandChild;
            }
            else{
                //      f                                         c
                //          c               =>                 f    g
                //              g
                child.parent = firstMismatch.parent;
                if(firstMismatch.parent.left == firstMismatch){
                    firstMismatch.parent.left = child;
                }
                else{
                    firstMismatch.parent.right = child;
                }

                firstMismatch.right = child.left;
                if(firstMismatch.right!=null){
                    firstMismatch.right.parent = firstMismatch;
                }

                child.left = firstMismatch;
                firstMismatch.parent = child;

                newroot = child;
            }
        }
        firstMismatch.checkUpdateCurrHeight();
        child.checkUpdateCurrHeight();
        grandChild.checkUpdateCurrHeight();
        return newroot;

    }

    public AVLTree Insert(int address, int size, int key) 
    { 
        
        AVLTree n = this.getRoot();
        // case when tree is empty
        if(n==null){
            AVLTree newNode = new AVLTree(address, size, key); 
            newNode.parent = this;
            this.right = newNode;
            return newNode;
        }
        int go; // 0 means go left, 1 means go right
        while(n!=null && (n.left!=null || n.right!=null)){
            if(n.key<key){
                go = 1;
            }
            else if(n.key>key){
                go = 0;
            }
            else{
                if(n.address<address){
                    go = 1;
                }
                else{
                    go = 0;
                }
            }
            // change n depending on the value of go
            if(go == 0){
                if(n.left!=null){
                    n = n.left;
                }
                else{
                    break;
                }
            }
            else{
                if(n.right!=null){
                    n = n.right;
                }
                else{
                    break;
                }
            }
        }
        AVLTree newNode = new AVLTree(address, size, key); 
        newNode.parent = n;
        if(n.key>key){
            n.left = newNode;
        }
        else if (n.key<key){
            n.right = newNode;
        }
        // Tie-breaking: use address  to compare if the keys are same
        else{
            if(n.address<address){
                n.right = newNode;
            }
            else{
                n.left = newNode;
            }
        }
        // now go up and check for the first height mismatch
        AVLTree firstMismatch = newNode.parent.parent;
        AVLTree child = newNode.parent;
        if(child.height < 1){
            child.height = 1;
        }
        AVLTree grandChild = newNode;
        boolean mismatchFound = false;
        while(!mismatchFound && firstMismatch.parent!=null){
            
            // updates the height and also checks for height imbalance
            mismatchFound = !firstMismatch.checkUpdateCurrHeight();
            if(mismatchFound){

                firstMismatch = this.rebalance(firstMismatch, child, grandChild);
                mismatchFound = true;
            }
                
            grandChild = child;
            child = firstMismatch;
            firstMismatch = firstMismatch.parent;
            
        }

        return newNode;
        
    }

    private void DeleteNode(){
        if(this.parent==null){
            return;
        }
        
        if(this.left==null && this.right == null){
            if(this.parent.left == this){
                this.parent.left = null;
            }
            if(this.parent.right == this){
                this.parent.right = null;
            }
            this.left = null;
            this.right = null;
            // return;
        }
        else if(this.left == null){
            this.right.parent = this.parent;
            if(this.parent.left == this){
                this.parent.left = this.right;
            }
            if(this.parent.right == this){
                this.parent.right = this.right;
            }
            this.right=null;
            // return;
        }
        else if(this.right == null){
            this.left.parent = this.parent;
            if(this.parent.left == this){
                this.parent.left = this.left;
            }
            if(this.parent.right == this){
                this.parent.right = this.left;
            }
            this.left=null;
            // return;
        }

        // below condition means this will be deleted. 
        // If so, go up the tree and check for height imbalances and use rebalance to resolve them
        if(this.left == null || this.right == null){
            this.height = -1;
            AVLTree firstMismatch = this.parent;
            AVLTree child;
            AVLTree grandChild;
            boolean mismatchFound;
            while(firstMismatch.parent!=null){
                
                mismatchFound = !firstMismatch.checkUpdateCurrHeight();
                if(mismatchFound){

                    int leftHeight = -1, rightHeight = -1;
                    if(firstMismatch.left!=null){
                        leftHeight = firstMismatch.left.height;
                    }
                    if(firstMismatch.right!=null){
                        rightHeight = firstMismatch.right.height;
                    }
                    if(leftHeight>=rightHeight){
                        child = firstMismatch.left;
                    }
                    else{
                        child = firstMismatch.right;
                    }
                    int leftGrandHeight = -1, rightGrandHeight = -1;
                    if(child.left!=null){
                        leftGrandHeight = child.left.height;
                    }
                    if(child.right!=null){
                        rightGrandHeight = child.right.height;
                    }
                    if(leftGrandHeight>=rightGrandHeight){
                        grandChild = child.left;
                    }
                    else{
                        grandChild = child.right;
                    }
                    firstMismatch = this.rebalance(firstMismatch, child, grandChild);
                }

                firstMismatch = firstMismatch.parent;
                
            }
            return;

        }
        AVLTree n = this.right;
        while(n!=null && n.left!=null){
            n = n.left;
        }
        AVLTree temp = new AVLTree(n.address, n.size, n.key);
        temp.left = this.left;
        temp.right = this.right;
        temp.parent = this.parent;
        temp.left.parent = temp;
        temp.right.parent = temp;
        if(temp.parent.left == this){
            temp.parent.left = temp;
        }
        else{
            temp.parent.right = temp;
        }

        n.DeleteNode();

    }

    public boolean Delete(Dictionary e)
    {
        AVLTree n = this.getRoot();
        while(n!=null){
            if(n.key<e.key){
                n = n.right;
            }
            else if(n.key>e.key){
                n = n.left;
            }
            else{
                if(n.address<e.address){
                    n = n.right;
                }
                else if (n.address>e.address){
                    n = n.left;
                }
                else{
                    if(n.size==e.size){
                        
                        n.DeleteNode();
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

    // this follows the same algorithm as that used in BSTree
    public AVLTree Find(int k, boolean exact)
    { 
        AVLTree n = this.getRoot();
        AVLTree notExactNode = null;
        AVLTree exactNode = null;
        while(n!=null){
            if(n.key<k){
                n = n.right;
            }
            else{
                if(n.key==k){
                    exactNode = n;
                }
                else{
                    notExactNode = n;
                }
                n = n.left;
            }
        }
        if(!exact){
            if(exactNode == null){
                return notExactNode;
            }   
        }
        if(exactNode==null){
            return null;
        }
        return exactNode;
    }

    // this follows the same algorithm as that used in BSTree
    public AVLTree getFirst()
    { 
        AVLTree n = this.getRoot();
        while(n!=null && n.left!=null){
            n = n.left;
        }
        return n;
    }

    // this follows the same algorithm as that used in BSTree
    public AVLTree getNext()
    {
        if(this.parent == null){
            return null;
        }
        if(this.right!=null){
            // find the minimum in the right subtree
            AVLTree n = this.right;
            while(n.left!=null){
                n = n.left;
            }
            return n;
        }
        else{
            // go to first ancestor that is the left child of its parent
            AVLTree n = this;

            while(n!=null && n.parent!= null && n.parent.left != n){
                n = n.parent;
            }
            if(n.parent == null){
                return null;
            }
            else{
                return n.parent;
            }
        }
    }

    // used to check if the heights of the nodes are sane. If sanity is violated, returns 2
    private int getHeight(AVLTree n){
        if(n == null){
            return -1;
        }
        int leftHeight = -1;
        int rightHeight = -1;
        if (n.left!=null){
            leftHeight = n.left.height;
        }
        if(n.right!=null){
            rightHeight = n.right.height;
        }
        if(this.getHeight(n.left)==-2 || this.getHeight(n.right)==-2){
            return -2;
        }
        if(leftHeight>rightHeight){
            if((n.height != leftHeight + 1)){
                System.out.println("height not updated ");
            }
            if((leftHeight>rightHeight+1) || (n.height != leftHeight + 1)){
                return -2;
            }
            
        }
        else{
            if((n.height != rightHeight + 1)){
                System.out.println("height not updated ");
            }
            if((rightHeight>leftHeight+1) || (n.height != rightHeight + 1)){
                return -2;
            }

        }
        return n.height;
    }

    public boolean sanity()
    { 
        // checks whether the BST preoperty and tree basic property is violated
        if(!super.sanity()){
            return false;
        }
        // checks sanity of heights
        if(this.getRoot()!= null){
            if(this.getHeight(this.getRoot())==-2){
                // System.out.println("height property violated");
                return false;
            }
        }
        
        return true;   
    }

}


