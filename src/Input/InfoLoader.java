package Input;
import InfoManager.Receipt;
import InfoManager.TaxPayer;


public class InfoLoader {	

	//constructors
	public InfoLoader(){
	}
	
	public TaxPayer loadInfo(String AFM){
		return TxtUtil.loadInfo(AFM);
	}
	
	
	public void addReceipt(Receipt r,String afm){
		 TxtUtil.writeReceipt2File(r,afm);
	}
	
	
	public void deleteReceipt(Receipt r,String AFM){
		TxtUtil.deleteReceipt(r.getReceiptId(), AFM);
	}
}
