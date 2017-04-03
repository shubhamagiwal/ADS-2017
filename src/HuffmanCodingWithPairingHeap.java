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


public class HuffmanCodingWithPairingHeap {
	

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
	
	
	public class pairingHeapDataFreq {
		
		
		private pairNode root=null;
		private pairNode minPointer=null; 
		private int size=0;
		
		
		class pairNode{
		    int freq;
		    int data;
		    pairNode leftSibling;
		    pairNode rightSibling;
		    pairNode child;
		    HuffmanNodes hnodes;
		    public pairNode(HuffmanNodes hnodes){
		    	this.leftSibling=null;
		    	this.rightSibling=null;
		    	this.child=null;
		    	this.data=hnodes.data;
		    	this.freq=hnodes.freq;
		    	this.hnodes=hnodes;
		    }   
		}
		
		 public int size( )
		    {
		        return this.size;
		    }
		
		public void insertKey(HuffmanNodes hnodes){
			pairNode newNode=new pairNode(hnodes);
			this.size++;
			if(minPointer==null){
				root=newNode;
				minPointer=newNode;
			}else{
				// Means create a new Node and insert it into linked list fashion
				// Start from min pointer
				//System.out.println("Entered insert for non root");
			    compareLink(minPointer,newNode);
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
				minPointer=first;
	 			return first;
			}else if(second.freq>=first.freq && first.child!=null){
				// attach the second node as a child to the first node and change the child pointers
				//System.out.println("greater than the root2");
				second.leftSibling=first;
				second.rightSibling=first.child;
				first.child.leftSibling=second;
				first.child=second;
				minPointer=first;
				return first;
			}else if(first.freq>second.freq && second.child==null){
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
			System.out.println(first.freq+" "+second.freq+" "+second.child);
			return null;
		
		}
		
		public HuffmanNodes extractMin(){
			pairNode minimum=minPointer;
		    int count=0;
		    int k=0;
		    this.size--;
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
	       
	       //root=pt[0];
		   
		   return minimum.hnodes;
		}
		
		void HuffmanCodes(int arr[]){
			pairingHeapDataFreq bhwf=new pairingHeapDataFreq(); 
	    	for(int i=0;i<arr.length;i++){
	    		if(arr[i]!=0){
	    			HuffmanNodes hnodes2=new HuffmanNodes(i,arr[i]);
		    		bhwf.insertKey(hnodes2);
	    		}	
	    	}
	    	
	    	while(bhwf.size()!=1){
	    		HuffmanNodes left=bhwf.extractMin();
	    		HuffmanNodes right=bhwf.extractMin();
	    		//HuffmanNodes hnode2=new HuffmanNodes()
	    		HuffmanNodes top= new HuffmanNodes(Integer.MIN_VALUE,left.freq+right.freq);
	    		top.left=left;
	    		top.right=right;
	    		if(bhwf.size()==0){
	    			bhwf.minPointer=null;
	    		}
	    		bhwf.insertKey(top);
	    	}
	    	
	    	printCodes(bhwf.minPointer.hnodes,"");
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
    	pairingHeapDataFreq bhwf=new pairingHeapDataFreq();
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
		HuffmanCodingWithPairingHeap hcbh=new HuffmanCodingWithPairingHeap();
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
//				for(i=0;i<10;i++){
//					HuffmanCodingWithPairingHeap hcbh1=new HuffmanCodingWithPairingHeap();
//					hcbh1.buildHuffmanTrees(arr);
//				}
				long stopTime = System.nanoTime();
				System.out.println((stopTime - startTime)/ 1000000000.0);
				
				hcbh.buildHuffmanTrees(arr);
				String encodedInput=hcbh.encodedOutput(fileContentarr);
				hcbh.createEncodedFile(encodedInput);
				hcbh.createCodeTableFile("code_table.txt");
				
			}catch (IOException e) {
					e.printStackTrace();
		    }
        }
	}

}
