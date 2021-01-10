package com.adriano.lista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;
import android.util.Log;

import com.adriano.lista.R;
import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.model.Item;
import com.adriano.lista.database.*;


public class ItemAdapter extends BaseAdapter {
	private static final String TAG = "com.adriano.lista.ItemAdapter";
	
	Context context;
    private List<Item> listItems;

    public ItemAdapter(Context context, List<Item> listItems) {
		this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

	@Override
    public Object getItem(int position) {
        return listItems.get(position);
	}
	
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
		final Item item = listItems.get(position);
		//final DatabaseHelper database = new DatabaseHelper(context);
		final DbController dbc = new DbController(context);
        
       	final ViewHolder holder;
		if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.textListView = (TextView)convertView.findViewById(R.id.txt_item_list);
            holder.checkBox = (CheckBox)convertView.findViewById(R.id.chkbox_item_list);
			
            convertView.setTag(holder);
        }else {
        	holder = (ViewHolder)convertView.getTag();
        }
		holder.textListView.setText(item.getItem());
		//holder.checkBox.setChecked(item.getStatus());
		holder.checkBox.setChecked(toBoolean(item.getStatus()));
		
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
				Log.i(TAG, "checkbox Click: " +position);
					
					dbc.updateStatus(item.getIdItem(), 1);
					Log.i(TAG, "chkbox id checked: " +item.getIdItem());
				} else {
					dbc.updateStatus(item.getIdItem(), 0);
					Log.i(TAG, "chkbox unchecked: " +listItems.get(position).getItem());
				}
			}
		});
		return convertView;
    }
	
	public boolean toBoolean(int num){
        return num!=0;
    }

    static class ViewHolder{
        TextView textListView;
        CheckBox checkBox;
    }
}
