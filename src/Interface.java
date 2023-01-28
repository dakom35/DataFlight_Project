import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
//import javafx.animation.AnimationTimer;

public class Interface extends Application 
{

@Override
	public void start(Stage primaryStage) throws Exception
	{
		String filename = "./airports.csv";
		
		World world = new World(filename);
		
		
		primaryStage.setTitle("Airports on Earth");
		Group root = new Earth(300);
		Pane pane = new Pane(root);
		Scene ihm = new Scene(pane, 600, 400,false);
		primaryStage.setScene(ihm);
		primaryStage.show();
		


		
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-1100);
		camera.setNearClip(0.1); // distance à partir de laquelle on commence à afficher
		camera.setFarClip(2000.0); // distance a partir de laquelle on n'affiche plus rien
		camera.setFieldOfView(40); 
		ihm.setCamera(camera);
		 //creating the rotation transformation
		Rotate y_rotateAntiTrigo = new Rotate();
		Rotate y_rotateTrigo = new Rotate();
		Rotate z_rotateAntiTrigo = new Rotate();
		Rotate z_rotateTrigo = new Rotate();
	    //Setting angles for the rotation
		y_rotateAntiTrigo.setAngle(1);	
		y_rotateTrigo.setAngle(1);
		z_rotateAntiTrigo.setAngle(1);
		z_rotateTrigo.setAngle(1); 		
		//Setting axis for the rotation
		y_rotateTrigo.setAxis(new Point3D(0,-1,0)) ;
		y_rotateAntiTrigo.setAxis(new Point3D(0,1,0)) ;
		z_rotateAntiTrigo.setAxis(new Point3D(1,0,0)) ;
		z_rotateTrigo.setAxis(new Point3D(-1,0,0)) ;
		
		// animation to make the Earth go round
		AnimationTimer animationTimer = new AnimationTimer() 
		{


			private long lastUpdate; // Last time in which `handle()` was called
			@Override
			public void handle(long now) 
			{
				long elapsedNanoSeconds = now - lastUpdate;

		        // 1 second = 1,000,000,000 (1 billion) nanoseconds
		        double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;
		        // on exécute handlee que si on ne l'a pas exécuté depuis au moins 1 seconde
				if(elapsedSeconds > 0.1 )
				{
					handlee();	
					lastUpdate = now;
				}
				else
				{
					return ;
				}

				
				
				
			}
			
		    @Override
		    public void start() 
		    {
		        lastUpdate = System.nanoTime();
		        super.start();
		    }
		    
		    
		    
			//method handlee
			private void handlee() 
			// Automatic rotation
			{
				//root.getTransforms().add(y_rotateTrigo);
			}
		};
		
		animationTimer.start(); 
		
		

		
		ihm.addEventHandler(MouseEvent.ANY, event4 -> 
		// get coordinates with right click
		{
			ArrayList<Aeroport> listAirport = new ArrayList<Aeroport>();
			listAirport = world.getList() ;
			if (event4.getButton()== MouseButton.SECONDARY && event4.getEventType()==MouseEvent.MOUSE_CLICKED) 
			{
				PickResult pickResult = event4.getPickResult();
				if (pickResult.getIntersectedNode() != null)
				{
					if(pickResult.getIntersectedTexCoord() != null)
					{
						double X = pickResult.getIntersectedTexCoord().getX();
						double Y = pickResult.getIntersectedTexCoord().getY();
						//System.out.println("X = "+X+" Y = "+Y);
						
						double latitude = -180 * (Y - 0.5);
						double longitude = 360 * (X - 0.5) ;
						System.out.println("Your click : "
								+ "[LAT = "+(int)latitude
								+ ", LONG = "+(int)longitude
								+ "]"
								);
						
						Aeroport nearestAirport = world.findNearest(listAirport, longitude , latitude);
						System.out.println("Nearest airport :" +
								 "[LAT = "+(int)nearestAirport.getLatitude()+
								", LONG = "+(int)nearestAirport.getLongitude()+
								", COUNTRY = "+nearestAirport.getCountry()+
								", NAME = "+ nearestAirport.getName() + 
								"]"
								);
						//((Earth) root).createSphere(nearestAirport, Color.RED) ;
						((Earth) root).displayRedSphere(nearestAirport);
					}
					else
					{
						System.out.println("pickResult.getIntersectedTexCoord() is null");
					}
				}

			// Affichage dans la console
			}
		});
		
		ihm.addEventHandler(KeyEvent.ANY, event2 ->
		{
			// press S key to translate the camera DOWN
			if(event2.getCode().equals(KeyCode.S))
			{
				camera.getTransforms().add(new Translate(0,10,0));
			}
			// press Z key to translate the camera UP
			if(event2.getCode().equals(KeyCode.Z))
			{
				camera.getTransforms().add(new Translate(0,-10,0));
			}
			// press Q key to translate the camera LEFT
			if(event2.getCode().equals(KeyCode.Q))
			{
				camera.getTransforms().add(new Translate(-10,0,0));
			}
			// press D key to translate the camera RIGHT
			if(event2.getCode().equals(KeyCode.D))
			{
				camera.getTransforms().add(new Translate(10,0,0));
			}
			// press SPACE to get the camera CLOSER
			if(event2.getCode().equals(KeyCode.SPACE))
			{
				camera.getTransforms().add(new Translate(0,0,10));
			}
			// press SHIFT the camera to 
			if(event2.getCode().equals(KeyCode.SHIFT))
			{
				camera.getTransforms().add(new Translate(0,0,-10));
			}
			// press LEFT to rotate Trigo along y_axis
			if(event2.getCode().equals(KeyCode.LEFT))
			{
				root.getTransforms().add(y_rotateTrigo);
			}
			// press RIGHT to rotate AntiTrigo along y_axis 
			if(event2.getCode().equals(KeyCode.RIGHT))
			{
				root.getTransforms().add(y_rotateAntiTrigo);
			}
			// press UP to rotate AntiTrigo along z_axis 
			if(event2.getCode().equals(KeyCode.UP))
			{
				root.getTransforms().add(z_rotateAntiTrigo);
			}
			// press DOWN to rotate Trigo along z_axis 
			if(event2.getCode().equals(KeyCode.DOWN))
			{
				root.getTransforms().add(z_rotateTrigo);
			}




		});
				
	}
	public static void main(String[] args) 
	{
		launch(args);
		
	}
}