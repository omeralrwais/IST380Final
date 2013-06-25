package cgu.edu.ist380.er;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import cgu.edu.ist380.er.adapters.HospitalAdapter;
import cgu.edu.ist380.er.db.Hospital;

public class ListClass extends FragmentActivity {
	
	private ListView hos;
	public BaseAdapter adapter2;
	Context context;
	public GoogleMap mMap;
	GPSTracker gps;
	public LatLngBounds.Builder builder2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list);
		hos = (ListView) findViewById(R.id.featureslist);
		
        context = this.getApplicationContext();
        gps = new GPSTracker(ListClass.this);
        
        

    		//builder2 = new LatLngBounds.Builder();
	 		
        
        if(gps.canGetLocation()){
            
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            
            String url=  "http://134.173.236.80:6080/arcgis/rest/services/er_wait_time_socal/MapServer/1/query?where=distance%3C31&text=&objectIds=&time=&geometry="+longitude+"%2C"+latitude+"&geometryType=esriGeometryPoint&inSR=4326&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=Hospital_N%2CAddress_1%2CCity_1%2CState_1%2CZIP_Code%2CPhone_Numb%2CRate2%2Cdistance%2Cx%2Cy&returnGeometry=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFields=distance&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&f=pjson"; // URL to Brian's service

            NetworkTask task = new NetworkTask(); // call service in a separate thread 
            task.execute(url);
            

                         
        }
        else
        {

            gps.showSettingsAlert();
        }
		
		/* some fake data
		Hospital hospitals[] = new Hospital[8];
		hospitals[0] = new Hospital(1,"San Antonio Community Hospital Average",53,92);
		hospitals[0].setLat((long) 34.1019);
		hospitals[0].setLng((long) -117.6377) ;
		
		hospitals[1] = new Hospital(2,"Pomona Valley Hospital Medical Center",28,55);
		hospitals[2] = new Hospital(3,"Loma Linda University Medical Center",40,65);
		
		hospitals[3] = new Hospital(3,"MONTCLAIR HOSPITAL MEDICAL CENTER",17,33);
		hospitals[4] = new Hospital(3,"CHINO VALLEY MEDICAL CENTER",25,55);
		hospitals[5] = new Hospital(3,"KAISER FOUNDATION HOSPITAL FONTANA",0000,30);
		hospitals[6] = new Hospital(3,"ARROWHEAD REGIONAL MEDICAL CENTER",47,97);
		hospitals[7] = new Hospital(3,"RIVERSIDE COMMUNITY HOSPITAL",13,43);
		
		//create my own custom adapter
		 adapter=new HospitalAdapter(this,hospitals);
		 
		//populate the data in the list based on my adapter
		hos.setAdapter(adapter);
		// Click event for single list row
		hos.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent();
				i.setClass(ListClass.this, Detail.class);

				// pass values to the next intent
				Hospital h = (Hospital)adapter2.getItem(position);
				i.putExtra("id",h.getId()+"");
				i.putExtra("name",h.getName());
				i.putExtra("waitingTime", h.getWaitTime()+"");
				i.putExtra("drivingTime",h.getDrivingTime()+"");
				i.putExtra("lat", h.getLat()+"");
				i.putExtra("lng", h.getLng()+"");
				 

				// start the sample activity
				startActivity(i);
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


private class NetworkTask extends AsyncTask<String, Integer, Hospital[]> {
	
	HospitalAdapter adapter;

    @Override
    protected void onPostExecute(Hospital [] hospitals) {
         
    	
    	//create my own custom adapter
		 adapter=new HospitalAdapter(context,hospitals);
		 		 
		//populate the data in the list based on my adapter
		hos.setAdapter(adapter);
		

        SupportMapFragment m= (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maphos); 
		mMap= m.getMap();
	 	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL );
		final LatLngBounds.Builder builder3 = new LatLngBounds.Builder();
		
	 	int n= hospitals.length;
	 
	 	for (int i=0;i<n;i++)
	 	{
	 	
	 		builder3.include(new LatLng(hospitals[i].getLat(),hospitals[i].getLng()));
			mMap.addMarker(new MarkerOptions().position(new LatLng(hospitals[i].getLat(),hospitals[i].getLng())).title(hospitals[i].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital)));	 		
	 	}
	 	
	 	//mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(9.491327, 76.571404), 10, 30, 0)));
	 	 mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder3.build(),50)) ;
				
		/*mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override 
            public void onCameraChange(CameraPosition arg0) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder3.build(),50)) ;
                mMap.setOnCameraChangeListener(null);
            }
        });*/


    
		
//    	//Display 
//    	adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.feature,result);
//    	Log.d("Before setting results to adapter", "");
//        hos.setAdapter(adapter); // link the result to the list view
//        Log.d("After setting results to adapter", "");
     
        // Click event for single list row*/
     		hos.setOnItemClickListener(new OnItemClickListener() {

     			public void onItemClick(AdapterView<?> parent, View view,
     					int position, long id) {

     				Intent i = new Intent();
     				i.setClass(ListClass.this, Detail.class);

     				// pass values to the next intent
     				Hospital h = (Hospital)adapter.getItem(position);
     				
//     				String[] lines = h.split("\n");
//     				String name = lines[0].split(":")[1].trim();
//     				int w= Integer.parseInt(lines[1].split(":")[1].trim());
//     				int d= Integer.parseInt(lines[2].split(":")[1].trim());
//     				
//     				double x= Double.parseDouble(lines[8].split(":")[1].trim());
//     				double y= Double.parseDouble(lines[9].split(":")[1].trim());
//     				
     				i.putExtra("id",0+"");
     				i.putExtra("name",h.getName());
     				i.putExtra("waitingTime",h.getWaitTime()+"");
     				i.putExtra("drivingTime",h.getDrivingTime()+"");
     				i.putExtra("lat", gps.getLatitude()+"");
     				i.putExtra("lng", gps.getLongitude()+"");
     				i.putExtra("x",h.getLng()+"");
     				i.putExtra("y",h.getLat()+"");
     				i.putExtra("add",h.getAddress());
     				i.putExtra("city",h.getCity());
     				i.putExtra("zib",h.getZipcode());
     				i.putExtra("phone",h.getPhoneNumber());
     				 

     				// start the sample activity
     				startActivity(i);
     			}
     		});
        
       // Log.d("Result",result[0]); 
    }

	@Override
	protected Hospital[] doInBackground(String... urls) {
		// TODO Auto-generated method stub

		String  result [] = null;
		Hospital hospitals[] = null;
		ArrayList<Hospital> hospitlaList = new ArrayList<Hospital>();
		
	        HttpGet request = new HttpGet(urls[0]); // create an http get method to the service URL
	        HttpClient httpclient = new DefaultHttpClient();  // http client
	        HttpResponse httpResponse; // response from the service
	        StringBuilder builder = new StringBuilder();
	        try 
	        {
	        	
	         // Call 
	        	httpResponse = (HttpResponse)httpclient.execute(request); // execute get method
	        	Log.d("Status", httpResponse.getStatusLine().getStatusCode()+""); // get the status if 200 then OK 
	        	if(httpResponse.getStatusLine().getStatusCode() == 200)
	        	{
	        	HttpEntity entity = httpResponse.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));    // get response content from the service
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				
				
				// Parse
				JSONObject  json= new JSONObject(builder.toString());   // parse content to JSON object 
				JSONArray fields = json.getJSONArray("features");   // get the incident array from the JSON object 
				
				
				int n = fields.length();
				result= new String [n];
					
				 hospitals = new Hospital[n];
				
				Log.d("Array size", n+"");
				
				int r=n; 
				/*if (n==0)
				{
					result[0]="Nothing within 21 Klm";
					Log.d("Nothing within 21 Klm", "");
				}
				else
				{*/
						for(int i=0;i<n ; i++)
						{ 
							Log.d("inside the loop", "");
							/*if(n==0)
							{
								i=0;
								Log.d("i=", i+"");
							}*/
							Log.d("JSON", fields.getJSONObject(r-1).getJSONObject("attributes").toString());
							
							// format list view as  Name + wait + distance
							String name = fields.getJSONObject(r-1).getJSONObject("attributes").getString("Hospital_N");  // get the hospital name
							String wait = fields.getJSONObject(r-1).getJSONObject("attributes").getString("Rate2").trim(); // get waiting time
							String distance = fields.getJSONObject(r-1).getJSONObject("attributes").getString("distance").trim(); // get distance
							String add =   fields.getJSONObject(r-1).getJSONObject("attributes").getString("Address_1"); // get distance
							String city =   fields.getJSONObject(r-1).getJSONObject("attributes").getString("City_1"); // get distance
							String state =   fields.getJSONObject(r-1).getJSONObject("attributes").getString("State_1"); // get distance
							String zib = fields.getJSONObject(r-1).getJSONObject("attributes").getString("ZIP_Code"); // get distance
							String phone = fields.getJSONObject(r-1).getJSONObject("attributes").getString("Phone_Numb"); // get distance
							String x =  fields.getJSONObject(r-1).getJSONObject("attributes").getString("X").trim(); // get distance
							String y =   fields.getJSONObject(r-1).getJSONObject("attributes").getString("Y").trim(); // get distance

		
							/*String name = fields.getJSONObject(i).getJSONObject("attributes").getString("Hospital_N") +"\n";  // get the hospital name
							String wait =  fields.getJSONObject(i).getJSONObject("attributes").getString("Rate2")+"\n"; // get waiting time
							String distance = fields.getJSONObject(i).getJSONObject("attributes").getString("distance")+"\n"; // get distance
							String add = fields.getJSONObject(i).getJSONObject("attributes").getString("Address_1")+"\n"; // get distance
							String city = fields.getJSONObject(i).getJSONObject("attributes").getString("City_1")+"\n"; // get distance
							String state = fields.getJSONObject(i).getJSONObject("attributes").getString("State_1")+"\n"; // get distance
							String zib =  fields.getJSONObject(i).getJSONObject("attributes").getString("ZIP_Code")+"\n"; // get distance
							String phone = fields.getJSONObject(i).getJSONObject("attributes").getString("Phone_Numb")+"\n"; // get distance*/
		
							
						//	result[r-1] = name+wait+distance+phone+city+state+add+zib+x+y;
							r--;
							
							 
							hospitals[i] = new Hospital();
							hospitals[i].setName(name);
							hospitals[i].setAddress(add);
							hospitals[i].setCity(city);
							hospitals[i].setZipcode(zib);
							hospitals[i].setLat((y != null ) ? Double.parseDouble(y) : (double)0);
							hospitals[i].setLng((x != null) ? Double.parseDouble(x) : (double)0);
							hospitals[i].setPhoneNumber(phone);
							hospitals[i].setState(state);
							hospitals[i].setDistance(distance);
							hospitals[i].setWaitTime(Integer.parseInt(wait));
							hospitals[i].setDrivingTime(Integer.parseInt(distance));
							
				

							
							if(!hospitlaList.contains(hospitals[i]))
							{
								hospitlaList.add(hospitals[i]);
								
							}
							
						
							
					    
							//(i+1,fields.getJSONObject(i).getJSONObject("attributes").getString("Hospital_N"),Integer.parseInt(fields.getJSONObject(i).getJSONObject("attributes").getString("Rate2")),0);
					
						}
						
						
						
				}

	        
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
	 

			    
		return  hospitlaList.toArray(new Hospital [hospitlaList.size()]);
	        
	        
	}
}

private void prcessArray(Hospital [] h)
{
	 
}

}
