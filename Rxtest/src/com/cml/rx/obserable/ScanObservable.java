package com.cml.rx.obserable;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

public class ScanObservable {
	/**
	 * 计算1+..10=?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Observable.range(1, 10).scan(new Func2<Integer, Integer, Integer>() {

			public Integer call(Integer t1, Integer t2) {
				System.out.println("============>" + t1 + "," + t2);
				return t1 + t2;
			}
		}).subscribe(new Subscriber<Integer>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onNext(Integer item) {
				System.out.println("===>" + item);
			}
		});
	}
}
