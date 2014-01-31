package cs.aaclark.droidcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Save {

	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static final String saveFileName = "counters.sav";
	public Save(){
		
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
	
	public void AppendToSave(CounterModel counter) throws IOException{
		try{
			counter.writeObject(out);
		}catch(IOException e){
			
		}
	}
	
	public void RewriteSave(ArrayList<CounterModel> counterModels) throws IOException{
		try{
			for(int i=0; i<counterModels.size(); i++){
				
			}
		}finally{
			
		}
	}
	
}
