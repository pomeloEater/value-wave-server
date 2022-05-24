package kr.vng.valuewave.config.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration(value = "globalPropertySource")
@PropertySource(value = "classpath:/application-env.yml", ignoreResourceNotFound = true)
public class GlobalPropertySource {

    /** DATASOURCE */
    @Value("${datasource.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    /** API KEY */
    @Value("${api.kakao.dev}")
    private String kakaoRest;
    @Value("${api.jusoro.road-address}")
    private String jusoroRoadAddress;
    @Value("${api.jusoro.geocoding}")
    private String jusoroGeocoding;
}
