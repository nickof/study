package com.itheima.autocompletetextview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	private String[] names = {"laowang","laozhang","laoli","xiaowang","xiaozhang","xiaoli"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv_text);
      //给自动补全的textview设置一个数据适配器 这个适配器用来提供显示下拉列表的内容
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item, names);
      //设置适配器
      actv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
