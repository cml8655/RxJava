package com.cml.rx;

import java.io.IOException;

import com.cml.rx.api.RetroService;
import com.cml.rx.model.InitBean;
import com.google.gson.Gson;

import retrofit.Call;
import retrofit.CallAdapter;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetroFitTest {
	public static void main(String[] args) throws IOException {
		System.out.println("111");
//		CallAdapter<T>
		// Retrofit retrofit = new
		// Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(converterFactory).build();
		Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new Gson()))
				.baseUrl("http://20150601.team-lab.cn/app/2.0/").build();

		RetroService service = retrofit.create(RetroService.class);
		Call<InitBean> result = service.getBaidu();

		Response<InitBean> response = result.execute();

		System.out.println(response.message());
		System.out.println(response.code());
	}
}
