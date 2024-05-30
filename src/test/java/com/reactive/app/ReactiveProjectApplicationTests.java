package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;

@SpringBootTest
class ReactiveProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void workingWithMono() {
		System.out.println("testing");
		Mono<String> m1 = Mono.just("Bhushan");
		m1.subscribe(s->System.out.println(s));
		 	
	}

}
