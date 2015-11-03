package com.example.trip.util;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class StringPostRequest extends StringRequest {
	private Map<String, String> params;

@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return this.params;
	}

	public StringPostRequest(String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(Request.Method.POST,url, listener, errorListener);
		// TODO Auto-generated constructor stub
		initMap();
	}

	public StringPostRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		// TODO Auto-generated constructor stub
		initMap();
	}
	public void initMap(){
		params=new HashMap<String, String>();		
	}
	public void PutParams(String key,String value){
		params.put(key, value);
	}
}
