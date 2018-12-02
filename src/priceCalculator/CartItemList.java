package priceCalculator;

import java.util.ArrayList; 

/** Class for an entire cart. It has all cartItems stored in a list
 */

public class CartItemList {
	private ArrayList<CartItem> cartItemList;
	
	public CartItemList(ArrayList<CartItem> cartItemList) {
		this.cartItemList = new ArrayList<CartItem>(cartItemList);
	}
	
	public ArrayList<CartItem> getCartItemList() {
		return cartItemList;
	}
	
	public String toString() {
		String s = "";
		for(int i=0; i < cartItemList.size(); i++) {
			s = s + cartItemList.get(i).toString() + "\n\n";
		}
		return s;
	}

}
