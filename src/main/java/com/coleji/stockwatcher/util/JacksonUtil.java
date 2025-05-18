package com.coleji.stockwatcher.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtil {
	public static ObjectMapper getObjectMapperWithModules() {
		return getObjectMapperBuilderWithModules().build();
	}

	public static JsonMapper.Builder getObjectMapperBuilderWithModules() {
		return JsonMapper.builder()
			.addModule(new Jdk8Module())
			.addModule(new JavaTimeModule());
//			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//			.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
//			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
}
