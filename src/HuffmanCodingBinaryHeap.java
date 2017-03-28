import java.util.HashMap;

public class HuffmanCodingBinaryHeap {
	
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	
	public class binaryHeapWithFreq {
		
		int capacity; // maximum possible size of min heap
	    int heapSize;
	    heapNodes heapArr[];
	    
	   public class heapNodes{
	    	int data;
	    	int freq;
	    	heapNodes left;
	    	heapNodes right;
	    	heapNodes(int data,int freq){
	    		this.data=data;
	    		this.freq=freq;
	    		this.left=null;
	    		this.right=null;
	    	}
	    }

	    binaryHeapWithFreq(int capacity){
	    	this.capacity=capacity;
	    	this.heapSize=0;
	    	heapArr=new heapNodes[capacity];
	    }
	    
	    binaryHeapWithFreq(){
	    	this.capacity=0;
	    	this.heapSize=0;
	    	heapArr=new heapNodes[capacity];
	    }
	  
	    // to get index of the parent
	    int parent(int i) { return (i-1)/2; }
	 
	    // to get index of left child of node at index i
	    int left_child(int i) { return (2*i + 1); }
	 
	    // to get index of right child of node at index i
	    int right_child(int i) { return (2*i + 2); }
	  
	    void insertKey(heapNodes k)
	    {
	        if (this.heapSize == this.capacity)
	        {
	            System.out.println("The Heap is full! Heap Overflow");
	            return;
	        }
	     
	        // First insert the new key at the end
	        this.heapSize++;
	        int i = this.heapSize - 1;
	        this.heapArr[i] = k;
	     
	        // Fix the min heap property if it is violated
	        while (i != 0 && this.heapArr[parent(i)].freq > this.heapArr[i].freq)
	        {
	           heapNodes temp=null;
	           temp=this.heapArr[i];
	           this.heapArr[i]=this.heapArr[parent(i)];
	           this.heapArr[parent(i)]=temp;
	           i = parent(i);
	         }
	    }
	 
	    heapNodes extractMin()
	    {
	        if (this.heapSize <= 0)
	            return null;
	        if (this.heapSize == 1)
	        {
	            this.heapSize--;
	            return this.heapArr[0];
	        }
	     
	        // Store the minimum value, and remove it from heap
	        heapNodes root = this.heapArr[0];
	        this.heapArr[0] = this.heapArr[this.heapSize-1];
	        this.heapSize--;
	        MinHeapify(0);
	        return root;
	    }
	    
	    void MinHeapify(int i)
	    {
	        int l = left_child(i);
	        int r = right_child(i);
	        int smallest = i;
	        if (l < this.heapSize && this.heapArr[l].freq < this.heapArr[i].freq)
	            smallest = l;
	        if (r < this.heapSize && this.heapArr[r].freq < this.heapArr[smallest].freq)
	            smallest = r;
	        if (smallest != i)
	        {
	        	heapNodes temp=this.heapArr[i];
	        	this.heapArr[i]=this.heapArr[smallest];
	        	this.heapArr[smallest]=temp;
	            MinHeapify(smallest);
	        }
	    }
	    
	    heapNodes getMin(){
	    	return this.heapArr[0];
	    }
	    
	    void printMinHeap(){
	    int value=0;
	    System.out.println("Freq"+" "+"Data");
	    for(int i=0;i<this.heapSize;i++){
	    	System.out.println(this.heapArr[i].freq+" "+this.heapArr[i].data);
	    	}
	    }
	    
	    void pushArray(int arr[]){
	    	for(int i=0;i<arr.length;i++){
	    		insertKey(new heapNodes(i,arr[i]));
	    	}
	    }
	    
	    void HuffmanCodes(int arr[]){
	    	binaryHeapWithFreq bhwf=new binaryHeapWithFreq(arr.length); 
	    	for(int i=0;i<arr.length;i++){
	    		heapNodes hn=new heapNodes(i,arr[i]);
	    		bhwf.insertKey(hn);
	    	}
	    	
	    	while(bhwf.heapSize!=1){
	    		heapNodes left=bhwf.extractMin();
	    		heapNodes right=bhwf.extractMin();
	    		heapNodes top= new heapNodes(Integer.MIN_VALUE,left.freq+right.freq);
	    		top.left=left;
	    		top.right=right;
	    		bhwf.insertKey(top);
	    	}
	    	
	    	printCodes(bhwf.heapArr[0],"");
	    	printHashMap();
	    	
	    }
	    
	    void printCodes(heapNodes root,String str){
	    	 if (root==null)
	    	        return;
	    	 
	    	 if(root.data!=Integer.MIN_VALUE){
	    		 hmap.put(root.data, str);
	    	 }
	    	 
	    	 printCodes(root.left,str+"0");
	    	 printCodes(root.right,str+"1"); 
	    }
	    
	    void printHashMap(){
	    	System.out.println(hmap);
	    } 
	    
	   
	}
	
	void buildHuffmanTrees(int arr[]){
		binaryHeapWithFreq bhwf=new binaryHeapWithFreq(arr.length);
    	for(int i=0;i<arr.length;i++){
    		bhwf.HuffmanCodes(arr);		
    	}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HuffmanCodingBinaryHeap hcbh=new HuffmanCodingBinaryHeap();
		int arr[] = {5, 4, 11, 10, 13, 45 };
		hcbh.buildHuffmanTrees(arr);
		
	}

}
