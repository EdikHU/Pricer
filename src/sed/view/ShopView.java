package sed.view;

import db.DB;
import android.content.Context;
import android.database.Cursor;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopView extends FrameLayout{

	private Context context;
	private TextView tv;
	private TextView tv2;

	public ShopView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		LinearLayout la = new LinearLayout(context);
		addView(la);
		
		tv = new TextView(context);
		tv.setTag(DB.T_SHOP_NAME);
		la.addView(tv);

		tv2 = new TextView(context);
		tv2.setTag(DB.T_SHOP_ID);
		la.addView(tv2);
		
	}

	public void setData(Cursor cursor) {
		tv2.setText(""+cursor.getLong(0));
		tv.setText(cursor.getString(1));
		System.out.println("HERE2 "+cursor.getColumnCount());
	}

	public void removeFromDB() {
		DB.shopRemoveItem(tv2.getText().toString());
	}

}
