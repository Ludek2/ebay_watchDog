package com.ebay_watchDog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TaskInfo {
	private int userId;
	private String email;
	private Double minPrice;
	private Double maxPrice;
	private String link;
	private int numberOfScans;
	private LocalDateTime created;
	private LocalDateTime expires;
	private HashMap<String, Double> allDealsFound = 
			new HashMap<String, Double>();
	private HashMap<String, Double> newDealsFound = 
			new HashMap<String, Double>();
	
	TaskInfo(){
		
	}
	
	TaskInfo(String link){
		setLink(link);
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public int getNumberOfScans() {
		return numberOfScans;
	}


	public void setNumberOfScans(int numberOfScans) {
		this.numberOfScans = numberOfScans;
	}


	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public LocalDateTime getExpires() {
		return expires;
	}


	public void setExpires(LocalDateTime expires) {
		this.expires = expires;
	}


	public HashMap<String, Double> getAllDealsFound() {
		return this.allDealsFound;
	}
	
	public void setDealsFound(HashMap<String, Double> deals){
		this.allDealsFound=deals;
	}

	public HashMap<String, Double> getNewDealsFound() {
		return newDealsFound;
	}

	public void addNewDealsFound(HashMap<String, Double> newDealsFound) {
		  ArrayList<String> duplicateLinks =
				  new ArrayList<String>();
		
		  Set set = newDealsFound.entrySet();
	      Iterator i = set.iterator();
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         //System.out.print(me.getValue() + ",");
	         //System.out.println(me.getKey());
	         if(this.allDealsFound.containsKey(me.getKey())){
	        	 duplicateLinks.add(me.getKey().toString());
	         }
	      }
	      for(int i2=0; i2<duplicateLinks.size(); i2++){
	    	  newDealsFound.remove(duplicateLinks.get(i2));
	      }
		this.newDealsFound = newDealsFound;
		this.allDealsFound.putAll(newDealsFound);
	}
}
