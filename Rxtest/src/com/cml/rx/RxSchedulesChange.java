package com.cml.rx;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.Subscriber;

public class RxSchedulesChange {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("--------rx线程切换---------");

		Observable.create(new OnSubscribe<Integer>() {

			@Override
			public void call(Subscriber<? super Integer> t) {
				printThread("obserabel call");
				t.onNext(1);
				t.onCompleted();
			}
		}).observeOn(Schedulers.newThread()).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				printThread("onnext");
			}
		}).observeOn(Schedulers.newThread()).map(new Func1<Integer, Long>() {

			@Override
			public Long call(Integer t) {
				printThread("int to long ");
				return t.longValue() + 1;
			}
		}).subscribeOn(Schedulers.newThread()).map(new Func1<Long, Long>() {

			@Override
			public Long call(Long t) {
				printThread("long to long ");
				return t.longValue() + 1;
			}
		}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe();

		Thread.sleep(1000);
	}

	static void printThread(String tag) {
		System.out.println(tag + "----------ThreadId:" + Thread.currentThread().getId());
	}
}
