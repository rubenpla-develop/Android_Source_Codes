package develop.rubenpla.spinner_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Spinner spin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spin = (Spinner) findViewById(R.id.spinner1);
		final String[] datos = new String[] { "Elem1", "Elem2", "Elem3",
				"Elem4", "Elem5" };

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spin.setAdapter(adaptador);

		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					android.view.View v, int position, long id) {
				Log.i("TAG", datos[position]);
			}

			public void onNothingSelected(AdapterView<?> parent) {

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
