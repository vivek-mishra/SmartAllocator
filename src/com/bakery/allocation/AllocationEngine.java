package com.bakery.allocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.bakery.model.Order;
import com.bakery.model.Pack;
import com.bakery.model.Product;
import com.bakery.model.ProductCode;

public class AllocationEngine {
	
	
	public List<Pack> allocate(Order order, Map<ProductCode, Product> productCatalog){
		
		List<Pack> allcatedPacks =  null;
		ProductCode pCode = order.getCode();
		Product product = productCatalog.get(pCode);
		
		if (product != null) {
			
			Map<Integer, Pack> productPacks = getProductPacks(product);
			List<Integer> sortedUnits = getSortedProductPackUnits(product);
			
			List<Integer> allocatedPacksUnits = coreAllcation(sortedUnits, order.getQuantity());
			
			allcatedPacks = deriveAllocatedPacksFromAllocatedPackUnits(productPacks, allocatedPacksUnits);
			
		}
		return allcatedPacks;
	}

	private List<Pack> deriveAllocatedPacksFromAllocatedPackUnits(
			Map<Integer, Pack> productPacks, List<Integer> allocatedPacksUnits) {
		List<Pack> allcatedPacks = new ArrayList<Pack>();
		if (allocatedPacksUnits != null && allocatedPacksUnits.size() > 0) {
			for (Integer unitSize : allocatedPacksUnits) {
				allcatedPacks.add(productPacks.get(unitSize).getCopy());
			}
		}

		return allcatedPacks;
	}

	private List<Integer> coreAllcation(List<Integer> sortedUnits, int quantity) {

		Stack<Integer> confirmedAllocations = new Stack<Integer>();
		int unitsSize = sortedUnits.size();

		int index = unitsSize - 1;
		int remainder = quantity;
		int currentUnitSize = sortedUnits.get(index);
		
		while (remainder != 0) {
			if (remainder >= currentUnitSize) {
				confirmedAllocations.push(currentUnitSize);
				remainder = remainder - currentUnitSize;
			} else if (remainder < currentUnitSize  && index >= 1) {
				index--;
				currentUnitSize = sortedUnits.get(index);
			} else if (remainder < currentUnitSize)  {
				int popped = confirmedAllocations.pop();
				remainder = remainder + popped;
				if (index == 0 && (remainder % currentUnitSize != 0)) {
					if (!confirmedAllocations.isEmpty()) {
						popped = confirmedAllocations.pop();
						remainder = remainder + popped;
						index++;
						currentUnitSize = sortedUnits.get(index);
					} else {
						break;
					}
				}
			} 
		}

		return confirmedAllocations;
	}


	private Map<Integer, Pack> getProductPacks(Product product) {
		Map<Integer, Pack> productPacks = new HashMap<Integer, Pack>();
		if (product != null && product.getPacks() != null) {
			for (Pack pack : product.getPacks()) {
				productPacks.put(pack.getUnits(), pack);
			}
		}
		return productPacks;
	}

	private List<Integer> getSortedProductPackUnits(Product product) {
		List<Integer> sortedProductPackUnits = new ArrayList<Integer>();
		for (Pack pack: product.getPacks()) {
			sortedProductPackUnits.add(pack.getUnits());
		}
		Collections.sort(sortedProductPackUnits);
		return sortedProductPackUnits;
	}

}
