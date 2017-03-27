
public class FourWayCacheHeap {
	
	int capacity; // maximum possible size of min heap
    int heapSize;
    int heapArr[];
    
    FourWayCacheHeap(int capacity){
    	this.capacity=capacity;
    	this.heapSize=3;
    	heapArr=new int[capacity];
    	heapArr[0]=-1;
    	heapArr[1]=-1;
    	heapArr[2]=-1;
    }
    
    FourWayCacheHeap(){
    	this.capacity=0;
    	this.heapSize=0;
    	heapArr=new int[capacity];
    }
    
    public int parent(int i) 
    {
    	System.out.println((int)Math.round((i-2)/4.0+2));
        return (int)Math.round((i-2)/4.0+2);
    }
 
    /** Function to get index of k th child of i **/
    public int kthChild(int i, int k) 
    {
    	System.out.println(Math.round(4 * (i-1) + (k-1)));
        return (4 * (i-1) + (k-1));
    }
    
    public void insert(int x)
    {
    	heapArr[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }
    
    public int ExtractMin(){
    	int minimum=heapArr[3];
    	int root = this.heapArr[3];
        this.heapArr[3] = this.heapArr[this.heapSize-1];
        this.heapSize--;
        heapifyDown(3);	
    	return minimum;
    }
    
    public void heapifyDown(int index){
    	int child;
        int tmp = heapArr[ index ];
        while (kthChild(index, 1) < heapSize)
        {
            child = minChild(index);
            if (heapArr[child] < tmp)
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
            if (heapArr[pos] < heapArr[bestChild]) 
                bestChild = pos;
            pos = kthChild(index, k++);
        }    
        return bestChild;
    }
    
    public void heapifyUp(int index)
    {
        int element = heapArr[index];  
        int temp;
        while (index > 0 && element < heapArr[parent(index)])
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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FourWayCacheHeap fw=new FourWayCacheHeap(100);
		 fw.insert(10);
		 fw.insert(9);
		 fw.insert(11);
		 fw.insert(1);
		 fw.insert(2);
		 fw.insert(5);
		 fw.printElements();
		 System.out.println(fw.ExtractMin());
		 System.out.println(fw.ExtractMin());
		 System.out.println(fw.ExtractMin());
		 System.out.println(fw.ExtractMin());
		 System.out.println(fw.ExtractMin());
		 System.out.println(fw.ExtractMin());
		 
		

	}

}
