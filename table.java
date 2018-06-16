import java.io.*;
import java.util.Date;
import java.util.*;
public class table implements Serializable{
	private String name;
	private String previousHash;
	private String hash;
	private Date timeStamp;
	private int number;
	public table(){}
	public table(String name,String previousHash,int number_table){
		this.name = name;
		this.previousHash = previousHash;
		this.timeStamp = new Date();
		this.hash = calculateHash();
		this.number=number_table;
	}
	public String calculateHash(){
		String calculatedhash = StringUtil.applySha256( 
			previousHash +
			timeStamp.toString() +
			name 
			);
		return calculatedhash;
	}

	public String getPrevious_hash(){
			return this.previousHash;
	}
	public String getHash(){
		return this.hash;
	}
	public String getName(){
		return this.name;
	}
	public Date getDate(){
		return this.timeStamp;
	}
	public int getNumber(){
		return number;
	}

	@Override
	public String toString(){
		return "Table number: "+number+" timeStamp: "+timeStamp+"  name: "+name+" previous_hash: "+previousHash;
	}
}