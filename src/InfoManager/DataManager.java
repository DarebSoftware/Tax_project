package InfoManager;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Input.TxtUtil;

public class DataManager {
	/*
	 * parakalw o kwdika na paramameinei idiotikos exei pnevamtika dikaiomata
	 * mporei na xrisimoipihthei gia extreme hacking kai alla tetoia tou satana
	 */
	
	private ArrayList<TaxPayer> taxPayerArray;
	
	public DataManager(){
		taxPayerArray=new ArrayList<TaxPayer>();
		
		
	}
	
	public DefaultListModel<String> createListModel(){
		DefaultListModel<String> taxPayerList=new DefaultListModel<String>();
		for(int i =0;i<taxPayerArray.size();i++){
			taxPayerList.addElement(taxPayerArray.get(i).getOverview());
		}
		return taxPayerList;
	}
	public void addTaxPayer(TaxPayer tp){
		taxPayerArray.add(tp);
	}
	public ArrayList<TaxPayer> getTaxPayerArray() {
		return taxPayerArray;
	}

	public void setTaxPayerArray(ArrayList<TaxPayer> taxPayerArray) {
		this.taxPayerArray = taxPayerArray;
	}
	public TaxPayer getTaxPayer(int index){
		return taxPayerArray.get(index);
	}
	
	public void deleteTaxpayer(String afm){
		int afm_int=Integer.parseInt(afm);
		for(int i=0;i<taxPayerArray.size();i++){
			if(taxPayerArray.get(i).getAfm()==afm_int){
				taxPayerArray.remove(i);
			}
		}
		TxtUtil.deleteFile(afm);
	}
}
