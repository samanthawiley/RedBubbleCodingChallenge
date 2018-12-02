package priceCalculator;

import java.util.Collections; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Class to hold data of all options in key-value pairs. If option combination has base-price, it is able to store
 * this as well.
 */

public class OptionMap {
	private Map<String, Set<String>> options;
	private int basePrice = 0;
	
	
	public OptionMap() {
		this.options = new HashMap<String, Set<String>>();
	}
	
	public Map<String, Set<String>> getOptionMap() {
		return options;
	}
	
	public int getBasePrice() {
		return basePrice;
	}
	
	public void addType(String type) {
		Set<String> s = new HashSet<String>();
		options.put(type, s);
	}
	
	public void addDescription(String type, String description) {
		Set<String> s = options.get(type);
		s.add(description);
		options.put(type, s);
	}
	
	public void addBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	
	@Override
	public boolean equals(Object o) {
		OptionMap p = (OptionMap) o;
		Map<String, Set<String>> pOptions = p.getOptionMap();
		
		for(String type : options.keySet()) {
			if(pOptions.containsKey(type)) {
				if(Collections.disjoint(options.get(type), pOptions.get(type))) return false;
			}
		}
		return true;	
	}
	
	public String toString() {
		String s = "options:\n( ";
		for(String type : options.keySet()) {
			s = s + type + ":" + options.get(type).toString() + " ";
		}
		s = s + ")";
		if(basePrice != 0) {
			s = s + "\nbase-price: " + basePrice;
		}
		return s;
	}
}
