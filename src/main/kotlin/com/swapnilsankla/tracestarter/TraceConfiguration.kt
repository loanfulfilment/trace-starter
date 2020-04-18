package com.swapnilsankla.tracestarter

import io.jaegertracing.internal.samplers.ConstSampler
import io.opentracing.Tracer
import io.opentracing.util.GlobalTracer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class TraceConfiguration {

    @Value("\${application.name}")
    private lateinit var applicationName: String

    @Value("\${tracer.host}")
    private lateinit var jaegerHost: String

    @Value("\${tracer.port}")
    private var jaegerPort: Int = 0

    @Bean
    fun tracer(): Tracer {
        return io.jaegertracing.Configuration.fromEnv(applicationName)
            .withSampler(
                io.jaegertracing.Configuration.SamplerConfiguration.fromEnv()
                    .withType(ConstSampler.TYPE)
                    .withParam(1)
            )
            .withReporter(
                io.jaegertracing.Configuration.ReporterConfiguration.fromEnv()
                    .withLogSpans(true)
                    .withFlushInterval(1000)
                    .withMaxQueueSize(10000)
                    .withSender(
                        io.jaegertracing.Configuration.SenderConfiguration.fromEnv()
                            .withAgentHost(jaegerHost)
                            .withAgentPort(jaegerPort)
                    )
            )
            .tracer
    }

    @PostConstruct
    fun registerToGlobalTracer() {
        if (!GlobalTracer.isRegistered()) {
            GlobalTracer.register(tracer())
        }
    }
}

