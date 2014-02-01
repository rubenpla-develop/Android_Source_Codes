package com.example.contentprovider_example1.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.contentprovider_example1.R;

public class Custom_Dialog2 extends Dialog {

	public Custom_Dialog2(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// obligamos al usuario a pulsar los botones para cerrarlo
		setCancelable(true);
		setContentView(R.layout.dialog_layout);
	}

}
