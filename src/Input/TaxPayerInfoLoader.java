package Input;
import InfoManager.Receipt;
import InfoManager.TaxPayer;

public class TaxPayerInfoLoader {	

	public TaxPayerInfoLoader(){
	}
	
	public TaxPayer loadInfo(String AFM){
		return TxtUtil.loadInfo(AFM);
	}
	
	public void addReceipt(Receipt receipt,String afm){
		 TxtUtil.writeReceipt2File(receipt,afm);
	}
	
	public void deleteReceipt(Receipt receipt,String AFM){
		TxtUtil.deleteReceipt(receipt.getReceiptId(), AFM);
	}
}
