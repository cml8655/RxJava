package com.cml.rx.obserable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class ZipObservable {
	/**
	 * 多从个Observables接收数据，处理它们，然后将它们合并成一个新的可观测序列来使用。
	 * <p>
	 * 数量取决于最小数量的Observable	
	 * </p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Integer> ob1 = Observable.just(1, 2, 3);
		Observable<Integer> ob2 = Observable.just(11, 22, 33, 44, 55);

		Observable.zip(ob1, ob2, new Func2<Integer, Integer, Integer>() {

			@Override
			public Integer call(Integer t1, Integer t2) {
				return t1 + t2;
			}
		}).subscribe(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("=======>" + t);
			}
		});
	}
}
