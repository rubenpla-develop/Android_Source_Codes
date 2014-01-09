package develop.rubenpla.dialogs1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dialogs1 extends Activity {

	Dialog myDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogs1);
	}

	public void ButtonHandler(View target) {
		Toast.makeText(this, "User Clicked Open Dialog Button",
				Toast.LENGTH_LONG).show();
		myDialog = new MyDialog(this);
		myDialog.setContentView(R.layout.my_dialog_layout);
		myDialog.setTitle("My Itty Bitty Dialog");
		myDialog.show();
		Button aButton = (Button) myDialog.findViewById(R.id.AButton);
		aButton.setOnClickListener(new dialogButtonListener());
	}

	private class dialogButtonListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			myDialog.dismiss();
		}
	}
}