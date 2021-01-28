package org.acme.security.jdbc;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.jboss.logging.Logger;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
//
//public class PostgresqlResource implements QuarkusTestResourceLifecycleManager {
//    private static final Logger LOGGER = Logger.getLogger(PostgresqlResource.class);
//    public static final String DB_NAME = "elytron_security_jdbc";
//    private PostgreSQLContainer<?> postgreSQLContainer;
//
//    @Override public Map<String, String> start() {
//        LOGGER.info("DockerClient init ...");
//        DockerClient dockerClient = DockerClientFactory.instance().client();
//        LOGGER.info("DockerClient END ...");
//
//        postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("quay.io/debezium/postgres:latest").asCompatibleSubstituteFor("postgres"))
//                .withDatabaseName(DB_NAME)
//                .withUsername("quarkus")
//                .withPassword("quarkus")
//                .withExposedPorts(5432)
//                .withStartupCheckStrategy(new CustomStartUpStrategy())
//                .waitingFor(new CustomWaitStrategy().withRegEx(".*database system is ready to accept connections.*"))
//                .withCreateContainerCmdModifier(cmd -> cmd
//                        .withHostName("localhost")
//                        .withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432))))
//                .withInitScript("test_user.sql");
//        LOGGER.info("Starting postgreSQLContainer...");
//        postgreSQLContainer.waitingFor(new HostPortWaitStrategy()).start();
//        LOGGER.info("postgreSQLContainer started");
//        Map<String, String> properties = new HashMap<>();
//        properties.put("quarkus.datasource.jdbc.url", postgreSQLContainer.getJdbcUrl());
//        return properties;
//    }
//
//    @Override public void stop() {
//        if (Objects.nonNull(postgreSQLContainer)) {
//            postgreSQLContainer.stop();
//        }
//    }
//}
