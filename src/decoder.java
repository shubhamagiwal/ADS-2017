import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

public class decoder {
	
	TreeNode root=null;
	
	public class TreeNode{
		TreeNode left;
		TreeNode right;
		int data;
		
		TreeNode(int data){
			this.left=null;
			this.right=null;
			this.data=data;
		}
		
		TreeNode(){
			this.left=null;
			this.right=null;
			this.data=Integer.MIN_VALUE;
		}
		
	}
	
	public String getEncodedString(String encoded_file_name){
		System.out.println("Entered");
		File file = new File(encoded_file_name);
		System.out.println(file.length());
		byte[] fileData = new byte[(int) file.length()];
		FileInputStream in;
		try {
			in=new FileInputStream(file);
			try {
				in.read(fileData);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Got the File");
		BitSet bitSet = new BitSet();
		bitSet=BitSet.valueOf(fileData);
		System.out.println(bitSet.length());
		StringBuilder binaryString =new StringBuilder();
		
		for(int i = 0; i <= fileData.length * 8; i++) {
		    if(bitSet.get(i)) {
		    	binaryString.append("1");
		        //binaryString += "1";
		    } else {
		    	binaryString.append("0");
		    	//binaryString += "0";
		    }
		}
		System.out.println("Finished");
		return binaryString.toString();
	}
	
	void code_table_creation_data(String code_table_file_name){
		String currentLine;
		try (BufferedReader br = new BufferedReader(new FileReader(code_table_file_name))) {
			System.out.println("Entered");
			while ((currentLine = br.readLine()) != null && !currentLine.equals("")) {
				//System.out.println(currentLine);
				String str[]=currentLine.split(" ");
				int key=Integer.parseInt(str[0]);
				String encodedKey=str[1];
				insertKey(key,encodedKey);
			}
			System.out.println("Done Inserting");
		}catch (IOException e) {
			e.printStackTrace();
	    }	
	}
	
	void insertKey(int key,String encodedKey){
		if(root==null){
			root=new TreeNode(Integer.MIN_VALUE);
		}
		
		TreeNode traversalPointer=root;
		StringBuilder sb = new StringBuilder(encodedKey);
		
		while(!sb.toString().equals("")){
			if(sb.toString().charAt(0)=='0'){
				if(traversalPointer.left==null){
					TreeNode newNode=new TreeNode(Integer.MIN_VALUE);
					traversalPointer.left=newNode;
					traversalPointer=newNode;
					sb.deleteCharAt(0);
				}else if(traversalPointer.left!=null){
					traversalPointer=traversalPointer.left;
					sb.deleteCharAt(0);
				}
			}else if(sb.toString().charAt(0)=='1'){
				if(traversalPointer.right==null){
					TreeNode newNode=new TreeNode(Integer.MIN_VALUE);
					traversalPointer.right=newNode;
					traversalPointer=newNode;
					sb.deleteCharAt(0);
				}else if(traversalPointer.right!=null){
					traversalPointer=traversalPointer.right;
					sb.deleteCharAt(0);
				}
			}
		}
		
		if(traversalPointer.data==Integer.MIN_VALUE){
			traversalPointer.data=key;
		}	
	}
	
	void decodedFileGeneration(String encodedString){
		int i=0;
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		
		try {
			fw = new FileWriter("decoded.txt");
			bw = new BufferedWriter(fw);
			TreeNode traversalPointer=root;
			while(i<encodedString.length()){
				char ch=encodedString.charAt(i);
				if(ch=='0'){
					traversalPointer=traversalPointer.left;
					if(traversalPointer.left==null && traversalPointer.right==null){
						int data=traversalPointer.data;
						bw.write(Integer.toString(data));
						traversalPointer=root;
						if(i<encodedString.length()-1){
							bw.write("\n");
						}
					}else{
						//traversalPointer=traversalPointer.left;
					}
				}else if(ch=='1'){
					traversalPointer=traversalPointer.right;
					if(traversalPointer.left==null && traversalPointer.right==null){
						int data=traversalPointer.data;
						bw.write(Integer.toString(data));
						traversalPointer=root;
						if(i<encodedString.length()-1){
							bw.write("\n");
						}
					}else{
						//traversalPointer=traversalPointer.right;
					}
				}	
				i++;
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
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long endStartTime=System.nanoTime();
		decoder hd=new decoder();
		if(args.length==0 || args.length==1 || args[0]==null || args[1]==null || args[0].equals("") || args[1].equals("") ){
			System.out.println("Invalid Invocation for the huffman decoder");
			System.exit(0);
		}else{
			String encoded_file_name=args[0];
			String code_table_file_name=args[1];
			String encodedString=hd.getEncodedString(encoded_file_name);
			hd.code_table_creation_data(code_table_file_name);
			hd.decodedFileGeneration(encodedString);
			long endEndTime =System.nanoTime();
			System.out.println((endEndTime-endStartTime)/ 1000000000.0);
		}
	}
}
