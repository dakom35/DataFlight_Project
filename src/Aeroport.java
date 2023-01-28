
public class Aeroport 
{
	private final String iata ; 
	private final String name ; 
	private final String country ;
	private final double latitude ; 
	private final double longitude ;
	
	public Aeroport(String iata, String name, String country, double latitude, double longitude) 
	{
		super();
		this.iata = iata ;
		this.name = name;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getIata() {
		return iata;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Aeroport [iata=" + iata + ", name=" + name + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	} 
	
	
	
	
	

}
