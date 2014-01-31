package cs.aaclark.droidcounter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class CounterModel implements Serializable {
	
	private String name;
	private int count;
	private Date modified;
	
	//Init
	public CounterModel(String newName, int init){
		this.name = newName;
		this.count = init;
		this.modified = new Date();
	}
	
	// Always WriteOut and ReadIn in same order!
	private void writeObject(java.io.ObjectOutputStream out) throws IOException{
		out.writeUTF(name);		
		out.writeInt(count);
		out.writeLong(modified.getTime());
		
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException{
		name = in.readUTF();
		count = in.readInt();
		modified = new Date(in.readLong());
		
	}
	
	

}