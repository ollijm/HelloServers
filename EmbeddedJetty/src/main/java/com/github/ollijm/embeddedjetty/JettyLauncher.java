package com.github.ollijm.embeddedjetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: olli
 * Date: 9/18/11
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class JettyLauncher {
    private static Logger log = LoggerFactory.getLogger(JettyLauncher.class);

    public static void main(String[] args) throws Exception {
        new JettyLauncher().launch();
    }

    public void launch() throws Exception {
        Server server = new Server();

        // Use the recommended NIO connector
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8090);
        connector.setMaxIdleTime(10000);

        server.setConnectors(new Connector[]{connector});
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}
