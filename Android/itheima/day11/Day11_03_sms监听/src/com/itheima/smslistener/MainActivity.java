package com.itheima.smslistener;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText et_code;
	private BroadcastReceiver receiver;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_code = (EditText) findViewById(R.id.et_code);
        receiver = new CodeReceiver();
        //动态注册收到验证码的广播接收者
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.itheima.getcode");
		registerReceiver(receiver, filter);
    }


	private class CodeReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String code = intent.getStringExtra("code");
			et_code.setText(code);
		}
	}
   
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}
