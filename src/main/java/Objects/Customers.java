package Objects;


public class Customers extends Users {
	
	private int Customer_ID;
	
	public Customers(String name, String username, String password, int customer_ID,String salt) {
		super(name, username, password,salt);
		this.Customer_ID = customer_ID;
		
	}

	public int getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		Customer_ID = customer_ID;
	}

	
	
}
