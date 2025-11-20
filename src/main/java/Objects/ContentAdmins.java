package Objects;

public class ContentAdmins extends Users {
	
	private int content_admin_ID;
	
	public ContentAdmins(String name, String username, String password, int content_admin,String salt) {
		super(name, username, password,salt);
		this.content_admin_ID = content_admin;
		
	}

	public int getContent_admin_ID() {
		return content_admin_ID;
	}

	public void setContent_admin_ID(int content_admin_ID) {
		this.content_admin_ID = content_admin_ID;
	}
}