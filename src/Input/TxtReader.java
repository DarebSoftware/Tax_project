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

public class TxtReader implements FactoryFileReader{
	private final String suffix = ".txt";
	
	public TaxPayer loadInfo(String AFM){
		Scanner inputReader = null;
		TaxPayer taxpayer = new TaxPayer(this);
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
				Receipt receipt = new Receipt();
				line = line.substring(line.lastIndexOf(": ")+2);
				receipt.setReceiptId(Integer.parseInt(line));
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				receipt.setDate(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				receipt.setKind(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				receipt.setAmount(Double.parseDouble(line));
				
				Company company = new Company();
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				company.setName(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				company.setCountry(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				company.setCity(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				company.setStreet(line);
				
				line = inputReader.nextLine();
				line = line.substring(line.lastIndexOf(": ")+2);
				company.setNumber(Integer.parseInt(line));
				
				receipt.setCompany(company);
				taxpayer.addReceiptToArray(receipt);
			}	
		}
		inputReader.close();
		return taxpayer;
	}
	
	public void writeReceipt2File(Receipt newReceipt,String AFM){
		FileOutputStream outputStream = null;
		try{
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
	
	public void deleteReceipt(int receiptId,String AFM){
		
		FileOutputStream outputStream = null;
		Scanner inputReader = null;
		
		try{
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
	
	public void moveFromFile2File(String source,String destination){
		FileOutputStream outputStream = null;
		Scanner inputReader = null;
		
		try{
			
			outputStream =new FileOutputStream(PATH+destination);
			inputReader = new Scanner(new FileInputStream(PATH+source));
		}catch (FileNotFoundException e){
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);}
		
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
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteFile(String afm){
		try {
			Files.delete(FileSystems.getDefault().getPath(PATH+afm+"_INFO.txt"));
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
