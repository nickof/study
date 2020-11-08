package com.itheima.savecontact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText et_address;
	private EditText et_name;
	private EditText et_email;
	private EditText et_phone;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        
        et_address = (EditText) findViewById(R.id.et_address);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
    }


   public void save(View v){
	   //①获取内容解析者
	   ContentResolver resolver = getContentResolver();
	   //②获取URI
	   Uri raw_contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
	   Uri data_uri = Uri.parse("content://com.android.contacts/data");
	   //③确定插入到 raw_contact表中id的值
	   Cursor cursor = resolver.query(raw_contact_uri, new String[]{"contact_id"}, null, null, null);
	   //获取当前查询的结果一共有多少行 那么 就用查询结果加一 值作为新插入的contact_id的值
	   int count = cursor.getCount();
	   //④拿到确定的contact_id值之后 把对应的值 插入到 raw_contact表中
	   ContentValues values = new ContentValues();
	   values.put("contact_id", count+1);
	   resolver.insert(raw_contact_uri, values);
	   //⑤操作data表 插入对应的内容   要保存联系人的 姓名 电话 地址 邮箱 每一个数据都对应这个data表中的一行
	   ContentValues add_values = new ContentValues();
	   //获取用户输入的地址
	   String address = et_address.getText().toString();
	   //添加要插入列名和具体的值
	   add_values.put("raw_contact_id", count+1);
	   //data1 保存的数据 具体的值
	   add_values.put("data1", address);
	   //mimetype指定了当前存入数据的类型
	   add_values.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
	   resolver.insert(data_uri, add_values);
	   
	   //保存了姓名
	   ContentValues name_values = new ContentValues();
	   String name = et_name.getText().toString();
	   name_values.put("raw_contact_id", count+1);
	   name_values.put("data1", name);
	   name_values.put("mimetype", "vnd.android.cursor.item/name");
	   resolver.insert(data_uri, name_values);
	   //insert into info values(null)
	   
	   //保存了电话
	   ContentValues phone_values = new ContentValues();
	   String phone = et_phone.getText().toString();
	   phone_values.put("raw_contact_id", count+1);
	   phone_values.put("data1", phone);
	   phone_values.put("mimetype", "vnd.android.cursor.item/phone_v2");
	   resolver.insert(data_uri, phone_values);
	   
	   //保存了email
	   ContentValues email_values = new ContentValues();
	   String email = et_email.getText().toString();
	   email_values.put("raw_contact_id", count+1);
	   email_values.put("data1", email);
	   email_values.put("mimetype", "vnd.android.cursor.item/email_v2");
	   resolver.insert(data_uri, email_values);
   }
    
}
