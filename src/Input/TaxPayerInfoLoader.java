package Input;

import InfoManager.Receipt;
import InfoManager.TaxPayer;

public class TaxPayerInfoLoader{	
	
	public FactoryFileReader fileReader;

	public TaxPayerInfoLoader(FactoryFileReader fileReader){
		this.fileReader=fileReader;
	}
	
	public TaxPayerInfoLoader(){
		fileReader=new TxtReader();
	}
	
	public void setFileReader(FactoryFileReader fileReader){
		this.fileReader = fileReader;
	}
	
	
	public TaxPayer loadTaxPayer(String AFM){
		return fileReader.loadInfo(AFM);
	}
	public void addReceipt(Receipt receipt,String afm){
		fileReader.writeReceipt2File(receipt,afm);
	}
	
	public void deleteReceipt(Receipt receipt,String AFM){
	  fileReader.deleteReceipt(receipt.getReceiptId(), AFM);
	}
}
