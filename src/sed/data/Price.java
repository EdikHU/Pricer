package sed.data;

import java.util.Date;

public class Price {

	float roubles;
	String place;
	Date date; 
	
	public Price(float roubles, String place, Date date) {
		this.roubles = roubles;
		this.place = place;
		this.date = date;
	}

	public Price(double roubles, String place, Date date) {
		this.roubles = (float)roubles;
		this.place = place;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Price [roubles=" + roubles + ", place=" + place + ", date="
				+ date + "]";
	}

	
}
