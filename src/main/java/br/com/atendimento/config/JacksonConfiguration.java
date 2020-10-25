package br.com.atendimento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class JacksonConfiguration {

	/*
	 * @Bean
	 * 
	 * @Primary public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder
	 * builder) {
	 * 
	 * ObjectMapper objectMapper = builder.build();
	 * 
	 * Hibernate5Module hibernateModule = new Hibernate5Module();
	 * objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
	 * false); hibernateModule.configure(Hibernate5Module.Feature.
	 * WRITE_MISSING_ENTITIES_AS_NULL, true);
	 * hibernateModule.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION,
	 * false); objectMapper.registerModule(hibernateModule); return objectMapper; }
	 */
    
	@Bean
	public com.fasterxml.jackson.databind.Module datatypeHibernateModule() {
	    return new Hibernate5Module();
	}
    
    
    
   
}
