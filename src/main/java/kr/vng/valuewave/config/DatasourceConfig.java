package kr.vng.valuewave.config;

import kr.vng.valuewave.config.prop.GlobalPropertySource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@DependsOn(value = {"globalPropertySource"})
@EnableTransactionManagement
public class DatasourceConfig {

    private final GlobalPropertySource globalPropertySource;

    @Bean
    @Primary
    public DataSource customDataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(globalPropertySource.getDriverClassName())
                .url(globalPropertySource.getUrl())
                .username(globalPropertySource.getUsername())
                .password(globalPropertySource.getPassword())
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource customDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(customDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
