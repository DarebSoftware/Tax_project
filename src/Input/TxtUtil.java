package Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Scanner;
import InfoManager.Company;
import InfoManager.Receipt;
import InfoManager.TaxPayer;


public class TxtUtil{
	private static String PATH=("C:\\Users\\xontrh\\Desktop\\");
	private static String suffix = ".txt";
	
	
	
	public static TaxPayer loadInfo(String AFM){
		Scanner inputReader = null;
		TaxPayer taxpayer = new TaxPayer();
		try{
			inputReader = new Scanner(new FileInputStream(PATH+AFM+"_INFO"+suffix));
		}
		catch(FileNotFoundException e){
			System.out.println("File AFM__INFO not found");
		}
		String line = null;
		
		line = inputReader.nextLine();
		line = line.substring(line.lastIndexOf(": ")+2);
		taxpayer.setName(line);
		System.out.println("name: "+taxpayer.getName());
		line = inputReader.nextLine();
		line = line.substring(line.lastIndexOf(": ")+2);
		taxpayer.setAfm(Integer.parseInt(line));
		
		line = inputReader.nextLine();
		line = line.substring(line.lastIndexOf(": ")+2);
		taxpayer.setMaritalStatus(line);
		
		line = inputReader.nextLine();
		line = line.substring(line.lastIndexOf(": ")+2);
		taxpayer.setIncome(Double.parseDouble(line));
		
		while(inputReader.hasNextLine()){
			line = inputReader.nextLine();
			if(line.contains("Receipt ID: ")){
				Receipt r = new Receipt();
				line = line.substring(line.lastIndexOf(": ")+2);
				r.setReceiptId(Integer.parseInt(line));
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				r.setDate(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				r.setKind(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				r.setAmount(Double.parseDouble(line));
				
				Company c = new Company();
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				c.setName(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				c.setCountry(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				c.setCity(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				c.setStreet(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				c.setNumber(Integer.parseInt(line));
				
				r.setCompany(c);
				taxpayer.addReceiptToArray(r);
			}	
		}
		inputReader.close();
		return taxpayer;
	}
	
	
	public static void writeReceipt2File(Receipt newReceipt,String AFM){
		FileOutputStream outputStream = null;
		try
		{
			outputStream =new FileOutputStream(PATH+AFM+"_INFO"+suffix,true);
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);
		}
		
		
		PrintWriter outputWriter = new PrintWriter(outputStream);
		
		outputWriter.println("Receipt ID: " + newReceipt.getReceiptId());
		outputWriter.println("Date: " + newReceipt.getDate());
		outputWriter.println("Kind: " + newReceipt.getKind());
		outputWriter.println("Amount: " + newReceipt.getAmount());
		outputWriter.println("Company: " + newReceipt.getCompany().getName());
		outputWriter.println("Country: " + newReceipt.getCompany().getCountry());
		outputWriter.println("City: " + newReceipt.getCompany().getCity());
		outputWriter.println("Street: " + newReceipt.getCompany().getStreet());
		outputWriter.println("Number: " + newReceipt.getCompany().getNumber());
		outputWriter.close();
	}
	
	public static void deleteReceipt(int receiptId,String AFM){
		
		FileOutputStream outputStream = null;
		Scanner inputReader = null;
		
		try
		{
			outputStream =new FileOutputStream(PATH+AFM+"_IN"+suffix,true);
			inputReader = new Scanner(new FileInputStream(PATH+AFM+"_INFO"+suffix));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);
		}
		PrintWriter outputWriter = new PrintWriter(outputStream);
		String line = null;
		while(inputReader.hasNextLine()){
			line = inputReader.nextLine();
			if(line.contains("Receipt ID:")&&line.endsWith(Integer.toString(receiptId))){
				line = inputReader.nextLine();
				while(!line.contains("Receipt ID:")&&inputReader.hasNextLine()){
					line = inputReader.nextLine();
					if(line.contains("Receipt ID:")){
						outputWriter.println(line);
					}
				}
			}
			else{
				outputWriter.println(line);
			}
		}
		inputReader.close();
		outputWriter.close();
		moveFromFile2File(AFM+"_IN"+suffix,AFM+"_INFO"+suffix);
		
	}
	
	public  static void moveFromFile2File(String source,String destination){
		FileOutputStream outputStream = null;
		Scanner inputReader = null;
		
		try
		{
			
			outputStream =new FileOutputStream(PATH+destination);
			inputReader = new Scanner(new FileInputStream(PATH+source));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);
		}
		PrintWriter outputWriter = new PrintWriter(outputStream);
		String line = null;
		while(inputReader.hasNextLine()){
			line = inputReader.nextLine();
			outputWriter.println(line);
			
		}
		
		inputReader.close();
		outputWriter.close();
		try {
			Files.delete(FileSystems.getDefault().getPath(PATH+source));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFile(String afm){
		try {
			Files.delete(FileSystems.getDefault().getPath(PATH+afm+"_INFO.txt"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveTaxPayerInfo(TaxPayer tp){
		tp.calculateDifferentReceiptsNumbers();
		FileOutputStream outputStream = null;
		try
		{
			outputStream =new FileOutputStream(PATH+tp.getAfm()+"_LOG"+suffix,false);
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);
		}
		
		
		PrintWriter outputWriter = new PrintWriter(outputStream);
		
		outputWriter.println("Name: " + tp.getName());
		outputWriter.println("AFM: " + tp.getAfm());
		outputWriter.println("Basic Tax: " + tp.calculateTax());
		outputWriter.println("Tax Increase: " + Double.toString(tp.calculateFinalTax()-tp.calculateTax()));
		outputWriter.println("Total Tax: " + tp.calculateFinalTax());
		outputWriter.println("TotalReceiptsGathered: " + tp.getTotalReceiptGathered());
		outputWriter.println("Entertainment: " + tp.getEntertainmentReceiptsNumber());
		outputWriter.println("Basic: " + tp.getBasicReceiptsNumber());
		outputWriter.println("Travel: " + tp.getTravelReceiptsNumber());
		outputWriter.println("Health: " + tp.getHealthReceiptsNumber());
		outputWriter.println("Other: " + tp.getOtherReceiptsNumber());
		outputWriter.close();
	}
}
