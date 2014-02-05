package com.lucky4all.xml_dom_example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.lucky4all.xml_dom_example.aSyncTask.aSyncTaskManager;
import com.lucky4all.xml_dom_example.adapter.ListViewAdapter;

public class MainActivity extends Activity implements OnClickListener {

	private Context mContext;
	private Button btnCargar;
	private ListView lvNoticias;
	private ListViewAdapter lvAdapter;

	private String url = "http://www.europapress.es/rss/rss.aspx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnCargar = (Button) findViewById(R.id.btnCargar);
		btnCargar.setOnClickListener(this);
		lvNoticias = (ListView) findViewById(R.id.lvNoticias);
		lvAdapter = new ListViewAdapter(this);
		lvNoticias.setAdapter(lvAdapter);
		lvNoticias.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				try {
					String link = lvAdapter.getItem(pos).getLink().toString();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(link));
					startActivity(i);

				} catch (Exception e) {
					
				}

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.btnCargar):
			aSyncTaskManager xmlAsync = new aSyncTaskManager(lvAdapter);
			xmlAsync.execute("http://feeds.weblogssl.com/genbetadev?format=xml");

		}

	}

}
