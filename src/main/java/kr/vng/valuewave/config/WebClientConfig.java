package kr.vng.valuewave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.function.Consumer;

@Configuration
public class WebClientConfig {

    @Value("${kakao.rest}")
    private String KAKAO_REST_API;

    @Bean
    public WebClient kakaoClient() {
        return WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeaders(new Consumer<HttpHeaders>() {
                    @Override
                    public void accept(HttpHeaders httpHeaders) {
                        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + KAKAO_REST_API);
                        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    }
                })
                .build();
    }


    // data portal client
}
