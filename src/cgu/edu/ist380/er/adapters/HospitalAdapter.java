package cgu.edu.ist380.er.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cgu.edu.ist380.er.R;
import cgu.edu.ist380.er.db.Hospital;


public class HospitalAdapter  extends BaseAdapter  {
	   Hospital[] hospitals;
	   Context context;
	   private LayoutInflater inflater=null;
	   public HospitalAdapter(Context context, Hospital[] hospitals)  {
	       this.context = context;
	       this.hospitals = hospitals;
	       inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   }
 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hospitals.length;
	}
	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return hospitals[index];
	}
	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return hospitals[index].getId();
	}
	
	 public class ViewHolder{
		 TextView hospitalName ;
         TextView waitTime ;
         TextView drivingTime ;
	    }

	 
	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        Hospital h = hospitals[position];
        if (convertView == null) {
            vi = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.hospitalName = (TextView) vi.findViewById(R.id.erName);
            holder.waitTime = (TextView) vi.findViewById(R.id.waitTime);
            holder.drivingTime = (TextView) vi.findViewById(R.id.expectedTime);
            vi.setTag(holder);
        }
        else
        	  holder=(ViewHolder)vi.getTag();

        holder.hospitalName.setText(h.getName());
        holder.waitTime.setText("Waiting time "+h.getWaitTime()+" min." );
        holder.drivingTime.setText("  ( "+ h.getDrivingTime()+" miles )" );
 
        return vi;
    }

	}