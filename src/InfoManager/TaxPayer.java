package InfoManager;
import java.util.*;

import Input.InfoLoader;
public class TaxPayer {
	private String name;
	private int afm;
	private String maritalStatus;
	private double income;
	ArrayList<Receipt> receiptsArray;
	private double tax; //tax withought Receipts calculated
	private double finalTax;
	private double entertainmentReceiptsNumber;
	private double basicReceiptsNumber;
	private double travelReceiptsNumber;
	private double healthReceiptsNumber;
	private double otherReceiptsNumber;
	private InfoLoader infoLoader;
	
	
	//constructors
	public TaxPayer(){
		 receiptsArray = new ArrayList<Receipt>();
		 infoLoader = new InfoLoader();
	}

	public String getOverview(){
		return "Name :"+name + " AFM : "+afm;
	}
	public void calculateDifferentReceiptsNumbers(){
		entertainmentReceiptsNumber=0;
		basicReceiptsNumber=0;
		travelReceiptsNumber=0;
		healthReceiptsNumber=0;
		otherReceiptsNumber=0;
		for(int i=0;i<receiptsArray.size();i++){
			if(receiptsArray.get(i).getKind().equals("Entertainment")){
				entertainmentReceiptsNumber+=receiptsArray.get(i).getAmount();
			}
			else if(receiptsArray.get(i).getKind().equals("Basic")){
				basicReceiptsNumber+=receiptsArray.get(i).getAmount();
			}
			else if(receiptsArray.get(i).getKind().equals("Travel")){
				travelReceiptsNumber+=receiptsArray.get(i).getAmount();
			}
			else if(receiptsArray.get(i).getKind().equals("Health")){
				healthReceiptsNumber+=receiptsArray.get(i).getAmount();
			}
			else if(receiptsArray.get(i).getKind().equals("Other")){
				otherReceiptsNumber+=receiptsArray.get(i).getAmount();
			}
			else{
				System.out.println("uknown kind "+ receiptsArray.get(i).getKind());
			}
		}
	}

	
	// add and delete Receipts from ArrayList of Receipts
	public void addReceipt(Receipt r){
		receiptsArray.add(r);
		calculateDifferentReceiptsNumbers();
		infoLoader.addReceipt(r,Integer.toString(afm));
	}
	
	public void addReceiptToArray(Receipt r){
		receiptsArray.add(r);
		calculateDifferentReceiptsNumbers();
	}
	
	public void deleteReceipt(int ReceiptId){
		Receipt r=null;
		for(int i=0;i<receiptsArray.size();i++){
			if(receiptsArray.get(i).getReceiptId()==ReceiptId){
				r=receiptsArray.get(i);
				receiptsArray.remove(i);
				break;
			}
		}
		calculateDifferentReceiptsNumbers();
		if(r!=null) infoLoader.deleteReceipt(r, Integer.toString(afm));
		else System.out.println("Id "+ ReceiptId+" not found");
	}
	
	//calculates Final Tax with Receipts being counted
	public double calculateFinalTax(){
		double tax=0;
		double finalTax=0;
		double receiptMoney = 0;
		receiptMoney = calculateReceiptsMoney();
		tax=calculateTax();
		if(receiptMoney>=(0/100)*income&&receiptMoney<(20/100)*income){
			finalTax = tax+0.08*tax;
		}
		else if(receiptMoney>=(20/100)*income&&receiptMoney<(40/100)*income){
			finalTax = tax+0.04*tax;
		}
		else if(receiptMoney>=(40/100)*income&&receiptMoney<(60/100)*income){
			finalTax = tax+0.15*tax;
		}
		else if(receiptMoney>=(60/100)*income){
			finalTax = tax+0.30*tax;
		}
		return finalTax;
	}
	
	// calculates Tax withought Receipts being counted
	public double calculateTax(){
		
		if(maritalStatus.equals("Single")){
			return calculateSingleTax();
		}
		else if(maritalStatus.equals("Married Filling Jointly")){
			return calculateMarriedFillingJointlyTax();
		}
		
		else if(maritalStatus.equals("Married Filling Separately")){
			return calculateMarriedFillingSeparatelyTax();
		}
		else if(maritalStatus.equals("Head of Household")){
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
	
	//These methods are being used in calculateTax Method.
	public double calculateMarriedFillingJointlyTax(){
		double tax=0;
		if(income>=0&&income<36080){
			tax = (5.35/100)* income;
		}
		else if(income>=36080&&income<90000){
			tax = 1930.28 + ((7.05/100)*(income-36080));
		}
		else if(income>=90000&&income<143350){
			tax = 5731.64 + ((7.05/100)*(income-90000));
		}
		else if(income>=143350&&income<254240){
			tax = 9492.82 + ((7.85/100)*(income-143350));
		}
		else if(income>=254240){
			tax = 18197.69 + ((9.85/100)*(income-254240));
		}
		return tax;
	}
	
	public double calculateMarriedFillingSeparatelyTax(){
		
		double tax=0;
		if(income>=0&&income<18040){
			tax = (5.35/100)* income;
		}
		else if(income>=18040&&income<71680){
			tax = 965.14 + ((7.05/100)*(income-18040));
		}
		else if(income>=71680&&income<90000){
			tax = 4746.76 + ((7.85/100)*(income-71680));
		}
		else if(income>=90000&&income<127120){
			tax = 6184.88 + ((7.85/100)*(income-90000));
		}
		else if(income>=127120){
			tax = 9098.80 + ((9.85/100)*(income-127120));
		}
		return tax;
	}
	
	public double calculateSingleTax(){
		double tax=0;
		if(income>=0&&income<24680){
			tax = (5.35/100)* income;
		}
		else if(income>=24680&&income<81080){
			tax = 1320.38 + ((7.05/100)*(income-24680));
		}
		else if(income>=81080&&income<90000){
			tax = 5296.58 + ((7.85/100)*(income-81080));
		}
		else if(income>=90000&&income<152540){
			tax = 5996.80 + ((7.85/100)*(income-90000));
		}
		else if(income>=152540){
			tax = 10906.19 + ((9.85/100)*(income-152540));
		}
		return tax;
	}
	
	public double calculateHeadofHouseholdTax(){
		double tax=0;
		if(income>=0&&income<30390){
			tax = (5.35/100)* income;
		}
		else if(income>=30390&&income<90000){
			tax = 1625.87 + ((7.05/100)*(income-30390));
		}
		else if(income>=90000&&income<122110){
			tax = 5828.38 + ((7.05/100)*(income-90000));
		}
		else if(income>=122110&&income<203390){
			tax = 8092.13 + ((7.85/100)*(income-122110));
		}
		else if(income>=203390){
			tax = 14472.61 + ((9.85/100)*(income-203390));
		}
		return tax;
	}
	// Get and Set Methods
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
		public void setTax(double tax) {
			this.tax = tax;
		}
		public double getFinalTax() {
			return finalTax;
		}
		public void setFinalTax(double finalTax) {
			this.finalTax = finalTax;
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
			return entertainmentReceiptsNumber+basicReceiptsNumber+
					travelReceiptsNumber+healthReceiptsNumber+
					otherReceiptsNumber;
		}
		
		public double getTaxIncrease(){
			return calculateFinalTax()-calculateTax();
		}
		//END of Get and Set Methods
}
