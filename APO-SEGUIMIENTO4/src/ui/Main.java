package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private BillboardControllerGUI controller; 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Main-Pane.fxml"));
		controller = new BillboardControllerGUI();
		fxmlloader.setController(controller);
		Parent root = fxmlloader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		controller.startApplication();
	}
}
