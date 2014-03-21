package sed.adapter;

import db.DB;
import modelviews.ModelProductCardView;
import sed.data.ProductCard;
import sed.view.ProductCardView;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ProductAdapterDB extends CursorAdapter{


	public ProductAdapterDB(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		ProductCardView view = new ProductCardView(context, new ModelProductCardView());
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		fillingViewFields((ProductCardView)view, DB.decode(cursor));
	}
	
	private void fillingViewFields(ProductCardView view, ProductCard product) {

		for (int i =0;i<view.modGetChildCount();i++){
			View fieldOfView = view.modGetChildAt(i);
			String nameField = (String)fieldOfView.getTag();

			if (nameField != null && product.getFieldByName(nameField)!= null){
				((TextView)fieldOfView).setText( (String)product.getFieldByName(nameField).toString() );
			}
			
		}
	}

}
