package com.example.ex_templete;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.yuchen.utils.HTTPUtils;
import com.yuchen.volley.VolleyListener;

public class MainActivity extends Activity {
	private TextView mTextView;

	public void startVolley(View v) {
		String url = "http://192.168.56.1/testjson.txt";
		HTTPUtils.get(url, new VolleyListener() {
			public void onErrorResponse(VolleyError error) {
				mTextView.setText(error.getMessage());
			}

			public void onResponse(String response) {
//				JSONObject jsonObj;
//				ItemData data = new ItemData();
//				try {
//					jsonObj = new JSONObject(response);
//					data.title = jsonObj.getString("title");
//					data.subtitle = jsonObj.getString("subtitle");
//					data.iconurl = jsonObj.getString("iconurl");
//				} catch (JSONException e) {
//				}
				
				Gson gson = new Gson();
				ItemData data = gson.fromJson(response, ItemData.class);
				
				mTextView.setText(data.subtitle);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = (TextView) findViewById(R.id.textView1);
	}
}
