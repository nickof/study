package com.itheima.savecontacts;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class Utils {

	public static ArrayList<Contact> getContacts(ContentResolver resolver){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		//通过resovler查询数据
    	//②确定要查询的表 使用的URI
    	//查询raw_contact表的Uri
    	Uri raw_contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
    	//查询data表的Uri
    	Uri data_uri = Uri.parse("content://com.android.contacts/data");
    	//③先查询raw_contact表 只查询contact_id这一列
		Cursor cursor = resolver.query(raw_contact_uri, new String[]{"contact_id"}, null, null, null);
		while(cursor.moveToNext()){
			//每查询出一个数据对应一个联系人 用id保存查询的结果
			String id = cursor.getString(0);
			System.out.println("id"+id);
			String[] projection ={"data1","mimetype"};
//			Cursor cursor2 = resolver.query(data_uri, null, "raw_contact_id=?", new String[]{id}, null);
//			cursor2.moveToNext();
//			int columnCount = cursor2.getColumnCount();
//			for(int i = 0;i<columnCount;i++){
//				String columnName = cursor2.getColumnName(i);
//				if(columnName.contains("mime")){
//					System.out.println(columnName);
//				}
//			}
			//④ 没查出一个id 创建一个联系人对象
			Contact contact = new Contact();
			//⑤ 使用查询出来的id作为条件 查询data表 要查询的列  "data1","mimetype"
			Cursor cursor2 = resolver.query(data_uri, projection, "raw_contact_id=?", new String[]{id}, null);
			while(cursor2.moveToNext()){
				//⑥获取每一列中的 data1数据 和mimetype数据
				String result = cursor2.getString(0);
				String type = cursor2.getString(1);
				System.out.println(result+"====="+type);
				//⑦通过mimetype的数据确定 data1数据 究竟要保存到javabean的哪一个字段中
				if("vnd.android.cursor.item/phone_v2".equals(type)){
					contact.phone = result;
				}else if("vnd.android.cursor.item/email_v2".equals(type)){
					contact.email = result;
				}else if("vnd.android.cursor.item/name".equals(type)){
					contact.name = result;
				}else if("vnd.android.cursor.item/postal-address_v2".equals(type)){
					contact.address = result;
				}
			}
			//⑧while循环结束 说明一个联系人保存的过程已经完成 可以把这个联系人对象放到联系人集合中
			contacts.add(contact);
		}
		//⑨所有的联系人都保存完了 可以遍历集合查看保存结果
		for(Contact contact:contacts){
			System.out.println(contact);
		}
		
		return contacts;
	}
}
