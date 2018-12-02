package priceCalculator;

/** Class for a single item in a cart. Stores data of its product-type, all options, artist-markup, and quantity
 */

public class CartItem {
	private String productType;
	private OptionMap options;
	private int artistMarkup;
	private int quantity;
	
	public CartItem(String productType, OptionMap options, int artistMarkup, int quantity) {
		this.productType = productType;
		this.options = options;
		this.artistMarkup = artistMarkup;
		this.quantity = quantity;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public OptionMap getOptions() {
		return options;
	}

	public int getArtistMarkup() {
		return artistMarkup;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String toString() {
		return  "product-type: " + productType + "\n" + options.toString()+ ")\nartist-markup: " 
	+ artistMarkup + "\nquantity: " + quantity;

	}
}





