package com.cml.rx.subscription;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class Test {
	public static void main(String[] args) {
		System.out.println("========================");

		System.out.println("====>cpu核数：" + Runtime.getRuntime().availableProcessors());

		BehaviorSubject<Boolean> subject = BehaviorSubject.create();
		Subscription sc = subject.subscribe(new Subscriber<Boolean>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError");
			}

			@Override
			public void onNext(Boolean t) {
				System.out.println("onNext:" + t);
			}
		});

		subject.onNext(false);
		subject.onNext(false);
		subject.onNext(true);

		Subscription subscription = Observable.just("Hello subscription").doOnUnsubscribe(new Action0() {

			@Override
			public void call() {
				System.out.println("unsubscription==>");
			}
		}).subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println(s);
			}
		});
		System.out.println(subscription.isUnsubscribed());
		subscription.unsubscribe();
		System.out.println(subscription.isUnsubscribed());

		CompositeSubscription compositeSubscription = new CompositeSubscription(subscription, sc);
		compositeSubscription.unsubscribe();

	}
}
