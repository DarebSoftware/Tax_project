package Output;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import InfoManager.TaxPayer;

public class XmlWriter implements FactoryFileWriter {
	public final String suffix =".xml";

	@Override
	public void saveTaxPayerInfo(TaxPayer taxPayer) {
				try {
					DocumentBuilderFactory dbFactory =
					         DocumentBuilderFactory.newInstance();
					         DocumentBuilder dBuilder;
					dBuilder = dbFactory.newDocumentBuilder();
					 Document doc = dBuilder.newDocument();
					 
					 Element rootElement = doc.createElement("Log");
			         doc.appendChild(rootElement);
					 
			         Element nameEl = doc.createElement("Name");
			         nameEl.setTextContent(taxPayer.getName());
			         rootElement.appendChild(nameEl);
			         
			         Element afmEl = doc.createElement("AFM");
			         afmEl.setTextContent(""+taxPayer.getAfm());
			         rootElement.appendChild(afmEl);
			         
			         Element incomeEl = doc.createElement("Income");
			         incomeEl.setTextContent(""+taxPayer.getIncome());
			         rootElement.appendChild(incomeEl);
			         
			         Element basicEl = doc.createElement("BasicTax");
			         basicEl.setTextContent(""+taxPayer.calculateTax());
			         rootElement.appendChild(basicEl);
			         
			         Element incrEl = doc.createElement("TaxIncrease");
			         incrEl.setTextContent(""+taxPayer.getTaxIncrease());
			         rootElement.appendChild(incrEl);
			         
			         Element totalTaxEl = doc.createElement("TotalTax");
			         totalTaxEl.setTextContent(""+taxPayer.calculateFinalTax());
			         rootElement.appendChild(totalTaxEl);
			         
			         Element receiptsEl = doc.createElement("Receipts");
			         receiptsEl.setTextContent(""+taxPayer.getTotalReceiptGathered());
			         rootElement.appendChild(receiptsEl);
			         
			         Element enteEl = doc.createElement("Entertainment");
			         enteEl.setTextContent(""+taxPayer.getEntertainmentReceiptsNumber());
			         rootElement.appendChild(enteEl);
			         
			         Element basicRecEl = doc.createElement("Basic");
			         basicRecEl.setTextContent(""+taxPayer.getBasicReceiptsNumber());
			         rootElement.appendChild(basicRecEl);
			         
			         Element travelEl = doc.createElement("Travel");
			         travelEl.setTextContent(""+taxPayer.getTravelReceiptsNumber());
			         rootElement.appendChild(travelEl);
			         
			         Element healthEl = doc.createElement("Health");
			         healthEl.setTextContent(""+taxPayer.getHealthReceiptsNumber());
			         rootElement.appendChild(healthEl);
			        
			         Element otherEl = doc.createElement("Other");
			         otherEl.setTextContent(""+taxPayer.getOtherReceiptsNumber());
			         rootElement.appendChild(otherEl);
			         
					 
			         TransformerFactory transformerFactory =
			                 TransformerFactory.newInstance();
			                 Transformer transformer =
			                 transformerFactory.newTransformer();
			                 DOMSource source = new DOMSource(doc);
			                 StreamResult result =
			                 new StreamResult(new File(PATH+taxPayer.getAfm()+"_LOG"+suffix));
			                 transformer.transform(source, result);
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
	}

}
