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
		//通过Builder 
		AlertDialog.Builder builder = new Builder(this);
		//给对话框设置一个标题
		builder.setTitle(R.string.dialog_title);
		builder.setMessage("对话框要显示的具体内容");
		//设置确定的按钮 需要显示的内容 以及点击按钮之后的业务逻辑
		//第一个参数 显示在按钮上的文字
		//第二个参数 按钮点击之后要走的方法 接收OnClickListener 是一个接口  按钮被点击之后 会调用接口中的 onclick方法
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "确定", Toast.LENGTH_LONG).show();
			}
		});
		//设置取消的按钮 需要显示的内容 以及点击按钮之后的业务逻辑
				//第一个参数 显示在按钮上的文字
				//第二个参数 按钮点击之后要走的方法 接收OnClickListener 是一个接口  按钮被点击之后 会调用接口中的 onclick方法
		builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();				
			}
		});
		//调用show方法 秀一下
		builder.show();
	}

	public void singleChoice(View v) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择你喜欢的明星");
		//等待选择的选项
		final String[] items = {"宝强","tf-boys","刘德华","成龙","冰冰"};
		builder.setSingleChoiceItems(items, 4, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
				//让当前的对话框消失
				dialog.dismiss();
			}
		});
		builder.show();
	}

	public void multiChoice(View v) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择你喜欢的水果");
		//等待选择的条目
		final String[] items = {"西瓜","芒果","香蕉","榴莲","苹果","荔枝"};
		//用来确定 那些条目是默认被选中的 可以通过这个数组来记录 各个条目选择的状态  boolean数组 元素个数要跟 items 数据个数对应
		checkedItems = new boolean[]{true,false,false,true,false,true};
		//设置多选的界面
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// which 那个条目被选中 对应的索引   
				// isChecked 被点击之后条目的选择状态
//				Toast.makeText(getApplicationContext(), items[which]+(isChecked?"被选中":"没选中"), Toast.LENGTH_SHORT).show();
//				dialog.dismiss();
				checkedItems[which]= isChecked;
				for(int i = 0;i<items.length;i++){
					if(checkedItems[i])
					System.out.println("选中"+items[i]);
				}
			}
		});
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
	}

	public void progress(View v) {
		final ProgressDialog dialog = new ProgressDialog(this);
		//设置当前的进度条对话框样式为水平
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//给对话框设置标题
		dialog.setTitle("正在玩命下载中....");
		//给对话框设置最大进度
		dialog.setMax(100);
		//显示对话框
		dialog.show();
		//开线程模拟下载进度
		new Thread(){
			public void run() {
				for(int i = 0;i<=100;i++){
					//进度条对话框可以在子线程更新进度
					dialog.setProgress(i);
					SystemClock.sleep(100);
				}
				//让当前的对话框消失
				dialog.dismiss();
			};
		}.start();

	}
}
