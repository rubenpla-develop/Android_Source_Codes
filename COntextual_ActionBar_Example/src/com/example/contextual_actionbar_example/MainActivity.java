package com.example.contextual_actionbar_example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ActionMode.Callback modeCallBack;
	Context mContext;
	ListView list;
	String[] datos = new String[] { "Barcelona", "Valencia", "Londres",
			"Paris", "Amsterdam" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		modeCallBack = new ActionMode.Callback() {

			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			public void onDestroyActionMode(ActionMode mode) {
				mode = null;
			}

			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.setTitle("Context Ops");
				mode.getMenuInflater().inflate(R.menu.context_menu, menu);
				return true;

			}

			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				return false;
			}
		};

		list = (ListView) findViewById(R.id.list1);

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, datos);

		list.setAdapter(adaptador);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						mContext,
						"has pulsado " + datos[position] + " pos "
								+ (position + 1), Toast.LENGTH_SHORT).show();
			}
		});

		list.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				startSupportActionMode(modeCallBack);
				v.setSelected(true);
				return true;
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
