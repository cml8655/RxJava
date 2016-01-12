package com.cml.rx.android;

import rx.Subscriber;
import rx.functions.Func1;

import com.cml.rx.android.api.ApiClient;
import com.cml.rx.android.api.ApiService;
import com.cml.rx.android.model.User;

public class Test {

	public static void main(String[] args) {
		ApiClient apiClient = ApiClient.create(ApiService.BASE_URL);
		User u = new User();
		u.setUsername("myusername11");
		u.setPassword("mypass11");
		apiClient.getApiService().addUser(u).subscribe(new Subscriber<User>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(User user) {
				System.out.println("====nextuser====>" + user);
			}
		});
	}
}
