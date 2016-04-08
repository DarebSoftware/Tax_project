package Output;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import InfoManager.TaxPayer;
import Input.TxtUtil;

public class InfoOutput {
	
	public InfoOutput(){
	}
	
	public void saveTaxPayerInfo(TaxPayer tp){
		TxtUtil.saveTaxPayerInfo(tp);
	}
	
	public void createPieChart(TaxPayer tp){
		DefaultPieDataset objDataset = new DefaultPieDataset();
		objDataset.setValue("Entertainment",tp.getEntertainmentReceiptsNumber());
		objDataset.setValue("Basic",tp.getBasicReceiptsNumber());
		objDataset.setValue("Travel",tp.getTravelReceiptsNumber());
		objDataset.setValue("Health",tp.getHealthReceiptsNumber());
		objDataset.setValue("Other",tp.getOtherReceiptsNumber());
		JFreeChart objChart = ChartFactory.createPieChart (
			    "Sxhma pie chart",   //Chart title
			    objDataset,          //Chart Data 
			    true,               // include legend?
			    true,               // include tooltips?
			    false               // include URLs?
			    );
		ChartFrame frame = new ChartFrame("Pie chart", objChart);
		frame.pack();
		frame.setVisible(true);
	}	
	
	public void createBarChart(TaxPayer tp){
		DefaultCategoryDataset objDataset = new DefaultCategoryDataset();

		objDataset.setValue(tp.calculateTax(),"Tax analysis in $","Basic Tax");
		objDataset.setValue(tp.getTaxIncrease(),"Tax analysis in $","Tax increase");
		objDataset.setValue(tp.calculateFinalTax(),"Tax analysis in $","Total Tax");
		
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
