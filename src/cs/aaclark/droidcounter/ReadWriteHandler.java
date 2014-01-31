package cs.aaclark.droidcounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ReadWriteHandler {

	private Gson gson;
	private BufferedReader in;
	private ArrayList<CounterModel> counterModelsBuffer;
	private String save;
	
	/**
	 * Instantiates the read/write file handler
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	ReadWriteHandler(String fileName) throws Exception{
		try{
			in = new BufferedReader(new FileReader(fileName));
			save = fileName;
		}catch(Exception e){}

	}

	public ArrayList<CounterModel> readIn() throws IOException{
		
		// erase bidirectional buffer
		counterModelsBuffer = new ArrayList<CounterModel>(); 
		String line = in.readLine();
		while(line != null){
			counterModelsBuffer.add(
					gson.fromJson(in, CounterModel.class)
					);
			line = in.readLine();
		}
		
		return counterModelsBuffer;
	}
	
	public void writeOut(ArrayList<CounterModel> writeBuffer) throws IOException{
		
		// erase bidirectional buffer
		counterModelsBuffer = new ArrayList<CounterModel>();
		Writer writer = new FileWriter(save);
		for(int i = 0; i < writeBuffer.size(); i++){
			gson.toJson(writeBuffer.get(i), writer);
		}
		writer.close();
		
	}
	

}
