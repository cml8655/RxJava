package com.cml.rx.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonRequest;

public class MyJsonRequest<T> extends JsonRequest<T> {

	public MyJsonRequest(String url, String requestBody, Listener<T> listener, ErrorListener errorListener) {
		super(Request.Method.GET, url, requestBody, listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		return (Response<T>) Response.success(response.data, null);
	}

}
