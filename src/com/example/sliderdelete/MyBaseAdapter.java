package com.example.sliderdelete;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.Car;
import com.example.main.R;

public class MyBaseAdapter extends BaseAdapter {
	List<Car> list;
	LayoutInflater mLayoutInflater;
	private Context mContext;
	private float down_x, move_x;
	private boolean closeOnclick = false;
	private boolean isOpen = true; 


	public MyBaseAdapter(Context context, List<Car> list) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(this.mContext);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		final ViewHolder viewHolder;
		// �Ż�
		if (arg1 == null) {
			arg1 = mLayoutInflater
					.inflate(R.layout.activity_main_lv_item, null);
			viewHolder = new ViewHolder();
			viewHolder.layout = (LinearLayout) arg1.findViewById(R.id.layout);
			viewHolder.tv_user_name = (TextView) arg1
					.findViewById(R.id.tv_user_name);
			viewHolder.tv_type = (TextView) arg1.findViewById(R.id.tv_type);
			viewHolder.tv_license = (TextView) arg1
					.findViewById(R.id.tv_license);
			viewHolder.deletBtn = (Button) arg1.findViewById(R.id.delete_btn);
			viewHolder.deleteLy = (LinearLayout) arg1
					.findViewById(R.id.delete_ly);
			viewHolder.animLy = (LinearLayout) arg1.findViewById(R.id.anim_ly);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}

		// ������
		viewHolder.tv_user_name.setText(String.valueOf(list.get(arg0)
				.getUser_id()));
		viewHolder.tv_type.setText(list.get(arg0).getType());
		viewHolder.tv_license.setText(list.get(arg0).getLicense());

		// ɾ��ť����
		viewHolder.deletBtn.setOnClickListener(new OnClickListener() {

			// ɾ��ҵ�����
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(mContext,
						R.anim.closeanim);
				viewHolder.animLy.setAnimation(anim);
				anim.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						viewHolder.deleteLy.setVisibility(View.INVISIBLE);
						viewHolder.animLy.setVisibility(View.INVISIBLE);
					}
				});
				viewHolder.animLy.setVisibility(View.VISIBLE);
				closeOnclick = true;
				isOpen = false;
				list.remove(arg0);
				notifyDataSetChanged();
				Toast.makeText(mContext, "删除" + arg0, 1).show();
			}
		});

		viewHolder.layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (viewHolder.deleteLy.getVisibility() == View.VISIBLE) {
					Animation anim = AnimationUtils.loadAnimation(mContext,
							R.anim.closeanim);
					viewHolder.animLy.setAnimation(anim);
					anim.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							viewHolder.deleteLy.setVisibility(View.INVISIBLE);
							viewHolder.animLy.setVisibility(View.INVISIBLE);
						}
					});
					viewHolder.animLy.setVisibility(View.VISIBLE);
					// �ر�
					closeOnclick = true;
					isOpen = false;
				} else {
					// ����¼�����
					Toast.makeText(mContext, "���", 1).show();
				}
			}
		});

		viewHolder.layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					down_x = event.getX();
					closeOnclick = false;
					isOpen = true;
					return closeOnclick;
				case MotionEvent.ACTION_UP:
					return closeOnclick;
				case MotionEvent.ACTION_MOVE:
					move_x = event.getX();
					float distance = Math.abs(move_x - down_x);
					if (distance > 20 && isOpen) {
						if (viewHolder.deleteLy.getVisibility() == View.INVISIBLE) {// �������
							Animation anim = AnimationUtils.loadAnimation(
									mContext, R.anim.openanim);
							viewHolder.animLy.setAnimation(anim);
							anim.setAnimationListener(new AnimationListener() {

								@Override
								public void onAnimationStart(Animation animation) {
								}

								@Override
								public void onAnimationRepeat(
										Animation animation) {
								}

								@Override
								public void onAnimationEnd(Animation animation) {
									viewHolder.animLy
									.setVisibility(View.INVISIBLE);
								}
							});
							viewHolder.animLy.setVisibility(View.VISIBLE);
							viewHolder.deleteLy.setVisibility(View.VISIBLE);
							// �رյ���¼�
							closeOnclick = true;
							isOpen = false;
						} else {
							Animation anim = AnimationUtils.loadAnimation(
									mContext, R.anim.closeanim);
							viewHolder.animLy.setAnimation(anim);
							anim.setAnimationListener(new AnimationListener() {

								@Override
								public void onAnimationStart(Animation animation) {
								}

								@Override
								public void onAnimationRepeat(
										Animation animation) {
								}

								@Override
								public void onAnimationEnd(Animation animation) {
									viewHolder.deleteLy
									.setVisibility(View.INVISIBLE);
								}
							});
							viewHolder.animLy.setVisibility(View.VISIBLE);
							viewHolder.deleteLy.setVisibility(View.VISIBLE);
							// �رյ��
							closeOnclick = true;
							isOpen = false;
						}
					}
					break;
				}
				return false;
			}
		});
		return arg1;
	}



	public class ViewHolder {
		LinearLayout layout;
		TextView tv_user_name;
		TextView tv_type;
		TextView tv_license;
		Button deletBtn; 
		LinearLayout deleteLy; 
		LinearLayout animLy;
	}

}
