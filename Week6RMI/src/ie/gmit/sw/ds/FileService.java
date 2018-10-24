package ie.gmit.sw.ds;

import java.rmi.*;
import java.util.List; 

public interface FileService extends Remote{
	
	public byte[] getFile(String Filename) throws RemoteException;
	
	public List<String> getFileNames() throws RemoteException;
	
	public void uploadFile(String fileName, byte[] fileContents) throws RemoteException;
	
}
