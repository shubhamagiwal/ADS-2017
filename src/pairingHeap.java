
public class pairingHeap {
	private pairNode root=null;
	private pairNode minPointer=null; 
	
	
	class pairNode{
	    int data;
	    pairNode leftSibling;
	    pairNode rightSibling;
	    pairNode child;
	    public pairNode(int value){
	    	leftSibling=null;
	    	rightSibling=null;
	    	child=null;
	    	data=value;
	    }   
	}
	
	public void insert(int data){
		pairNode newNode=new pairNode(data);
		if(root==null){
			root=newNode;
			minPointer=newNode;
		}else{
			// Means create a new Node and insert it into linked list fashion
			// Start from min pointer
			System.out.println("Entered insert for non root");
		    compareLink(root,newNode);
		}
	}
	
	public pairNode compareLink(pairNode first,pairNode second){
		if(second == null){
			// Do nothing
			System.out.println("Do nothing");
			return first;
		}else if(second.data>=first.data && first.child==null){
			//Just attach the second node as child to the first node
			System.out.println("greater than the root1");
			first.child=second;
 			second.leftSibling=first;
 			return first;
		}else if(second.data>=first.data && first.child!=null){
			// attach the second node as a child to the first node and change the child pointers
			System.out.println("greater than the root2");
			second.leftSibling=first;
			second.rightSibling=first.child;
			first.child.leftSibling=second;
			first.child=second;
			return first;
		}else if(first.data>second.data && second.child==null){
			System.out.println("lesser than the root");
//			pairNode temp=second.child;
//			root=second;
//			root.leftSibling=null;
//			root.rightSibling=null;
//			root.child=first;
//			first.leftSibling=root;
//			first.rightSibling=null;
//			if(temp!=null){
//				temp.leftSibling=first.rightSibling;
//			}
			second.child=first;
			first.leftSibling=second;
			root=second;
			minPointer=second;
			return second;
		}else if(first.data>second.data && second.child!=null){
			System.out.println("lesser than the root");
			first.rightSibling=second.child;
			second.child.leftSibling=first;
			second.child=first;
			first.leftSibling=second;
//			pairNode temp=second.child;
//			root=second;
//			root.leftSibling=null;
//			root.rightSibling=null;
//			root.child=first;
//			first.leftSibling=root;
//			first.rightSibling=temp;
//			temp.leftSibling=first.rightSibling;
			root=second;
			minPointer=second;
			return second;
		}
		return null;
	
	}
	
	public int removeMin(){
	    int minimum=minPointer.data;
	    int count=0;
	    int k=0;
	    pairNode child=minPointer.child;
	    pairNode temp=child;
	    
	    minPointer.child=null;

	    while(temp!=null){
	    	temp=temp.rightSibling;
	    	count=count+1;
	    }
	    
	   pairNode pt[]=new pairNode[count];
	   temp=child;
	   
	   for(int i=0;i<count;i++){
		   pt[i]=temp;
	       temp=temp.rightSibling;
	       pt[i].rightSibling=null;
	       pt[i].leftSibling=null;
	   }
	   
	   for( k=0; k + 1 < count; k += 2 ){   
		   pt[ k ] = compareLink( pt[ k ], pt[ k + 1 ] );
	   }
	   
       int j = k - 2;
           // j has the result of last compareAndLink.
           // If an odd number of trees, get the last one.
       if( j == count - 3 && count!=1)
    	   pt[ j ] = compareLink( pt[ j ], pt[ j + 2 ] );

           // Now go right to left, merging last tree with
           // next to last. The result becomes the new last.
       for( ; j >= 2; j -= 2 ){
    	   pt[ j - 2 ] = compareLink( pt[ j - 2 ], pt[ j ] );
	   }
       
       
       if(count==1){
	    	minPointer=pt[0];
	    }
	   
	   // System.out.println(count);   
	   //System.out.println(pt[0]);
	   return minimum;
	}
	
//	public pairNode meld(pairNode first,pairNode second){
//		
//	}
	
	
	public static void main(String args[]){
		pairingHeap ph=new pairingHeap();
		ph.insert(11);
		ph.insert(10);
		ph.insert(12);
		ph.insert(9);
		ph.insert(13);
		ph.insert(13);
		System.out.println(ph.removeMin());
		System.out.println(ph.removeMin());
		System.out.println(ph.removeMin());
		System.out.println(ph.removeMin());
		System.out.println(ph.removeMin());
		System.out.println(ph.removeMin());
		//ph.inorder();
		
	}

}
