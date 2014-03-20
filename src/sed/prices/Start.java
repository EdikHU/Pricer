package sed.prices;

import java.util.ArrayList;
import java.util.Date;

import sed.adapter.ProductAdapter;
import sed.data.ListPrices;
import sed.data.Price;
import sed.data.ProductCard;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		FrameLayout layout = (FrameLayout)findViewById(R.id.start);
		ListView listView = new ListView(this);
		layout.addView(listView);
		listView.addHeaderView(new Button(this));
		
		
		ArrayList<ProductCard> products = new ArrayList<ProductCard>();
		products.add(new ProductCard("Любительская","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская2","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская3","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская4","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская5","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская6","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская7","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская8","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская9","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская0","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская1","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская2","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская3","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		products.add(new ProductCard("Любительская4","Колбаса","Еда",new ListPrices(new Price( 299.56,"Пчелка",new Date()),new Price( 350,"Пятерочка",new Date()),new Price( 330,"Остап",new Date()),new Price( 305,"Ашан",new Date()))));	
		
		ProductAdapter adapter = new ProductAdapter(this,products);
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
