package sed.view;

import sed.data.ModelProductCardView;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductCardView extends FrameLayout{

	private Context context;
	private LinearLayout layout;

	private ProductCardView(Context context) {
		super(context);
	}

	public ProductCardView(Context context, ModelProductCardView model) {
		super(context);
		this.context = context;
		init(model);
	}

	private void init(ModelProductCardView model) {
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		this.addView(layout);

		TextView name = new TextView(context);
		name.setTag("name");
		layout.addView(name);

		TextView group = new TextView(context);
		group.setTag("group");
		layout.addView(group);

		TextView category = new TextView(context);
		category.setTag("category");
		layout.addView(category);
		
		TextView prices = new TextView(context);
		prices.setTag("prices");
		layout.addView(prices);
		
	}

	public int modGetChildCount() {
		return layout.getChildCount();
	}

	public View modGetChildAt(int index) {
		return layout.getChildAt(index);
	}


}
