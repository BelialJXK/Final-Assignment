import java.io.*;
import java.net.*;
import java.util.*;

public class customer {
	public static void main(String[] args) throws Exception {
		//show the choose :
		//booking(get chain from restaurant,sace as text file) 

		while(true){	
			System.out.println("input the number which you want:1.booking 2.exit");
			Scanner scanner = new Scanner(System.in);
			String text =scanner.nextLine();
			//System.out.println(text);

		
			switch (text) {
			case "1":	
				//customer booking a table 
				try {
					//connection				
					Socket s = new Socket("localhost", 12345);
					PrintWriter print = new PrintWriter(s.getOutputStream());
					print.println("booking");
					print.flush();
					//get reply
					Scanner scanner1 = new Scanner(s.getInputStream());
			        String text1 = scanner1.nextLine();
			        System.out.println(text1);
			        //write name 
			        Scanner writeNmae = new Scanner(System.in);
					String customer_name =writeNmae.nextLine();
					PrintWriter send_name = new PrintWriter(s.getOutputStream());
					print.println(customer_name);
					print.flush();
			       	//wait for chain
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					table t1=(table) ois.readObject();
					//System.out.println(t1);
					System.out.println("booking success,table number: "+t1.getNumber()+" name: "+t1.getName()+" Date: "+t1.getDate());
			        s.close();
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case "2":	
				//customer exit		
				System.exit(0);
				break;	
			}
		}
	}
}
		