package br.com.atendimento.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration 
@EnableSpringDataWebSupport
@EnableJpaRepositories("br.com.atendimento.repository")
@EnableCaching
@EnableSwagger2
@EntityScan("br.com.atendimento.domain")
@ComponentScan(basePackages={"br.com.atendimento"})
public class AtendimentoApplication{

	public static void main(String[] args) {
		SpringApplication.run(AtendimentoApplication.class, args);
	}
	
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(AtendimentoApplication.class); }
	 */

}
