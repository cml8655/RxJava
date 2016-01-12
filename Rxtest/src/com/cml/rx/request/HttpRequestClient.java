package com.cml.rx.request;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.cml.rx.request.JsonOkHttpclientUtils.ResquestResult;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public abstract class HttpRequestClient {

	private static OkHttpClient client = new OkHttpClient();

	public <T> ResquestResult<T> post(String url, Map<String, String> params, Class<T> target) throws IOException {

		FormEncodingBuilder requestBuilder = new FormEncodingBuilder();
		// 封装请求参数信息
		wrapRequestParams(params, requestBuilder);

		Request request = new Request.Builder().url(url).post(requestBuilder.build()).build();

		Response resp = client.newCall(request).execute();

		return onCallback(resp, target);
	}

	public abstract <T> ResquestResult<T> onCallback(Response resp, Class<T> target) throws IOException;

	/**
	 * 封装请求参数信息
	 * 
	 * @param params
	 * @param requestBuilder
	 */
	private static void wrapRequestParams(Map<String, String> params, FormEncodingBuilder requestBuilder) {
		if (null != params && params.size() > 0) {

			Iterator<String> it = params.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next();
				String value = params.get(key);

				requestBuilder.add(key, value);
			}
		}
	}

	public static class ResquestResult<T> {
		public boolean isSuccess;
		public T result;
	}
}
