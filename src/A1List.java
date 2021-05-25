// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if(this==null || this.next==null){
            return null;
        }
        A1List n = new A1List(address, size, key);
        
        this.next.prev = n;
        n.next = this.next;
        
        this.next = n;
        n.prev = this;

        return n;
    }

    public boolean Delete(Dictionary d) 
    {
        if(d==null){
            return false;
        }
        for(A1List n = this.getFirst(); n!=null; n = n.getNext()){
            if ((n.address == d.address) && (n.size == d.size) && (n.key == d.key)){
                n.prev.next = n.next;
                n.next.prev = n.prev;
                n = null;
                return true;
            }
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        for(A1List n = this.getFirst(); n!=null; n = n.getNext()){
            if(exact){
                if (n.key == k) {
                    return n;
                }
            }
            else{
                if (n.key >= k) {
                    return n;
                }
            }
        }
        return null;
    }

    public A1List getFirst()
    {
    	if (this==null){
    		return null;
    	}

        // if this is head sentinel, return its next if its non-empty and null when empty
        if(this.prev == null){
        	if (this.next.next == null){
        		return null;
        	}
        	else{
        		return this.next;
        	}
        }
        else{
        	return this.prev.getFirst();
        }
    }
    
    public A1List getNext() 
    {
        // tail sentinel or empty list
        if (this == null || this.next == null || this.next.next == null){
        	return null;
        }
        else{
        	return this.next;
        }
        
    }

    public boolean sanity(){

        // null list or empty list is sane
        if(this == null){
            return true;
        }
        if (this.getFirst() == null){
            if((this.next.size!=-1)||(this.next.address!=-1)||(this.next.key!=-1)){
                return false;
            }
            return true;
        }

        // checks for cycle in the list using the hare and tortoise method
        A1List hare = this.getFirst().getNext();
        for(A1List tortoise = this.getFirst(); tortoise!=null; tortoise = tortoise.getNext()){
            if(hare == tortoise){
                // this means there is a cycle
                return false;
            }
            if(hare == null){
                break;
            }
            hare = hare.getNext();
            if(hare == null){
                break;
            }
            hare = hare.getNext();
        }
        // note that this is important to be checked before other checks as they would be caught in an infinite loop if
        // the list would have a cycle

        // checks sanity of head sentinel
        A1List n = this.getFirst();
        if((n.prev.size!=-1)||(n.prev.address!=-1)||(n.prev.key!=-1)||(n.prev.prev!=null)){
            return false;
        }
        // checks whether next and prev point to curr for all nodes
        for( ; n.next!=null; n = n.next){
            if(!(n.next.prev == n) || !(n.prev.next == n)){
                return false;
            }
        }
        // checks sanity of tail sentinel
        if((n.size!=-1)||(n.address!=-1)||(n.key!=-1)||(n.next!=null)){
            return false;
        }
        return true;
    }

}