package com.cml.rx.request;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class JsonOkHttpclientUtils {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient client = new OkHttpClient();
	private static Gson gson = new Gson();

	private JsonOkHttpclientUtils() {
	}

	public static final <T> ResquestResult<T> post(String url, Map<String, String> params, Class<T> clazz)
			throws IOException {

		FormEncodingBuilder requestBuilder = new FormEncodingBuilder();
		// 封装请求参数信息
		wrapRequestParams(params, requestBuilder);

		Request request = new Request.Builder().url(url).post(requestBuilder.build()).build();

		Response resp = client.newCall(request).execute();

		ResquestResult<T> requestResult = new ResquestResult<T>();

		requestResult.isSuccess = resp.isSuccessful();

		if (requestResult.isSuccess) {
			String resStr = resp.body().string();
			requestResult.result = (T) gson.fromJson(resStr, clazz);
		}

		return requestResult;
	}

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
