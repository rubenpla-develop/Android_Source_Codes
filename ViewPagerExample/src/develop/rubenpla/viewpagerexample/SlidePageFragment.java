package develop.rubenpla.viewpagerexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SlidePageFragment extends Fragment {

	/**
	 * Key to insert the background color into the mapping of a Bundle.
	 */
	private static final String BACKGROUND_COLOR = "color";

	/**
	 * Key to insert the index page into the mapping of a Bundle.
	 */
	private static final String INDEX = "index";

	private int color;
	private String index;

	/**
	 * Instances a new fragment with a background color and an index page.
	 * 
	 * @param color
	 *            background color
	 * @param index
	 *            index page
	 * @return a new page
	 */
	public static SlidePageFragment newInstance(String campo) {

		// Instantiate a new fragment
		SlidePageFragment fragment = new SlidePageFragment();
		// Save the parameters
		Bundle bundle = new Bundle();
		bundle.putString(BACKGROUND_COLOR, campo);

		fragment.setArguments(bundle);
		fragment.setRetainInstance(true);

		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Load parameters when the initial creation of the fragment is done
		this.index = getArguments().getString(BACKGROUND_COLOR);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_screen_slide_page, container, false);

		// Show the current page index in the view
		TextView tvIndex = (TextView) rootView.findViewById(R.id.tvIndex);
		tvIndex.setText(index);

		return rootView;

	}
}