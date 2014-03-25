package sed.prices;

import sed.view.ShopView;
import db.DB;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class StartAddPrice extends Activity{

	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.start_price_add, null);
		setContentView(layout);
		
		spinner = (Spinner)findViewById(R.id.spinner);
		spinner.setAdapter(new StartAddShop.ModCursorAdapter(getBaseContext(), DB.getShopAll(), true));
		spinner.setSelection(-1);
//		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View v,
//					int position, long id) {
//				System.out.println("=>> "+ ((ShopView)v).getName()+"  ===> "+((ShopView)spinner.getSelectedView()).getName()   );
//				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
		
		spinner.setOnItemLongClickListener( new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("HERRRRERERER");
				return false;
			}
		});
	}
}
