package com.bakery.model;

import java.math.BigDecimal;

public class Pack {

	private int units;

	private BigDecimal price;

	public Pack(int units, BigDecimal price) {
		super();
		this.units = units;
		this.price = price;
	}

	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	public Pack getCopy() {
		return new Pack(this.getUnits(), new BigDecimal(this.getPrice()
				.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + units;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pack other = (Pack) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (units != other.units)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + this.units + " $" + this.price.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}
}
