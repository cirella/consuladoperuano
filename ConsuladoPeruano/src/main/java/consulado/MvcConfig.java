package consulado;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error").setViewName("error");
		//registry.addViewController("/index").setViewName("index");
		//registry.addViewController("/").setViewName("index");
		
	//	registry.addViewController("/registro").setViewName("c-add-usuario");
		
	}

}

