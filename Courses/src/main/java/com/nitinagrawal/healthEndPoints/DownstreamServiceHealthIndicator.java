package com.nitinagrawal.healthEndPoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class DownstreamServiceHealthIndicator implements ReactiveHealthIndicator {
 
    @Override
    public Mono<Health> health() {
        return checkDownstreamServiceHealth().onErrorResume(
          ex -> Mono.just(new Health.Builder().down(ex).build())
        );
    }
 
    private Mono<Health> checkDownstreamServiceHealth() {
        // we could use WebClient to check health reactively
    	Health health = new Health.Builder()
    							  .up()
    							  .withDetail("ReactiveHealthIndicator", "This custom indicator is working!")
    							  .build();
        return Mono.just(health);
    }
}