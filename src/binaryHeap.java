import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class binaryHeap {
	
	int capacity; // maximum possible size of min heap
    int heapSize;
    int heapArr[];
    
    binaryHeap(int capacity){
    	this.capacity=capacity;
    	this.heapSize=0;
    	heapArr=new int[capacity];
    }
    
    binaryHeap(){
    	this.capacity=0;
    	this.heapSize=0;
    	heapArr=new int[capacity];
    }
  
    // to get index of the parent
    int parent(int i) { return (i-1)/2; }
 
    // to get index of left child of node at index i
    int left_child(int i) { return (2*i + 1); }
 
    // to get index of right child of node at index i
    int right_child(int i) { return (2*i + 2); }
  
    void insertKey(int k)
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
        while (i != 0 && this.heapArr[parent(i)] > this.heapArr[i])
        {
           int temp=0;
           temp=this.heapArr[i];
           this.heapArr[i]=this.heapArr[parent(i)];
           this.heapArr[parent(i)]=temp;
           i = parent(i);
         }
    }
 
    int extractMin()
    {
        if (this.heapSize <= 0)
            return Integer.MIN_VALUE;
        if (this.heapSize == 1)
        {
            this.heapSize--;
            return this.heapArr[0];
        }
     
        // Store the minimum value, and remove it from heap
        int root = this.heapArr[0];
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
        if (l < this.heapSize && this.heapArr[l] < this.heapArr[i])
            smallest = l;
        if (r < this.heapSize && this.heapArr[r] < this.heapArr[smallest])
            smallest = r;
        if (smallest != i)
        {
        	int temp=this.heapArr[i];
        	this.heapArr[i]=this.heapArr[smallest];
        	this.heapArr[smallest]=temp;
            MinHeapify(smallest);
        }
    }
    
    int getMin(){
    	return this.heapArr[0];
    }
    
    void printMinHeap(){
    int value=0;
    for(int i=0;i<this.heapSize;i++){
    	System.out.println(this.heapArr[i]);
    	}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		binaryHeap bh=new binaryHeap(100);
		bh.insertKey(10);
		bh.insertKey(5);
		bh.insertKey(2);
		bh.insertKey(3);
		System.out.println("Extract Min ="+bh.extractMin());
		bh.printMinHeap();
		System.out.println("Get Min="+bh.getMin());
	}
	
}
