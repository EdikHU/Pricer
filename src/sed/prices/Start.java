package sed.prices;

import java.util.Date;
import db.DB;
import sed.adapter.ProductAdapterDB;
import sed.data.ListPrices;
import sed.data.Price;
import sed.data.ProductCard;
import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;


public class Start extends Activity {

	private DB db;
	private ProductAdapterDB adapter;
	
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
				db.addProdCard(new ProductCard("Любительская "+(""+new Date()).split("\\s")[3],"Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));
				cursorRequery();
			}
		});
		layout.addView(btn2);
		
		Button btnD = new Button(this);
		btnD.setText("Del");
		btnD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db.delAllData();
				cursorRequery();
			}
		});
		layout.addView(btnD);
		//------------
		
		ListView listView = new ListView(this);
		layout.addView(listView);
		listView.setDivider(null);
		
		adapter = new  ProductAdapterDB(this, db.getAllData(), true);
		listView.setAdapter(adapter);
		
		/////////////////////////////
		Button btn3 = new Button(this);
		btn3.setText("Some");
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("here");
			}
		});
		layout.addView(btn3);

		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
	
	private void cursorRequery() {
		new AsyncTask<Void, Void, Void>(){
			Cursor c = null;
			@Override
			protected Void doInBackground(Void... params) {
				c = db.getAllData();
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				adapter.changeCursor(c);
			}
		}.execute();
	}

}
