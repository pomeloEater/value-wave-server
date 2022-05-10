package kr.vng.valuewave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${kakao.rest}")
    private String KAKAO_REST_API;

    @Bean
    public WebClient kakaoClient() {
        return WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + KAKAO_REST_API)
                .build();
    }

    // data portal client
}
