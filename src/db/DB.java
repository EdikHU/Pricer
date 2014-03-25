package db;

import sed.data.ProductCard;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

	private static final String DB_NAME = "priser";
	private static final int DB_VERSION = 1;
	
	private static final String T_PROD_ID = "_id";
	public static final String T_PROD_NAME = "name";
	public static final String T_PROD_GROUP = "grp";
	public static final String T_PROD_CATEGORY = "category";
	private static final String TABLE_PROD = "table_production";
	private static final String DB_CREATE_TABLE_PROD = "create table "+TABLE_PROD+" ("+T_PROD_ID+" integer primary key autoincrement, "+T_PROD_NAME+" text, "+T_PROD_GROUP+" integer, "+T_PROD_CATEGORY+" integer)";
	
	
	private static final String TABLE_GROUP = "table_group";
	private static final String T_GROUP_ID = "_id";
	private static final String T_GROUP_NAME = "name";
	private static final String DB_CREATE_TABLE_GROUP = "create table "+TABLE_GROUP+" ("+T_GROUP_ID+" integer primary key autoincrement, "+T_GROUP_NAME+" text )";

	
	private static final String TABLE_CATEGORY = "table_category";
	private static final String T_CATEG_ID = "_id";
	private static final String T_CATEG_NAME = "name";
	private static final String DB_CREATE_TABLE_CATEGORY = "create table "+TABLE_CATEGORY+" ("+T_CATEG_ID+" integer primary key autoincrement, "+T_CATEG_NAME+" text )";
	
	
	private static final String TABLE_SHOP = "table_shop";
	public static final String T_SHOP_ID = "_id";
	public static final String T_SHOP_NAME = "name";
	private static final String DB_CREATE_TABLE_SHOP = "create table "+TABLE_SHOP+" ("+T_SHOP_ID+" integer primary key autoincrement, "+T_SHOP_NAME+" text )";
	
	
	private static final String TABLE_PRICES = "table_prices";
	private static final String T_PRICES_ID = "_id";
	private static final String T_PRICES_PRICE = "price";
	private static final String T_PRICES_SHOP = "shop";
	private static final String T_PRICES_DATE = "date";
	private static final String DB_CREATE_TABLE_PRICES = "create table "+TABLE_PRICES+" ("+T_PRICES_ID+" integer primary key autoincrement, "+T_PRICES_SHOP+" integer, "+T_PRICES_PRICE+" integer, "+T_PRICES_DATE+" date)";
	
	private static final String RQ_GET_ALL_DATA = "select tp."+T_PROD_ID+", tp."+T_PROD_NAME+",  tg."+T_GROUP_NAME+" "+T_PROD_GROUP+", tc."+T_CATEG_NAME+" "+T_PROD_CATEGORY+"  from "+TABLE_PROD+" tp left outer join "+TABLE_GROUP+" tg  on tp."+T_PROD_GROUP+"  = tg."+T_GROUP_ID+"   left outer join  "+TABLE_CATEGORY+" tc on tp."+T_PROD_CATEGORY+" = tc."+T_CATEG_ID+"    ";
	private static final String RQ_GET_SHOP_ALL_DATA = "select * from "+TABLE_SHOP;
	
	private Context context;
	private DBHelper dbHelper;
	private static SQLiteDatabase db;

	public DB(Context context) {
		this.context = context;
	}

	public void open() {
		dbHelper = new DBHelper(context,DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	
	
	private static class DBHelper extends SQLiteOpenHelper{


		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE_TABLE_PROD);
			db.execSQL(DB_CREATE_TABLE_GROUP);
			db.execSQL(DB_CREATE_TABLE_CATEGORY);
			
			db.execSQL(DB_CREATE_TABLE_SHOP);
			db.execSQL(DB_CREATE_TABLE_PRICES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
		
	}



	public Cursor getAllData() {
		//return db.query(TABLE_PROD, null, null, null, null, null, null);
		System.out.println("--> "+RQ_GET_ALL_DATA);
		return db.rawQuery(RQ_GET_ALL_DATA, null);
	}

	public void addProdCard(ProductCard productCard) {
		ContentValues cv = new ContentValues();
		cv.put(T_PROD_NAME, (String) productCard.getFieldByName("name"));
		cv.put(T_PROD_GROUP, findOrAddGroupId((String) productCard.getFieldByName("group")));
		cv.put(T_PROD_CATEGORY, findOrAddCategoryId((String) productCard.getFieldByName("category")));
		db.insert(TABLE_PROD, null, cv);
		
	}

	private String findOrAddCategoryId(String fieldCategoryName) {
		Cursor curs_id = db.query(TABLE_CATEGORY, new String[] {T_CATEG_ID}, (T_CATEG_NAME+"='"+fieldCategoryName+"'"), null, null, null, null);
		if (curs_id.moveToFirst()){
			return ""+ curs_id.getLong(0);
		} else{
			ContentValues cv = new ContentValues();
			cv.put(T_CATEG_NAME,fieldCategoryName);
			long id = db.insert(TABLE_CATEGORY, null, cv);
			return ""+id;
		}
	}

	private String findOrAddGroupId(String fieldGroupName) {
		Cursor curs_id = db.query(TABLE_GROUP, new String[] {T_GROUP_ID}, (T_GROUP_NAME+"='"+fieldGroupName+"'"), null, null, null, null);
		if (curs_id.moveToFirst()){
			return ""+ curs_id.getLong(0);
		} else{
			ContentValues cv = new ContentValues();
			cv.put(T_GROUP_NAME,fieldGroupName);
			long id = db.insert(TABLE_GROUP, null, cv);
			return ""+id;
		}
	}

	public void delAllData() {
		db.delete(TABLE_PROD, null, null);
	}

	public void close() {
		db.close();
	}

	public static ProductCard decode(Cursor c) {
		System.out.println("--------\n"+c.getColumnCount()+"  ["+c.getCount()+"] \n "+c+"\n["+c.getLong(0)+"]["+c.getString(1)+"]["+c.getString(2)+"]("+c.getColumnName(2)+")("+DB.T_PROD_GROUP+")["+c.getString(3)+"]");
		return new ProductCard(c.getString(c.getColumnIndex(DB.T_PROD_NAME)), c.getString(c.getColumnIndex(DB.T_PROD_GROUP)), c.getString(c.getColumnIndex(DB.T_PROD_CATEGORY)), null);
	}

	public static void shopAddOrReplace(String str) {
		Cursor c = db.query(TABLE_SHOP, null, T_SHOP_NAME+"='"+str+"'" , null, null, null, null);
		if ( !c.moveToFirst() ){
			ContentValues cv = new ContentValues();
			cv.put(T_SHOP_NAME, str);
			db.insert(TABLE_SHOP, null, cv );
		}	
	}
	
	public static Cursor getShopAll(){
		return db.rawQuery(RQ_GET_SHOP_ALL_DATA, null);
	}

	public static void shopRemoveItem(String string) {
		db.delete(TABLE_SHOP, T_SHOP_ID+"='"+string+"'", null);
	}
	
}
