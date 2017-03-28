
public class fourWayHeapWithFreq {
	

	int capacity; // maximum possible size of min heap
    int heapSize;
    heapNodes heapArr[];
    
    fourWayHeapWithFreq(int capacity){
    	this.capacity=capacity;
    	this.heapSize=3;
    	heapArr=new heapNodes[capacity];
    	heapArr[0]=new heapNodes(0,-1);
    	heapArr[1]=new heapNodes(0,-1);
    	heapArr[2]=new heapNodes(0,-1);
    }
    
    fourWayHeapWithFreq(){
    	this.capacity=0;
    	this.heapSize=0;
    	heapArr=new heapNodes[capacity];
    }
    
    public class heapNodes{
    	int data;
    	int freq;
    	heapNodes(int data,int freq){
    		this.data=data;
    		this.freq=freq;
    	}
    }
    
    public int parent(int i) 
    {
    	//System.out.println((int)Math.round((i-2)/4.0+2));
        return (int)Math.round((i-2)/4.0+2);
    }
 
    /** Function to get index of k th child of i **/
    public int kthChild(int i, int k) 
    {
    	//System.out.println(Math.round(4 * (i-1) + (k-1)));
        return (4 * (i-2) + (k-1));
    }
    
    public void insert(heapNodes x)
    {
    	heapArr[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }
    
    public heapNodes ExtractMin(){
    	heapNodes minimum=heapArr[3];
    	heapNodes root = this.heapArr[3];
        this.heapArr[3] = this.heapArr[this.heapSize-1];
        this.heapSize--;
        heapifyDown(3);	
    	return minimum;
    }
    
    public void heapifyDown(int index){
    	int child;
    	heapNodes tmp = heapArr[ index ];
        while (kthChild(index, 1) < heapSize)
        {
            child = minChild(index);
            if (heapArr[child].freq < tmp.freq)
            	heapArr[index] = heapArr[child];
            else
                break;
            index = child;
        }
        heapArr[index] = tmp;
    }
    
    public int minChild(int index){
    	int bestChild = kthChild(index, 1);
        int k = 2;
        int pos = kthChild(index, k);
        while ((k <= 4) && (pos < heapSize)) 
        {
            if (heapArr[pos].freq < heapArr[bestChild].freq) 
                bestChild = pos;
                k=k+1;
            pos = kthChild(index, k);
        }    
        return bestChild;
    }
    
    public void heapifyUp(int index)
    {
    	heapNodes element = heapArr[index];  
    	heapNodes temp;
        while (index > 0 && element.freq < heapArr[parent(index)].freq)
        {
        	temp=heapArr[index];
        	heapArr[index] = heapArr[ parent(index) ];
        	heapArr[parent(index)]=temp;
            index = parent(index);
        }
     }
    
    public void printElements(){
    	System.out.println("Entered the Prin Element");
    	for(int i=0;i<heapSize;i++){
    		System.out.println(heapArr[i]);
    	}
    	System.out.println("Finished the Prin Element");
    }
    
    void pushArray(int arr[]){
    	for(int i=0;i<arr.length;i++){
    		insert(new heapNodes(i,arr[i]));
    	}
    }
    
     void printMinHeap(){
        int value=0;
        System.out.println("Freq"+" "+"Data");
        for(int i=0;i<this.heapSize;i++){
        	System.out.println(this.heapArr[i].freq+" "+this.heapArr[i].data);
        	}
        }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fourWayHeapWithFreq bhf=new fourWayHeapWithFreq(100);
		int arr[] = {5, 4, 11, 10, 13, 45 };
		bhf.pushArray(arr);
		bhf.printMinHeap();
		System.out.println("freq"+" "+"data");
		for(int i=0;i<arr.length;i++){
			heapNodes hp=bhf.ExtractMin();
			System.out.println(hp.freq+" "+hp.data);
		}
	}

}
