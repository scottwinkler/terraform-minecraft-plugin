package com.cocoapebbles.terraform.web;

import com.cocoapebbles.terraform.web.resources.EntityResource;
import com.cocoapebbles.terraform.web.resources.ShapeResource;
import com.cocoapebbles.terraform.web.resources.ShapeJsonReader;
import com.cocoapebbles.terraform.web.resources.EntityResource;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.inject.Singleton;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import java.io.File;
import java.util.logging.Logger;

/**
 * Simple Jetty FileServer.
 * This is a simple example of Jetty configured as a FileServer.
 */
public class WebServer {
    private Logger logger;
    private Integer port;
    private JavaPlugin p;
    private Server server;
    public WebServer(JavaPlugin p) {
        logger = p.getLogger();
        this.p = p;
        port = p.getConfig().getInt("server.port");
        if (p.getConfig().getBoolean("server.enable")) {
            initializeFileServer();
        } else {
            logger.info("Server disabled, skipping");
        }

    }
    public void shutDownServer(){
        try {
            if(server!=null){
                logger.info("Shutting down server running on port: "+port);
                server.stop();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initializeFileServer() {
        logger.info("Initializing server");
        try {
            File dataDir = p.getDataFolder();
            server = new Server(port);
            server.setStopAtShutdown(true);
/*
            //cube servlet
            ResourceConfig resourceConfig = new ResourceConfig(CubeResource.class);
            Logger logger = p.getLogger();
            resourceConfig.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(logger).to(Logger.class);
                    bind(p).to(JavaPlugin.class);
                }
            });
            ServletContainer container = new ServletContainer(resourceConfig);
            ServletHolder holder = new ServletHolder(container);
            ServletContextHandler cubeContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            cubeContext.setContextPath("/cube");
            cubeContext.addServlet(holder,"/*");
*/
            //shape servlet
            ResourceConfig shapeResourceConfig = new ResourceConfig(ShapeResource.class);;
            shapeResourceConfig.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(ShapeJsonReader.class).in(Singleton.class);
                    bind(logger).to(Logger.class);
                    bind(p).to(JavaPlugin.class);
                }
            });
            ServletContainer shapeContainer = new ServletContainer(shapeResourceConfig);
            ServletHolder shapeHolder = new ServletHolder(shapeContainer);
            ServletContextHandler shapeContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            shapeContext.setContextPath("/shape");
            shapeContext.addServlet(shapeHolder,"/*");

            //entity servlet
            ResourceConfig entityResourceConfig = new ResourceConfig(EntityResource.class);;
            entityResourceConfig.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(logger).to(Logger.class);
                    bind(p).to(JavaPlugin.class);
                }
            });
            ServletContainer entityContainer = new ServletContainer(entityResourceConfig);
            ServletHolder entityHolder = new ServletHolder(entityContainer);
            ServletContextHandler entityContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            entityContext.setContextPath("/entity");
            entityContext.addServlet(entityHolder,"/*");
/*
            //cylinder servlet
            ResourceConfig cylinderResourceConfig = new ResourceConfig(CylinderResource.class);;
            cylinderResourceConfig.register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(logger).to(Logger.class);
                    bind(p).to(JavaPlugin.class);
                }
            });
            ServletContainer cylinderContainer = new ServletContainer(cylinderResourceConfig);
            ServletHolder cylinderHolder = new ServletHolder(cylinderContainer);
            ServletContextHandler cylinderContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            cylinderContext.setContextPath("/cylinder");
            cylinderContext.addServlet(cylinderHolder,"/*");
*/

            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[]{
                    //cubeContext,entityContext,cylinderContext
                    shapeContext,
                    entityContext
            });
            server.setHandler(contexts);
            server.start();
            logger.info("Server running on port: " + port + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
