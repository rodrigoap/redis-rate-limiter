package com.rodrigoap.redis;

import java.util.Random;

public class RedisClient {

	public static void main(String[] args) {
		Random rnd = new Random();
		Service srv = new Service();
		while (true) {
			srv.doSomething("APIKEY2342", "{id:199, action:354}");
			try {
				Thread.sleep(rnd.nextInt(1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
