package kr.vng.valuewave.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.function.Consumer;

@Configuration
public class WebClientConfig {

    @Value("${kakao.rest}")
    private String KAKAO_REST_API;

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String KAKAO_BASE = "https://dapi.kakao.com";
    private static final String DATA_BASE = "http://apis.data.go.kr";

    /**
     * 카카오 로컬 API WebClient 객체
     * @return WebClient
     */
    @Bean(name = "kakaoClient")
    public WebClient kakaoClient() {
        return WebClient.builder()
                .baseUrl(KAKAO_BASE)
                .defaultHeaders(new Consumer<HttpHeaders>() {
                    @Override
                    public void accept(HttpHeaders httpHeaders) {
                        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + KAKAO_REST_API);
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
     * 공공데이터포털 부동산WFS WebClient 객체
     * @return WebClient
     */
    @Bean(name = "wfsClient")
    public WebClient wfsClient() {
        // uri ServiceKey encoding
        DefaultUriBuilderFactory builderFactory = new DefaultUriBuilderFactory(DATA_BASE);
        builderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        return WebClient.builder()
                .uriBuilderFactory(builderFactory)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .defaultHeaders(new Consumer<HttpHeaders>() {
                    @Override
                    public void accept(HttpHeaders httpHeaders) {
                        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("text/xml; subtype=\"gml/3.1.1\"; charset=\"UTF-8\"")));
                    }
                })
                .baseUrl(DATA_BASE)
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
