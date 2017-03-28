
public class pairingHeapDataFreq {
	
	
	private pairNode root=null;
	private pairNode minPointer=null; 
	
	
	class pairNode{
	    int freq;
	    int data;
	    pairNode leftSibling;
	    pairNode rightSibling;
	    pairNode child;
	    public pairNode(int data,int freq){
	    	this.leftSibling=null;
	    	this.rightSibling=null;
	    	this.child=null;
	    	this.data=data;
	    	this.freq=freq;
	    }   
	}
	
	public void insert(int data,int freq){
		pairNode newNode=new pairNode(data,freq);
		if(root==null){
			root=newNode;
			minPointer=newNode;
		}else{
			// Means create a new Node and insert it into linked list fashion
			// Start from min pointer
			//System.out.println("Entered insert for non root");
		    compareLink(root,newNode);
		}
	}
	
	public pairNode compareLink(pairNode first,pairNode second){
		if(second == null){
			// Do nothing
			System.out.println("Do nothing");
			return first;
		}else if(second.freq>=first.freq && first.child==null){
			//Just attach the second node as child to the first node
			//System.out.println("greater than the root1");
			first.child=second;
 			second.leftSibling=first;
 			return first;
		}else if(second.freq>=first.freq && first.child!=null){
			// attach the second node as a child to the first node and change the child pointers
			//System.out.println("greater than the root2");
			second.leftSibling=first;
			second.rightSibling=first.child;
			first.child.leftSibling=second;
			first.child=second;
			return first;
		}else if(first.freq>second.data && second.child==null){
			second.child=first;
			first.leftSibling=second;
			root=second;
			minPointer=second;
			return second;
		}else if(first.freq>second.freq && second.child!=null){
			first.rightSibling=second.child;
			second.child.leftSibling=first;
			second.child=first;
			first.leftSibling=second;
			root=second;
			minPointer=second;
			return second;
		}
		return null;
	
	}
	
	public pairNode removeMin(){
		pairNode minimum=minPointer;
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
	   
	   return minimum;
	}
	
	void pushArray(int arr[]){
    	for(int i=0;i<arr.length;i++){
    		insert(i,arr[i]);
    	}
    }
	
	public static void main(String args[]){
		pairingHeapDataFreq phdf=new pairingHeapDataFreq();
		int arr[] = {5, 4, 11, 10, 13, 45 };
		phdf.pushArray(arr);
		System.out.println("freq"+" "+"data");
		for(int i=0;i<arr.length;i++){
			pairNode ph=phdf.removeMin();
			System.out.println(ph.freq+" "+ph.data);
		}
	}

}
