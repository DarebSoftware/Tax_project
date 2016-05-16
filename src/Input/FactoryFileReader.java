package Input;

import InfoManager.Receipt;
import InfoManager.TaxPayer;

public interface FactoryFileReader {
	public TaxPayer loadInfo(String AFM);
	public void writeReceipt2File(Receipt newReceipt,String AFM);
	public void deleteReceipt(int receiptId,String AFM);
	public void moveFromFile2File(String source,String destination);
	public void deleteFile(String afm);
}
