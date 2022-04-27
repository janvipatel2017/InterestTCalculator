package gui;

import java.text.NumberFormat;



import javafx.application.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class ComputeInterestDisplay extends Application {

	private TextField principalText, rateText;
	private TextArea displayArea;
	private Slider horizontalSlider;
	
	public void start(Stage primaryStage) {
		
		int sceneWidth = 650, sceneHeight = 400;
		
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setPrefSize(sceneWidth, sceneHeight / 3);
		
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		GridPane fieldsPane = new GridPane();
		fieldsPane.setHgap(horSpaceBetweenNodes);
		fieldsPane.setVgap(verSpaceBetweenNodes);
		fieldsPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		Label principal = new Label("Principal: ");
		principalText = new TextField();
		fieldsPane.add(principal, 0, 0);
		fieldsPane.add(principalText, 1, 0);
		
		Label rate = new Label("Rate(Percentage):  ");
		rateText = new TextField();
		fieldsPane.add(rate, 2, 0);
		fieldsPane.add(rateText, 3, 0);

		Label years = new Label("Number of Years: ");
		fieldsPane.add(years, 0, 1);
		
		horizontalSlider = new Slider();
		horizontalSlider.setMin(1);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(1);
		horizontalSlider.setMajorTickUnit(4);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		horizontalSlider.setSnapToTicks(true);
		fieldsPane.add(horizontalSlider, 1, 1);

		Button simpleButton = new Button("SimpleInterest");
		Button compoundButton = new Button("CompoundInterest");
		Button bothButton = new Button("BothInterests");
		fieldsPane.add(simpleButton, 0, 2);
		fieldsPane.add(compoundButton, 1, 2);
		fieldsPane.add(bothButton, 2, 2);
		
		simpleButton.setOnAction(new ButtonHandler());
		
		compoundButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Interest interest = new Interest();
				double principal = Double.parseDouble(principalText.getText());
				double rate = Double.parseDouble(rateText.getText());
				double years = horizontalSlider.getValue();
				String finalText = "";
				finalText += "Principal: " + NumberFormat.getCurrencyInstance().format(principal);
				finalText += ", Rate: " + rate + "\n";
				finalText += "Year, Compound Interest Amount\n";
				int[] listOfYears = new int[(int) years];
				for (int i = 0; i < listOfYears.length; i++) {
					int newYear = i + 1;
					finalText += newYear + "-->" + "$" + String.format("%,.2f", interest.computeCompoundInterest(principal, rate, newYear));
					finalText += "\n";
				}
				displayArea.setText(finalText);
			}
		});
		
		bothButton.setOnAction(e -> {
			Interest interest = new Interest();
			double bothPrincipal = Double.parseDouble(principalText.getText());
			double bothRate = Double.parseDouble(rateText.getText());
			double bothYears = horizontalSlider.getValue();
			String finalText = "";
			finalText += "Principal: " + NumberFormat.getCurrencyInstance().format(bothPrincipal);
			finalText += ", Rate: " + bothRate + "\n";
			finalText += "Year, Simple Interest Amount, Compound Interest Amount\n";
			int[] listOfYears = new int[(int) bothYears];
			for (int i = 0; i < listOfYears.length; i++) {
				int newYear = i + 1;
				finalText += newYear + "-->" + "$" + String.format("%,.2f", interest.computeSimpleInterest(bothPrincipal, bothRate, newYear));
				finalText += "-->$" + String.format("%,.2f", interest.computeCompoundInterest(bothPrincipal, bothRate, newYear));
				finalText += "\n";
			}
			displayArea.setText(finalText);
		});
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(displayArea);
		borderPane.setBottom(fieldsPane);
		
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			Interest interest = new Interest();
			double principal = Double.parseDouble(principalText.getText());
			double rate = Double.parseDouble(rateText.getText());
			double years = horizontalSlider.getValue();
			String finalText = "";
			finalText += "Principal: " + NumberFormat.getCurrencyInstance().format(principal);
			finalText += ", Rate: " + rate + "\n";
			finalText += "Year, Simple Interest Amount\n";
			int[] listOfYears = new int[(int) years];
			for (int i = 0; i < listOfYears.length; i++) {
				int newYear = i + 1;
				finalText += newYear + "-->" + "$" + String.format("%,.2f", interest.computeSimpleInterest(principal, rate, newYear));
				finalText += "\n";
			}
			displayArea.setText(finalText);
		}
	}
}
