package com.ebay_watchDog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;



public class ScheduledTasks {
	@Autowired
	private TextProcessor tProcessor;
	@Autowired
    private Mail mail;
	
	public static Set<TaskInfo> tasks= new HashSet<TaskInfo>();
	int i=0;
	
	
	public static void setNewTask(TaskInfo task){
		tasks.add(task);
	}
	
    public static void removeTask(String link){
    	for (Iterator<TaskInfo> i = tasks.iterator(); i.hasNext();) {
    	    TaskInfo element = i.next();
    	    if (element.getLink().equals(link)) {
    	        i.remove();
    	        break;
    	    }
    	}
	}
    
    public static ArrayList<String> getLinks(){
    	ArrayList<String> links = new ArrayList<String>();
    	
    	for(Iterator<TaskInfo> it=tasks.iterator(); it.hasNext();){
    		TaskInfo taskInfo = it.next();
    		links.add(taskInfo.getLink());
    	}
    	return links;
    }

	public static Set<TaskInfo> getTasks() {
		return tasks;
	}
    
    public void scheduledTask(){
    	i++;
    	System.out.println("Task execution number " +i);
    	
    	for(Iterator<TaskInfo> it=tasks.iterator(); it.hasNext();){
    		TaskInfo taskInfo = it.next();
    		HashMap<String, Double> results = tProcessor.getFilteredPriceAndURL(
    				taskInfo.getLink(), taskInfo.getMinPrice(), taskInfo.getMaxPrice());
    		taskInfo.addNewDealsFound(results);
    		
    	    if(!taskInfo.getNewDealsFound().isEmpty()){
    	    	String message = "New deals found: \n";
    	    	//address etc
    	    	Set set = taskInfo.getNewDealsFound().entrySet();
  	      		Iterator i = set.iterator();
  	      		while(i.hasNext()) {
  	      			Map.Entry me = (Map.Entry)i.next();
  	      			message += "£" + me.getValue() + "," + me.getKey() + "\n\n";
  	      			//System.out.print(me.getValue() + ",");
  	      			//System.out.println(me.getKey());      
  	      		}
  	      	System.out.println(message);
  	        mail.sendSimpleMail(taskInfo.getEmail(), 
  	        		"New ebay deal", message);
  	      	}
    	  }
        }
	
}
