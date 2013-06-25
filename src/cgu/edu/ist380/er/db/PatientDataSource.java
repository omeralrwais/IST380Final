package cgu.edu.ist380.er.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PatientDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { 
			  MySQLiteHelper.ER_COLUMN_ID,
			  MySQLiteHelper.ER_COLUMN_DOB,
			  MySQLiteHelper.ER_COLUMN_GENDER,
			  MySQLiteHelper.ER_COLUMN_FIRSTNAME,
			  MySQLiteHelper.ER_COLUMN_LASTNAME,
			  MySQLiteHelper.ER_COLUMN_PHONE,
			  MySQLiteHelper.ER_COLUMN_ADDRESS,
			  MySQLiteHelper.ER_COLUMN_CITY,
			  MySQLiteHelper.ER_COLUMN_ZIB
	      };
	  
	  public PatientDataSource(Context context) {
		  try{
	    dbHelper = new MySQLiteHelper(context);
		  }
		  catch (Exception e)
		  {
			    Log.e(PatientDataSource.class.getName(), "Error opening the db "+ e.getMessage());
		  }
	  }
	  
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }
		  
	public Patient createPatient(Patient p) {
			    ContentValues values = new ContentValues();
			    
			    values.put(   MySQLiteHelper.ER_COLUMN_DOB,p.getDob().toString());
				values.put( MySQLiteHelper.ER_COLUMN_GENDER,p.getGender());
			    values.put( MySQLiteHelper.ER_COLUMN_FIRSTNAME,p.getFirstName());
			    values.put( MySQLiteHelper.ER_COLUMN_LASTNAME,p.getLastName());
			    values.put(  MySQLiteHelper.ER_COLUMN_PHONE,p.getPhone());
			    values.put(  MySQLiteHelper.ER_COLUMN_ADDRESS,p.getAddress());
			    values.put(  MySQLiteHelper.ER_COLUMN_CITY,p.getCity());
			    values.put(MySQLiteHelper.ER_COLUMN_ZIB, p.getZib());
			    
			   
			    long insertedId = database.insert(MySQLiteHelper.TABLE_ER,null, values);
			    p.setId((int)insertedId);
			    Log.i(PatientDataSource.class.getName(), "Record : Patient with id:" + p.getId() +" was inserted to the db.");
			    return p; /* What is the point of returning the passed object ?????????? */
			    
			  }
	
	public void deletePatient(Patient p) {
	    long id = p.getId();
	    database.delete(MySQLiteHelper.TABLE_ER, MySQLiteHelper.ER_COLUMN_ID
	        + " = " + id, null);
	    Log.i(PatientDataSource.class.getName(), "Record : Patient with id:" + p.getId() +" was deleted from the db.");  
	  }
	  
	
	public List<Patient> getAllPatients() {
	    List<Patient> medsList = new ArrayList<Patient>();
 
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_ER,
	        allColumns, null, null, null, null, null);
 
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Patient meds = cursorToMeds(cursor);
	      medsList.add(meds);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return medsList;
	  }
	
	private Patient cursorToMeds(Cursor cursor) {
	    Patient meds = new Patient();
	    /* get the values from the cursor 
	    MySQLiteHelper.ER_COLUMN_ID,
		  MySQLiteHelper.ER_COLUMN_DOB,
		  MySQLiteHelper.ER_COLUMN_GENDER,
		  MySQLiteHelper.ER_COLUMN_FIRSTNAME,
		  MySQLiteHelper.ER_COLUMN_LASTNAME,
		  MySQLiteHelper.ER_COLUMN_PHONE,
		  MySQLiteHelper.ER_COLUMN_ADDRESS,
		  MySQLiteHelper.ER_COLUMN_CITY,
		  MySQLiteHelper.ER_COLUMN_ZIB */
		  
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_ID));
	    String dob=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_DOB));
	    String g = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_GENDER));
	    String f = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_FIRSTNAME));
	    String l = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_LASTNAME));
	    int ph = cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_PHONE));
	    String qty =cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_ADDRESS));
	    String qtyType = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_CITY));
	    int enabled =cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.ER_COLUMN_ZIB));
	    meds.setId((int) id);
	    Date d= new Date (dob);
	    meds.setDob(d);
	    meds.setGender(g);
	    meds.setFirstName(f);
	    meds.setLastName(l);
	    meds.setPhone(ph);
	    meds.setAddress(qty);
	    meds.setCity(qtyType);
	    meds.setZib(enabled);
	    return meds;
	  }
}
