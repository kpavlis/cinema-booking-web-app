package Objects;

public class Films {
	
	private String filmID;
	private String filmTitle;
	private String filmCategory;
	private String filmDescription;
	private int filmDuration;
	private int ContentAdminID;

	public int getFilmDuration() {
		return filmDuration;
	}
	public void setFilmDuration(int filmDuration) {
		this.filmDuration = filmDuration;
	}

	public String getFilmID() {
		return filmID;
	}
	public void setFilmID(String filmID) {
		this.filmID = filmID;
	}
	public String getFilmTitle() {
		return filmTitle;
	}
	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}
	public String getFilmCategory() {
		return filmCategory;
	}
	public void setFilmCategory(String filmCategory) {
		this.filmCategory = filmCategory;
	}
	public String getFilmDescription() {
		return filmDescription;
	}
	public void setFilmDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}
	public int getContentAdminID() {
		return ContentAdminID;
	}
	public void setContentAdminID(int ContentAdminID) {
		this.ContentAdminID=ContentAdminID;
	}
	
	public Films (String filmID, String filmTitle, String filmCategory, String filmDescription, int filmDuration,int ContentAdminID) {

		this.filmID = filmID;
		this.filmTitle = filmTitle;
		this.filmCategory = filmCategory;
		this.filmDescription = filmDescription;
		this.filmDuration = filmDuration;
		this.ContentAdminID =ContentAdminID;
	}
	
}