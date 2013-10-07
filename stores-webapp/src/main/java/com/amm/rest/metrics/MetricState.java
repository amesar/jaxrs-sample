package com.amm.stores.rest.metrics;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.MetricRegistry;

public class MetricState {
	public static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();
	public static final MetricRegistry REGISTRY = new MetricRegistry();
}
