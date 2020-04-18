package com.swapnilsankla.tracestarter

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CustomKafkaTemplate(@Autowired private val kafkaTemplate: KafkaTemplate<String, String>,
                          @Autowired private val objectMapper: ObjectMapper) {
    fun <T> publish(topic: String, data: T, trace: Trace? = null) {

        val producerRecord = ProducerRecord<String, String>(
                topic,
                objectMapper.writeValueAsString(data)
        )

        trace?.let {
            producerRecord.headers().remove(trace.key)
            producerRecord.headers().add(trace.key, trace.value)
        }

        kafkaTemplate.send(producerRecord)
    }
}