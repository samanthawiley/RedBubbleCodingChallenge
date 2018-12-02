package priceCalculator;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import java.io.FileReader; 
import java.util.ArrayList;
import java.util.Map;

/** Calculates cart total given JSON file name of a cart and JSON file name of base-prices. Uses product-type of each
 * cart item as a key to find all options of that product-type. It then matches the desired cart item option combination 
 * to its respected base-price.
 * 
 */
public class PriceCalculator {
	public static void main(String [] args) throws Exception{
		GsonBuilder gsonBuilder1 = new GsonBuilder();
		gsonBuilder1.registerTypeAdapter(CartItemList.class, new CartItemListDeserializer());
		Gson gson1 = gsonBuilder1.create();
		
		GsonBuilder gsonBuilder2 = new GsonBuilder();
		gsonBuilder2.registerTypeAdapter(ProductBasePriceMap.class, new ProductBasePriceMapDeserializer());
		Gson gson2 = gsonBuilder2.create();
		
		CartItemList cartItemList = gson1.fromJson(new FileReader(args[0]), CartItemList.class);
		ProductBasePriceMap productBasePriceMap = gson2.fromJson(new FileReader(args[1]), ProductBasePriceMap.class);
		
		calculateCartTotal(cartItemList, productBasePriceMap);
	}
	
	public static void calculateCartTotal(CartItemList cartItemList, ProductBasePriceMap productBasePriceMap) {
		ArrayList<CartItem> cartItems = cartItemList.getCartItemList();
		Map<String, OptionMapList> basePrices = productBasePriceMap.getBasePriceMap();
		int cartTotal = 0;
		
		for(int i=0; i < cartItems.size(); i++) {
			String productType = cartItems.get(i).getProductType();
			OptionMap options = cartItems.get(i).getOptions();
			int artistMarkup = cartItems.get(i).getArtistMarkup();
			int quantity = cartItems.get(i).getQuantity();
			
			OptionMapList optionMaps = basePrices.get(productType);
			OptionMap basePriceOption = optionMaps.get(options);
			int basePrice = basePriceOption.getBasePrice();
			
			int itemTotal = (basePrice + Math.round(((float) basePrice * artistMarkup/100))) * quantity;
			cartTotal += itemTotal;
		}
		System.out.println(cartTotal + "\n");
	}
}
