package sed.data;

import java.util.Date;

public class Price {

	float price;
	String shop;
	Date date; 
	
	public Price(float price, String shop, Date date) {
		this.price = price;
		this.shop = shop;
		this.date = date;
	}

	public Price(double price, String shop, Date date) {
		this.price = (float)price;
		this.shop = shop;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + ", shop=" + shop + ", date="
				+ date + "]";
	}

	
}
