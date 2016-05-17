package InfoManager;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

import Input.FactoryFileReader;
import Input.TaxPayerInfoLoader;
import Input.TxtReader;
import Input.XmlReader;

public class TaxPayerListManager {
	private static ArrayList<TaxPayer> taxPayerArray;


	public TaxPayerListManager(){
		taxPayerArray=new ArrayList<TaxPayer>();
	}
	
	public DefaultListModel<String> createTaxPayerListModel(){
		DefaultListModel<String> taxPayerList=new DefaultListModel<String>();
		for(int i =0; i<taxPayerArray.size(); i++){
			taxPayerList.addElement(taxPayerArray.get(i).getOverview());
		}
		return taxPayerList;
	}
	
	public void addTaxPayer(TaxPayer taxPayer){
		taxPayerArray.add(taxPayer);
	}
	public void importTaxPayer(String afm){
		//kappa keppo
		FactoryFileReader fileReader;
		TaxPayerInfoLoader infoLoader = new TaxPayerInfoLoader();
		System.out.println(FactoryFileReader.guessSuffix(afm));
		if(FactoryFileReader.guessSuffix(afm).equals("txt")){
			fileReader = new TxtReader();
			infoLoader.setFileReader(fileReader);
		}else if(FactoryFileReader.guessSuffix(afm).equals("xml")){
			fileReader = new XmlReader();
			infoLoader.setFileReader(fileReader);
		}
		addTaxPayer(infoLoader.loadTaxPayer(afm));
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
		for(int i=0; i<taxPayerArray.size(); i++){
			if(taxPayerArray.get(i).getAfm() == afm_int){
				taxPayerArray.remove(i);
			}
		}
	}
}
