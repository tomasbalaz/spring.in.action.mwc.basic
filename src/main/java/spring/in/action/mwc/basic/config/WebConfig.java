package spring.in.action.mwc.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import spring.in.action.mwc.basic.service.SpittleRepository;
import spring.in.action.mwc.basic.service.SpittleRepositoryImpl;

@Configuration
@EnableWebMvc
@ComponentScan("spring.in.action.mwc.basic")
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public SpittleRepository spittleRepository() {
		SpittleRepository spittleController = new SpittleRepositoryImpl();
		return spittleController;
	}

}
