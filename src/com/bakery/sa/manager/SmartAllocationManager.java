package com.bakery.sa.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bakery.allocation.AllocationEngine;
import com.bakery.allocation.AllocationParser;
import com.bakery.model.Order;
import com.bakery.model.Pack;
import com.bakery.model.Product;
import com.bakery.model.ProductCode;


public class SmartAllocationManager {
	

	static {
		producCatalogtInitialiser();
		orderInitialiser();
	}
	
	private static  Map<ProductCode, Product> productCatalog;
	
	private static List<Order> masterOrder;
	
	
	public static void main(String[] args) {
		
		if (productCatalog != null && masterOrder != null) {
			
			AllocationEngine engine = new AllocationEngine();
			AllocationParser parser = new AllocationParser();
			
			for (Order order : masterOrder) {
				List<Pack> allocatedPacks = engine.allocate(order, productCatalog);
				parser.parse(order, allocatedPacks);
			}
		}
	}
	

	private static void orderInitialiser() {
		
		Order order1 = new Order(10, ProductCode.VS5);
		Order order2 = new Order(14, ProductCode.MB11);
		Order order3 = new Order(13, ProductCode.CF);
		masterOrder = new ArrayList<>();
		
		masterOrder.add(order1);
		masterOrder.add(order2);
		masterOrder.add(order3);
	}


	private static void producCatalogtInitialiser() {
		
		Pack vScrollPk1 = new Pack(3, new BigDecimal(6.99));
		Pack vScrollPk2 = new Pack(5, new BigDecimal(8.99));
		Set<Pack> vScrollPacks = new HashSet<Pack>();
		vScrollPacks.add(vScrollPk1);
		vScrollPacks.add(vScrollPk2);
		Product vScroll = new Product("Vegemite Scrool", ProductCode.VS5, vScrollPacks);
		
		Pack bMuffinPk1 = new Pack(2, new BigDecimal(9.95));
		Pack bMuffinPk2 = new Pack(5, new BigDecimal(16.95));
		Pack bMuffinPk3 = new Pack(8, new BigDecimal(24.95));
		Set<Pack> bMuffinPacks = new HashSet<Pack>();
		bMuffinPacks.add(bMuffinPk1);
		bMuffinPacks.add(bMuffinPk2);
		bMuffinPacks.add(bMuffinPk3);
		Product bMuffin = new Product("Blueberry Muffin", ProductCode.MB11, bMuffinPacks);
		
		Pack croissantPk1 = new Pack(3, new BigDecimal(5.95));
		Pack croissantPk2 = new Pack(5, new BigDecimal(9.95));
		Pack croissantPk3 = new Pack(9, new BigDecimal(16.99));
		Set<Pack> croissantPacks = new HashSet<Pack>();
		croissantPacks.add(croissantPk1);
		croissantPacks.add(croissantPk2);
		croissantPacks.add(croissantPk3);
		Product croissant = new Product("Blueberry Muffin", ProductCode.CF, croissantPacks);
		
		productCatalog = new HashMap<ProductCode, Product>();
		
		productCatalog.put(ProductCode.VS5, vScroll);
		productCatalog.put(ProductCode.MB11, bMuffin);
		productCatalog.put(ProductCode.CF, croissant);
		
	}

}
 