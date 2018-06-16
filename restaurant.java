import java.io.*;
import java.net.*;
import java.util.*;
class restaurant{
	public static void main(String[] args) throws Exception {
		ArrayList<table> tl=new ArrayList<table>();
		int nt=1;
		 //suppose the table is enough to booking

		 
		 try{
		 	ServerSocket ss = new ServerSocket(12345);
		 	while(true){
		 		Socket s = ss.accept();
		 		// System.out.println(nt);
		 		// if(tl.size()!=0){
		 		// 	System.out.println(tl.get(nt-2).toString());	
		 		// }
		 		ServerThread thread = new ServerThread(s,tl,nt);
		 		thread.start();
		 		thread.join();
		 		nt=thread.number_table;
		 		tl=thread.tlist;
		 		thread.interrupt();
		 		// System.out.println(nt);
		 	}
		 }catch(Exception e){
       		System.out.println(e);
     	 }
    } 	 
}

class ServerThread extends Thread{
    Socket s;
	ArrayList<table> tlist=new ArrayList<table>();
	int number_table;
    public ServerThread(Socket s,ArrayList<table> tlist,int number_table){
        this.s = s;
        this.tlist=tlist;
        this.number_table=number_table;
    }		
    public void run(){
		 	try{       	

	        	Scanner scanner = new Scanner(s.getInputStream());
		        String text = scanner.nextLine();

		        
		        	
		        PrintWriter print = new PrintWriter(s.getOutputStream());
				print.println("please write your name.");
				print.flush();

				//get name
				Scanner getName = new Scanner(s.getInputStream());
		        String Cname = getName.nextLine();
		        //set table
		        table c ;
		        if(tlist.size()==0){	        	
		        	c=new table(Cname,"0",number_table);
		        	number_table++;
	    	 		tlist.add(c);
		        }else{
		        	c =new table(Cname,tlist.get(number_table-2).getHash(),number_table);
					number_table++;
	    	 		tlist.add(c);
		        }	        		
		        //send booking information to customer
		        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		        oos.writeObject(c);
		        		        		
		        System.out.println(tlist.get(number_table-2).toString());
	    	 	s.close();		        		
	        }catch(Exception e){
	        	System.out.println(e);
	     	}
	    
    }	
}		 