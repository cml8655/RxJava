package com.cml.rx.subscription;

import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

public class MultiThread {
	public static void main(String[] args) throws InterruptedException {

		final PublishSubject<Integer> subject = PublishSubject.create();

		subject.subscribe(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("======onnext===>value:" + t + ",threadId:" + Thread.currentThread().getId());
			}

		});

		final SerializedSubject<Integer, Integer> ser = new SerializedSubject<Integer, Integer>(subject);

		for (int i = 0; i < 20; i++) {
			final int value = i;
			new Thread() {
				public void run() {
					ser.onNext((int) (value * 10000 + Thread.currentThread().getId()));
				};
			}.start();
		}

		Thread.sleep(2000);
		//
		// for (int i = 11; i < 20; i++) {
		// final int value = i;
		// new Thread() {
		// public void run() {
		// subject.onNext(value);
		// };
		// }.start();
		// }

	}
}