package develop.rubenpla.prueba_dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btn, btn_dialog_toast;
	TextView txt1, txt2;
	Dialog miDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn_dialog);

	}

	public void BotonHandler(View vi) {

		miDialog = (new Dialog(this));
		miDialog.setTitle("Mi Dialog");
		miDialog.show();
		miDialog.setContentView(R.layout.layout_dialog);
		btn_dialog_toast = (Button) miDialog.findViewById(R.id.btn_dialog);
		txt1 = (TextView) miDialog.findViewById(R.id.editText1);
		txt2 = (TextView) miDialog.findViewById(R.id.editText2);
		btn_dialog_toast.setOnClickListener(new MiListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	private class MiListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(
					getApplicationContext(),
					"Mensaje: " + txt1.getText().toString() + " "
							+ txt2.getText().toString(), Toast.LENGTH_LONG)
					.show();

			miDialog.dismiss();
		}
	}

}
