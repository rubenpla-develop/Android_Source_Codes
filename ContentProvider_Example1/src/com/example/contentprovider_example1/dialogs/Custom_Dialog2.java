package com.example.contentprovider_example1.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Window;

import com.example.contentprovider_example1.R;

public class Custom_Dialog2 extends AlertDialog {

	public Custom_Dialog2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// obligamos al usuario a pulsar los botones para cerrarlo
		setCancelable(false);
		setContentView(R.layout.dialog_layout);
	}

}
