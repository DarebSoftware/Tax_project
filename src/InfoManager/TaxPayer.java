package InfoManager;

import java.util.*;

import Input.FactoryFileReader;
import Input.TaxPayerInfoLoader;

public class TaxPayer {
	private String name;
	private int afm;
	private String maritalStatus;
	private double income;
	ArrayList<Receipt> receiptsArray;
	private double tax;
	private double finalTax;
	private double entertainmentReceiptsNumber;
	private double basicReceiptsNumber;
	private double travelReceiptsNumber;
	private double healthReceiptsNumber;
	private double otherReceiptsNumber;
	private TaxPayerInfoLoader taxPayerInfoLoader;
	private HashMap<String,double[]> taxParameters;

	public TaxPayer(FactoryFileReader fileReader){
		 receiptsArray = new ArrayList<Receipt>();
		 taxPayerInfoLoader = new TaxPayerInfoLoader(fileReader);
		 configTaxParameters();
	}

	private void configTaxParameters(){
		taxParameters = new HashMap<>();
		taxParameters.put("MarriedFilingJointly", new double[]{1930.28,5731.64,9492.82,18197.69,36080,90000,143350,254240,5.35,7.05,7.05,7.85,9.85});
		taxParameters.put("MarriedFilingSeparately", new double[]{965.14,4746.76,6184.88,9098.80,18040,71680,90000,127120,5.35,7.05,7.85,7.85,9.85});
		taxParameters.put("Single", new double[]{1320.38,5296.58,5996.80,10906.19,24680,81080,90000,152540,5.35,7.05,7.85,7.85,9.85});
		taxParameters.put("MarriedHeadofHousehold", new double[]{1625.87,5828.38,8092.13,14472.61,30390,90000,122110,203390,5.35,7.05,7.05,7.85,9.85});
	}
	
	public String getOverview(){
		return "Name :"+name + " AFM : "+afm;
	}
	
	public void calculateDifferentReceiptsNumbers(){
	  resetReceiptcounter();
	  for(int i=0; i<receiptsArray.size(); i++){
			if(receiptsArray.get(i).getKind().equals("Entertainment")){
				entertainmentReceiptsNumber += receiptsArray.get(i).getAmount();
			}else if(receiptsArray.get(i).getKind().equals("Basic")){
				basicReceiptsNumber += receiptsArray.get(i).getAmount();
			}else if(receiptsArray.get(i).getKind().equals("Travel")){
				travelReceiptsNumber += receiptsArray.get(i).getAmount();
			}else if(receiptsArray.get(i).getKind().equals("Health")){
				healthReceiptsNumber += receiptsArray.get(i).getAmount();
			}else if(receiptsArray.get(i).getKind().equals("Other")){
				otherReceiptsNumber += receiptsArray.get(i).getAmount();
			}else{
				System.out.println("uknown kind "+ receiptsArray.get(i).getKind());
			}
		}
	}
	
	public void resetReceiptcounter(){
	  entertainmentReceiptsNumber = 0;
    basicReceiptsNumber = 0;
    travelReceiptsNumber = 0;
    healthReceiptsNumber = 0;
    otherReceiptsNumber = 0;
	}

	public void addReceipt(Receipt receipt){
		receiptsArray.add(receipt);
		calculateDifferentReceiptsNumbers();
		taxPayerInfoLoader.addReceipt(receipt,Integer.toString(afm));
	}
	
	public void addReceiptToArray(Receipt receipt){
		receiptsArray.add(receipt);
		calculateDifferentReceiptsNumbers();
	}
	
	public int findReceipt(int receiptId){
	  for(int i=0; i<receiptsArray.size(); i++){
      if(receiptsArray.get(i).getReceiptId()==receiptId){
        return i;
      }
	  }
    return -1;
	}
	
	public void deleteReceipt(int receiptId){
		Receipt receipt=null;
		int receiptPosition = findReceipt(receiptId);
		receipt=receiptsArray.get(receiptPosition);
    receiptsArray.remove(receiptPosition);
		calculateDifferentReceiptsNumbers();
		if(receipt!=null) taxPayerInfoLoader.deleteReceipt(receipt, Integer.toString(afm));
		else System.out.println("Id "+ receiptId+" not found");
	}
	
	public double calculateFinalTax(){
		double finalTax = 0;
		double receiptMoney = calculateReceiptsMoney();
		double tax = calculateTax();
		if(receiptMoney>=0 && receiptMoney<(20/100)*income){
			finalTax = tax + 0.08*tax;
		}else if(receiptMoney < (40/100)*income){
			finalTax = tax + 0.04*tax;
		}else if(receiptMoney < (60/100)*income){
			finalTax = tax - 0.15*tax;
		}else{
			finalTax = tax - 0.30*tax;
		}
		return finalTax;
	}
	
	public double calculateTax(){
		if(maritalStatus.contains("Single")){
			return calculateSingleTax();
		}else if(maritalStatus.contains("Married Filing Jointly")){
			return calculateMarriedFilingJointlyTax();
		}else if(maritalStatus.contains("Married Filing Separately")){
			return calculateMarriedFilingSeparatelyTax();
		}else if(maritalStatus.contains("Head of Household")){
			return calculateHeadofHouseholdTax();
		}
		return -1;
	}
	
	public double calculateReceiptsMoney(){
		double receiptsMoney = 0;
		for(int i=0;i<receiptsArray.size();i++){
			receiptsMoney+=receiptsArray.get(i).getAmount();
		}
		return receiptsMoney;
	}
	
	public double calculateTaxByKind(String kind){
		double tax=0;
		if(income >= 0 && income < taxParameters.get(kind)[4]){
			tax = (taxParameters.get(kind)[8]/100)* income;
		}else if(income < taxParameters.get(kind)[5]){
			tax = taxParameters.get(kind)[0] + ((taxParameters.get(kind)[9]/100)*(income-taxParameters.get(kind)[4]));
		}else if(income < taxParameters.get(kind)[6]){
			tax = taxParameters.get(kind)[1]+ ((taxParameters.get(kind)[10]/100)*(income-taxParameters.get(kind)[5]));
		}else if(income < taxParameters.get(kind)[7]){
			tax = taxParameters.get(kind)[2] + ((taxParameters.get(kind)[11]/100)*(income-taxParameters.get(kind)[6]));
		}else{
			tax = taxParameters.get(kind)[3] + ((taxParameters.get(kind)[12]/100)*(income-taxParameters.get(kind)[7]));
		}
		return tax;
	}
	
	public double calculateMarriedFilingJointlyTax(){
		return calculateTaxByKind("MarriedFilingJointly");
	}
	
	public double calculateMarriedFilingSeparatelyTax(){
		return calculateTaxByKind("MarriedFilingSeparately");
	}
	
	public double calculateSingleTax(){
		return calculateTaxByKind("Single");
	}
	
	public double calculateHeadofHouseholdTax(){
		return calculateTaxByKind("HeadofHousehold");
	}
	
	public ArrayList<Receipt> getReceiptsArray(){
		return receiptsArray;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAfm() {
		return afm;
	}
	public void setAfm(int afm) {
		this.afm = afm;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getTax() {
		return tax;
	}
	public double getFinalTax() {
		return finalTax;
	}
	public void setReceiptsArray(ArrayList<Receipt> receiptsArray) {
		this.receiptsArray = receiptsArray;
	}
	public double getEntertainmentReceiptsNumber() {
		return entertainmentReceiptsNumber;
	}
	public double getBasicReceiptsNumber() {
		return basicReceiptsNumber;
	}
	public double getTravelReceiptsNumber() {
		return travelReceiptsNumber;
	}
	public double getHealthReceiptsNumber() {
		return healthReceiptsNumber;
	}
	public double getOtherReceiptsNumber() {
		return otherReceiptsNumber;
	}
	public double getTotalReceiptGathered(){
		return entertainmentReceiptsNumber+basicReceiptsNumber
		    +travelReceiptsNumber+healthReceiptsNumber
		    +otherReceiptsNumber;
	}
	public double getTaxIncrease(){
		return calculateFinalTax()-calculateTax();
	}
}