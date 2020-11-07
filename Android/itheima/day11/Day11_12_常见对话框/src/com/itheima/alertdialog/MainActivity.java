package com.itheima.alertdialog;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private boolean[] checkedItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause");
	}

	public void normal(View v) {
		//ͨ��Builder 
		AlertDialog.Builder builder = new Builder(this);
		//���Ի�������һ������
		builder.setTitle(R.string.dialog_title);
		builder.setMessage("�Ի���Ҫ��ʾ�ľ�������");
		//����ȷ���İ�ť ��Ҫ��ʾ������ �Լ������ť֮���ҵ���߼�
		//��һ������ ��ʾ�ڰ�ť�ϵ�����
		//�ڶ������� ��ť���֮��Ҫ�ߵķ��� ����OnClickListener ��һ���ӿ�  ��ť�����֮�� ����ýӿ��е� onclick����
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "ȷ��", Toast.LENGTH_LONG).show();
			}
		});
		//����ȡ���İ�ť ��Ҫ��ʾ������ �Լ������ť֮���ҵ���߼�
				//��һ������ ��ʾ�ڰ�ť�ϵ�����
				//�ڶ������� ��ť���֮��Ҫ�ߵķ��� ����OnClickListener ��һ���ӿ�  ��ť�����֮�� ����ýӿ��е� onclick����
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "ȡ��", Toast.LENGTH_SHORT).show();				
			}
		});
		//����show���� ��һ��
		builder.show();
	}

	public void singleChoice(View v) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ����ϲ��������");
		//�ȴ�ѡ���ѡ��
		final String[] items = {"��ǿ","tf-boys","���»�","����","����"};
		builder.setSingleChoiceItems(items, 4, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
				//�õ�ǰ�ĶԻ�����ʧ
				dialog.dismiss();
			}
		});
		builder.show();
	}

	public void multiChoice(View v) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ����ϲ����ˮ��");
		//�ȴ�ѡ�����Ŀ
		final String[] items = {"����","â��","�㽶","����","ƻ��","��֦"};
		//����ȷ�� ��Щ��Ŀ��Ĭ�ϱ�ѡ�е� ����ͨ�������������¼ ������Ŀѡ���״̬  boolean���� Ԫ�ظ���Ҫ�� items ���ݸ�����Ӧ
		checkedItems = new boolean[]{true,false,false,true,false,true};
		//���ö�ѡ�Ľ���
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// which �Ǹ���Ŀ��ѡ�� ��Ӧ������   
				// isChecked �����֮����Ŀ��ѡ��״̬
//				Toast.makeText(getApplicationContext(), items[which]+(isChecked?"��ѡ��":"ûѡ��"), Toast.LENGTH_SHORT).show();
//				dialog.dismiss();
				checkedItems[which]= isChecked;
				for(int i = 0;i<items.length;i++){
					if(checkedItems[i])
					System.out.println("ѡ��"+items[i]);
				}
			}
		});
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
	}

	public void progress(View v) {
		final ProgressDialog dialog = new ProgressDialog(this);
		//���õ�ǰ�Ľ������Ի�����ʽΪˮƽ
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//���Ի������ñ���
		dialog.setTitle("��������������....");
		//���Ի�������������
		dialog.setMax(100);
		//��ʾ�Ի���
		dialog.show();
		//���߳�ģ�����ؽ���
		new Thread(){
			public void run() {
				for(int i = 0;i<=100;i++){
					//�������Ի�����������̸߳��½���
					dialog.setProgress(i);
					SystemClock.sleep(100);
				}
				//�õ�ǰ�ĶԻ�����ʧ
				dialog.dismiss();
			};
		}.start();

	}
}
