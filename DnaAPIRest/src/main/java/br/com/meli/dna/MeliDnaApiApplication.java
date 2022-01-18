package br.com.meli.dna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = "br.com.meli.dna.*", basePackageClasses = MeliDnaApiApplication.class)
public class MeliDnaApiApplication extends SpringBootServletInitializer{

	public MeliDnaApiApplication() {
	    super();
	    setRegisterErrorPageFilter(false);
	}

	public static void main(String[] args) {
		SpringApplication.run(MeliDnaApiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MeliDnaApiApplication.class);
	}
	
}


