package com.github.ollijm.embeddedjetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: olli
 * Date: 9/18/11
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloHandler extends AbstractHandler {

    private static Logger log = LoggerFactory.getLogger(HelloHandler.class);

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Embedded Jetty says Hullo!</h1>");
    }
}
