package com.br.fsc.testcontainer;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
/*
@ContextConfiguration(inheritInitializers = AbstractIntegrationTest.Initializer.class != null)
public class AbstractIntegrationTest {

	public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.32");
		
		private static void startContainer() { 
			Startables.deepStart(Stream.of(mysql)).join();
		}

		private static Map<String, String> createConnectionConfiguation() {
	
			return Map.of(
					"spring.datasource.url", mysql.getJdbcUrl(),
					"spring.datasource.username", mysql.getUsername(),
					"spring.datasource.password", mysql.getPassword()					
					);
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			startContainer();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			
			MapPropertySource mapPropertySource = new MapPropertySource(
					"testcontainer", 
					(Map) createConnectionConfiguation());
			environment.getPropertySources().addFirst(mapPropertySource);
			
		}

	
	
	
	
	}

	
}
*/