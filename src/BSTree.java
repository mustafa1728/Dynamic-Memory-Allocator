// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }


    // usefull function, will be used in insert and other functions as well
    private BSTree getRoot(){
        if (this.parent == null){
            return this.right;
        }
        else{
            return this.parent.getRoot();
        }
    }

    public BSTree Insert(int address, int size, int key) 
    {   

        BSTree n = this.getRoot();
        // case when tree is empty
        if(n==null){
            BSTree newNode = new BSTree(address, size, key); 
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
        BSTree newNode = new BSTree(address, size, key); 
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
        return newNode;
        
    }
    private void DeleteNode(){
        if(this.left==null && this.right == null){
            if(this.parent.left == this){
                this.parent.left = null;
            }
            if(this.parent.right == this){
                this.parent.right = null;
            }
            this.left = null;
            this.right = null;
            return;
        }
        if(this.left == null){
            this.right.parent = this.parent;
            if(this.parent.left == this){
                this.parent.left = this.right;
            }
            if(this.parent.right == this){
                this.parent.right = this.right;
            }
            this.right=null;
            return;
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
            return;
        }
        BSTree n = this.right;
        while(n!=null && n.left!=null){
            n = n.left;
        }
        BSTree temp = new BSTree(n.address, n.size, n.key);
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

        BSTree n = this.getRoot();
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
        
    public BSTree Find(int key, boolean exact)
    { 

        BSTree n = this.getRoot();
        BSTree notExactNode = null;
        BSTree exactNode = null;
        while(n!=null){
            if(n.key<key){
                n = n.right;
            }
            else{
                if(n.key==key){
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

    public BSTree getFirst()
    { 

        BSTree n = this.getRoot();
        while(n!=null && n.left!=null){
            n = n.left;
        }
        return n;
    }

    public BSTree getNext()
    { 
        // As inorder traversal should give a sorted array, getting the successor should give the next element in the inorder

        if(this.parent == null){
            return null;
        }
        if(this.right!=null){
            // find the minimum in the right subtree
            BSTree n = this.right;
            while(n.left!=null){
                n = n.left;
            }
            return n;
        }
        else{
            // go to first ancestor that is the left child of its parent
            BSTree n = this;

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

    private boolean checkCycle(BSTree n, BSTree visited){
        if(n == null){
            return true;
        }
        if(visited.Find(n.address, true) == null){
            // it is assumed that address is unique
            visited.Insert(n.address, n.size, n.address);
            return checkCycle(n.left, visited) && checkCycle(n.right, visited);
        }
        else{
            return false;
        }
    }
    public boolean sanity()
    { 
        BSTree n = this;
        // empty tree is sane
        if(n.parent==null){
            if(!(n.left==null && (n.address == -1 && n.size == -1 && n.key == -1))){
                return false;
            }
            else{
                if(n.right == null){
                    return true;
                }
            }
        }

        // go to sentinel and check if there is a cycle from this to sentinel
        BSTree tortoise = this;
        BSTree hare = this.parent;
        while(hare!=null && hare.parent!=null && tortoise.parent!=null){
            if(hare == tortoise){
                return false;
            }
            tortoise = tortoise.parent;
            hare = hare.parent;
            if(hare!=null && hare.parent!=null){
                hare = hare.parent;
            }   
        }
        n=hare;
        if(n==null){
            n = tortoise;
        }
        // as there is no cycle here, n will be the sentinel!
        
        // sanity of sentinel root
        if((n.parent == null)){
            if(!(n.address == -1 && n.size == -1 && n.key == -1)){
                System.out.println(1);
                return false;
            }
            if(n.left!=null){
                return false;
            }
        }


        BSTree visited = new BSTree();

        // n.right is the actual root
        if(!checkCycle(n.right, visited)){
            return false;
        }
        // note that this is important to be checked before other checks as they would be caught in an infinite loop if
        // the list would have a loop
        
        
        for(n = this.getFirst(); n.getNext()!=null; n = n.getNext()){
            // check inorder traversal in increaseing order
            if(n.key>n.getNext().key){
                System.out.println(2);
                return false;
            }
            else if(n.key==n.getNext().key){
                if(n.address>n.getNext().address){
                    System.out.println(3);
                    return false;
                }
            }
            // check if nodes are parents of their children
            if(n.left!=null && n.left.parent != n){
                System.out.println(4);
                return false;
            }
            if(n.right!=null && n.right.parent != n){
                System.out.println(5);
                return false;
            }

            // children should be different from parent
            if(n.left!=null && n.right != null){
                if(n.right == n.left || n.left == n.parent || n.right == n.parent){
                    System.out.println(6);
                    return false;
                }
            }
            
        }
        return true;
    }

}