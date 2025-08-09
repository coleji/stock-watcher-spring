package com.coleji.stockwatcher.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JacksonUtil {
	fun getObjectMapperWithModules(): ObjectMapper {
		return getObjectMapperBuilderWithModules().build().registerKotlinModule()
	}

	fun getObjectMapperBuilderWithModules(): JsonMapper.Builder {
		return JsonMapper.builder()
			.addModule(Jdk8Module())
			.addModule(JavaTimeModule())
//			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//			.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
//			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
}