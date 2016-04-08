package InfoManager;

public class Receipt {
	private int receiptId;
	private String Date;
	private String Kind;
	private double Amount;
	private String Country;
	private String City;
	private String Street;
	private int Number;
	private Company company;
	//constructors
	public Receipt(){
		company = new Company();
	}
	//set Methods
	public void setCompany(Company c){
		this.company=c;
	}
	public void setReceiptId(int receiptId){
		this.receiptId=receiptId;
	}
	public void setDate(String Date){
		this.Date=Date;
	}
	public void setKind(String Kind){
		this.Kind=Kind;
	}
	public void setAmount(double Amount){
		this.Amount=Amount;
	}
	
	public void setCity(String City){
		this.City=City;
	}
	public void setStreet(String Street){
		this.Street=Street;
	}
	public void setNumber(int Number){
		this.Number=Number;
	}
	public void setCountry(String Country){
		this.Country=Country;
	}
	// get Methods
	public Company getCompany(){
		return company;
	}
	public double getAmount(){
		return Amount;
	}
	
	public int getReceiptId(){
		return receiptId;
	}
	public String getDate(){
		return Date;
	}
	public String getKind(){
		return Kind;
	}
	public String getCity(){
		return City;
	}
	public String getStreet(){
		return Street;
	}
	public int getNumber(){
		return Number;
	}
	public String getCountry(){
		return Country;
	}	
}
