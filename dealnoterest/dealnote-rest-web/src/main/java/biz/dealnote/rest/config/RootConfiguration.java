package biz.dealnote.rest.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by lutay.d on 25.07.2015.
 */
@Configuration
/*@ComponentScan(value = "biz.dealnote.rest.config", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RootConfiguration.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SecurityConfig.class)
})*/
@Import({PersistenceConfig.class, SecurityConfig.class})
@PropertySource("classpath:spring/data-access.properties")
public class RootConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer restPlaceholder(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
