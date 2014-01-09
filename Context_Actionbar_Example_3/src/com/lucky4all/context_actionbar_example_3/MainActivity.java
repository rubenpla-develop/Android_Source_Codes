package com.lucky4all.context_actionbar_example_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	Context mContext;
	ListView list;
	ActionMode mActionMode;
	String[] datos = new String[] { "Elemento 1", "Elemento 2", "Elemento 3",
			"Elemento 4", "Elemento 5" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		list = (ListView) findViewById(R.id.list1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, datos);
		list.setAdapter(adaptador);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adap, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				mActionMode = MainActivity.this
						.startSupportActionMode(new ActionBarContext());

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

class ActionBarContext implements ActionMode.Callback {

	@Override
	public boolean onActionItemClicked(ActionMode acMode, MenuItem item) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode acMode, Menu menu) {
		// TODO Auto-generated method stub
		acMode.getMenuInflater().inflate(R.menu.context_menu, menu);
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode acMode) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onPrepareActionMode(ActionMode acMode, Menu menu) {
		// TODO Auto-generated method stub
		// Toast.makeText(MainActivity.class, "Contextual AB creada",
		// Toast.LENGTH_LONG).show();
		acMode.setTitle("Context Bar");
		return false;
	}

}
