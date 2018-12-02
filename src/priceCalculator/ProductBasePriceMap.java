package priceCalculator;

import java.util.HashMap;
import java.util.Map;

/** Class stores key-value pairs, with the keys being a product-type and the value being all of the option
 * combinations for that product-type, along with their respected base-prices (OptionMapList)
 */

public class ProductBasePriceMap {
	private Map<String, OptionMapList> basePriceMap;
	
	public ProductBasePriceMap(Map<String, OptionMapList> basePriceMap) {
		this.basePriceMap = new HashMap<String, OptionMapList>(basePriceMap);
	}
	
	public Map<String, OptionMapList> getBasePriceMap() {
		return basePriceMap;
	}
	
	public String toString() {
		String s = "";
		for(String productType : basePriceMap.keySet()) {
			s = s + "product-type: " + productType + "\n" + basePriceMap.get(productType).toString() + "\n\n\n";
		}
		return s;
	}
}
