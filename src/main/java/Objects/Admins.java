package Objects;

public class Admins extends Users {
	
	private int Admin_ID;
	
	public Admins(String name, String username, String password, int admin_ID,String salt) {
		super(name, username, password,salt);
		this.Admin_ID = admin_ID;
		
	}
	
	public int getAdmin_ID() {
		return Admin_ID;
	}

	public void setAdmin_ID(int admin_ID) {
		Admin_ID = admin_ID;
	}

}

