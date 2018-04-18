package com.mtm.widget;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;


import com.mtm.baseapp.R;
import com.mtm.utils.DateUtils;
import com.mtm.widget.datepicker.MonthDateView;

import java.util.ArrayList;
import java.util.List;

public class DateActivity extends FragmentActivity {
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_date;
	private TextView tv_week;
	private TextView tv_today;
	private MonthDateView monthDateView;
	private Intent intent;
	private TimePicker timePick1;
	private ListView lv_time;
	private ListView lv_minute;
	private View findViewById;
	private Spinner sp_time;
	private Spinner sp_minute;
	private Button btn_complete;
	private LinearLayout linear_time;
	private String time;
	private String currentDate; 
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String isXJSQ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.StyleDialog);
		setContentView(R.layout.activity_date);
		findViewById(R.id.mFl_layout).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#50000000")));
		isXJSQ = getIntent().getStringExtra("xjsq");
		initCurrent();
		btn_complete = (Button) findViewById(R.id.btn_complete);
		linear_time = (LinearLayout) findViewById(R.id.linear_time);
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(16);
		intent = getIntent();
		time = intent.getStringExtra("time");
		currentDate = intent.getStringExtra("currentDate");
		if(null != currentDate && !"".equals(currentDate)){
			String currDateArray[] = new String[]{};
			String currArray[] = currentDate.split(" ");
			if (currArray[0].contains("/")) {
				currDateArray = currArray[0].split("/");
			}
			if (currArray[0].contains("-")) {
				currDateArray = currArray[0].split("-");
			}
			year = Integer.parseInt(currDateArray[0]);
			month = Integer.parseInt(currDateArray[1]) - 1;
			day= Integer.parseInt(currDateArray[2]);
			Log.e("currentDate", currentDate);
		}
		if ("NO".equals(time)) {
			linear_time.setVisibility(View.GONE);
		} else if ("YES".equals(time)) {
			linear_time.setVisibility(View.VISIBLE);
		}

		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		tv_date = (TextView) findViewById(R.id.date_text);
		tv_week = (TextView) findViewById(R.id.week_text);
		tv_today = (TextView) findViewById(R.id.tv_today);
		sp_time = (Spinner) findViewById(R.id.sp_time);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				DateActivity.this, android.R.layout.simple_spinner_item,
				DateUtils.initTime());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_time.setAdapter(adapter);
		sp_time.setSelection(hour);
		sp_time.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		sp_minute = (Spinner) findViewById(R.id.sp_minute);
		ArrayAdapter<String> adapterMinute;
		if(null != isXJSQ && !"".equals(isXJSQ) && "xjsq".equals(isXJSQ)){
			adapterMinute = new ArrayAdapter<String>(
					DateActivity.this, android.R.layout.simple_spinner_item,
					DateUtils.initMinuteXJ());
			adapterMinute
			.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp_minute.setAdapter(adapterMinute);
			sp_minute.setSelection(0);
		}else{
			adapterMinute = new ArrayAdapter<String>(
				DateActivity.this, android.R.layout.simple_spinner_item,
					DateUtils.initMinute());
			adapterMinute
			.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp_minute.setAdapter(adapterMinute);
			sp_minute.setSelection(minute);
		}
		sp_minute.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		if(null != currentDate && !"".equals(currentDate)){
			monthDateView.setTextView(tv_date, tv_week, year, month, day);
		}else{
			monthDateView.setTextView(tv_date, tv_week);
		}
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new MonthDateView.DateClick() {
			@Override
			public void onClickOnDate() {

			}
		});
		btn_complete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Intent intent_date = new Intent();
				if ("NO".equals(time)) {

					if ("start".equals(intent.getStringExtra("type"))) {
						intent_date.putExtra("datestart", tv_date.getText()
								+ "" + monthDateView.getmSelDay());

					} else if ("end".equals(intent.getStringExtra("type"))) {
						intent_date.putExtra("dateend", tv_date.getText() + ""
								+ monthDateView.getmSelDay());
					}
				} else if ("YES".equals(time)) {
					String aString = tv_date.getText() + ""
							+ monthDateView.getmSelDay() + "日"
							+ sp_time.getSelectedItem().toString() + ":"
							+ sp_minute.getSelectedItem().toString() + ":"
							+ "00";
					if ("start".equals(intent.getStringExtra("type"))) {
						intent_date.putExtra("datestart", aString);

					} else if ("end".equals(intent.getStringExtra("type"))) {
						intent_date.putExtra("dateend", aString);
					}
				}
				setResult(RESULT_OK, intent_date);
				finish();

			}

		});
		setOnlistener();
	}
	class TimeListener implements OnTimeChangedListener {
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			System.out.println("h:" + hourOfDay + " m:" + minute);
		}
	}

	private void setOnlistener() {
		iv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
			}
		});

		tv_today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.setTodayToView();
			}
		});
	}

	public void initCurrent() {
		Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
		t.setToNow(); // 取得系统时间。
		hour = t.hour; // 0-23
		minute = t.minute;
	}
}
