package testrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages={"testrest"})
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public Jaxb2Marshaller jaxbMarshaller(){
		Jaxb2Marshaller jaxbMarshaller = new Jaxb2Marshaller();
		jaxbMarshaller.setClassesToBeBound(User.class);
		return jaxbMarshaller;
	}
	
	@Bean
	public MarshallingHttpMessageConverter xmlConverter(){
		MarshallingHttpMessageConverter converter = 
				new MarshallingHttpMessageConverter(jaxbMarshaller(), jaxbMarshaller());
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
		return converter;
	}
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(xmlConverter());
	}
}
