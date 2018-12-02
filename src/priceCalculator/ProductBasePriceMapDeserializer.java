package priceCalculator;

import java.lang.reflect.Type ;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/** Custom deserializer for ProductBasePriceMap. Breaks down data of all base-prices, and stores them in key-value 
 * pairs, where the key is the product-type and the value is all of its option and base-price data. 
 */

public class ProductBasePriceMapDeserializer implements JsonDeserializer<ProductBasePriceMap>{

	@Override
	public ProductBasePriceMap deserialize(JsonElement element, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonArray jsonArray = element.getAsJsonArray();
		Map <String, OptionMapList> basePrices = new HashMap<String, OptionMapList>();
		
		for(Object o : jsonArray) {
			JsonObject bp = (JsonObject) o;
			
			String productType = bp.get("product-type").getAsString();
			
			JsonObject options = bp.get("options").getAsJsonObject();
			OptionMap optionMap = new OptionMap();
			for(Map.Entry<String, JsonElement> entry : options.entrySet()) {
				String optionType = entry.getKey();
				JsonArray optionDescriptions = entry.getValue().getAsJsonArray();
				optionMap.addType(optionType);
				
				for(int i=0; i < optionDescriptions.size(); i++) {
					optionMap.addDescription(optionType, optionDescriptions.get(i).getAsString());
				}
			}
			
			int basePrice = bp.get("base-price").getAsInt();
			optionMap.addBasePrice(basePrice);
			
			if(!basePrices.containsKey(productType)) {
				OptionMapList oml = new OptionMapList();
				oml.addOptionMap(optionMap);
				basePrices.put(productType, oml);
			}
			else {
				OptionMapList oml = basePrices.get(productType);
				oml.addOptionMap(optionMap);
				basePrices.put(productType, oml);
			}
		}
	
		return new ProductBasePriceMap(basePrices);
	}

}
