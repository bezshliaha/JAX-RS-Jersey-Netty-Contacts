package org.example.app.config;

import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.MalformedURLException;
import java.net.URI;

public class NettyServerProvider {

    private final static String BASE_URI = "http://localhost/";
    private final static int PORT = 8081;

    public static String startHttpServer(final Class<?>... classes)
            throws MalformedURLException {
        final ResourceConfig rc = new ResourceConfig(classes);
        URI baseUri = UriBuilder.fromUri(BASE_URI)
                .port(PORT).build();
        NettyHttpContainerProvider.createServer(baseUri, rc, false);
        return String.format("App running on %s%n",
                baseUri.toURL().toExternalForm());
    }
}
