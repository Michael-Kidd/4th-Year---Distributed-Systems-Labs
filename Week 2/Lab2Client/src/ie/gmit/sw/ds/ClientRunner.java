package ie.gmit.sw.ds;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
	      
	      OutputStream out = socket.getOutputStream();
	      
	      ObjectOutputStream oout = new ObjectOutputStream(out);
	      
	      oout.writeObject("xml");
	      
	      oout.close();

	      socket.close();
	      
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    
	  }

}
