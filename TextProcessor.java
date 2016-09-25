package com.ebay_watchDog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class TextProcessor {

	public HashMap<String, Double> getFilteredPriceAndURL(String link,double minPrice,double maxPrice){
		try {
			//gtx 970
			//URL url = new URL("http://www.ebay.co.uk/sch/i.html?_from=R40&_sacat=0&LH_BIN=1&_ipg=200&_nkw=gtx+970&_sop=15");		
			URL url = new URL(link);
			//File fl= new File("test.html");
			File fl= new File("/opt/tomcat/webapps/ebay_watchDog/ebay_input.html");	
			FileUtils.copyURLToFile(url, fl);
			FileReader fr = new FileReader(fl);
			BufferedReader reader;
			reader = Files.newBufferedReader(fl.toPath());
			String line = null;
			//String price_p = "£[0-9]+([,.][0-9]{1,2})?";
			String price_p = "£[\\d,]*[\\d]+[\\.]?[\\d]*";
			String url_p ="\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
			// Create a Pattern object
			Pattern Price_pattern = Pattern.compile(price_p);
			Pattern Url_pattern = Pattern.compile(url_p);
	
			String url_temp = null;
			HashMap<String, Double> hm=new HashMap<String, Double>();
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				//create matcher object
				Matcher m_price = Price_pattern.matcher(line);
				Matcher m_url = Url_pattern.matcher(line);
				if (m_url.find()){
					//System.out.println("Found value: " + m_url.group(0) );
					url_temp=m_url.group(0);
				}
				if (m_price.find( )) {
					//System.out.println("Found value: " + m_price.group(0) );
					try{
						Double price = Double.parseDouble(m_price.group(0).replace("£","").replace(",",""));
						if(price>=minPrice && price <= maxPrice){
							//System.out.println(price +","+ url_temp);
							hm.put(url_temp, price);
						}	
					}catch(NumberFormatException e2){
						e2.printStackTrace();
					}
				} else {
					//System.out.println("NO MATCH");
				}
			}
			reader.close();
			fr.close();
			return hm;
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
