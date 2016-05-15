package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import InfoManager.Company;
import InfoManager.TaxPayerListManager;
import InfoManager.Receipt;
import InfoManager.TaxPayer;
import Input.TaxPayerInfoLoader;
import Output.TaxPayerInfoOutput;

public class MainWindow {

	private JFrame frame;
	private JTextField txtIncomeTaxCalculator;
	private JPanel taxPayerListPanel;
	private JPanel menuPanel;
	private JPanel addTaxPayerPanel;
	private JTextField titleAfm;
	private JTextField inputAfm;
	private TaxPayerInfoLoader infoLoader;
	private DefaultListModel<String> taxPayerListModel;
	JList<String> taxPayerList;
	private TaxPayerListManager dataManager;
	private JPanel panel;
	private JButton viewDetailsButton;
	private JPanel taxPayerDetailPanel;
	private JTextField txtName;
	private JTextField nameTxt;
	private JTextField txtAfm;
	private JTextField afmTxt;
	private JTextField txtIncome;
	private JTextField incomeTxt;
	private JTextField txtBasicTax;
	private JTextField basicTaxTxt;
	private JTextField txtTaxIncrease;
	private JTextField taxIncreaseTxt;
	private JTextField txtTotalTax;
	private JTextField totalTaxTxt;
	private JTextField txtTotalreceiptsgathered;
	private JTextField totalReceiptsGatheredTxt;
	private JTextField txtEntertainment;
	private JTextField entertainmentTxt;
	private JTextField taxPayerInfo;
	private JTextField txtBasic;
	private JTextField basicTxt;
	private JTextField txtTravel;
	private JTextField travelTxt;
	private JTextField txtHealth;
	private JTextField healthTxt;
	private JTextField txtOther;
	private JTextField otherTxt;
	private JPanel addReceiptPanel;
	private JPanel deleteReceiptoPanel;
	private JTextField txtTime;
	private JTextField txtDate;
	private JTextField txtReceiptId;
	private JTextField txtCountry;
	private JTextField txtCompany;
	private JTextField txtAmount;
	private JTextField countryTxt;
	private JTextField companyTxt;
	private JTextField amountTxt;
	private JTextField kindTxt;
	private JTextField dateTxt;
	private JTextField receiptIDTxt;
	private JTextField txtCity;
	private JTextField txtStreet;
	private JTextField txtNumber;
	private JTextField cityTxt;
	private JTextField streetTxt;
	private JTextField numberTxt;
	private JTextField txtReciptDetails;
	private int taxPayerIndex;
	private JTextField txtReceiptIdTo;
	private JTextField txtDeleteReceipt;
	private JTextField receiptIDdeleteTxt;
	private JPanel deleteTaxPayerPanel;
	private JTextField txtAfmToDelete;
	private JTextField afm2deleteTxt;
	private JButton btnNewButton_1;
	private JButton backListButton;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnBack;
	private JButton btnBack_1;
	private JButton btnNewButton_5;
	private TaxPayerInfoOutput infoOutput;
	private JTextField txtAddTaxpayerFrom;
	private JTextField txtDeleteTaxPayer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dataManager = new TaxPayerListManager();
		infoLoader = new TaxPayerInfoLoader();
		infoOutput = new TaxPayerInfoOutput();
		frame = new JFrame();
		frame.setBounds(100, 100, 511, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		menuPanel = new JPanel();
		frame.getContentPane().add(menuPanel, "name_834297475694020");
		menuPanel.setLayout(null);
		
		txtIncomeTaxCalculator = new JTextField();
		txtIncomeTaxCalculator.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtIncomeTaxCalculator.setBounds(100, 11, 234, 20);
		menuPanel.add(txtIncomeTaxCalculator);
		txtIncomeTaxCalculator.setEditable(false);
		txtIncomeTaxCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		txtIncomeTaxCalculator.setText("Income Tax Calculator");
		txtIncomeTaxCalculator.setColumns(10);
		
		JButton loadTaxPayerButton = new JButton("Load Tax Payer Info");
		loadTaxPayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(true);
				
			}
		});
		loadTaxPayerButton.setBounds(125, 79, 171, 23);
		menuPanel.add(loadTaxPayerButton);
		
		JButton deleteTaxPayerButton = new JButton("Delete Tax Payer");
		deleteTaxPayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
				deleteTaxPayerPanel.setVisible(true);
			}
		});
		deleteTaxPayerButton.setBounds(125, 191, 171, 23);
		menuPanel.add(deleteTaxPayerButton);
		
		JButton chooseTaxPayerButton = new JButton("Choose Tax Payer");
		chooseTaxPayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(true);
				taxPayerListModel=dataManager.createTaxPayerListModel();
				taxPayerList.setModel(taxPayerListModel);
				taxPayerList.repaint();
				taxPayerList.revalidate();
			}
		});
		chooseTaxPayerButton.setBounds(125, 134, 171, 23);
		menuPanel.add(chooseTaxPayerButton);
		
		taxPayerListPanel = new JPanel();
		frame.getContentPane().add(taxPayerListPanel, "name_834297479952213");
		taxPayerListPanel.setLayout(new BorderLayout(0, 0));
		
		taxPayerList = new JList<String>();
		taxPayerListPanel.add(taxPayerList);
		
		panel = new JPanel();
		taxPayerListPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		viewDetailsButton = new JButton("VIEW DETAILS");
		viewDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = taxPayerList.getSelectedIndex();
				if(index!=-1){
					updateDetails(index);
					menuPanel.setVisible(false);
					taxPayerListPanel.setVisible(false);
					addTaxPayerPanel.setVisible(false);
					taxPayerDetailPanel.setVisible(true);
					taxPayerIndex=index;
				}
				else{
					System.out.println("Nothing selected");
				}
			}
		});
		panel.add(viewDetailsButton, BorderLayout.NORTH);
		
		backListButton = new JButton("BACK");
		backListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(true);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
			}
		});
		panel.add(backListButton, BorderLayout.SOUTH);
		
		addTaxPayerPanel = new JPanel();
		frame.getContentPane().add(addTaxPayerPanel, "name_855092070439180");
		addTaxPayerPanel.setLayout(null);
		
		titleAfm = new JTextField();
		titleAfm.setHorizontalAlignment(SwingConstants.CENTER);
		titleAfm.setEditable(false);
		titleAfm.setText("Give AFM to Load:");
		titleAfm.setBounds(10, 78, 149, 20);
		addTaxPayerPanel.add(titleAfm);
		titleAfm.setColumns(10);
		
		inputAfm = new JTextField();
		inputAfm.setBounds(181, 78, 86, 20);
		addTaxPayerPanel.add(inputAfm);
		inputAfm.setColumns(10);
		
		JButton submitAfmButton = new JButton("SUBMIT");
		submitAfmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Afm = inputAfm.getText();
				dataManager.addTaxPayer(infoLoader.loadInfo(Afm));
				menuPanel.setVisible(true);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
			}
		});
		submitAfmButton.setBounds(178, 146, 89, 23);
		addTaxPayerPanel.add(submitAfmButton);
		
		btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(true);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(54, 146, 89, 23);
		addTaxPayerPanel.add(btnNewButton_2);
		
		txtAddTaxpayerFrom = new JTextField();
		txtAddTaxpayerFrom.setEditable(false);
		txtAddTaxpayerFrom.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtAddTaxpayerFrom.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddTaxpayerFrom.setText("ADD TAXPAYER FROM FILE");
		txtAddTaxpayerFrom.setBounds(25, 25, 419, 20);
		addTaxPayerPanel.add(txtAddTaxpayerFrom);
		txtAddTaxpayerFrom.setColumns(10);
		
		taxPayerDetailPanel = new JPanel();
		frame.getContentPane().add(taxPayerDetailPanel, "name_858431996589003");
		taxPayerDetailPanel.setLayout(null);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setText("Name:");
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(44, 45, 86, 20);
		taxPayerDetailPanel.add(txtName);
		txtName.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		nameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		nameTxt.setBounds(186, 45, 86, 20);
		taxPayerDetailPanel.add(nameTxt);
		nameTxt.setColumns(10);
		
		txtAfm = new JTextField();
		txtAfm.setEditable(false);
		txtAfm.setText("AFM:");
		txtAfm.setHorizontalAlignment(SwingConstants.CENTER);
		txtAfm.setBounds(44, 76, 86, 20);
		taxPayerDetailPanel.add(txtAfm);
		txtAfm.setColumns(10);
		
		afmTxt = new JTextField();
		afmTxt.setEditable(false);
		afmTxt.setHorizontalAlignment(SwingConstants.CENTER);
		afmTxt.setBounds(186, 76, 86, 20);
		taxPayerDetailPanel.add(afmTxt);
		afmTxt.setColumns(10);
		
		txtIncome = new JTextField();
		txtIncome.setEditable(false);
		txtIncome.setText("Income:");
		txtIncome.setHorizontalAlignment(SwingConstants.CENTER);
		txtIncome.setBounds(44, 107, 86, 20);
		taxPayerDetailPanel.add(txtIncome);
		txtIncome.setColumns(10);
		
		incomeTxt = new JTextField();
		incomeTxt.setEditable(false);
		incomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		incomeTxt.setBounds(186, 107, 86, 20);
		taxPayerDetailPanel.add(incomeTxt);
		incomeTxt.setColumns(10);
		
		txtBasicTax = new JTextField();
		txtBasicTax.setEditable(false);
		txtBasicTax.setText("Basic Tax:");
		txtBasicTax.setHorizontalAlignment(SwingConstants.CENTER);
		txtBasicTax.setBounds(44, 138, 86, 20);
		taxPayerDetailPanel.add(txtBasicTax);
		txtBasicTax.setColumns(10);
		
		basicTaxTxt = new JTextField();
		basicTaxTxt.setEditable(false);
		basicTaxTxt.setHorizontalAlignment(SwingConstants.CENTER);
		basicTaxTxt.setBounds(186, 138, 86, 20);
		taxPayerDetailPanel.add(basicTaxTxt);
		basicTaxTxt.setColumns(10);
		
		txtTaxIncrease = new JTextField();
		txtTaxIncrease.setEditable(false);
		txtTaxIncrease.setText("Tax Increase:");
		txtTaxIncrease.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaxIncrease.setBounds(44, 169, 86, 20);
		taxPayerDetailPanel.add(txtTaxIncrease);
		txtTaxIncrease.setColumns(10);
		
		taxIncreaseTxt = new JTextField();
		taxIncreaseTxt.setEditable(false);
		taxIncreaseTxt.setHorizontalAlignment(SwingConstants.CENTER);
		taxIncreaseTxt.setBounds(186, 169, 86, 20);
		taxPayerDetailPanel.add(taxIncreaseTxt);
		taxIncreaseTxt.setColumns(10);
		
		txtTotalTax = new JTextField();
		txtTotalTax.setEditable(false);
		txtTotalTax.setText("Total Tax:");
		txtTotalTax.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalTax.setBounds(44, 200, 86, 20);
		taxPayerDetailPanel.add(txtTotalTax);
		txtTotalTax.setColumns(10);
		
		totalTaxTxt = new JTextField();
		totalTaxTxt.setEditable(false);
		totalTaxTxt.setHorizontalAlignment(SwingConstants.CENTER);
		totalTaxTxt.setBounds(186, 200, 86, 20);
		taxPayerDetailPanel.add(totalTaxTxt);
		totalTaxTxt.setColumns(10);
		
		txtTotalreceiptsgathered = new JTextField();
		txtTotalreceiptsgathered.setEditable(false);
		txtTotalreceiptsgathered.setText("TotalReceiptsGathered:");
		txtTotalreceiptsgathered.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalreceiptsgathered.setBounds(10, 231, 120, 20);
		taxPayerDetailPanel.add(txtTotalreceiptsgathered);
		txtTotalreceiptsgathered.setColumns(10);
		
		totalReceiptsGatheredTxt = new JTextField();
		totalReceiptsGatheredTxt.setEditable(false);
		totalReceiptsGatheredTxt.setHorizontalAlignment(SwingConstants.CENTER);
		totalReceiptsGatheredTxt.setBounds(186, 231, 86, 20);
		taxPayerDetailPanel.add(totalReceiptsGatheredTxt);
		totalReceiptsGatheredTxt.setColumns(10);
		
		txtEntertainment = new JTextField();
		txtEntertainment.setEditable(false);
		txtEntertainment.setText("Entertainment:");
		txtEntertainment.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntertainment.setBounds(44, 262, 86, 20);
		taxPayerDetailPanel.add(txtEntertainment);
		txtEntertainment.setColumns(10);
		
		entertainmentTxt = new JTextField();
		entertainmentTxt.setEditable(false);
		entertainmentTxt.setHorizontalAlignment(SwingConstants.CENTER);
		entertainmentTxt.setBounds(186, 262, 86, 20);
		taxPayerDetailPanel.add(entertainmentTxt);
		entertainmentTxt.setColumns(10);
		
		taxPayerInfo = new JTextField();
		taxPayerInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		taxPayerInfo.setEditable(false);
		taxPayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		taxPayerInfo.setText("TAX PAYER INFO");
		taxPayerInfo.setBounds(43, 14, 229, 20);
		taxPayerDetailPanel.add(taxPayerInfo);
		taxPayerInfo.setColumns(10);
		
		txtBasic = new JTextField();
		txtBasic.setEditable(false);
		txtBasic.setText("Basic: ");
		txtBasic.setHorizontalAlignment(SwingConstants.CENTER);
		txtBasic.setBounds(44, 293, 86, 20);
		taxPayerDetailPanel.add(txtBasic);
		txtBasic.setColumns(10);
		
		basicTxt = new JTextField();
		basicTxt.setEditable(false);
		basicTxt.setHorizontalAlignment(SwingConstants.CENTER);
		basicTxt.setBounds(186, 293, 86, 20);
		taxPayerDetailPanel.add(basicTxt);
		basicTxt.setColumns(10);
		
		txtTravel = new JTextField();
		txtTravel.setEditable(false);
		txtTravel.setText("Travel:");
		txtTravel.setHorizontalAlignment(SwingConstants.CENTER);
		txtTravel.setBounds(44, 325, 86, 20);
		taxPayerDetailPanel.add(txtTravel);
		txtTravel.setColumns(10);
		
		txtHealth = new JTextField();
		txtHealth.setEditable(false);
		txtHealth.setText("Health:");
		txtHealth.setHorizontalAlignment(SwingConstants.CENTER);
		txtHealth.setBounds(44, 357, 86, 20);
		taxPayerDetailPanel.add(txtHealth);
		txtHealth.setColumns(10);
		
		healthTxt = new JTextField();
		healthTxt.setEditable(false);
		healthTxt.setHorizontalAlignment(SwingConstants.CENTER);
		healthTxt.setBounds(186, 357, 86, 20);
		taxPayerDetailPanel.add(healthTxt);
		healthTxt.setColumns(10);
		
		txtOther = new JTextField();
		txtOther.setEditable(false);
		txtOther.setText("Other:");
		txtOther.setHorizontalAlignment(SwingConstants.CENTER);
		txtOther.setBounds(44, 388, 86, 20);
		taxPayerDetailPanel.add(txtOther);
		txtOther.setColumns(10);
		
		otherTxt = new JTextField();
		otherTxt.setEditable(false);
		otherTxt.setHorizontalAlignment(SwingConstants.CENTER);
		otherTxt.setColumns(10);
		otherTxt.setBounds(186, 388, 86, 20);
		taxPayerDetailPanel.add(otherTxt);
		
		travelTxt = new JTextField();
		travelTxt.setEditable(false);
		travelTxt.setHorizontalAlignment(SwingConstants.CENTER);
		travelTxt.setBounds(186, 325, 86, 20);
		taxPayerDetailPanel.add(travelTxt);
		travelTxt.setColumns(10);
		
		JButton addReceiptButton = new JButton("ADD RECEIPT");
		addReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(true);
			}
		});
		addReceiptButton.setBounds(300, 75, 120, 23);
		taxPayerDetailPanel.add(addReceiptButton);
		
		JButton deleteReceiptButton = new JButton("DELETE RECEIPT");
		deleteReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(true);
			}
		});
		deleteReceiptButton.setBounds(300, 137, 120, 23);
		taxPayerDetailPanel.add(deleteReceiptButton);
		
		JButton btnBarChart = new JButton("BAR CHART");
		btnBarChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoOutput.createBarChart(dataManager.getTaxPayer(taxPayerIndex));
			}
		});
		btnBarChart.setBounds(300, 199, 120, 23);
		taxPayerDetailPanel.add(btnBarChart);
		
		JButton btnPipaChart = new JButton("PIPA CHART ");
		btnPipaChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoOutput.createPieChart(dataManager.getTaxPayer(taxPayerIndex));
			}
		});
		btnPipaChart.setBounds(300, 261, 120, 23);
		taxPayerDetailPanel.add(btnPipaChart);
		
		btnNewButton_3 = new JButton("BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(true);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(300, 356, 120, 23);
		taxPayerDetailPanel.add(btnNewButton_3);
		
		btnNewButton_5 = new JButton("SAVE REPORT");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoOutput.saveTaxPayerInfo(dataManager.getTaxPayer(taxPayerIndex));
			}
		});
		btnNewButton_5.setBounds(300, 311, 120, 23);
		taxPayerDetailPanel.add(btnNewButton_5);
		
		addReceiptPanel = new JPanel();
		frame.getContentPane().add(addReceiptPanel, "name_26974980837306");
		addReceiptPanel.setLayout(null);
		
		txtReceiptId = new JTextField();
		txtReceiptId.setEditable(false);
		txtReceiptId.setText("Receipt ID");
		txtReceiptId.setHorizontalAlignment(SwingConstants.CENTER);
		txtReceiptId.setBounds(31, 44, 86, 20);
		addReceiptPanel.add(txtReceiptId);
		txtReceiptId.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setText("Date");
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setBounds(31, 86, 86, 20);
		addReceiptPanel.add(txtDate);
		txtDate.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setText("Kind");
		txtTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTime.setBounds(31, 130, 86, 20);
		addReceiptPanel.add(txtTime);
		txtTime.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setEditable(false);
		txtCountry.setText("Country");
		txtCountry.setHorizontalAlignment(SwingConstants.CENTER);
		txtCountry.setColumns(10);
		txtCountry.setBounds(31, 264, 86, 20);
		addReceiptPanel.add(txtCountry);
		
		txtCompany = new JTextField();
		txtCompany.setEditable(false);
		txtCompany.setText("Company");
		txtCompany.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompany.setColumns(10);
		txtCompany.setBounds(31, 220, 86, 20);
		addReceiptPanel.add(txtCompany);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setText("Amount");
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmount.setColumns(10);
		txtAmount.setBounds(31, 178, 86, 20);
		addReceiptPanel.add(txtAmount);
		
		countryTxt = new JTextField();
		countryTxt.setHorizontalAlignment(SwingConstants.CENTER);
		countryTxt.setColumns(10);
		countryTxt.setBounds(161, 264, 86, 20);
		addReceiptPanel.add(countryTxt);
		
		companyTxt = new JTextField();
		companyTxt.setHorizontalAlignment(SwingConstants.CENTER);
		companyTxt.setColumns(10);
		companyTxt.setBounds(161, 220, 86, 20);
		addReceiptPanel.add(companyTxt);
		
		amountTxt = new JTextField();
		amountTxt.setHorizontalAlignment(SwingConstants.CENTER);
		amountTxt.setColumns(10);
		amountTxt.setBounds(161, 178, 86, 20);
		addReceiptPanel.add(amountTxt);
		
		kindTxt = new JTextField();
		kindTxt.setHorizontalAlignment(SwingConstants.CENTER);
		kindTxt.setColumns(10);
		kindTxt.setBounds(161, 130, 86, 20);
		addReceiptPanel.add(kindTxt);
		
		dateTxt = new JTextField();
		dateTxt.setHorizontalAlignment(SwingConstants.CENTER);
		dateTxt.setColumns(10);
		dateTxt.setBounds(161, 86, 86, 20);
		addReceiptPanel.add(dateTxt);
		
		receiptIDTxt = new JTextField();
		receiptIDTxt.setHorizontalAlignment(SwingConstants.CENTER);
		receiptIDTxt.setColumns(10);
		receiptIDTxt.setBounds(161, 44, 86, 20);
		addReceiptPanel.add(receiptIDTxt);
		
		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setText("City");
		txtCity.setHorizontalAlignment(SwingConstants.CENTER);
		txtCity.setColumns(10);
		txtCity.setBounds(31, 316, 86, 20);
		addReceiptPanel.add(txtCity);
		
		txtStreet = new JTextField();
		txtStreet.setEditable(false);
		txtStreet.setText("Street");
		txtStreet.setHorizontalAlignment(SwingConstants.CENTER);
		txtStreet.setColumns(10);
		txtStreet.setBounds(31, 358, 86, 20);
		addReceiptPanel.add(txtStreet);
		
		txtNumber = new JTextField();
		txtNumber.setEditable(false);
		txtNumber.setText("Number");
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumber.setColumns(10);
		txtNumber.setBounds(31, 402, 86, 20);
		addReceiptPanel.add(txtNumber);
		
		cityTxt = new JTextField();
		cityTxt.setHorizontalAlignment(SwingConstants.CENTER);
		cityTxt.setColumns(10);
		cityTxt.setBounds(161, 316, 86, 20);
		addReceiptPanel.add(cityTxt);
		
		streetTxt = new JTextField();
		streetTxt.setHorizontalAlignment(SwingConstants.CENTER);
		streetTxt.setColumns(10);
		streetTxt.setBounds(161, 358, 86, 20);
		addReceiptPanel.add(streetTxt);
		
		numberTxt = new JTextField();
		numberTxt.setHorizontalAlignment(SwingConstants.CENTER);
		numberTxt.setColumns(10);
		numberTxt.setBounds(161, 402, 86, 20);
		addReceiptPanel.add(numberTxt);
		
		txtReciptDetails = new JTextField();
		txtReciptDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtReciptDetails.setEditable(false);
		txtReciptDetails.setText("Receipt Details");
		txtReciptDetails.setHorizontalAlignment(SwingConstants.CENTER);
		txtReciptDetails.setBounds(28, 11, 220, 27);
		addReceiptPanel.add(txtReciptDetails);
		txtReciptDetails.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receipt r = new Receipt();
				r.setReceiptId(Integer.parseInt(receiptIDTxt.getText()));
				r.setDate(dateTxt.getText());
				r.setKind(kindTxt.getText());
				r.setAmount(Double.parseDouble(amountTxt.getText()));
				Company c = new Company();
				c.setName(companyTxt.getText());
				c.setCountry(countryTxt.getText());
				c.setCity(cityTxt.getText());
				c.setStreet(streetTxt.getText());
				c.setNumber(Integer.parseInt(numberTxt.getText()));
				r.setCompany(c);
				dataManager.getTaxPayer(taxPayerIndex).addReceipt(r);
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(true);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
				updateDetails(taxPayerIndex);
			}
		});
		btnAdd.setBounds(298, 357, 89, 23);
		addReceiptPanel.add(btnAdd);
		
		btnNewButton_4 = new JButton("BACK");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(true);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(298, 401, 89, 23);
		addReceiptPanel.add(btnNewButton_4);
		
		deleteReceiptoPanel = new JPanel();
		frame.getContentPane().add(deleteReceiptoPanel, "name_26990123426201");
		deleteReceiptoPanel.setLayout(null);
		
		txtDeleteReceipt = new JTextField();
		txtDeleteReceipt.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtDeleteReceipt.setText("DELETE RECEIPT");
		txtDeleteReceipt.setHorizontalAlignment(SwingConstants.CENTER);
		txtDeleteReceipt.setEditable(false);
		txtDeleteReceipt.setBounds(141, 31, 170, 20);
		deleteReceiptoPanel.add(txtDeleteReceipt);
		txtDeleteReceipt.setColumns(10);
		
		txtReceiptIdTo = new JTextField();
		txtReceiptIdTo.setEditable(false);
		txtReceiptIdTo.setText("Receipt ID to delete");
		txtReceiptIdTo.setHorizontalAlignment(SwingConstants.CENTER);
		txtReceiptIdTo.setBounds(28, 105, 135, 20);
		deleteReceiptoPanel.add(txtReceiptIdTo);
		txtReceiptIdTo.setColumns(10);
		
		receiptIDdeleteTxt = new JTextField();
		receiptIDdeleteTxt.setBounds(214, 105, 89, 20);
		deleteReceiptoPanel.add(receiptIDdeleteTxt);
		receiptIDdeleteTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = receiptIDdeleteTxt.getText();
				dataManager.getTaxPayer(taxPayerIndex).deleteReceipt(Integer.parseInt(id));
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(true);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
				updateDetails(taxPayerIndex);
			}
			
		});
		btnNewButton.setBounds(214, 205, 89, 23);
		deleteReceiptoPanel.add(btnNewButton);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(true);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
			}
		});
		btnBack.setBounds(74, 205, 89, 23);
		deleteReceiptoPanel.add(btnBack);
		
		deleteTaxPayerPanel = new JPanel();
		frame.getContentPane().add(deleteTaxPayerPanel, "name_31146394228655");
		deleteTaxPayerPanel.setLayout(null);
		
		txtAfmToDelete = new JTextField();
		txtAfmToDelete.setEditable(false);
		txtAfmToDelete.setText("TaxPayer afm  to delete:");
		txtAfmToDelete.setHorizontalAlignment(SwingConstants.CENTER);
		txtAfmToDelete.setBounds(10, 109, 136, 20);
		deleteTaxPayerPanel.add(txtAfmToDelete);
		txtAfmToDelete.setColumns(10);
		
		afm2deleteTxt = new JTextField();
		afm2deleteTxt.setBounds(177, 109, 86, 20);
		deleteTaxPayerPanel.add(afm2deleteTxt);
		afm2deleteTxt.setColumns(10);
		
		btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dataManager.deleteTaxpayer(afm2deleteTxt.getText());
			}
		});
		btnNewButton_1.setBounds(181, 184, 89, 23);
		deleteTaxPayerPanel.add(btnNewButton_1);
		
		btnBack_1 = new JButton("BACK");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(true);
				taxPayerListPanel.setVisible(false);
				addTaxPayerPanel.setVisible(false);
				taxPayerDetailPanel.setVisible(false);
				addReceiptPanel.setVisible(false);
				deleteReceiptoPanel.setVisible(false);
				deleteTaxPayerPanel.setVisible(false);
			}
		});
		btnBack_1.setBounds(57, 184, 89, 23);
		deleteTaxPayerPanel.add(btnBack_1);
		
		txtDeleteTaxPayer = new JTextField();
		txtDeleteTaxPayer.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtDeleteTaxPayer.setEditable(false);
		txtDeleteTaxPayer.setText("DELETE TAX PAYER ");
		txtDeleteTaxPayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtDeleteTaxPayer.setBounds(124, 17, 183, 20);
		deleteTaxPayerPanel.add(txtDeleteTaxPayer);
		txtDeleteTaxPayer.setColumns(10);
	}
	public void updateDetails(int index){
		TaxPayer tp = dataManager.getTaxPayer(index);
		nameTxt.setText(tp.getName());
		afmTxt.setText((Integer.toString(tp.getAfm())));
		incomeTxt.setText(Double.toString(tp.getIncome()));
		basicTaxTxt.setText(Double.toString(tp.getTax()));
		taxIncreaseTxt.setText(Double.toString(tp.calculateFinalTax()-tp.calculateTax()));
		totalTaxTxt.setText(Double.toString(tp.calculateTax()));
		totalReceiptsGatheredTxt.setText(Double.toString(tp.getTotalReceiptGathered()));
		entertainmentTxt.setText(Double.toString(tp.getEntertainmentReceiptsNumber()));
		basicTxt.setText(Double.toString(tp.getBasicReceiptsNumber()));
		otherTxt.setText(Double.toString(tp.getOtherReceiptsNumber()));
		healthTxt.setText(Double.toString(tp.getHealthReceiptsNumber()));
		travelTxt.setText(Double.toString(tp.getTravelReceiptsNumber()));
	}
}
