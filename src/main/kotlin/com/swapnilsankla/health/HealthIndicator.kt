package com.swapnilsankla.health

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.actuate.health.Status
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.ToDoubleFunction

@Component
class HealthIndicator(@Autowired val healthEndpoint: HealthEndpoint) {

    @Bean
    fun prometheusHealthCheck(): MeterRegistryCustomizer<*> {
        return MeterRegistryCustomizer<MeterRegistry> {
            it.gauge("health", healthEndpoint, ToDoubleFunction<HealthEndpoint> { healthEndpoint ->
                when {
                    healthEndpoint == null -> 0.0
                    healthEndpoint.health().status == Status.UP -> 1.0
                    else -> 0.0
                }
            })
        }
    }
}