package com.amm.stores.rest.metrics;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import com.codahale.metrics.servlet.InstrumentedFilterContextListener;
import com.codahale.metrics.servlet.InstrumentedFilter;

public class MetricsServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent eve) {
		ServletContext servletContext = eve.getServletContext();
		MetricRegistry metricRegistry = MetricState.REGISTRY;
		servletContext.setAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY,MetricState.HEALTH_CHECK_REGISTRY);
		servletContext.setAttribute(MetricsServlet.METRICS_REGISTRY, metricRegistry);
		servletContext.setAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE, metricRegistry);
	}

	@Override
	public void contextDestroyed(javax.servlet.ServletContextEvent event) {
	}
}
