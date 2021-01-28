package org.acme.security.jdbc;

import java.util.concurrent.TimeUnit;
import org.rnorth.ducttape.ratelimits.RateLimiter;
import org.rnorth.ducttape.ratelimits.RateLimiterBuilder;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

public class CustomWaitStrategy extends AbstractWaitStrategy {

    private static final RateLimiter DOCKER_CLIENT_RATE_LIMITER;
    private org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy delegateWaitStrategy = new org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy();

    static {
        DOCKER_CLIENT_RATE_LIMITER = RateLimiterBuilder.newBuilder().withRate(60, TimeUnit.SECONDS).withConstantThroughput().build();
    }

    @Override
    protected void waitUntilReady() {
        delegateWaitStrategy.waitUntilReady(this.waitStrategyTarget);
    }

    public CustomWaitStrategy withRegEx(String regEx) {
        delegateWaitStrategy.withRegEx(regEx);
        return this;
    }

    public CustomWaitStrategy withTimes(int times) {
        delegateWaitStrategy.withTimes(times);
        return this;
    }
}
