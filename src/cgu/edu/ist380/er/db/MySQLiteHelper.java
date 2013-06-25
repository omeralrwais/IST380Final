package cgu.edu.ist380.er.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "er.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_ER = "patient";
	public static final String TABLE_DIAG = "diagnose";
	
	public static final String ER_COLUMN_ID = "_id";
	public static final String ER_COLUMN_DOB = "dob";
	public static final String ER_COLUMN_GENDER = "gender";
	public static final String ER_COLUMN_FIRSTNAME = "firstname";
	public static final String ER_COLUMN_LASTNAME = "lastname";
	public static final String ER_COLUMN_PHONE = "phone";
	public static final String ER_COLUMN_ADDRESS = "address";
	public static final String ER_COLUMN_CITY = "city";
	public static final String ER_COLUMN_ZIB = "zib";
	
	public static final String DIAG_COLUMN_ID = "_id";
	public static final String DIAG_COLUMN_PID = "pid";
	public static final String DIAG_COLUMN_TEMP = "temp";
	public static final String DIAG_COLUMN_AMBULANCE = "ambulance";
	public static final String DIAG_COLUMN_BLEED = "bleed";
	public static final String DIAG_COLUMN_DIZZY = "dizzy";
	public static final String DIAG_COLUMN_PAIN = "pain";
	public static final String DIAG_COLUMN_BONE = "bone";
	public static final String DIAG_COLUMN_COLD = "cold";
	public static final String DIAG_COLUMN_TOOTHACHE = "toothache";
	public static final String DIAG_COLUMN_WOUND = "wound";
	public static final String DIAG_COLUMN_SPEAK = "speak";
	public static final String DIAG_COLUMN_URINE = "urine";
	public static final String DIAG_COLUMN_BREATH = "breath";
	public static final String DIAG_COLUMN_PAINSCALE = "painScale";
	public static final String DIAG_COLUMN_VISITTIME = "visitTime";
	
	private static final String DATABASE_CREATE = "create table " + TABLE_ER
			+ "(" 
			+ ER_COLUMN_ID + " integer primary key autoincrement, "
			+ ER_COLUMN_DOB + " text not null," 
			+ ER_COLUMN_GENDER + " text not null," 
			+ ER_COLUMN_FIRSTNAME + " text not null,"
			+ ER_COLUMN_LASTNAME + " text not null,"
			+ ER_COLUMN_PHONE + " integer not null,"
			+ ER_COLUMN_ADDRESS + " text not null," 
			+ ER_COLUMN_CITY + " text not null," 
			+ ER_COLUMN_ZIB + " integer not null"   
			+ ")";
	
	private static final String DATABASE_CREATE2 = "create table " + TABLE_DIAG
			+ "(" 
			+ DIAG_COLUMN_ID + " integer primary key autoincrement, "
			+ DIAG_COLUMN_PID + " integer null," 
			+ DIAG_COLUMN_TEMP + " real not null," 
			+ DIAG_COLUMN_AMBULANCE + " text not null,"
			+ DIAG_COLUMN_BLEED + " text not null,"
			+ DIAG_COLUMN_DIZZY + " text not null,"
			+ DIAG_COLUMN_PAIN + " text not null," 
			+ DIAG_COLUMN_BONE + " text not null,"
			+ DIAG_COLUMN_COLD + " text not null,"
			+ DIAG_COLUMN_TOOTHACHE + " text not null,"
			+ DIAG_COLUMN_WOUND + " text not null,"
			+ DIAG_COLUMN_SPEAK + " text not null,"
			+ DIAG_COLUMN_URINE + " text not null,"
			+ DIAG_COLUMN_BREATH + " text not null,"
			+ DIAG_COLUMN_VISITTIME + " text not null,"
			+ DIAG_COLUMN_PAINSCALE + " integer not null"   
			+ ")";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}	

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(DATABASE_CREATE);
		arg0.execSQL(DATABASE_CREATE2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_ER);
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAG);
	    onCreate(arg0);
	Log.w(MySQLiteHelper.class.getName(),
			        "Upgrading database from version " + arg1 + " to "
			            + arg2 + ", which will destroy all old data");

	}

}
