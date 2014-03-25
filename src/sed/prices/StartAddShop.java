package sed.prices;

import sed.view.ShopView;
import db.DB;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StartAddShop extends Activity{

	private static EditText shopName;
	private Button okBtn;
	private ListView lv;
	private static ModCursorAdapter adapter;
	@SuppressWarnings("unused")
	private static long shop_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_add_shop);
		shopName = (EditText)findViewById(R.id.start_add_shop_editText);
		okBtn = (Button)findViewById(R.id.start_add_shop_ok_but);
		
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = shopName.getText().toString();
				DB.shopAddOrReplace(str);
				adapter.changeCursor(DB.getShopAll());
			}
		});
		
		lv = (ListView)findViewById(R.id.start_add_shop_listView);
		//adapter = new ModCursorAdapter(getBaseContext(), R.layout.start_add_shop_item, DB.getShopAll(), new String[] {"name"}, new int[]{R.id.start_add_shop_item}, 0);
		adapter = new ModCursorAdapter(getBaseContext(), DB.getShopAll(), true);
		lv.setAdapter(adapter );
		//registerForContextMenu(lv);
		
	}
	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//		super.onCreateContextMenu(menu, v, menuInfo);
//		menu.add(0, 0, 0, "Here One");
//	}
	
	static class ModCursorAdapter extends CursorAdapter{

		public ModCursorAdapter(Context context, Cursor c, boolean autoRequery) {
			super(context, c, autoRequery);
		}


		@Override
		public void bindView(View cv, Context context, Cursor cursor) {
//			cv.setOnLongClickListener(null);
//			cv.setOnLongClickListener(new OnLongClickListener() {
//				@Override
//				public boolean onLongClick(View v) {
////					System.out.println("HERRREER");
////					//DB.shopRemoveItem(((TextView)v).getText().toString());
////					((ShopView)v).removeFromDB();
////					adapter.changeCursor(DB.getShopAll());
//					StartAddShop.setForModify((ShopView)v);
//					return true;
//				}
//			});
			((ShopView)cv).setData(cursor);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return new ShopView(context);
		}
	}

	protected static void setForModify(ShopView v) {
		shop_id = Long.parseLong(((TextView)v.findViewWithTag(DB.T_SHOP_ID)).getText().toString());
		shopName.setText( ((TextView)v.findViewWithTag(DB.T_SHOP_NAME)).getText().toString() );
	}
	
}
