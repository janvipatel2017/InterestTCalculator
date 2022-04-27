package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class InterestTableGUI extends Application {

	public void start(Stage primaryStage) {
		ComputeInterestDisplay compute = new ComputeInterestDisplay();
		compute.start(primaryStage);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}


}
