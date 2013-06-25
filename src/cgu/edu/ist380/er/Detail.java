package cgu.edu.ist380.er;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.google.android.gms.maps.model.PolylineOptions;

import cgu.edu.ist380.er.db.Hospital;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends FragmentActivity {
	Hospital h;
	TextView hospitalName;
	TextView waitTime;
	TextView drivingTime;
	TextView hosinfo;
	private WebView map;
	public GoogleMap mMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.details);
		// Get hospital to display from the previous activity
        Intent i = getIntent();
        h= new Hospital();
        h.setId(Integer.parseInt( i.getStringExtra("id")));
        h.setName(i.getStringExtra("name"));
        double lat= Double.parseDouble(i.getStringExtra("lat"));
        double lang= Double.parseDouble(i.getStringExtra("lng"));
        h.setWaitTime(Integer.parseInt( i.getStringExtra("waitingTime")));
        h.setDrivingTime(Integer.parseInt( i.getStringExtra("drivingTime")));
        double x= Double.parseDouble(i.getStringExtra("x"));
        double y= Double.parseDouble(i.getStringExtra("y"));
        h.setAddress(i.getStringExtra("add"));
        h.setCity(i.getStringExtra("city"));
        h.setZipcode(i.getStringExtra("zib"));
        h.setPhoneNumber(i.getStringExtra("phone"));
        
        hosinfo = (TextView) findViewById(R.id.hospitalinformation);
        
        hosinfo.setText("Address: "+h.getAddress()+" , City: "+h.getCity()+" , Zib: "+h.getZipcode()+" , Phone: "+h.getPhoneNumber());
    
        
        
		final LatLng current = new LatLng(lat, lang);
		final LatLng hos = new LatLng(y, x);
	    
		String url = getDirectionsUrl(current, hos);				
		DownloadTask downloadTask = new DownloadTask();
		downloadTask.execute(url);
	      
         hospitalName = (TextView) findViewById(R.id.erName);
         hospitalName.setText(h.getName());
         waitTime = (TextView) findViewById(R.id.waitTime);
         waitTime.setText("Waiting time "+h.getWaitTime()+" mins, " );
         drivingTime = (TextView) findViewById(R.id.expectedTime);
         //drivingTime.setText("  ( "+ h.getDrivingTime()+" Kilometers )" );
         
       
         
         SupportMapFragment m= (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map); 
 		 mMap= m.getMap();
 		 
 		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		final LatLngBounds.Builder builder = new LatLngBounds.Builder();
		builder.include(current);
		builder.include(hos);
		Marker currentmarker = mMap.addMarker(new MarkerOptions().position(current).title("My Location"));
		Marker hosmarker = mMap.addMarker(new MarkerOptions().position(hos).title(h.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital)));
		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override 
            public void onCameraChange(CameraPosition arg0) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),50)) ;
                mMap.setOnCameraChangeListener(null);
            }
        });
         
         map = (WebView) findViewById(R.id.dir);
         map.getSettings().setJavaScriptEnabled(true);
 	     map.setWebViewClient(new WebViewClient());
 	     map.getSettings().setJavaScriptEnabled(true);
 	     map.getSettings().setBuiltInZoomControls(true);
 	     map.getSettings().setSupportZoom(true);
 	     map.loadUrl("http://maps.google.com/maps?q&saddr="+lat+","+lang+"&daddr="+h.getName());
    
	}

	
private String getDirectionsUrl(LatLng origin,LatLng dest){
		
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;		
		
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		// https://maps.googleapis.com/maps/api/directions/json?origin=34.101944,-117.714043&destination=34.101302,-117.636514&sensor=false
		
		
		return url;
	}
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }

	// Fetches data from url passed
		private class DownloadTask extends AsyncTask<String, Void, String>{			
					
			// Downloading data in non-ui thread
			@Override
			protected String doInBackground(String... url) {
					
				// For storing data from web service
				String data = "";
						
				try{
					// Fetching the data from web service
					data = downloadUrl(url[0]);
				}catch(Exception e){
					Log.d("Background Task",e.toString());
				}
				return data;		
			}
			
			// Executes in UI thread, after the execution of
			// doInBackground()
			@Override
			protected void onPostExecute(String result) {			
				super.onPostExecute(result);			
				
				ParserTask parserTask = new ParserTask();
				
				// Invokes the thread for parsing the JSON data
				parserTask.execute(result);
					
			}		
		}
		
		/** A class to parse the Google Places in JSON format */
	    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
	    	
	    	// Parsing the data in non-ui thread    	
			@Override
			protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
				
				JSONObject jObject;	
				List<List<HashMap<String, String>>> routes = null;			           
	            
	            try{
	            	jObject = new JSONObject(jsonData[0]);
	            	DirectionsJSONParser parser = new DirectionsJSONParser();
	            	
	            	// Starts parsing data
	            	routes = parser.parse(jObject);    
	            }catch(Exception e){
	            	e.printStackTrace();
	            }
	            return routes;
			}
			
			// Executes in UI thread, after the parsing process
			@Override
			protected void onPostExecute(List<List<HashMap<String, String>>> result) {
				ArrayList<LatLng> points = null;
				PolylineOptions lineOptions = null;
				MarkerOptions markerOptions = new MarkerOptions();
				String distance = "";
				String duration = "";
				
				
				
				if(result.size()<1){
					Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
					return;
				}
					
				
				// Traversing through all the routes
				for(int i=0;i<result.size();i++){
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();
					
					// Fetching i-th route
					List<HashMap<String, String>> path = result.get(i);
					
					// Fetching all the points in i-th route
					for(int j=0;j<path.size();j++){
						HashMap<String,String> point = path.get(j);	
						
						if(j==0){	// Get distance from the list
							distance = (String)point.get("distance");						
							continue;
						}else if(j==1){ // Get duration from the list
							duration = (String)point.get("duration");
							continue;
						}
						
						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);	
						
						points.add(position);						
					}
					
					// Adding all the points in the route to LineOptions
					lineOptions.addAll(points);
					lineOptions.width(2);
					lineOptions.color(Color.RED);	
					
				}
				
				//tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
				
				// Drawing polyline in the Google Map for the i-th ro
				mMap.addPolyline(lineOptions);
				
				
				// add to the hospital information

				
				
				drivingTime.setText("Distance:"+distance+",Driving time:"+duration);
				
				
					
			}			
	    }   
	    
		
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
