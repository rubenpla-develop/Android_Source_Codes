package develop.rubenpla.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		String[] listItems = new String[] { "Alfa", "Beta", "Gamma", "Delta",
				"Epsilon", "Dseta", "Eta", "Theta", "Iota" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, listItems);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		String registro = (String) getListAdapter().getItem(position);
		detalleFragment fragment = (detalleFragment) getFragmentManager()
				.findFragmentById(R.id.detallesFragment);

		if (fragment != null && fragment.isInLayout()) {

			fragment.establecerTexto(registro);
		} else {

			Intent intent = new Intent(getActivity().getApplicationContext(),
					detalleActivity.class);
			intent.putExtra("texto", registro);
			startActivity(intent);

		}
	}
}