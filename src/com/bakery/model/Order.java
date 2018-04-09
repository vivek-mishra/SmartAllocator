package com.bakery.model;

public class Order {
	
	private int quantity;
	
	private ProductCode code;
	
	public Order(int quantity, ProductCode code) {
		super();
		this.quantity = quantity;
		this.code = code;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the code
	 */
	public ProductCode getCode() {
		return code;
	}

}
