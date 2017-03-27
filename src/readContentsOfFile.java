import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readContentsOfFile {

	public readContentsOfFile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector elements = new Vector(3,2);
		Vector count=new Vector(3,2);
		int freq_table[]=new int[10000000];
		Arrays.fill(freq_table, 0);
		
		if(args.length==0){
			System.out.println("Please Enter the file Path");
			System.exit(0);
		}else{
			String FILENAME=args[0];
			String currentLine;
			
			try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
				while ((currentLine = br.readLine()) != null) {
					System.out.println(currentLine);
					int value=Integer.parseInt(currentLine);
					freq_table[value]=freq_table[value]+1;
//					if(elements.contains(value)){
//						int index=elements.indexOf(value);
//						count.set(index,Integer.parseInt(count.elementAt(index).toString())+1);
//					}else{
//						elements.add(value);
//						int index=elements.indexOf(value);
//						count.insertElementAt(1, index);
//					}
				}
				
//				System.out.println(elements);
//				System.out.println(count);
				
				for(int i=0;i<freq_table.length;i++){
					if(freq_table[i]!=0){
						System.out.println(freq_table[i]+" ");
					}	
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
