package com.cml.springbatch.thread;

import java.util.concurrent.ExecutionException;

import com.google.gson.JsonObject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class WatingThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		final String url = "https://raw.githubusercontent.com/cml8655/data/master/data1";
		ApiRequest.<JsonObject> requestObservableApi(url, JsonObject.class)
				.flatMap(new Func1<JsonObject, Observable<? extends JsonObject>>() {

					@Override
					public Observable<? extends JsonObject> call(JsonObject t) {
						return ApiRequest.<JsonObject> requestObservableApi(
								"https://raw.githubusercontent.com/cml8655/data/master/data2", JsonObject.class);
					}
				}).subscribe(new Subscriber<JsonObject>() {

					@Override
					public void onCompleted() {
						System.out.println("completed");
					}

					@Override
					public void onError(Throwable e) {
						System.out.println("error");
						e.printStackTrace();
					}

					@Override
					public void onNext(JsonObject t) {
						System.out.println("onnext:" + t);
					}
				});
		Thread.sleep(6000);
		System.out.println("end");
	}
}
