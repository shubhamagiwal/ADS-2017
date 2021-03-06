import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class HuffmanCodingBinaryHeapNew {


	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	
	public class HuffmanNodes{
		int data;
		int freq;
		HuffmanNodes left;
		HuffmanNodes right;
		
		HuffmanNodes(int data,int freq){
			this.data=data;
			this.freq=freq;
			this.left=null;
			this.right=null;
		}
	}
	
	public class binaryHeapWithFreq {
		
		int capacity; // maximum possible size of min heap
	    int heapSize;
	    heapNodes heapArr[];
	    
	   public class heapNodes{
	    	int data;
	    	int freq;
	    	HuffmanNodes hnodes;
	    	heapNodes(HuffmanNodes hnodes1){
	    		this.data=hnodes1.data;
	    		this.freq=hnodes1.freq;
	    		this.hnodes=hnodes1;

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
	  
	    void insertKey(HuffmanNodes k)
	    {
	        if (this.heapSize == this.capacity)
	        {
	            System.out.println("The Heap is full! Heap Overflow");
	            return;
	        }
	     
	        // First insert the new key at the end
	        this.heapSize++;
	        int i = this.heapSize - 1;
	        heapNodes hn=new heapNodes(k);
	        this.heapArr[i] = hn;
	     
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
	 
	    HuffmanNodes extractMin()
	    {
	        if (this.heapSize <= 0)
	            return null;
	        if (this.heapSize == 1)
	        {
	            this.heapSize--;
	            return this.heapArr[0].hnodes;
	        }
	     
	        // Store the minimum value, and remove it from heap
	        heapNodes root = this.heapArr[0];
	        this.heapArr[0] = this.heapArr[this.heapSize-1];
	        this.heapSize--;
	        MinHeapify(0);
	        return root.hnodes;
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
	    
	    void printMinHeap(){
	    int value=0;
	    System.out.println("Freq"+" "+"Data");
	    for(int i=0;i<this.heapSize;i++){
	    	System.out.println(this.heapArr[i].freq+" "+this.heapArr[i].data);
	    	}
	    }
	    
	    void pushArray(int arr[]){
	    	for(int i=0;i<arr.length;i++){
	    		insertKey(new HuffmanNodes(i,arr[i]));
	    	}
	    }
	    
	    void HuffmanCodes(int arr[]){
	    	binaryHeapWithFreq bhwf=new binaryHeapWithFreq(arr.length); 
	    	for(int i=0;i<arr.length;i++){
	    		if(arr[i]!=0){
	    			HuffmanNodes hnodes2=new HuffmanNodes(i,arr[i]);
		    		bhwf.insertKey(hnodes2);
	    		}	
	    	}
	    	
	    	while(bhwf.heapSize!=1){
	    		HuffmanNodes left=bhwf.extractMin();
	    		HuffmanNodes right=bhwf.extractMin();
	    		//HuffmanNodes hnode2=new HuffmanNodes()
	    		HuffmanNodes top= new HuffmanNodes(Integer.MIN_VALUE,left.freq+right.freq);
	    		top.left=left;
	    		top.right=right;
	    		bhwf.insertKey(top);
	    	}
	    	
	    	printCodes(bhwf.heapArr[0].hnodes,"");
	    	//printHashMap();
	    	
	    }
	    
	    void printCodes(HuffmanNodes root,String str){
	    	 if (root==null)
	    	        return;
	    	 
	    	 if(root.data!=Integer.MIN_VALUE){
	    		 hmap.put(root.data, str);
	    	 }
	    	 
	    	 printCodes(root.left,str+"0");
	    	 printCodes(root.right,str+"1"); 
	    }
	    
	  }
	
	    
	    void printHashMap(){
	    	System.out.println(hmap);
	    } 
	    
	    void buildHuffmanTrees(int arr[]){
			binaryHeapWithFreq bhwf=new binaryHeapWithFreq(arr.length);
				//long startTime = System.nanoTime();
	    		bhwf.HuffmanCodes(arr);	
	    		System.out.println("Done");
	    		//long stopTime = System.nanoTime();
	    		//System.out.println((stopTime - startTime)/ 1000000000.0);
	    		System.out.println("Done");
	    }
	    
	    
	    String encodedOutput(ArrayList fileContentarr){
			
			StringBuilder encoded = new StringBuilder();
			System.out.println(fileContentarr.size());
			for(int i=0;i<fileContentarr.size();i++){
				encoded.append((String)hmap.get(fileContentarr.get(i)));
			}
			//System.out.println(encoded);
			return encoded.toString();
		}
		
		void createEncodedFile(String encoded){
			System.out.println("Enterd the file creation stage");
			BitSet bitSet = new BitSet(encoded.length());
			int bitcounter = 0;
			for(Character c : encoded.toCharArray()) {
			    if(c.equals('1')) {
			        bitSet.set(bitcounter);
			    }
			    bitcounter++;
			}
//			
//			for(int i=0;i<=bitSet.length();i++){
//				System.out.print(bitSet.get(i) ==false?0:1);
//			}
			
			byte encodedArr[]=bitSet.toByteArray();

			try {
				FileOutputStream fos = new FileOutputStream("encoded.bin");
				BufferedOutputStream bos = null;
				bos = new BufferedOutputStream(fos);
				try {
					bos.write(encodedArr);
					bos.flush();
					System.out.println("Done with flushing");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					 try {
						 if(bos!=null){
							 bos.flush();
							 bos.close(); 
						 }
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		public void createCodeTableFile(String FILENAME){
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {
				fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
				 Iterator it = hmap.entrySet().iterator();
			        while (it.hasNext()) {
			        	Map.Entry hmapValue = (Map.Entry)it.next();
			        	System.out.println(Integer.toString((int)hmapValue.getKey())+" "+(String)hmapValue.getValue());
			        	try {
							bw.write(Integer.toString((int)hmapValue.getKey()) + " " + (String)hmapValue.getValue());
							if(it.hasNext()){
								bw.write("\n");
							}
							//bw.write(hmapValue.getKey()+" "+hmapValue.getValue()+"\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						  }	
						}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(bw!=null){
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(fw!=null){
				  try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}      
	        System.out.println("Created Code Table : code_table.txt");
		}
	
	    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HuffmanCodingBinaryHeapNew hcbh=new HuffmanCodingBinaryHeapNew();
		//int arr[] = {5, 4, 11, 10, 13, 45 };
		int arr[]=new int[10000000];
		ArrayList <Integer> fileContentarr=new ArrayList<Integer>();
		int i=0;
		Arrays.fill(arr, 0);	
		if(args.length==0){
			System.out.println("Please Enter the file Path");
			System.exit(0);
		}else{
			String FILENAME=args[0];
			String currentLine;
			
			try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
				while ((currentLine = br.readLine()) != null && !currentLine.equals("")) {
					int value=Integer.parseInt(currentLine);
					arr[value]=arr[value]+1;
					fileContentarr.add(value);
				}
				
				long startTime = System.nanoTime();
				for(i=0;i<10;i++){
					HuffmanCodingBinaryHeapNew hcbh1=new HuffmanCodingBinaryHeapNew();
					hcbh1.buildHuffmanTrees(arr);
				}
				hcbh.buildHuffmanTrees(arr);
				long stopTime = System.nanoTime();
				System.out.println((stopTime - startTime)/ 1000000000.0);
				String encodedInput=hcbh.encodedOutput(fileContentarr);
				hcbh.createEncodedFile(encodedInput);
				hcbh.createCodeTableFile("code_table.txt");
				
			}catch (IOException e) {
					e.printStackTrace();
		    }
        }
	}
}
