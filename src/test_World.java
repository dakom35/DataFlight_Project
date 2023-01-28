import java.util.ArrayList;
import java.io.BufferedReader ;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.math.BigDecimal;

public class test_World 
{

	public static void main(String[] args) 
		throws IOException 
	{
		String filename = "./airports.csv";
		ArrayList<Aeroport> listAirport = new ArrayList<Aeroport>();
		World world = new World(filename);
		listAirport = world.getList() ;
		Aeroport a1 = listAirport.get(0);
		Aeroport a2 = listAirport.get(1);
		BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(a1.toString());
		double lon1 = a1.getLongitude() ;
		double lat1 = a1.getLatitude();
		double lon2 = a2.getLongitude();
		double lat2 = a2.getLatitude();
		// format for nice display
		String sLon1 = String.format("%.2f", lon1);
		String sLon2 = String.format("%.2f", lon2);
		String sLat1 = String.format("%.2f", lat1);
		String sLat2 = String.format("%.2f", lat2);
		
		System.out.println("Which function to test : \n"
				+ "[1] : distance() \n"
				+ "[2] : findNearest() ?");
		int ans = Integer.parseInt(bfn.readLine()); 
		if(ans == 1 )
		{
			System.out.println("You chose to test distance() : ");
			double dist = world.distance(lon1,lat1,lon2,lat2);
			System.out.println("The distance between [" + a1.getName() 
					+"] (" +sLat1+";"+sLon1+
					") and \n["+ a2.getName()+ "] ("+sLat2+";"+
					sLon2+") is : "+ dist+ "km");
		}
		else
		{
			System.out.println("Error : type 1 or 2");
		}
		
 

		
		

		// test de la fonction findNearest 
		
		lat1 = 21 ;
		lon1 = 76 ; 
		 

		Aeroport nearestAirport = world.findNearest(listAirport,lon1,lat1);
//		System.out.println("Nearest airport :" +
//				 "[LAT = "+(int)nearestAirport.getLatitude()+
//				", LONG = "+(int)nearestAirport.getLongitude()+
//				", COUNTRY = "+nearestAirport.getCountry()+
//				", NAME = "+ nearestAirport.getName() + 
//				"]"
//				);
		
		// test de la fonction findByCode 
		//String iata = nearestAirport.getIata() ;
		//Aeroport magicAirport = world.findByCode(listAirport,iata);
		//System.out.println("magicAirport founded by the code iata "+iata+" is : "+magicAirport);
	}

}
