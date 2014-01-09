package develop.rubenpla.fragmenttest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class detalleActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}

		setContentView(R.layout.activity_detalle);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s = extras.getString("Prueba de Fragments en Android");
			TextView txt = (TextView) findViewById(R.id.detallesTxt);
			txt.setText(s);
		}
	}
}