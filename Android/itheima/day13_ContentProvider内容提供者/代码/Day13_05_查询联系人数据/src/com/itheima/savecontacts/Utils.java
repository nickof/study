package com.itheima.savecontacts;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class Utils {

	public static ArrayList<Contact> getContacts(ContentResolver resolver){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		//ͨ��resovler��ѯ����
    	//��ȷ��Ҫ��ѯ�ı� ʹ�õ�URI
    	//��ѯraw_contact���Uri
    	Uri raw_contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
    	//��ѯdata���Uri
    	Uri data_uri = Uri.parse("content://com.android.contacts/data");
    	//���Ȳ�ѯraw_contact�� ֻ��ѯcontact_id��һ��
		Cursor cursor = resolver.query(raw_contact_uri, new String[]{"contact_id"}, null, null, null);
		while(cursor.moveToNext()){
			//ÿ��ѯ��һ�����ݶ�Ӧһ����ϵ�� ��id�����ѯ�Ľ��
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
			//�� û���һ��id ����һ����ϵ�˶���
			Contact contact = new Contact();
			//�� ʹ�ò�ѯ������id��Ϊ���� ��ѯdata�� Ҫ��ѯ����  "data1","mimetype"
			Cursor cursor2 = resolver.query(data_uri, projection, "raw_contact_id=?", new String[]{id}, null);
			while(cursor2.moveToNext()){
				//�޻�ȡÿһ���е� data1���� ��mimetype����
				String result = cursor2.getString(0);
				String type = cursor2.getString(1);
				System.out.println(result+"====="+type);
				//��ͨ��mimetype������ȷ�� data1���� ����Ҫ���浽javabean����һ���ֶ���
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
			//��whileѭ������ ˵��һ����ϵ�˱���Ĺ����Ѿ���� ���԰������ϵ�˶���ŵ���ϵ�˼�����
			contacts.add(contact);
		}
		//�����е���ϵ�˶��������� ���Ա������ϲ鿴������
		for(Contact contact:contacts){
			System.out.println(contact);
		}
		
		return contacts;
	}
}
