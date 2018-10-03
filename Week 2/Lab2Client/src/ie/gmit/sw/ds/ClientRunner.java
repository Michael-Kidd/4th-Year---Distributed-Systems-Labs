package ie.gmit.sw.ds;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientRunner {

	public static void main(String[] args) throws IOException {
		    
	    try
	    {
	      Socket socket = null;
	      
	      SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 5000);
	      socket = new Socket();

	      socket.connect(socketAddress);
	      
	      System.out.println("Connected to Server");

	      socket.close();
	      
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    
	  }

}
