package kr.vng.valuewave.config;

import kr.vng.valuewave.config.prop.GlobalPropertySource;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
@DependsOn(value = {"globalPropertySource"})
public class WebClientConfig {

    private final GlobalPropertySource globalPropertySource;

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String KAKAO_BASE = "https://dapi.kakao.com";
    private static final String JUSORO_SOLUTION_BASE = "http://127.0.0.1:8983/app/search/addrSearchApi.do";
    private static final String JUSORO_OPEN_BASE = "https://www.juso.go.kr/addrlink";

    /**
     * 카카오 REST API WebClient
     * @return WebClient
     */
    @Bean
    public WebClient kakaoClient() {
        return WebClient.builder()
                .baseUrl(KAKAO_BASE)
                .defaultHeaders(new Consumer<HttpHeaders>() {
                    @Override
                    public void accept(HttpHeaders httpHeaders) {
                        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + globalPropertySource.getKakaoRest());
                        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    }
                })
                .build();
    }

    /**
     * 도로명주소검색솔루션 WebClient
     * @return WebClient
     */
    @Bean
    public WebClient jusoroSolutionClient() {
        return getWebClientByBaseUrl(JUSORO_SOLUTION_BASE);
    }

    /**
     * 도로명주소 오픈API WebClient
     * @return WebClient
     */
    @Bean
    public WebClient jusoroOpenClient() {
        return getWebClientByBaseUrl(JUSORO_OPEN_BASE);
    }

    // data portal client


    /**
     * WebClient 생성
     * @param baseUrl 기본 URL
     * @return WebClient
     */
    private WebClient getWebClientByBaseUrl(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(new Consumer<HttpHeaders>() {
                    @Override
                    public void accept(HttpHeaders httpHeaders) {
                        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    }
                })
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .build();
    }

    /**
     * logging request
     */
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            LOGGER.info("WEBCLIENT | Performing {} {} request", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> LOGGER.info("WEBCLIENT | Request Headers {}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    /**
     * logging response
     */
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            LOGGER.info("WEBCLIENT | Respond with {} status code", clientResponse.rawStatusCode());
            return Mono.just(clientResponse);
        });
    }
}
