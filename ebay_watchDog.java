/*
 * Author: Ludek Navratil
 *to be done:
 * - new deals must be identified not only with the link but also price with the same link 
 * can change over time
 * 
 */

package com.ebay_watchDog;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class ebay_watchDog {
	
	 @RequestMapping(value = "/watchdog", method = RequestMethod.GET)
	   public ModelAndView watchDog() {
		 ModelAndView model=new ModelAndView("watchdog", "command", new TaskInfo());
	     model.addObject("tasks", ScheduledTasks.getTasks());
	     
		 return model;
	   }
	 
	 @RequestMapping(value = "/watchdog", method = RequestMethod.POST)
	   public ModelAndView scheduleWatchDog(@ModelAttribute("SpringWeb")TaskInfo taskInfo, 
	   ModelAndView model) {	
		 ModelAndView model2=new ModelAndView("watchdog", "command", new TaskInfo());
	     ScheduledTasks.setNewTask(taskInfo);
	     model2.addObject("tasks", ScheduledTasks.getTasks());
	     
		 return model2;
	   }
	 
	 @RequestMapping(value = "/removeLink", method = RequestMethod.POST)
	   public String removeLink(@RequestParam("link") String link) {
	     ScheduledTasks.removeTask(link);
	     return "redirect:/watchdog";
	   }
	
}