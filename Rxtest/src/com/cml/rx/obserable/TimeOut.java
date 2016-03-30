package com.cml.rx.obserable;

import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class TimeOut {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("----------time out --------------");
		// 要执行 先得publish，在subscribe，只有subscribe后才有效
		PublishSubject<String> subject = PublishSubject.create();

		PublishSubject<Integer> other = PublishSubject.create();

		subject.onNext("1");
		subject.takeUntil(other).subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("call->" + t);
			}
		});

		subject.onNext("2");
		subject.onNext("3");

		other.onNext(33);

		subject.onNext("4");
		subject.onNext("5");

		subject.onNext("6");

		System.out.println("=-------------end-------------");
		// timeOut();
	}


	private static void timeOut() throws InterruptedException {
		// 在指定时间内必须要有元素添加，否则抛出异常
		PublishSubject<Integer> subject = PublishSubject.create();
		subject.timeout(2, TimeUnit.SECONDS).subscribe(new Observer<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError");
				e.printStackTrace();

			}

			@Override
			public void onNext(Integer t) {
				System.out.println("onNext===>" + t);

			}
		});
		Thread.sleep(1000);
		subject.onNext(1);
		Thread.sleep(5000);
	}
}
