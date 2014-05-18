package fragments;

import interfaces.CorreosListener;
import modelo.Correo;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lucky4all.fragment_masterdetail_sg.R;

public class FragmentListado extends Fragment {

	Correo[] datos = new Correo[] {
			new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
			new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
			new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
			new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
			new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5") };

	private ListView lstListado;
	private CorreosListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_listado, container,
				false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		lstListado = (ListView) getView().findViewById(R.id.LstListado);

		lstListado.setAdapter(new AdaptadorCorreos(this));

		lstListado.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				if (listener != null) {
					listener.onCorreoSeleccionado((Correo) lstListado
							.getAdapter().getItem(pos));
				}

			}

		});
	}

	public class AdaptadorCorreos extends ArrayAdapter<Correo> {

		Activity context;

		public AdaptadorCorreos(Fragment context) {
			super(context.getActivity(), R.layout.listitem_correo, datos);
			this.context = context.getActivity();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_correo, null);

			TextView Asunto = (TextView) item.findViewById(R.id.txtAsunto);
			TextView Contacto = (TextView) item.findViewById(R.id.txtContacto);

			Asunto.setText(datos[position].getAsunto().toString());
			Contacto.setText(datos[position].getContacto().toString());

			return item;
		}

	}

	public void setCorreosListener(CorreosListener listener) {
		this.listener = listener;
	}

}
