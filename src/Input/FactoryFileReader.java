package Input;

import java.io.File;

import InfoManager.Receipt;
import InfoManager.TaxPayer;

public interface FactoryFileReader {
	final String PATH=("C:\\Users\\pantzos\\Desktop\\");
	
	public TaxPayer loadInfo(String AFM);
	public void writeReceipt2File(Receipt newReceipt,String AFM);
	public void deleteReceipt(int receiptId,String AFM);
	public void deleteFile(String afm);
	
	
	public static String guessSuffix(String afm){
		File folder = new File(PATH);
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String fileName = listOfFiles[i].getName();
		    	  int indexOfDot = fileName.indexOf('.');
		    	  if(fileName.substring(0, indexOfDot).contains(afm+"_INFO")){
		    		  return fileName.substring(indexOfDot+1);
		    	  }
		      }
		    }
		return "";
	}
}
