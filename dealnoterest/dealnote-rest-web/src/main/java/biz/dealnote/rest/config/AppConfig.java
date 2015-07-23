package biz.dealnote.rest.config;

import biz.dealnote.rest.util.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

/**
 * Created by lutay.d on 22.07.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"biz.dealnote.rest.controllers, biz.dealnote.rest.service, biz.dealnote.web.dao.jpa"})
@PropertySource("classpath:spring/data-access.properties")
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {

    @Value("${jndiDatabaseAddress}")
    private String jndiDatabaseAddress;

    @Value("${jpa.database}")
    private String jpaDatabase;

    @Value("${jpa.showSql}")
    private Boolean jpaShowSql;

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages/messages");
        resource.setFallbackToSystemLocale(false);
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    @Bean(name = "messageSourceAccessor")
    public MessageSourceAccessor getMessageSourceAccessor(){
        return new MessageSourceAccessor(getMessageSource());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "localeResolver")
    public CookieLocaleResolver getLocaleResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        resolver.setCookieName("locale");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor =  new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new HibernateAwareObjectMapper());
        converters.add(converter);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer restPlaceholder(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Profile(value = {"production"})
    @Bean(name = "dataSource")
    public JndiObjectFactoryBean dataSourceProd(){
        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setJndiName(jndiDatabaseAddress);
        dataSource.setExpectedType(DataSource.class);
        return dataSource;
    }

    @Profile(value = {"test"})
    @Bean(name = "dataSource", destroyMethod = "shutdown")
    public EmbeddedDatabase dataSourceTest(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:sql/schema.sql")
                .addScript("classpath:sql/measures.sql")
                .addScript("classpath:sql/goods.sql")
                .addScript("classpath:sql/measurelink.sql")
                .addScript("classpath:sql/payform.sql")
                .addScript("classpath:sql/doctype.sql")
                .addScript("classpath:sql/document.sql")
                .addScript("classpath:sql/locationSaveState.sql")
                .addScript("classpath:sql/location.sql")
                .addScript("classpath:sql/user.sql")
                .addScript("classpath:sql/partjobs.sql")
                .addScript("classpath:sql/test-data.sql")
                .build();
        return db;
    }

    @Profile(value = "production")
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryProd(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource((DataSource) dataSourceProd().getObject());
        HibernateJpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.DEFAULT);
        jpaVendorAdapter.setShowSql(jpaShowSql);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.FirebirdDialect");

        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPersistenceUnitName("DealNote");
        entityManagerFactory.setPackagesToScan("biz.dealnote.web.model");
        return entityManagerFactory;
    }

    @Profile(value = "test")
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTest(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource((DataSource) dataSourceProd().getObject());
        HibernateJpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(jpaShowSql);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");

        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPersistenceUnitName("DealNote");
        entityManagerFactory.setPackagesToScan("biz.dealnote.web.model");
        return entityManagerFactory;
    }
    @Bean
    public PlatformTransactionManager txManager(EntityManagerFactory emf){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor translationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
