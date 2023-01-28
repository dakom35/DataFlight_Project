import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList ;
import java.lang.Math ;


public class World 
{
	private ArrayList<Aeroport> list = new ArrayList<Aeroport>();
	private String iata ; 
	private String name ; 
	private String country ;
	private double latitude ; 
	private double longitude ;


    
    public World (String fileName) 
	 {
		 try 
		 {
			 BufferedReader buf = new BufferedReader (new FileReader(fileName));
			 String s = buf.readLine();


			 while(s!=null) 
			 {
				 s=s.replaceAll("\"","");
				 // enleve les guillemets qui séparent les champs de données GPS
				 String fields[] = s.split(",");
				 // une bonne idée : placer un point d'arrêt ici pour du débugage.
				 if (fields[1].equals("large_airport")) 
				 {
					 iata = fields[9];
					 name = fields[2];
					 country = fields[5];
					 // coordinates = fields[11] ;
					 latitude = Double.parseDouble(fields[12]) ;
					 longitude = Double.parseDouble(fields[11]);				 
					 Aeroport airport = new Aeroport(iata,name,country,latitude,longitude);
					 list.add(airport);
				 }
				 s = buf.readLine();
			 }
			 buf.close();
		 }
		 
		 catch (Exception e) 
		 {
			 System.out.println("Maybe the file isn't there ?");
			 //System.out.println(list.get(list.size()-1));
			 e.printStackTrace();
		 }
	}
	
//	 public World (String fileName) 
//	 {
//		 try 
//		 {
//			 BufferedReader buf = new BufferedReader (new FileReader(fileName));
//			 String s = buf.readLine();
//
//
//			 while(s!=null) 
//			 {
//				 s=s.replaceAll("\"","");
//				 // enleve les guillemets qui séparent les champs de données GPS
//				 String fields[] = s.split(",");
//				 // une bonne idée : placer un point d'arrêt ici pour du débugage.
//				 if (fields[1].equals("large_airport")) 
//				 {
//					 iata = fields[9];
//					 name = fields[2];
//					 country = fields[5];
//					 // coordinates = fields[11] ;
//					 latitude = Double.parseDouble(fields[12]) ;
//					 longitude = Double.parseDouble(fields[11]);				 
//					 Aeroport airport = new Aeroport(iata,name,country,latitude,longitude);
//					 list.add(airport);
//				 }
//				 s = buf.readLine();
//			 }
//			 buf.close();
//		 }
//		 
//		 catch (Exception e) 
//		 {
//			 System.out.println("Maybe the file isn't there ?");
//			 //System.out.println(list.get(list.size()-1));
//			 e.printStackTrace();
//		 }
//	}
	 

	public ArrayList<Aeroport> getList() 
	{
		return list;
	}
	 
	public double distance(double lon1,double lat1, double lon2, double lat2)
	{
		// using Haversine formula to determine distance between two points
		double R =  6371 ; // Earth radius in kilometers
		double p = Math.PI / 180 ; // to convert degrees into radians
		double d ;
		
		double dist = Math.sin((lat2 - lat1)*p/2) ;
		dist = dist * dist ;
		d = Math.sin((lon2 - lon1)*p / 2) ; 
		d = d * d ; 
		d = d * Math.cos(lat1*p) * Math.cos(lat2*p) ;
		dist = dist + d ; 
		dist = Math.pow(dist, 0.5) ; 
		dist = Math.asin(dist); 
		dist = dist * 2 * R ; // result in kilometers
		return dist ;
	}
	
	public Aeroport findNearest(ArrayList<Aeroport> listAirport, double lon1, double lat1)
	{
		// renvoie l'aéroport le plus proche des coordonnées envoyées 

		double lon2 = listAirport.get(0).getLongitude() ;
		double lat2 = listAirport.get(0).getLatitude() ;
		double minDist = distance(lon1,lat1,lon2,lat2);
		double dist = 0 ;
		int minIndex = 0 ;
		for(int i=1 ; i<listAirport.size() ; i++) 
			// je saute le 0 car on l'a déjà calculé
		{
			lon2 = listAirport.get(i).getLongitude() ;
			lat2 = listAirport.get(i).getLatitude();
			dist = distance(lon1,lat1,lon2,lat2);
			if( dist < minDist) 
			{
				minDist = dist ;
				minIndex = i ;
			}
		}	
		return listAirport.get(minIndex) ;	
	}
	
	public Aeroport findByCode(ArrayList<Aeroport> listAirport,String iata)
	{
		int i = 0 ;
		Aeroport airport = listAirport.get(0);
		while(iata != airport.getIata() )
		{
			i++;
			airport = listAirport.get(i);
		}
		return airport ;
	}



}
