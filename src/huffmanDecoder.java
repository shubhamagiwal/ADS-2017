import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

public class huffmanDecoder {

	public huffmanDecoder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==0){
			System.out.println("Invalid file invocation");
			System.exit(0);
		}else{
			String encodedFileName=args[0];
			//String codeTableFileName=args[1];
			File file = new File("encoded.bin");
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
			
			BitSet bitSet = new BitSet();
			bitSet=BitSet.valueOf(fileData);
			String binaryString = "";
			
			for(int i = 0; i <= bitSet.length(); i++) {
			    if(bitSet.get(i)) {
			        binaryString += "1";
			    } else {
			        binaryString += "0";
			    }
			}
			System.out.println(binaryString);
			
		}
	}
}
