package ie.gmit.sw.ds;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class FileServiceSetup {
	
	public static void main(String[] args) {
		
		try {
			
			FileService fs = new FileServiceImpl();
			
			LocateRegistry.createRegistry(1099);
			Naming.rebind("fileService", fs);
			
			System.out.println("Server ready.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
}
