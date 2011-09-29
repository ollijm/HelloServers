package com.github.ollijm.embeddedjetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Launch embedded Jetty with few handlers.
 * First try if request is for servlets.
 * If no match for servlets, look for pleaseSay URL param.
 * If no pleaseSay param, return 404.
 *
 * GET /servlet/Hello* -> HelloServlet
 * GET /servlet/Hullo* -> HulloServlet
 * GET /*?pleaseSay=something -> HelloHandler
 * GET /* -> NotFoundHandler
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

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/servlet");
        context.addServlet(new ServletHolder(new HelloServlet()), "/Hello/*");
        context.addServlet(new ServletHolder(new HulloServlet()), "/Hullo/*");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{context, new HelloHandler(), new NotFoundHandler()}); // Order matters
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
