package com.github.ollijm.embeddedjetty;

import org.eclipse.jetty.server.Server;
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

    public static void main(String[] args) {
        Server server = new Server(8090);
        server.setHandler(new HelloHandler());

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("Something terrible happened...", e);
        }
    }
}
