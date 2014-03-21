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
	public static final String T_PROD_CATEGORY = "category";
	public static final String T_PROD_GROUP = "grp";
	private static final String TABLE_PROD = "production";
	private static final String DB_CREATE_TABLE_PROD = "create table "+TABLE_PROD+" ("+T_PROD_ID+" integer primary key autoincrement, "+T_PROD_NAME+" text, "+T_PROD_GROUP+" text, "+T_PROD_CATEGORY+" text)";
	
	
	private Context context;
	private DBHelper dbHelper;
	private SQLiteDatabase db;

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
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
		
	}



	public Cursor getAllData() {
		return db.query(TABLE_PROD, null, null, null, null, null, null);
	}

	public void add(ProductCard productCard) {
		ContentValues cv = new ContentValues();
		cv.put(T_PROD_NAME, (String) productCard.getFieldByName("name"));
		cv.put(T_PROD_GROUP, (String) productCard.getFieldByName("group"));
		cv.put(T_PROD_CATEGORY, (String) productCard.getFieldByName("category"));
		db.insert(TABLE_PROD, null, cv);
		
	}

	public void delAllData() {
		db.delete(TABLE_PROD, null, null);
	}

	public void close() {
		db.close();
	}

	public static ProductCard decode(Cursor c) {
		return new ProductCard(c.getString(c.getColumnIndex(DB.T_PROD_NAME)), c.getString(c.getColumnIndex(DB.T_PROD_GROUP)), c.getString(c.getColumnIndex(DB.T_PROD_CATEGORY)), null);
	}
	
}
