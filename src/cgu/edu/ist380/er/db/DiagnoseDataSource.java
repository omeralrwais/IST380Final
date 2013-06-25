package cgu.edu.ist380.er.db;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DiagnoseDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { 
			  MySQLiteHelper.DIAG_COLUMN_ID,
			  MySQLiteHelper.DIAG_COLUMN_PID,
			  MySQLiteHelper.DIAG_COLUMN_TEMP,
			  MySQLiteHelper.DIAG_COLUMN_AMBULANCE,
			  MySQLiteHelper.DIAG_COLUMN_BLEED,
			  MySQLiteHelper.DIAG_COLUMN_DIZZY,
			  MySQLiteHelper.DIAG_COLUMN_PAIN,
			  MySQLiteHelper.DIAG_COLUMN_BONE,
			  MySQLiteHelper.DIAG_COLUMN_COLD,
			  MySQLiteHelper.DIAG_COLUMN_TOOTHACHE,
			  MySQLiteHelper.DIAG_COLUMN_WOUND,
			  MySQLiteHelper.DIAG_COLUMN_SPEAK,
			  MySQLiteHelper.DIAG_COLUMN_URINE,
			  MySQLiteHelper.DIAG_COLUMN_BREATH,
			  MySQLiteHelper.DIAG_COLUMN_VISITTIME,
			  MySQLiteHelper.DIAG_COLUMN_PAINSCALE	
	      };
	  
	  public DiagnoseDataSource(Context context) {
		  try{
	    dbHelper = new MySQLiteHelper(context);
		  }
		  catch (Exception e)
		  {
			    Log.e(DiagnoseDataSource.class.getName(), "Error opening the db "+ e.getMessage());
		  }
	  }
	  
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }
		  
	public Diagnose createDiagnose(Diagnose d) {
			    ContentValues values = new ContentValues();
			    
			    values.put( MySQLiteHelper.DIAG_COLUMN_PID,d.getPid());
			    values.put( MySQLiteHelper.DIAG_COLUMN_TEMP,d.getBodyTemperature());
			    values.put( MySQLiteHelper.DIAG_COLUMN_AMBULANCE,d.isAmbulance());
			    values.put( MySQLiteHelper.DIAG_COLUMN_BLEED,d.isBleed());
			    values.put( MySQLiteHelper.DIAG_COLUMN_DIZZY,d.isDizzy());
			    values.put( MySQLiteHelper.DIAG_COLUMN_PAIN,d.isPain());
			    values.put( MySQLiteHelper.DIAG_COLUMN_BONE,d.isBone());
			    values.put( MySQLiteHelper.DIAG_COLUMN_COLD,d.isCold());
			    values.put( MySQLiteHelper.DIAG_COLUMN_TOOTHACHE,d.isToothache());
			    values.put( MySQLiteHelper.DIAG_COLUMN_WOUND,d.isWound());
			    values.put( MySQLiteHelper.DIAG_COLUMN_SPEAK,d.isSpeak());
			    values.put( MySQLiteHelper.DIAG_COLUMN_URINE,d.isUrine());
			    values.put( MySQLiteHelper.DIAG_COLUMN_BREATH,d.isBreath());
			    values.put( MySQLiteHelper.DIAG_COLUMN_VISITTIME,d.getVisitTime().toString());
			    values.put( MySQLiteHelper.DIAG_COLUMN_PAINSCALE,d.getPainScale());
			   
			    long insertedId = database.insert(MySQLiteHelper.TABLE_DIAG,null, values);
			    d.setId((int)insertedId);
			    Log.i(DiagnoseDataSource.class.getName(), "Record : Diagnose with id:" + d.getId() +" was inserted to the db.");
			    return d; /* What is the point of returning the passed object ?????????? */
			    
			  }
	
	public void deleteDiagnose(Diagnose d) {
	    long id = d.getId();
	    database.delete(MySQLiteHelper.TABLE_DIAG, MySQLiteHelper.DIAG_COLUMN_ID
	        + " = " + id, null);
	    Log.i(PatientDataSource.class.getName(), "Record : Diagnose with id:" + d.getId() +" was deleted from the db.");  
	  }
	  
	// no listing because there is no need to
	
	
}
