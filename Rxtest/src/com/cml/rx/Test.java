package com.cml.rx;

import java.io.File;

import rx.Notification;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Test {
	public static void main(String[] args) throws InterruptedException {

		Observable<File> fileObserver = Observable.just(new File("F:/android_workspace_lawsonph3"));
		fileObserver.flatMap(new Func1<File, Observable<? extends File>>() {

			@Override
			public Observable<? extends File> call(File t) {
				System.out.println("======================flatMap====>" + t.getName());
				return Observable.from(t.listFiles());
			}
		}).filter(new Func1<File, Boolean>() {

			@Override
			public Boolean call(File t) {
//				System.out.println("===filter==>" + t.getName());
				return true;
			}
		}).flatMap(new Func1<File, Observable<? extends File>>() {

			@Override
			public Observable<? extends File> call(File t) {
				System.out.println("======================flatMap2====>" + t.getName());
				return Observable.from(t.listFiles());
			}
		}).map(new Func1<File, File>() {

			@Override
			public File call(File t) {
				return t;
			}
		}).doOnNext(new Action1<File>() {

			@Override
			public void call(File t) {
				 System.out.println("===doOnNext===>" + t.getName());
			}
		}).subscribe();

	}

	public static void main3(String[] args) throws InterruptedException {

		final String[] data = new String[] { "1", "2", "3" };
		// 自定义类型转换
		// 在 doOnSubscribe()的后面跟一个 subscribeOn() ，就能指定准备工作的线程了
		Observable<Integer> ob = Observable.just(1, 2);
		ob.map(new Func1<Integer, String>() {

			@Override
			public String call(Integer arg0) {
				return arg0 + "==" + Thread.currentThread().getId() + "==";
			}
		}).subscribeOn(Schedulers.newThread()).doOnCompleted(new Action0() {

			@Override
			public void call() {
				System.out.println("complete" + Thread.currentThread().getId());
			}
		}).doOnNext(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("doOnNext===>" + Thread.currentThread().getId());
			}
		}).doOnSubscribe(new Action0() {

			@Override
			public void call() {
				System.out.println("onsubscribe" + Thread.currentThread().getId());
			}
		}).subscribeOn(Schedulers.newThread()).subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				System.out.println("===============" + arg0 + Thread.currentThread().getId());
			}
		});

		Thread.sleep(1000);
	}

	public static void test1() throws InterruptedException {
		final String[] data = new String[] { "1", "2", "3" };
		Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				for (int i = 0; i < data.length; i++) {
					subscriber.onNext(data[i]);
				}
				subscriber.onCompleted();
				System.out.println("call=====>" + Thread.currentThread().getId());
			}

		}).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("==============>end：" + Thread.currentThread().getId());
			}

			@Override
			public void onError(Throwable arg0) {
				System.out.println("==============>报错了：" + Thread.currentThread().getId());
			}

			@Override
			public void onNext(String arg0) {
				System.out.println("==============>onNext：" + Thread.currentThread().getId());

			}
		});

		Thread.sleep(1000);
	}
}
