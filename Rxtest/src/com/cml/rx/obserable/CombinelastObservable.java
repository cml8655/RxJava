package com.cml.rx.obserable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

public class CombinelastObservable {

	/**
	 * 只会获取观察者最后一个数据进行处理
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final BehaviorSubject<Integer> ob1 = BehaviorSubject.create(1);
		Observable<Long> ob2 = Observable.just(1l, 2l, 3l, 4l);
		Observable.combineLatest(ob1, ob2, new Func2<Integer, Long, Long>() {

			@Override
			public Long call(Integer t1, Long t2) {
				System.out.println("-----" + t1 + ":" + t2);
				return t1 * t2;
			}
		}).subscribe(new Action1<Long>() {

			@Override
			public void call(Long t) {
				System.out.println("===>" + t);
			}
		});

		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					ob1.onNext(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ob1.onCompleted();
			};
		}.start();

		Thread.sleep(100000);
	}

}
