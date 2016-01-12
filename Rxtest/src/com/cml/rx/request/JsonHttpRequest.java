package com.cml.rx.request;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;

public class JsonHttpRequest extends HttpRequestClient {

	private static Gson gson = new Gson();

	@Override
	public <T> ResquestResult<T> onCallback(Response resp, Class<T> target) throws IOException {
		ResquestResult<T> requestResult = new ResquestResult<T>();

		requestResult.isSuccess = resp.isSuccessful();

		if (requestResult.isSuccess) {
			String resStr = resp.body().string();
			requestResult.result = (T) gson.fromJson(resStr, target);
		}
		return requestResult;
	}

}
