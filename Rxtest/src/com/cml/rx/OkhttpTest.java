package com.cml.rx;

import java.io.IOException;

import com.cml.rx.model.InitBean;
import com.cml.rx.request.JsonOkHttpclientUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OkhttpTest {
	public static void main(String[] args) throws IOException {
		// OkHttpClient client = new OkHttpClient();
		// client.interceptors().add(e)
		// Request request = new
		// Request.Builder().url("http://20150601.team-lab.cn/app/2.0/init")
		// .post(RequestBody.create(MediaType.parse("json"),
		// "")).header("User-Agent", "OkHttp Example").build();
		// Response response = client.newCall(request).execute();

		// System.out.println(response.body().string());

		JsonOkHttpclientUtils.ResquestResult<InitBean> result = JsonOkHttpclientUtils
				.post("http://20150601.team-lab.cn/app/2.0/init", null, InitBean.class);

		System.out.println(result.isSuccess+"::"+result.result.getStatus());

	}
}
