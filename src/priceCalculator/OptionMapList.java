package priceCalculator;


import java.util.ArrayList;  

/** Class to store a list of all options combinations and base-prices (OptionMap) for a product.
 */

public class OptionMapList {
	private ArrayList<OptionMap> optionMapList;
	
	public OptionMapList() {
		this.optionMapList = new ArrayList<OptionMap>();
	}
	
	public ArrayList<OptionMap> getOptionMapList() {
		return optionMapList;
	}
	
	public void addOptionMap(OptionMap option) {
		optionMapList.add(option);
	}
	
	public OptionMap get(OptionMap o) {
		int index = optionMapList.indexOf(o);
		if(index != -1) {
			OptionMap optionMap = optionMapList.get(index);
			return optionMap;
		}
		return null;
	}
	
	public String toString() {
		String s = "";
		for(int i=0; i < optionMapList.size(); i++) {
			s = s + optionMapList.get(i).toString() + "\n\n";
		}
		return s;
	}
}
