package develop.rubenpla.dialogs1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class MyDialog extends Dialog {

	public MyDialog(Context context) {
		super(context);
		setContentView(R.layout.my_dialog_layout);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(getContext(), "Dialog onCreate", Toast.LENGTH_LONG)
				.show();
	}
}