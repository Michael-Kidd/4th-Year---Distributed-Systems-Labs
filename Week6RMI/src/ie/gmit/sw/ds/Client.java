package ie.gmit.sw.ds;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


public class Client {
	
	static String folderName2 = "./clientFiles/";
	
	public static void main(String [] args) throws Exception {
		
		FileService fs = null;
		
		try {
			
			fs = (FileService) Naming.lookup("rmi://127.0.0.1:1099/fileService");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		List<String> files = fs.getFileNames();
		
		for (String file : files){
			
			String t = new String(file);
			
			System.out.println(t);
			
			byte[] fileContents = fs.getFile(file);
			String s = new String(fileContents);	
			
			System.out.println(s);
			
			fileContents = s.getBytes();
			
			writeToFile(fileContents, file);
			
			String str = "test3";
			
			fileContents = str.getBytes();
			
			fs.uploadFile("serverFile3.txt", fileContents);
			
		}
		
		
	}
	
	private static void writeToFile(byte[] fileContents, String fileName) {
		
		try {
			
			FileOutputStream stream = new FileOutputStream(folderName2+fileName);
			stream.write(fileContents);
			stream.close(); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
