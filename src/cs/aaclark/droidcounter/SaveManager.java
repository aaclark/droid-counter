package cs.aaclark.droidcounter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveManager {

	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	
	public SaveManager(String saveFileName){

		
		try {
			File file = new File(saveFileName);
			if(!file.exists()){
				if(!file.createNewFile()){
					throw new IOException("Cannot create file");
				}
			}
			// Set up object-to and object-from file

			FileInputStream fileInStream = new FileInputStream(file);
			in = new ObjectInputStream(fileInStream);
			
			FileOutputStream fileOutStream = new FileOutputStream(file);
			out = new ObjectOutputStream(fileOutStream);
			
		} catch (IOException e) {
		}
	}
	
	public void AppendObject(CounterModel counter) throws IOException{
		try{
			counter.writeObject(out);
		}catch(IOException e){
			
		}
	}

}
