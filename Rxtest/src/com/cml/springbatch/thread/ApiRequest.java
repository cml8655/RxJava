package com.cml.springbatch.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ApiRequest {

	public static <T> Observable<T> requestObservableApi(final String url, final Class target) {
		return Observable.create(new OnSubscribe<T>() {

			@Override
			public void call(Subscriber<? super T> t) {
				if (!t.isUnsubscribed()) {
					try {
						T result = new ApiRequest().request(url, target);
						t.onNext(result);
						t.onCompleted();
					} catch (Exception e) {
						t.onError(e);
					}

				}
			}
		}).subscribeOn(Schedulers.io());
	}

	private Gson gson = new Gson();

	public <T> T request(String url, Class target) throws Exception {
		String resp = getString(url);
		System.out.println("resp:"+resp);
		return (T) gson.fromJson(resp, target);
	}

	private String getString(String url) throws Exception {
		BufferedReader reader = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");

			conn.setDoInput(true);
			conn.setDoOutput(true);

			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));

			String line;
			StringBuffer response = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			return response.toString();

		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
