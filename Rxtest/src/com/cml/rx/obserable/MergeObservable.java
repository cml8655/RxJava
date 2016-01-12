package com.cml.rx.obserable;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class MergeObservable {
	/**
	 * 在异步的世界经常会创建这样的场景，我们有多个来源但是只想有一个结果：多输入，单输出。RxJava的merge()
	 * 方法将帮助你把两个甚至更多的Observables合并到他们发射的数据里。下图给出了把两个序列合并在一个最终发射的Observable。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Observable<Integer> ob1 = Observable.create(new OnSubscribe<Integer>() {

			@Override
			public void call(Subscriber<? super Integer> t) {
				for (int i = 100; i < 110; i++) {
					t.onNext(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				t.onCompleted();
			}
		});
		Observable<Integer> ob2 = Observable.just(1, 2);
		Observable.merge(ob2, ob1).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("===============onCompleted============");
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Integer t) {
				System.out.println("====>" + t);
			}
		});
	}
}
