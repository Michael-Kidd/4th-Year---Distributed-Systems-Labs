package ie.gmit.sw.ds;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

	      ServerSocket serverSocket = new ServerSocket(5000);

	      System.out.println("Listening on port 5000");
	      
	      while (System.in.available() == 0) {

	    	  Socket socket = serverSocket.accept();

	    	  System.out.println("A client has connected.");
	    	  
	    	  InputStream in = socket.getInputStream();
	    	  
	    	  ObjectInputStream oin = new ObjectInputStream(in);
	    	  
	    	  String stringFromClient = (String) oin.readObject();
	    	  
	    	  System.out.println(stringFromClient);
	    	  
	    	  in.close();

	          socket.close();

	       }

	      serverSocket.close();
		      
	}
}
