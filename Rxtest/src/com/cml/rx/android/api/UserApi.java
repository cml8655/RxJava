package com.cml.rx.android.api;

import com.cml.rx.android.model.Game;
import com.cml.rx.android.model.User;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import rx.Observable;

public interface UserApi {
	@POST("https://api.bmob.cn/1/classes/User")
	Observable<User> addUser(@Body User u);

	@Headers("name:1254")
	@POST("https://api.bmob.cn/1/classes/Game")
	Observable<Game> addGame(@Body Game g);
}
