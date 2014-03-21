package sed.adapter;

import java.util.ArrayList;

import modelviews.ModelProductCardView;

import sed.data.ProductCard;
import sed.view.ProductCardView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<ProductCard> products;

	public ProductAdapter(Context context, ArrayList<ProductCard> products) {
		this.context = context;
		this.products  = products;
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProductCardView view = (ProductCardView)convertView;
		if (view == null){
			view = new ProductCardView(context, new ModelProductCardView());
		}
		
		System.out.println("-->> "+products.get(position));
		fillingViewFields(view, products.get(position));
		
		return view;
	}

	private void fillingViewFields(ProductCardView view, ProductCard product) {
		//LinearLayout view = (LinearLayout) container.getChildAt(0);
		for (int i =0;i<view.modGetChildCount();i++){
			View fieldOfView = view.modGetChildAt(i);
			
			String nameField = (String)fieldOfView.getTag();
			System.out.println("  >> "+fieldOfView.getClass().getName());
			if (nameField != null){
				System.out.println("   > ["+nameField+"] "+product.getFieldByName(nameField));
				((TextView)fieldOfView).setText( (String)product.getFieldByName(nameField).toString() );
			}
			
		}
	}

}
