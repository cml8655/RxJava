package com.cml.rx;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;

public class DeferTest {

	static String value = "default";

	public static void main(String[] args) throws InterruptedException {
		System.out.println("----------");

		Observable<Object> o1 = Observable.defer(new Func0<Observable<Object>>() {

			@Override
			public Observable<Object> call() {
				String v=value;
				return Observable.just(test("defer==>"+v));
			}
		});
		Observable<String> o2 = Observable.just(value);

		value = "remove";

	
		o1.subscribe();
		o2.subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("=ddd"+t);
			}
		});

		System.out.println("=-=========");
	}

	private static Object test(String from) {
		System.out.println("test===>" + from);
		return from;
	}
}
