package com.bakery.allocation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bakery.model.Order;
import com.bakery.model.Pack;

public class AllocationParser {
	
	public void parse(Order order, List<Pack> allocatedPacks) {
		System.out.println("================================================ ");
		System.out.println("");
		if (allocatedPacks != null && allocatedPacks.size() > 0) {
			
			Map<Pack, Integer> allocatedPacksMap = deduceAllocationsOfPacks(allocatedPacks);
			BigDecimal cost = calculateTotalCost(allocatedPacks);
			System.out.println(order.getQuantity() + " " + order.getCode().toString() + " " + cost);
			
			List<Pack> sortedPacks =  generateSortedUniquePacks(allocatedPacksMap);
			for (Pack pack : sortedPacks) {
				System.out.println("\t " + allocatedPacksMap.get(pack) + " X " + pack);
			}
			
		} else {
			System.out.println(order.getQuantity() + " " + order.getCode().toString());
			System.out.println( " Not Allocatable :(");
		}
		System.out.println("");
		System.out.println("================================================ ");
	}

	private List<Pack> generateSortedUniquePacks(Map<Pack, Integer> allocatedPacksMap) {
		List<Pack> sortedPacks = new ArrayList<Pack>();
		sortedPacks.addAll(allocatedPacksMap.keySet());
		Collections.sort(sortedPacks, new Comparator<Pack>() {

			@Override
			public int compare(Pack thisPack, Pack thatPack) {
				return Integer.valueOf(thatPack.getUnits()).compareTo(Integer.valueOf(thisPack.getUnits()));
			}
		});
		return sortedPacks;
	}

	private Map<Pack, Integer> deduceAllocationsOfPacks(
			List<Pack> allocatedPacks) {
		Map<Pack, Integer> allocatedPacksMap = new HashMap<Pack, Integer>();
		if (allocatedPacks != null && !allocatedPacks.isEmpty()) {
			for (Pack pack : allocatedPacks) {
				int count = allocatedPacksMap.containsKey(pack) ? allocatedPacksMap.get(pack) : 0;
				count++;
				allocatedPacksMap.put(pack, count);
				
			}
		}
		return allocatedPacksMap;
	}

	private BigDecimal calculateTotalCost(List<Pack> allocatedPacks) {
		BigDecimal totalCost = BigDecimal.ZERO;
		 MathContext mc = new MathContext(8);
		for (Pack pack : allocatedPacks) {
			totalCost = totalCost.add(pack.getPrice(), mc);
		}
		return totalCost.setScale(2);
	}

}
