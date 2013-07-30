package com.malaz.app;

import com.malaz.services.SIMService;
import com.malaz.services.ServiceFactory;
import com.malaz.util.AlertUtil;
import com.malaz.util.CallUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConvertActivity extends BaseActivity {

	private EditText numberEditText;
	private EditText balanceEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert);
		this.initializingActionBar();
		
		numberEditText = (EditText) findViewById(R.id.otherNumberEditText);
		balanceEditText = (EditText) findViewById(R.id.balanceEditText);
	}
	
	public void convertBalanceButtonClicked(View view) {
		String number = numberEditText.getText().toString();
		String balance = balanceEditText.getText().toString();
		
		SIMService service = new ServiceFactory(this).getSendBalanceService(balance, number);
		
		if ( service == null ) {
			AlertUtil.selectSIMTypeDialog(ConvertActivity.this);
			return;
		}
		
		boolean state = CallUtil.convert(this, service);
		
		if ( state ) {
			Toast.makeText(this, "Convert Balance Done!", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, "Error in Balance Converting", Toast.LENGTH_LONG).show();
		}
	}

}
