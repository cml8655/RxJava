package com.cml.rx;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observable.Transformer;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.BlockingObservable;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class RxComposeTest {
	public static void main(String[] args) throws InterruptedException {

		final BehaviorSubject<String> subject = BehaviorSubject.create();
		subject.subscribe();

		Observable<Integer> ob = Observable.just(1, 2, 3);
		ob.compose(new Transformer<Integer, Integer>() {

			@Override
			public Observable<Integer> call(Observable<Integer> t) {
				System.out.println("transformer=-===>");
				return t.takeUntil(subject.takeFirst(new Func1<String, Boolean>() {

					@Override
					public Boolean call(String t) {
						System.out.println("take===>" + t);
						return "end".equals(t);
					}
				}));
			}
		}).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("doOnNext111111===>" + t);
				subject.onNext("end");
				subject.onNext("end1");
			}
		}).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("doOnNext222222===>" + t);
			}
		}).subscribe();

	}

	private static void w() throws InterruptedException {
		BehaviorSubject<Integer> he = BehaviorSubject.create();
		he.asObservable().timeout(5, TimeUnit.SECONDS, Observable.range(1, 20)).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("???????????====>" + t);
			}
		}).subscribe();

		System.out.println("-------------");

		Observable.just(1, 2, 8, 9).compose(new Transformer<Integer, Integer>() {

			@Override
			public Observable<Integer> call(Observable<Integer> t) {
				System.out.println("----Transformer---->" + t);
				return t.takeUntil(new Func1<Integer, Boolean>() {

					@Override
					public Boolean call(Integer t) {
						System.out.println("takeunitl------>" + t);
						return t >= 4;
					}
				});
			}
		}).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("----doOnNext---->" + t);
			}
		}).doOnUnsubscribe(new Action0() {

			@Override
			public void call() {
				System.out.println("---------doOnUnsubscribe----");
			}
		}).subscribe();
		Thread.sleep(10000);
	}

	private static void dd() throws InterruptedException {
		System.out.println("--------------------------");
		Observable<String> readObverable = Observable.just("saveUrl").map(new Func1<String, File>() {

			@Override
			public File call(String t) {
				File f = new File(t);
				System.out.println("将地址转换成url:");
				return f;
			}
		}).flatMap(new Func1<File, Observable<? extends File>>() {

			@Override
			public Observable<? extends File> call(File t) {
				System.out.println("读取文件，获取每一个sheet===》");
				return Observable.just(t, t, t);
			}
		}).map(new Func1<File, String>() {

			@Override
			public String call(File t) {
				System.out.println("文件读取完毕，获取返回读取结果！！");
				return "读取ok";
			}
		}).doOnNext(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("文件结果返回完毕,插入db，一个sheet读取ok");
			}
		});

		Observable<Object> ob = Observable.empty().doOnCompleted(new Action0() {

			@Override
			public void call() {
				System.out.println("===================调用结束，统计db信息");
			}
		});

		Observable.merge(readObverable, ob).doOnCompleted(new Action0() {

			@Override
			public void call() {
				System.out.println("======doOnCompleted==>合并的结束了，系统结束！！！！！！！！！！！！！！！");
			}
		}).subscribe();

		System.out.println("---------------------------");

		new Subscriber<String>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onNext(String t) {
				// TODO Auto-generated method stub

			}
		}.unsubscribe();

		Observable.create(new OnSubscribe<Integer>() {

			@Override
			public void call(Subscriber<? super Integer> t) {
				System.out.println("--------------begin-------------" + Thread.currentThread().getId());
				for (int i = 0; i < 10; i++) {
					t.onNext(i);
				}
				t.onCompleted();
			}
		}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("-1---" + t + "," + Thread.currentThread().getId());
			}
		}).map(new Func1<Integer, Integer>() {

			@Override
			public Integer call(Integer t) {
				return 100 + t;
			}
		}).doOnNext(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("--2--" + t + "," + Thread.currentThread().getId());
				if (t >= 100) {
					throw new RuntimeException("ddd");
				}
			}
		}).map(new Func1<Integer, Integer>() {

			@Override
			public Integer call(Integer t) {
				return 100 + t;
			}
		}).subscribe(new Observer<Integer>() {

			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				System.out.println("出错了");
			}

			@Override
			public void onNext(Integer t) {

				System.out.println("---3333-" + t + "," + Thread.currentThread().getId());

			}
		});

		Thread.sleep(3000);
	}

	private static void c() {
		Observable.just("Apples", "Bananas", "a", "b", "c").doOnNext(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.println("do next===>" + t);
				if ("a".equals(t)) {
					throw new RuntimeException("ds");
				}

			}
		}).onExceptionResumeNext(Observable.just("error", "dd", "sss")).subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println("=====onnext===" + t);
			}
		}, new Action1<Throwable>() {
			@Override
			public void call(Throwable t) {
				System.out.println("ddddssss");
			}
		});
	}

	private static void zip() {
		Observable<Integer> evens = Observable.just(2, 4, 6, 8, 10);
		Observable<Integer> odds = Observable.just(1, 3, 5, 7, 9, 11);

		Observable.zip(evens, odds, new Func2<Integer, Integer, String>() {

			@Override
			public String call(Integer t1, Integer t2) {
				return t1 + "+" + t2 + "=" + (t1 + t2);
			}
		}).doOnNext(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.println("=-------------" + t);
			}
		}).subscribe();
	}

	/**
	 * // 内部使用countdownlatch进行阻塞运行 效果同方法a()
	 */
	public void b() {
		// 内部使用countdownlatch进行阻塞运行
		BlockingObservable<Integer> blockingObservable = Observable.just(1, 2, 3, 4, 77, 88)
				.subscribeOn(Schedulers.io()).toBlocking();
		blockingObservable.forEach(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				System.out.println("----value--" + t + "," + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * 推荐使用CountDownLatch进行等待而不是使用线程睡眠
	 * 
	 * @throws InterruptedException
	 */
	private void a() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(5);
		Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
			@Override
			public void call(Long counter) {
				latch.countDown();
				System.out.println("Got: " + counter);
			}
		});

		latch.await();
	}
}
