package com.cml.rx.android.api;

import com.cml.rx.android.model.User;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface ApiService {
	String BASE_URL = "https://api.bmob.cn";
	String VERSION = "/1";

	interface Module {
		String BASE_MODULE = VERSION + "/classes";
		String USER = BASE_MODULE + "/User";
	}

	@POST(Module.USER)
	Observable<User> addUser(@Body User u);

}