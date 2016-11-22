package com.rodrigoap.redis;

import redis.clients.jedis.Jedis;

public class Service {
	
	private Long MAX_CALLS = 60L;
	private int TIME_WINDOW = 30; //seconds
	
	private Jedis jedis = new Jedis("localhost");
	
	public Service() {
	}

	public void doSomething(String clientId, String data) {
		String key = "doSomething" + clientId;
		if (allow(key)) {
			System.out.println("yes!");
		} else {
			System.out.println("\tno");
		}
	}
	
	private boolean allow(String key) {
		boolean allowed = false;
		Long nowSeconds = System.currentTimeMillis() / 1000;
		jedis.zremrangeByScore(key, 0, nowSeconds-TIME_WINDOW);
		Long count = jedis.zcount(key, "-inf", "+inf");
		System.out.println(count);
		allowed = count < MAX_CALLS;
		if (allowed) {
			jedis.zadd(key, nowSeconds, "" + System.currentTimeMillis());
		}
		return allowed;
	}

}
