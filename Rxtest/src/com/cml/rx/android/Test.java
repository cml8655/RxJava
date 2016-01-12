package com.cml.rx.android;

import com.cml.rx.android.api.ApiClient;
import com.cml.rx.android.model.Game;
import com.cml.rx.android.model.User;

public class Test {
	private static final String APP_ID = "1ab6250f9a9a0441947ba6ddeee0d5ee";
	// -H "X-Bmob-Application-Id: 1ab6250f9a9a0441947ba6ddeee0d5ee" \
	// -H "X-Bmob-REST-API-Key: 317c3a4f35ad80ccfd70df985f152274" \
	// -H "Content-Type: application/json" \
	//

	public static void main(String[] args) {
		User u = new User();
		u.setPassword("pass");
		u.setUsername("username");
		ApiClient.addUser(u);
//		Game g=new Game();
//		g.setScore(11);
//		ApiClient.addGame(g);
	}
}
