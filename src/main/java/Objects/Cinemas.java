package Objects;

public class Cinemas {
	
	private String cinemaId;
	private boolean cinemaIs3D;
	private int cinemaNumberOfSeats;
	private String Name;
	
	public Cinemas(String cinemaId, boolean cinemaIs3D, int cinemaNumberOfSeats, String Name) {
		this.cinemaId = cinemaId;
		this.cinemaIs3D = cinemaIs3D;
		this.cinemaNumberOfSeats = cinemaNumberOfSeats;
		this.Name=Name;
	}
	
	public String getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}

	public boolean isCinemaIs3D() {
		return cinemaIs3D;
	}

	public void setCinemaIs3D(boolean cinemaIs3D) {
		this.cinemaIs3D = cinemaIs3D;
	}

	public int getCinemaNumberOfSeats() {
		return cinemaNumberOfSeats;
	}

	public void setCinemaNumberOfSeats(int cinemaNumberOfSeats) {
		this.cinemaNumberOfSeats = cinemaNumberOfSeats;
	}

	
	public String getName() {
		return Name;
	}
	
	public void setName(String Name) {
		this.Name=Name;
	}
	
	
}