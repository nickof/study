package com.itheima.ipdialer;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText et_prefix;
	private SharedPreferences sp;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_prefix = (EditText) findViewById(R.id.et_prefix);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        
        sp = getSharedPreferences("info", MODE_PRIVATE);
        
        btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取用户输入
				String prefix = et_prefix.getText().toString().trim();
				if(TextUtils.isEmpty(prefix)){
					Toast.makeText(getApplicationContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
				}else{
					//通过sp保存用户的输入 并且 commit提交
					sp.edit().putString("prefix", prefix).commit();
				}
				
			}
		});
    }


   
}
