package com.adriano.lista.adapter;

import android.annotation.*;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import com.adriano.lista.database.DbController;
import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.model.Lista;
import com.adriano.lista.R;

/**
 * @author AdrBender
 */
public class ListaAdapter extends BaseAdapter {
	
	private Context context;
	private List<Lista> values;
	LayoutInflater inflater;
	
	private DbController dbc;
	
	@SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	public ListaAdapter(Context context, List<Lista> values) {
		this.context = context;
		this.values = values;
		inflater = (LayoutInflater.from(context));
	}

	@Override
    public int getCount() {
        return values.size();
    }
    @Override
    public Object getItem(int position) {
        return values.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.list_item, null);
		
		TextView txt_list = convertView.findViewById(R.id.txt_list_row);
		txt_list.setText(values.get(position).getLista());
		
		TextView txt_data = convertView.findViewById(R.id.txt_data_row);
		txt_data.setText(dateFormat.format(values.get(position).getData()));
		
		return convertView;
	}
}
