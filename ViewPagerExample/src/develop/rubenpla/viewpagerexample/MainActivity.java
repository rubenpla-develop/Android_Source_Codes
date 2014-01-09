package develop.rubenpla.viewpagerexample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next pages.
	 */
	ViewPager pager = null;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	//MyFragmentPageAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);

		// Instantiate a ViewPager
		pager = (ViewPager) this.findViewById(R.id.pager);

		// Create an adapter with the fragments we show on the ViewPager
		MyFragmentPageAdapter adapter = new MyFragmentPageAdapter(
				getSupportFragmentManager());
		adapter.addFragment(SlidePageFragment.newInstance("Prueba"));
		adapter.addFragment(SlidePageFragment.newInstance("de"));
		adapter.addFragment(SlidePageFragment.newInstance("ViewPager"));
		adapter.addFragment(SlidePageFragment.newInstance("Completada"));
		this.pager.setAdapter(adapter);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * @Override public void onBackPressed() {
	 * 
	 * // Return to previous page when we press back button if
	 * (this.pager.getCurrentItem() == 0) super.onBackPressed(); else
	 * this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
	 * 
	 * }
	 */
}