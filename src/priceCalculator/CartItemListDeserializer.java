package priceCalculator;

import java.lang.reflect.Type; 
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/** Custom deserializer for CartItemList.  Breaks down the data of each cartItem and stores it in a list.
 */

public class CartItemListDeserializer implements JsonDeserializer<CartItemList>{

	@Override
	public CartItemList deserialize(JsonElement element, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonArray jsonArray = element.getAsJsonArray();
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		
		for(Object o : jsonArray) {
			JsonObject cartItem = (JsonObject) o;
			
			String productType = cartItem.get("product-type").getAsString();
			
			JsonObject options = cartItem.get("options").getAsJsonObject();
			OptionMap optionMap = new OptionMap();
			for(Map.Entry<String, JsonElement> entry : options.entrySet()) {
				optionMap.addType(entry.getKey());
				optionMap.addDescription(entry.getKey(), entry.getValue().getAsString());
			}
			
			int artistMarkup = cartItem.get("artist-markup").getAsInt();
			int quantity = cartItem.get("quantity").getAsInt();
			
			CartItem c = new CartItem(productType, optionMap, artistMarkup, quantity);
			cartItems.add(c);
		}
		
		return new CartItemList(cartItems);
	}
	

}
