package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {

	public String BILLBOARD_PRIMITIVE_FILE = "data/Billboard.bbd";
	public String BILLBOARD_TXT = "data/BillboardDR.txt";
	public String BILLBOARD_LIST = "data/BillboardDataExportedPan.csv";
	private List <Billboard> billboard;
	private int counter =0;  
	
	/**
	 * @return the billboard
	 */
	public List<Billboard> getBillboard() {
		return billboard;
	}



	
	public InfrastructureDepartment(){
		billboard = new ArrayList<Billboard>();
		
	}
	//
	//	public void addBillboard(double w,double h, boolean iu, String b ){
	//
	//	}



	public boolean addBillboard(Billboard billboardInfo){

		if(billboard.add(billboardInfo)) {
			
			 counter++; 
			return true; 
		}
		else {
			return false;			
		}
	}

	/**
	 * @return the billboard




	/**
	 * @param billboard the billboard to set
	 */
	public void setBillboard(List<Billboard> billboard) {
		this.billboard = billboard;
	}



	/**
	 * @return the bILLBOARD_LIST
	 */
	public String getBILLBOARD_LIST() {
		return BILLBOARD_LIST;
	}



	public void saveBillboards() throws IOException{
		
		FileWriter fileW = new FileWriter(BILLBOARD_LIST, false);
		
		for(int i = 0; i < billboard.size(); i++) {
			
			Billboard  newBillboard = billboard.get(i);
			fileW.write(newBillboard.getWidth()+"|"+newBillboard.getHeight()+"|"+
					newBillboard.getIsInUse()+"|"+newBillboard.getBrand()+"\n");
			
		}
		fileW.close();
	}

	public void savePrimitiveData() throws FileNotFoundException, IOException{
		
		ObjectOutputStream oos = new  ObjectOutputStream(new FileOutputStream(BILLBOARD_PRIMITIVE_FILE));
		oos.writeObject(billboard);
		oos.close();

	}

	public void exportDangerousBillboardReport() throws IOException{

		FileWriter fileD = new FileWriter(BILLBOARD_TXT , false);
		
		
		fileD.write("===========================\n"
				+   "DANGEROUS BILLBOARD REPORT\n"
				+   "=========================== \n");
				
		for(int i = 0; i < billboard.size(); i++) {
			
			Billboard  newBillboard = billboard.get(i);
			
			double w = Double.parseDouble(newBillboard.getWidth());
			double h = Double.parseDouble(newBillboard.getHeight());
			
			if(w+h>=160) {
				fileD.write(i + 1 + ". " + "Billboards " + newBillboard.getBrand() + 
                        " with an area of " + w*h + " cm2\n\n");
			}
			
		}
		fileD.close();
	}

	@Override
	public String toString() {
		return "InfrastructureDepartment []";
	}

	public void importData() throws IOException {
		
		billboard.removeAll(billboard);
		
		BufferedReader br = new BufferedReader(new FileReader(BILLBOARD_LIST));
		String line = br.readLine();

		while(line != null) {

			String [] parts = line.split("\\|");
			Billboard  newBillboard = new Billboard(parts[0], parts[1], parts[2], parts[3]);
			addBillboard(newBillboard);

			line = br.readLine();
		}

		br.close();

	}



	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}




	public int getCounter() {
		return counter;
	}

}
