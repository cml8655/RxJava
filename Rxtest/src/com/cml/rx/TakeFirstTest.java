package com.cml.rx;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class TakeFirstTest {
	public static void main(String[] args) {
		System.out.println("===============");
		Observable<Integer> first = Observable.just(1, 2, 3);
		Observable<Integer> second = Observable.just(11, 12, 13);
		Observable<Integer> third = Observable.just(21, 22, 23);

		Observable.concat(first, second, third).takeFirst(new Func1<Integer, Boolean>() {

			@Override
			public Boolean call(Integer t) {
				// TODO 判断内存是否有
				return t == 3;
			}
		}).subscribe(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("===>value:" + t);
			}
		});
	}
}
