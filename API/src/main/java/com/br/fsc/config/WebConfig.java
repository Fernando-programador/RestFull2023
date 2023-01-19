package com.br.fsc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.br.fsc.config.yaml.YamlConverterHttpMessage;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private final static MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	@Value("${cors.originPatterns:default}")
	private String corsOriginPatterns = "";
	
	
	/** 
	 * CORS DE METODO GLOBAL
	 *  -> /** para todas as rotas da minha API
	 *  -> allowedMethod para metodos citados
	 *  -> allowedOrigins um array de strings que criei
	 *  -> allowCredentials para possibilitar autenticação
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	 var allowedOriginsAPI = corsOriginPatterns.split(",");

	 registry.addMapping("/**")
	 //.allowedMethods("GET", "POST, "PUT")
	 .allowedMethods("*")
	 .allowedOrigins(allowedOriginsAPI)
	 .allowCredentials(true);
	}


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlConverterHttpMessage());
	}


	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	/*	
		//via QUERY PARAM -> http://localhost:8080/person/6?mediaType=xml
		configurer.favorParameter(true).parameterName("mediaType")
		.ignoreAcceptHeader(true)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
	}
	*/
		
		
	// via HEADER -> http://localhost:8080/person/6
	configurer.favorParameter(false).parameterName("mediaType")
	.ignoreAcceptHeader(false)
	.useRegisteredExtensionsOnly(false)
	.defaultContentType(MediaType.APPLICATION_JSON)
	.mediaType("json", MediaType.APPLICATION_JSON)
	.mediaType("xml", MediaType.APPLICATION_XML)
	.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
}

	
	
}
