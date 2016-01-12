package com.cml.rx.android.api;

import java.io.IOException;

import com.cml.rx.android.model.Game;
import com.cml.rx.android.model.User;
import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import okio.BufferedSink;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Retrofit.Builder;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Header;
import rx.Subscriber;

public class ApiClient {
	private static Retrofit retrofit;
	private static final String BASE_URL = "https://api.bmob.cn/1/classes/";

	static {
		retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(new Gson())).baseUrl(BASE_URL).build();
	}

	public static void addUser(User u) {
		retrofit.client().interceptors().add(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				// com.squareup.okhttp.Headers.Builder builder =
				// request.headers().newBuilder();
				// builder.add("X-Bmob-Application-Id",
				// "1ab6250f9a9a0441947ba6ddeee0d5ee");
				// builder.add("X-Bmob-REST-API-Key",
				// "317c3a4f35ad80ccfd70df985f152274");
				// builder.add("Content-Type", "application/json");

				com.squareup.okhttp.Request.Builder requestBuilder = chain.request().newBuilder();
				requestBuilder.addHeader("X-Bmob-Application-Id", "1ab6250f9a9a0441947ba6ddeee0d5ee");
				requestBuilder.addHeader("X-Bmob-REST-API-Key", "317c3a4f35ad80ccfd70df985f152274");
				requestBuilder.addHeader("Content-Type", "application/json");
				
				System.out.println(request.body().contentLength());
				System.out.println(request.headers());
				System.out.println("-------------------==========---------");
				return chain.proceed(requestBuilder.build());
			}
		});
		retrofit.create(UserApi.class).addUser(u).subscribe(new Subscriber<User>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError");
				e.printStackTrace();
			}

			@Override
			public void onNext(User t) {
				System.out.println("dddnext:==>" + t);
			}
		});
	}

	public static void addGame(Game u) {
		retrofit.client().interceptors().add(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				// com.squareup.okhttp.Headers.Builder builder =
				// request.headers().newBuilder();
				// builder.add("X-Bmob-Application-Id",
				// "1ab6250f9a9a0441947ba6ddeee0d5ee");
				// builder.add("X-Bmob-REST-API-Key",
				// "317c3a4f35ad80ccfd70df985f152274");
				// builder.add("Content-Type", "application/json");

				com.squareup.okhttp.Request.Builder requestBuilder = chain.request().newBuilder();
				requestBuilder.addHeader("X-Bmob-Application-Id", "1ab6250f9a9a0441947ba6ddeee0d5ee");
				requestBuilder.addHeader("X-Bmob-REST-API-Key", "317c3a4f35ad80ccfd70df985f152274");
				requestBuilder.addHeader("Content-Type", "application/json");
				
				System.out.println(request.body().contentLength());
				System.out.println(request.headers());
				System.out.println("-------------------==========---------");
				return chain.proceed(requestBuilder.build());
			}
		});
		retrofit.create(UserApi.class).addGame(u).subscribe(new Subscriber<Game>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError");
				e.printStackTrace();
			}

			@Override
			public void onNext(Game g) {
				System.out.println("dddnext:==>" + g);
			}
		});
	}
}
