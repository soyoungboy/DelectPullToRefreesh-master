package com.example.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bean.Car;
import com.example.main.R;
import com.example.sliderdelete.MyBaseAdapter;
import com.example.xlistview.XListView;
import com.example.xlistview.XListView.IXListViewListener;

public class MainActivity extends Activity implements IXListViewListener {
	private XListView listView;
	private MyBaseAdapter myBaseAdapter;
	private Boolean isRefresh = true;
	private Boolean isLoadMore = false;
	// ��ʼ��
	private List<Car> list = new ArrayList<Car>();
	private Integer start = 1;
	private Integer size = 14;
	private Car car;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		initDBDate();
		isRefresh = true;
		findData();
		listener();
	}



	public void findView() {
		listView = (XListView) findViewById(R.id.listview);
	}
	private void initDBDate() {
		for (int i = 0; i < 100; i++) {
			car = new Car();
			car.setId(i);
			car.setLicense("嘻嘻"+i);
			car.setType("哈哈"+i);
			car.setUser_id(i);
			list.add(car);
		}}

	public void findData() {


		if (isRefresh) {
			size = 14;
			MyBaseAdapter myBaseAdapter = new MyBaseAdapter(
					MainActivity.this, MainActivity.this.list.subList(1, size));
			listView.setAdapter(myBaseAdapter);
			isRefresh = false;
			return;
		}
		if (isLoadMore&&size<100) {
			MyBaseAdapter myBaseAdapter = new MyBaseAdapter(
					MainActivity.this, MainActivity.this.list.subList(start, size));
			listView.setAdapter(myBaseAdapter);
			listView.stopLoadMore();
			listView.setSelection(size-12);
			// 时间戳
			listView.setRefreshTime("刚刚");
			myBaseAdapter.notifyDataSetChanged();

			isLoadMore = false;
			return;
		}else if (size>=100) {
			Toast.makeText(getApplicationContext(), "无更多内容", Toast.LENGTH_LONG).show();
			listView.stopLoadMore();
			size = size-14;
		}
	}

	public void listener() {
		myBaseAdapter = new MyBaseAdapter(MainActivity.this,
				MainActivity.this.list.subList(1, 14));
		listView.setAdapter(myBaseAdapter);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(this);
	}

	// ˢ�²���
	@Override
	public void onRefresh(){
		// �ӵ�һҳ��ʼ��ȡ
		isRefresh = true;
		findData();
		listView.stopRefresh();
		// ʱ���
		listView.setRefreshTime("刚刚");
	}

	// ���ظ��
	@Override
	public void onLoadMore() {
		size = size+10;
		isLoadMore = true;
		findData();
	}
}
