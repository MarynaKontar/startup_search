package ua.goit.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by User on 15.10.2017.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ua.goit.dao"})
@PropertySource("classpath:test.properties")
@EnableJpaRepositories("ua.goit.dao")
public class TestServicesConfiguration {

    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${dn.password}")
    private String password;
    @Value("${db.driver}")
    private String driverClass;
    @Value("${db.dialect}")
    private String hibernateDialect;
    @Value("classpath:testDb.sql")
    private Resource scriptResource;
    @Value("classpath:fillDb.sql")
    private Resource fillScript;

    @Bean(destroyMethod = "close")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);


        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        bean.setPackagesToScan("ua.goit.entity");
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(scriptResource);
        populator.addScript(fillScript);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }


}
