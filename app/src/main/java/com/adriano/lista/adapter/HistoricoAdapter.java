package com.adriano.lista.adapter;

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
import java.util.List;

import java.text.*;
import android.annotation.*;

import com.adriano.lista.model.Lista;
import com.adriano.lista.R;

public class HistoricoAdapter extends BaseAdapter {

	private final Context context;
	private List<Lista> values;
	LayoutInflater inflater;

	@SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public HistoricoAdapter(Context context, List<Lista> values) {
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
		convertView = inflater.inflate(R.layout.item_list_historico, null);

		TextView txt_list_hist = convertView.findViewById(R.id.txt_nome_list_hist);
		txt_list_hist.setText(values.get(position).getLista());

		TextView txt_valor = convertView.findViewById(R.id.txt_valor_total_hist);
		txt_valor.setText("R$ "+values.get(position).getValor().toString());

		TextView txt_data = convertView.findViewById(R.id.txt_data_hist);
		txt_data.setText(dateFormat.format(values.get(position).getData()));

		return convertView;
	}
}
