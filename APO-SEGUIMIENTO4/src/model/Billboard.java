package model;
import java.io.Serializable;


public class Billboard implements Serializable {

	
	
	private String width;
	private String height; 
	private String inUse;
	private String brand; 
	

	/**
	 * @param width
	 * @param heihgt
	 * @param inUse
	 * @param brand
	 */
	public Billboard(String width, String height, String inUse, String brand) {
		super();
		this.width = width;  
		this.height = height;
		this.inUse = inUse; 
		this.brand = brand;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @return the heihgt
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @return the inUse
	 */
	public String getIsInUse() {
		return inUse;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
