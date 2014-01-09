package develop.rubenpla.sliding_layout_example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	SlidingPaneLayout slide = (SlidingPaneLayout) findViewById(R.id.slidingPane);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		slide.setParallaxDistance(30);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (slide.isOpen()) {
				slide.closePane();
			} else {
				slide.openPane();
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
