package ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Billboard;
import model.InfrastructureDepartment;

public class BillboardControllerGUI {
	
	
	private InfrastructureDepartment infDepart; 
	
	public BillboardControllerGUI() {
		
		infDepart = new InfrastructureDepartment(); 
		
	}
	
	@FXML
	private Pane mainPane;
	
    @FXML
    private TextField txtBBData;

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Billboard-Menu.fxml"));
		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		mainPane.getChildren().setAll(menu);
    }


  

    @FXML
    void showData(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Show-Billboard.fxml"));
		fxmlloader.setController(this);
		Parent showBillboards = fxmlloader.load();
		mainPane.getChildren().setAll(showBillboards);
		
		
		infDepart.setCounter(0);
		impData();		
		tableView(); 
    }
	@FXML
	void start(ActionEvent event) {

	}

	void startApplication() throws IOException  {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Billboard-Menu.fxml"));
		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		mainPane.getChildren().setAll(menu);
		impData();
	}
	  
	@FXML
	  void addBillboard(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Add-Billboard.fxml"));
		fxmlloader.setController(this);
		DialogPane dialoguePane = fxmlloader.load();
		
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(dialoguePane);
		dialog.showAndWait();
		
	    }
	
		
	   @FXML
	    private TableView<Billboard> tbBB;
	    
	    @FXML
	    private TableColumn<Billboard, String> tcWidth;

	    @FXML
	    private TableColumn<Billboard, String> tcHeight;

	    @FXML
	    private TableColumn<Billboard, String> tcInUse;

	    @FXML
	    private TableColumn<Billboard, String> tcBrand;

	    @FXML
	    private TextField totalBB;
	     
	    private ObservableList<Billboard>observableList;

	    public void tableView() {

	    	observableList = FXCollections.observableArrayList(infDepart.getBillboard());
	    	tbBB.setItems(observableList);
	    	tcWidth.setCellValueFactory(new PropertyValueFactory<Billboard, String>("width"));
	    	tcHeight.setCellValueFactory(new PropertyValueFactory<Billboard, String>("height"));
	    	tcInUse.setCellValueFactory(new PropertyValueFactory<Billboard, String>("inUse"));
	    	tcBrand.setCellValueFactory(new PropertyValueFactory<Billboard, String>("brand"));

	    	String totalBillb = String.valueOf(infDepart.getCounter());
	    	totalBB.setText(totalBillb);

	    }

	    public void impData() throws IOException {

	    	infDepart.importData();
	    	
	    }
	    
	    @FXML
	    public void txtGenerate() throws IOException{
	    	
	    	
	    	String data = txtBBData.getText();
	    	String [] billboard = data.split("\\++");
	    	Billboard  newBillboard = new Billboard(billboard[0],billboard[1], billboard[2], billboard[3]);
	    	
	      if(infDepart.addBillboard(newBillboard)) {
	    	  
	    		String title = "Data Generate";
	    		String text = "Billboard was successfully generated";
	    		alert(title, text);
	      }
	      	txtBBData.setText(null);
	    	infDepart.saveBillboards();
	    	infDepart.savePrimitiveData();
	    }
	    
	    @FXML
	    void dangerReports(ActionEvent event) throws IOException {
	    	
	    	infDepart.exportDangerousBillboardReport();
	    	String title = "Dangerous Billboard Report";
    		String text = "Dangerous Billboard Report Billboard was successfully created as a txt file (BillboardDR.txt)";
    		alert(title, text);

	    }
	    
	    public void alert(String title, String text) {
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle(title);
	    	alert.setContentText(text);
	    	alert.showAndWait();
	    }
	    
    @FXML
    void closeApp(ActionEvent event) {
    	Platform.exit();
    }
	    
}

