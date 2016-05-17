package Input;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import InfoManager.Company;
import InfoManager.Receipt;
import InfoManager.TaxPayer;

public class XmlReader implements FactoryFileReader{
	private final String suffix = ".xml";

	private Document doc;
	@Override
	public TaxPayer loadInfo(String AFM) {
		initializeFileInput(AFM);
		TaxPayer taxpayer = new TaxPayer(this);
		taxpayer.setAfm(Integer.parseInt(AFM));
		Element taxPayerElement= (Element)doc.getElementsByTagName("Taxpayer").item(0);
		
		taxpayer.setName(taxPayerElement.getElementsByTagName("Name").item(0).getTextContent());
		
		taxpayer.setMaritalStatus(taxPayerElement.getElementsByTagName("Status").item(0).getTextContent());
		
		String tempIncome = taxPayerElement.getElementsByTagName("Income").item(0).getTextContent();
		taxpayer.setIncome(Integer.parseInt(tempIncome.replaceAll(" ", "")));
		
		Element receiptsElement = (Element)taxPayerElement.getElementsByTagName("Receipts").item(0);
		
		NodeList receiptIdList = receiptsElement.getElementsByTagName("ReceiptID");
		NodeList dateList =  receiptsElement.getElementsByTagName("Date");
		NodeList kindList =  receiptsElement.getElementsByTagName("Kind");
		NodeList amountList =  receiptsElement.getElementsByTagName("Amount");
		NodeList companyList =  receiptsElement.getElementsByTagName("Company");
		NodeList countryList =  receiptsElement.getElementsByTagName("Country");
		NodeList cityList =  receiptsElement.getElementsByTagName("City");
		NodeList streetList =  receiptsElement.getElementsByTagName("Street");
		NodeList numberList =  receiptsElement.getElementsByTagName("Number");
		for (int i = 0; i < receiptIdList.getLength(); i++){
			Receipt receipt = new Receipt();
			
			String tempId=receiptIdList.item(i).getTextContent();
			receipt.setReceiptId(Integer.parseInt(tempId.replaceAll(" ", "")));
			
			receipt.setDate(dateList.item(i).getTextContent());
			receipt.setKind(kindList.item(i).getTextContent());
			
			String tempAmount = amountList.item(i).getTextContent();
			receipt.setAmount(Double.parseDouble(tempAmount.replaceAll(" ", "")));
			
			Company company = new Company();
			company.setName(companyList.item(i).getTextContent());
			company.setCountry(countryList.item(i).getTextContent());
			company.setCity(cityList.item(i).getTextContent());
			company.setStreet(streetList.item(i).getTextContent());
			
			String tempNumber = numberList.item(i).getTextContent();
			company.setNumber(Integer.parseInt(tempNumber.replaceAll(" ", "")));
			receipt.setCompany(company);
			taxpayer.addReceiptToArray(receipt);
		}
		
		return taxpayer;
	}

	@Override
	public void writeReceipt2File(Receipt newReceipt, String AFM) {		
		if(doc ==null) initializeFileInput(AFM);
		try {
			Element taxPayerElement= (Element)doc.getElementsByTagName("Taxpayer").item(0);
			Element receiptsElement = (Element)taxPayerElement.getElementsByTagName("Receipts").item(0);
			
			Element receiptIdElememt = doc.createElement("ReceiptID");
			receiptIdElememt.appendChild(doc.createTextNode(""+newReceipt.getReceiptId()));
			receiptsElement.appendChild(receiptIdElememt);
			
			Element dataElememt = doc.createElement("Date");
			dataElememt.appendChild(doc.createTextNode(newReceipt.getDate()));
			receiptsElement.appendChild(dataElememt);
			
			Element kindElememt = doc.createElement("Kind");
			kindElememt.appendChild(doc.createTextNode(newReceipt.getKind()));
			receiptsElement.appendChild(kindElememt);
			
			Element amountElememt = doc.createElement("Amount");
			amountElememt.appendChild(doc.createTextNode(""+newReceipt.getAmount()));
			receiptsElement.appendChild(amountElememt);
			
			Element companyElememt = doc.createElement("Company");
			companyElememt.appendChild(doc.createTextNode(newReceipt.getCompany().getName()));
			receiptsElement.appendChild(companyElememt);
			
			Element countryElememt = doc.createElement("Country");
			countryElememt.appendChild(doc.createTextNode(newReceipt.getCompany().getCountry()));
			receiptsElement.appendChild(countryElememt);
			
			Element cityElememt = doc.createElement("City");
			cityElememt.appendChild(doc.createTextNode(newReceipt.getCompany().getCity()));
			receiptsElement.appendChild(cityElememt);
			
			Element streetElememt = doc.createElement("Street");
			streetElememt.appendChild(doc.createTextNode(newReceipt.getCompany().getStreet()));
			receiptsElement.appendChild(streetElememt);
			
			Element numberElememt = doc.createElement("Number");
			numberElememt.appendChild(doc.createTextNode(""+newReceipt.getCompany().getNumber()));
			receiptsElement.appendChild(numberElememt);
			
			// write the content into xml file
	        TransformerFactory transformerFactory =
	        TransformerFactory.newInstance();
	        Transformer transformer =
	        transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result =
	        new StreamResult(new File(PATH+AFM+"_INFO"+suffix));
	        transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteReceipt(int receiptId, String AFM) {
		try {
			Element taxPayerElement= (Element)doc.getElementsByTagName("Taxpayer").item(0);
			Element receiptsElement = (Element)taxPayerElement.getElementsByTagName("Receipts").item(0);
			NodeList receiptIdList = receiptsElement.getElementsByTagName("ReceiptID");
			NodeList dateList =  receiptsElement.getElementsByTagName("Date");
			NodeList kindList =  receiptsElement.getElementsByTagName("Kind");
			NodeList amountList =  receiptsElement.getElementsByTagName("Amount");
			NodeList companyList =  receiptsElement.getElementsByTagName("Company");
			NodeList countryList =  receiptsElement.getElementsByTagName("Country");
			NodeList cityList =  receiptsElement.getElementsByTagName("City");
			NodeList streetList =  receiptsElement.getElementsByTagName("Street");
			NodeList numberList =  receiptsElement.getElementsByTagName("Number");
			for(int i=0;i<receiptIdList.getLength(); i++){
				if(receiptIdList.item(i).getTextContent().contains(""+receiptId)){
					receiptsElement.removeChild(receiptIdList.item(i));
					receiptsElement.removeChild(dateList.item(i));
					receiptsElement.removeChild(kindList.item(i));
					receiptsElement.removeChild(amountList.item(i));
					receiptsElement.removeChild(companyList.item(i));
					receiptsElement.removeChild(countryList.item(i));
					receiptsElement.removeChild(cityList.item(i));
					receiptsElement.removeChild(streetList.item(i));
					receiptsElement.removeChild(numberList.item(i));
				}
			}
				
			// write the content into xml file
	        TransformerFactory transformerFactory =
	        TransformerFactory.newInstance();
	        Transformer transformer =
	        transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result =
	        new StreamResult(new File(PATH+AFM+"_INFO"+suffix));
	        transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFile(String afm) {
		// TODO Auto-generated method stub
		
	}
	
	public void initializeFileInput(String AFM){
		File inputFile = new File(PATH+AFM+"_INFO"+suffix);
        DocumentBuilderFactory dbFactory 
            =DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Check your xml format.");
		}
	}
}
