package InfoManager;

public class Receipt {
	
	private int receiptId;
	private String date;
	private String kind;
	private double amount;
	private String country;
	private String city;
	private String street;
	private int number;
	private Company company;
	
	public Receipt(){
		company = new Company();
	}
	
	
	public void setCompany(Company c){
		this.company = c;
	}
	
	public void setReceiptId(int receiptId){
		this.receiptId = receiptId;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setKind(String kind){
		this.kind = kind.replaceAll(" ", "");
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setStreet(String street){
		this.street = street;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public Company getCompany(){
		return company;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public int getReceiptId(){
		return receiptId;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getKind(){
		return kind;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getStreet(){
		return street;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getCountry(){
		return country;
	}
	
	
}
