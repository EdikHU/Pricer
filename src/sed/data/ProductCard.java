package sed.data;


public class ProductCard {

	String name;
	String group;
	String category;
//	Link image;
	ListPrices prices;

	
	public ProductCard(String name, String group, String category,
			ListPrices prices) {
		this.name = name;
		this.group = group;
		this.category = category;
		this.prices = prices;
	}


	@Override
	public String toString() {
		return "ProductCard [name=" + name + ", group=" + group + ", category="
				+ category + ", prices=" + prices + "]";
	}


	public Object getFieldByName(String nameField) {
		Object returnObject = null;
		if ("name".equals(nameField)){
			returnObject = name;
		} else if ("group".equals(nameField)){
			returnObject = group;
		} else if ("category".equals(nameField)){
			returnObject = category;
		} else if ("prices".equals(nameField)){
			returnObject = prices;
		}
		return returnObject;
	}
	
	
	
}
