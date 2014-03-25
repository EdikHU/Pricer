package sed.view;

import db.DB;
import android.content.Context;
import android.database.Cursor;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopView extends FrameLayout{

	private Context context;
	private TextView tvName;
	private TextView tvId;

	public ShopView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		LinearLayout la = new LinearLayout(context);
		addView(la);
		
		tvName = new TextView(context);
		tvName.setTag(DB.T_SHOP_NAME);
		la.addView(tvName);

		tvId = new TextView(context);
		tvId.setTag(DB.T_SHOP_ID);
		la.addView(tvId);
		
	}

	public void setData(Cursor cursor) {
		tvId.setText(""+cursor.getLong(0));
		tvName.setText(cursor.getString(1));
	}

	public void removeFromDB() {
		DB.shopRemoveItem(tvId.getText().toString());
	}

	public String getName() {
		return tvName.getText().toString();
	}

}
