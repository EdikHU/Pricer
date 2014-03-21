package sed.prices;

import java.util.ArrayList;
import java.util.Date;

import db.DB;

import sed.adapter.ProductAdapter;
import sed.adapter.ProductAdapterDB;
import sed.data.ListPrices;
import sed.data.Price;
import sed.data.ProductCard;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Start extends Activity {

	private DB db;
	private ProductAdapterDB adapter;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		FrameLayout frLayout = (FrameLayout)findViewById(R.id.start);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		frLayout.addView(layout);
		
		//-- DB here ------
		db = new DB(this);
		db.open();
		
		Button btn = new Button(this);
		btn.setText("Show");
		layout.addView(btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Cursor c = db.getAllData();
				if(c.moveToFirst()){
					do{
						System.out.println("--> ("+c.getColumnCount()+") ["+c.getString(c.getColumnIndex(DB.T_PROD_NAME))+"]["+c.getString(c.getColumnIndex(DB.T_PROD_GROUP))+"]["+c.getString(c.getColumnIndex(DB.T_PROD_CATEGORY))+"]");
					}while(c.moveToNext());
				}
			}
		});

		Button btn2 = new Button(this);
		btn2.setText("Add");
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db.add(new ProductCard("Любительская "+(""+new Date()).split("\\s")[3],"Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));
				//adapter.notifyDataSetChanged();
				cursor.requery();
			}
		});
		layout.addView(btn2);
		
		Button btn3 = new Button(this);
		btn3.setText("Del");
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db.delAllData();
				//adapter.notifyDataSetChanged();
				cursor.requery();
			}
		});
		layout.addView(btn3);
		//------------
		
		ListView listView = new ListView(this);
		layout.addView(listView);
		listView.addHeaderView(new Button(this));
		
//		ArrayList<ProductCard> products = new ArrayList<ProductCard>();
//		products.add(new ProductCard("Любительская","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская2","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская3","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская4","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская5","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская6","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская7","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская8","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская9","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская0","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская1","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская2","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская3","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		products.add(new ProductCard("Любительская4","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
//		ProductAdapter adapter = new ProductAdapter(this,products);
		cursor = db.getAllData();
		adapter = new  ProductAdapterDB(this, cursor, true);
		listView.setAdapter(adapter);
		
		
		
		
		
//		LinearLayout la = new LinearLayout(this);
//		la.setOrientation(LinearLayout.VERTICAL);
//		layout.addView(la);
//		
//		
//		Button btn1 = new Button(this);
//		btn1.setTag("btn1");
//		la.addView(btn1);
//		la.addView(new Button(this));
//		la.addView(new Button(this));
//		la.addView(new Button(this));
//		la.addView(new Button(this));
//		
//		View fndBtn1 = layout.findViewWithTag("btn1");
//		String ss = fndBtn1.getClass().getCanonicalName();
//		System.out.println("--> "+ss +" ["+fndBtn1.getClass()+"]");
//		Button trnsfm = (Button)fndBtn1.getClass().cast(fndBtn1);
//		trnsfm.setText("here");
//		
		//layout.getch
		
//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start,
					container, false);

			TextView tv = (TextView)rootView.findViewById(R.id.tv_field);
			tv.setText(""+(""+new Date()).split("\\s")[3]);

			
			return rootView;
		}
	}

}
