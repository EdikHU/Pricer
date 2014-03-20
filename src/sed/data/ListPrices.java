package sed.data;

import java.util.ArrayList;


public class ListPrices extends ArrayList<Price>{

	private static final long serialVersionUID = 1575848803863498468L;

	public ListPrices(Price... prices) {
		for (Price price: prices){
			this.add(price);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ListPrices [");
		for (Price elm : this){
			sb.append(elm);
		}
		sb.append("]");
		return sb.toString();
	}

	
	
	
}
