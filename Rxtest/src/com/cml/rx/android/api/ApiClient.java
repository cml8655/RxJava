package com.cml.rx.android.api;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

public class ApiClient {

	private static final String APP_ID = "1ab6250f9a9a0441947ba6ddeee0d5ee";
	private static final String API_KEY = "317c3a4f35ad80ccfd70df985f152274";

	private ApiService apiService;

	private ApiClient() {
	}

	public static final ApiClient create(String baseUrl) {
		ApiClient apiClient = new ApiClient();
		Retrofit retrofit = apiClient.build(baseUrl);
		apiClient.buildApiService(retrofit);
		return apiClient;
	}

	public ApiService getApiService() {
		return apiService;
	}

	private void buildApiService(Retrofit retrofit) {
		this.apiService = retrofit.create(ApiService.class);
	}

	/**
	 * 基于baseurl build retrofit对象，推荐使用静态变量定义
	 * 
	 * @param baseUrl
	 * @return
	 */
	private Retrofit build(String baseUrl) {
		Retrofit retrofit = new Retrofit.Builder()
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(new Gson()))
				.baseUrl(baseUrl).build();
		retrofit.client().interceptors().add(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				com.squareup.okhttp.Request.Builder requestBuilder = chain
						.request().newBuilder();
				requestBuilder.addHeader("X-Bmob-Application-Id", APP_ID);
				requestBuilder.addHeader("X-Bmob-REST-API-Key", API_KEY);
				requestBuilder.addHeader("Content-Type", "application/json");
				System.out.println("===========>"
						+ requestBuilder.build().httpUrl());
				return chain.proceed(requestBuilder.build());
			}
		});
		return retrofit;
	}

}
