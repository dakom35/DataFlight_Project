//import javafx.application.Application;

import javafx.geometry.Point3D;
import javafx.scene.Group;
//import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
//import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
 
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate ;
//import javafx.stage.Stage;
import javafx.scene.transform.Translate;



public class Earth extends Group 
{
	private double radius = 300 ; // defines the radius of the sphere 
	private final Sphere sph = new Sphere(radius) ;
	private Image EarthImg = new Image("/Earth.png", true);
	private Rotate ry = new Rotate();
	
	//Create Material
	
	
	public Earth(double radius) 
	{
		
		super();
		this.radius = radius;
		PhongMaterial material = new PhongMaterial();
		this.getChildren().add(sph);
		material.setDiffuseMap(EarthImg);
		sph.setMaterial(material);
		ry.setAxis(new Point3D(0,1,0)); // investigate the purpose of this line
		ry.setAngle(0.5); // and this one 	
	}
	public Sphere createSphere(Aeroport airport, Color color) 
	{
		PhongMaterial material = new PhongMaterial();
		Sphere sphere = new Sphere(2);
		sphere.setMaterial(material);
		material.setDiffuseColor(color);
		double latitude = airport.getLatitude(); 
		double longitude = airport.getLongitude();
		double p = Math.PI / 180 ; // to convert degrees into radians
		//double c = 0 ; // facteur de correction initialement à 13
		// convert longitude and latitude to cartesian coordinates 
		double X = -radius * Math.cos(latitude*p)*Math.cos(longitude*p);
		double Y = -radius * Math.cos(latitude*p)* Math.sin(longitude*p);
		double Z = radius * Math.sin(latitude*p);
		//double Z = radius ;
		sphere.setTranslateX(X);
		sphere.setTranslateY(Y);
		sphere.setTranslateZ(Z);
		
		return sphere ;
	}
	
	public void displayRedSphere(Aeroport airport)
	{
		Sphere redSphere = createSphere(airport, Color.RED);
		this.getChildren().add(redSphere);
	}

}
