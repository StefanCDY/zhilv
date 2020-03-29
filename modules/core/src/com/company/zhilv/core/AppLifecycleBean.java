package com.company.zhilv.core;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.sys.events.AppContextInitializedEvent;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.core.sys.events.AppContextStoppedEvent;
import com.haulmont.cuba.core.sys.servlet.events.ServletContextDestroyedEvent;
import com.haulmont.cuba.core.sys.servlet.events.ServletContextInitializedEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(AppLifecycleBean.NAME)
public class AppLifecycleBean {
    public static final String NAME = "zhilv_AppLifecycleBean";
    @Inject
    private Logger logger;

    @EventListener(AppContextInitializedEvent.class)
    @Order(Events.HIGHEST_PLATFORM_PRECEDENCE)
    protected void appInitialized() {
        logger.info("The Application Initialized");
    }

    @EventListener
    @Order(Events.HIGHEST_PLATFORM_PRECEDENCE)
    protected void appStarted(AppContextStartedEvent event) {
        logger.info("The Application Started");
    }

    @Order(Events.LOWEST_PLATFORM_PRECEDENCE)
    @EventListener
    protected void appStopped(AppContextStoppedEvent event) {
        logger.info("The Application Stopped");
    }

    @EventListener
    @Order(Events.HIGHEST_PLATFORM_PRECEDENCE)
    public void servletContextInitialized(ServletContextInitializedEvent e) {
        logger.info("The Application and servlet context is initialized");
    }

    @EventListener
    @Order(Events.LOWEST_PLATFORM_PRECEDENCE)
    public void servletContextDestroyed(ServletContextDestroyedEvent e) {
        logger.info("The Application is about to shut down, all contexts are now destroyed");
    }
}