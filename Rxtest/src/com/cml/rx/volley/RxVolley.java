package com.cml.rx.volley;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

public class RxVolley {


	public static void main(String[] args) {
		// https://raw.githubusercontent.com/cml8655/note/master/data/test.json
		// JsonRequest<>
		// JsonRequest<MyModel> request=JsonRequest.class;
		String url = "";
		StringRequest req = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				System.out.println("success===>" + response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
			}
		});

		RequestQueue quene = new RequestQueue(null, new BasicNetwork(new HurlStack()));
		quene.add(req);
	}
}
