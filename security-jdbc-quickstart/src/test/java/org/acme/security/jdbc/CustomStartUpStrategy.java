package org.acme.security.jdbc;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import java.util.concurrent.TimeUnit;
import org.rnorth.ducttape.ratelimits.RateLimiter;
import org.rnorth.ducttape.ratelimits.RateLimiterBuilder;
import org.testcontainers.containers.startupcheck.StartupCheckStrategy;
import org.testcontainers.utility.DockerStatus;

public class CustomStartUpStrategy extends StartupCheckStrategy {

    private static final RateLimiter DOCKER_CLIENT_RATE_LIMITER;

    static {
        DOCKER_CLIENT_RATE_LIMITER = RateLimiterBuilder.newBuilder().withRate(60, TimeUnit.SECONDS).withConstantThroughput().build();
    }

    @Override
    public StartupStatus checkStartupState(DockerClient dockerClient, String containerId) {
        InspectContainerResponse.ContainerState state = getCurrentState(dockerClient, containerId);
        if (state.getRunning()) {
            return StartupStatus.SUCCESSFUL;
        } else if (!DockerStatus.isContainerExitCodeSuccess(state)) {
            return StartupStatus.FAILED;
        } else {
            return StartupStatus.NOT_YET_KNOWN;
        }
    }
}
