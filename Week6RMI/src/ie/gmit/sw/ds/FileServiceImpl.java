package ie.gmit.sw.ds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl extends UnicastRemoteObject implements FileService {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FileServiceImpl() throws RemoteException {
		super();
	}
	
	static List<String> fileNames = new ArrayList<String>();
	static String folderName = "./serverFiles/";
	
	public byte[] getFile(String fileName) {
		
		byte[] fileContent = null;
		
		try {
			
		    fileContent = Files.readAllBytes(new File(folderName+fileName).toPath());
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return fileContent;
		
	}
	
	public List<String> getFileNames() {
		
		fileNames.removeAll(fileNames);
		
		File file = new File(folderName);  
		File[] files = file.listFiles();
		
		for(File f: files) {
			fileNames.add(f.getName());
		}
		 
		return fileNames;
	}
	
	public void uploadFile(String fileName, byte[] fileContents) {
		
		try {
			
			FileOutputStream stream = new FileOutputStream(folderName+fileName);
			stream.write(fileContents);
			stream.close(); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
