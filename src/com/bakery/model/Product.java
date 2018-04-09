package com.bakery.model;

import java.util.Set;

public class Product {	
	
	private String name;
	
	private ProductCode code;
	
	private Set<Pack> packs;
	
	

	public Product(String name, ProductCode code, Set<Pack> packs) {
		this.name = name;
		this.code = code;
		this.packs = packs;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the packs
	 */
	public Set<Pack> getPacks() {
		return packs;
	}

	/**
	 * @return the code
	 */
	public ProductCode getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Product other = (Product) obj;
		if (code != other.code)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
