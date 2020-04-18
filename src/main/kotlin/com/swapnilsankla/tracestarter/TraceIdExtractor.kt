package com.swapnilsankla.tracestarter

import org.springframework.stereotype.Component

@Component
class TraceIdExtractor {

    private val traceHeader: String = "uber-trace-id"

    fun fromKafkaHeaders(headers: Map<String, Any>): Trace {
        return Trace(
            key = traceHeader,
            value = headers[traceHeader] as? ByteArray ?: ByteArray(1))
    }
}