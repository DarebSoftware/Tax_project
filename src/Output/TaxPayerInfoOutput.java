package Output;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import InfoManager.TaxPayer;


public class TaxPayerInfoOutput {
	public TaxPayerInfoOutput(){
		
	}
	
	public void saveTaxPayerInfo(TaxPayer taxPayer,FactoryFileWriter fileWriter){
		//calculate need numbers for the log
		taxPayer.calculateDifferentReceiptsNumbers();
		fileWriter.saveTaxPayerInfo(taxPayer);
	}
	
	public void createPieChart(TaxPayer taxPayer){
		DefaultPieDataset objDataset = createPieDataset(taxPayer);
		JFreeChart objChart = ChartFactory.createPieChart(
			    "Sxhma pie chart",   //Chart title
			    objDataset,          //Chart Data 
			    true,               // include legend?
			    true,               // include tooltips?
			    false               // include URLs?
			    );
		ChartFrame frame = new ChartFrame("Pie chart", objChart);
		frame.pack();
		frame.setVisible(true);}	
	
	public DefaultPieDataset createPieDataset(TaxPayer taxPayer){
	  DefaultPieDataset objDataset = new DefaultPieDataset();
    objDataset.setValue("Entertainment",taxPayer.getEntertainmentReceiptsNumber());
    objDataset.setValue("Basic",taxPayer.getBasicReceiptsNumber());
    objDataset.setValue("Travel",taxPayer.getTravelReceiptsNumber());
    objDataset.setValue("Health",taxPayer.getHealthReceiptsNumber());
    objDataset.setValue("Other",taxPayer.getOtherReceiptsNumber());
    return objDataset;
	}
	
	public void createBarChart(TaxPayer taxPayer){
		DefaultCategoryDataset objDataset = new DefaultCategoryDataset();
		objDataset.setValue(taxPayer.calculateTax(),"Tax analysis in $","Basic Tax");
		objDataset.setValue(taxPayer.getTaxIncrease(),"Tax analysis in $","Tax increase");
		objDataset.setValue(taxPayer.calculateFinalTax(),"Tax analysis in $","Total Tax");
		
		JFreeChart objChart = ChartFactory.createBarChart(
			       "Bar Chart",     //Chart title
			    " ",     //Domain axis label
			    "Tax analysis in $",         //Range axis label
			    objDataset,         //Chart Data 
			    PlotOrientation.VERTICAL, // orientation
			    true,             // include legend?
			    true,             // include tooltips?
			    false             // include URLs?
			);
		ChartFrame frame = new ChartFrame("Bar chart", objChart);
		frame.pack();
		frame.setVisible(true);
	}
}
