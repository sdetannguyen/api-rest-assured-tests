package mock.impl;

import com.xebialabs.restito.server.StubServer;
import io.restassured.RestAssured;
import mock.MockService;
import org.apache.log4j.Logger;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.resourceContent;

public class RestitoMockServiceImpl implements MockService<StubServer> {

    private static final Logger LOGGER = Logger.getLogger(RestitoMockServiceImpl.class);

    private static final ThreadLocal<StubServer> mockThreadLocal = new ThreadLocal<>();

    @Override
    public void up(final String hostName, final String basePath, final String dataSource) {
        mockThreadLocal.set(new StubServer().run());
        startRestitoMock(mockThreadLocal.get(), hostName, mockThreadLocal.get().getPort(), basePath, dataSource);
    }

    @Override
    public StubServer getInstance() {
        return mockThreadLocal.get();
    }

    @Override
    public void down() {
        LOGGER.info("Destroying Restito Mock >>>>");
        mockThreadLocal.get().stop();
    }

    public void freeAll() {
        if(mockThreadLocal.get() != null) {
            down();
            LOGGER.info("Free all Restito Mock >>>>");
            mockThreadLocal.remove();
        }
    }

    private void startRestitoMock(final StubServer server, final String hostName, final int port,
                                  final String basePath, final String dataSource) {
        LOGGER.info("Starting Restito Mock with configs: ");
        setHostNameAndPortForMock(hostName, port);
        whenHttp(server)
                .match(String.valueOf(RestAssured.get(basePath)))
                .then(resourceContent(dataSource));
    }

    private static void setHostNameAndPortForMock(final String hostName, final int port) {
        RestAssured.port = port;
        RestAssured.baseURI = hostName;
        RestAssured.basePath = hostName + ":" + port;
    }
}
