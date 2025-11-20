package Objects;


public class Provoles {
	
	private String PROVOLI_ID;
	private String FILM_ID;
	private String CINEMA_ID;
	private String FILM_NAME;
	private int CONTENT_ADMIN_ID;
	private String provoliStartDate;
	private String provoliEndDate;
	private int provoliNumberOfReservations;
	private boolean provoliIsAvailable;
	
	public Provoles(String PROVOLI_ID, String FILM_ID, String CINEMA_ID, String provoliStartDate, String provoliEndDate,
int provoliNumberOfReservations, boolean provoliIsAvailable, String FILM_NAME, int CONTENT_ADMIN_ID ) {
		this.PROVOLI_ID = PROVOLI_ID;
		this.FILM_ID = FILM_ID;
		this.CINEMA_ID = CINEMA_ID;
		this.provoliStartDate = provoliStartDate;
		this.provoliEndDate = provoliEndDate;
		this.provoliNumberOfReservations = provoliNumberOfReservations;
		this.provoliIsAvailable = provoliIsAvailable;
		this.FILM_NAME=FILM_NAME;
		this.CONTENT_ADMIN_ID=CONTENT_ADMIN_ID;
	}

	public String getPRVOLI_ID() {
		return PROVOLI_ID;
	}

	public void setPROVOLI_ID(String provoliID) {
		this.PROVOLI_ID = provoliID;
	}

	public String getProvoliFilm() {
		return FILM_ID;
	}

	public void setProvoliFilm(String provoliFilm) {
		this.FILM_ID = provoliFilm;
	}

	public String getProvoliCinema() {
		return CINEMA_ID;
	}

	public void setProvoliCinema(String provoliCinema) {
		this.CINEMA_ID = provoliCinema;
	}

	public String getProvoliStartDate() {
		return provoliStartDate;
	}

	public void setProvoliStartDate(String provoliStartDate) {
		this.provoliStartDate = provoliStartDate;
	}

	public String getProvoliEndDate() {
		return provoliEndDate;
	}

	public void setProvoliEndDate(String provoliEndDate) {
		this.provoliEndDate = provoliEndDate;
	}

	public int getProvoliNumberOfReservations() {
		return provoliNumberOfReservations;
	}

	public void setProvoliNumberOfReservations(int provoliNumberOfReservations) {
		this.provoliNumberOfReservations = provoliNumberOfReservations;
	}

	public boolean getProvoliIsAvailable() {
		return provoliIsAvailable;
	}

	public void setProvoliIsAvailable(boolean provoliIsAvailable) {
		this.provoliIsAvailable = provoliIsAvailable;
	}
	
	public String getFILM_NAME() {
		return FILM_NAME;
	}
	
	public void setFILM_NAME(String FILM_NAME) {
		this.FILM_NAME=FILM_NAME;
	}
	
	public int getCONTENT_ADMIN_ID() {
		return CONTENT_ADMIN_ID;
	}
	
	public void setCONTENT_ADMIN_ID(int CONTENT_ADMIN_ID) {
		this.CONTENT_ADMIN_ID=CONTENT_ADMIN_ID;
	}
}