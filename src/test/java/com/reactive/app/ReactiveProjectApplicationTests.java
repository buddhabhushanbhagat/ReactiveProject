package com.reactive.app;

import java.util.Arrays;

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
		Mono<String> m1 = Mono.just("Bhushan")
				.log();
		m1.subscribe(s->System.out.println(s));
		
		//Map and flatMap
		Mono<String> nameInCap = m1.map(s->s.toUpperCase());
		nameInCap.subscribe(e->System.out.println(e));
		
		Mono<String[]> stringArray = m1.flatMap(s->Mono.just(s.split(" ")));
		
		stringArray.subscribe(e->System.out.println(Arrays.toString(e)));
		 	
	}

}
