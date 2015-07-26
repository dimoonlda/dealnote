package biz.dealnote.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by lutay.d on 26.07.2015.
 */
@Configuration
@ComponentScan(basePackages = {"biz.dealnote.web.dao.jpa"})
@EnableTransactionManagement
public class PersistenceConfig {

    @Value("${jndiDatabaseAddress}")
    private String jndiDatabaseAddress;

    @Value("${jpa.showSql}")
    private Boolean jpaShowSql;

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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
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
