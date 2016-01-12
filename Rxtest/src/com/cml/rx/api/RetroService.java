package com.cml.rx.api;

import com.cml.rx.model.InitBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Part;

public interface RetroService {
	
	@POST("/init")
	@Headers({"authorization:Basic c2hhbmdoYWk6dGVhbS1sYWI="})
	public Call<InitBean> getBaidu();
}
