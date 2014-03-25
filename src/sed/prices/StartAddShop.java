package sed.prices;

import sed.view.ShopView;
import db.DB;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class StartAddShop extends Activity{

	private EditText edTxt;
	private Button okBtn;
	private StartAddShop content;
	private ListView lv;
	private static ModCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		content = this;
		setContentView(R.layout.start_add_shop);
		edTxt = (EditText)findViewById(R.id.start_add_shop_editText);
		okBtn = (Button)findViewById(R.id.start_add_shop_ok_but);
		
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = edTxt.getText().toString();
				System.out.println("--> "+str);
				DB.addShop(str);
				Cursor c = DB.getShopAll();
				if (c.moveToFirst()){
					do{
						System.out.println("  ["+c.getColumnCount()+"]  ["+c.getInt(0)+"]  ["+c.getString(1)+"]  []  ");
					}while (c.moveToNext());
				}
				System.out.println("---- here ");
				adapter.changeCursor(DB.getShopAll());
				//content.finish();
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
			cv.setOnClickListener(null);
			cv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					System.out.println("HERRREER");
					//DB.shopRemoveItem(((TextView)v).getText().toString());
					((ShopView)v).removeFromDB();
					adapter.changeCursor(DB.getShopAll());
				}
			});
			((ShopView)cv).setData(cursor);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return new ShopView(context);
		}
	}
	
}
