package Output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import InfoManager.TaxPayer;

public class TxtWriter implements FactoryFileWriter {
	private static String suffix = ".txt";
	
	public void saveTaxPayerInfo(TaxPayer taxPayer){		
		FileOutputStream outputStream = null;
		
		try
		{
			outputStream =new FileOutputStream(PATH+taxPayer.getAfm()+"_LOG"+suffix,false);
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening the file stuff.txt.");
			System.exit(0);
		}
		PrintWriter outputWriter = new PrintWriter(outputStream);
		
		outputWriter.println("Name: " + taxPayer.getName());
		outputWriter.println("AFM: " + taxPayer.getAfm());
		outputWriter.println("Basic Tax: " + taxPayer.calculateTax());
		outputWriter.println("Tax Increase: " + Double.toString(taxPayer.calculateFinalTax()-taxPayer.calculateTax()));
		outputWriter.println("Total Tax: " + taxPayer.calculateFinalTax());
		outputWriter.println("TotalReceiptsGathered: " + taxPayer.getTotalReceiptGathered());
		outputWriter.println("Entertainment: " + taxPayer.getEntertainmentReceiptsNumber());
		outputWriter.println("Basic: " + taxPayer.getBasicReceiptsNumber());
		outputWriter.println("Travel: " + taxPayer.getTravelReceiptsNumber());
		outputWriter.println("Health: " + taxPayer.getHealthReceiptsNumber());
		outputWriter.println("Other: " + taxPayer.getOtherReceiptsNumber());
		outputWriter.close();
	}
}
