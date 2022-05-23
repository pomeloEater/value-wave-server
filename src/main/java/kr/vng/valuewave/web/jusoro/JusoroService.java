package kr.vng.valuewave.web.jusoro;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.vng.valuewave.web.jusoro.model.JusoroPayload;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 도로명주소검색솔루션 주소검색 API Service
 */
@RequiredArgsConstructor
@Service
public class JusoroService {

    private final WebClient jusoroClient;

    private static final Logger LOGGER = LogManager.getLogger(JusoroService.class);

    /**
     * 주소 검색
     * reference(https://www.juso.go.kr/addrlink/jusoSearchSolutionIntroduce.do)
     *
     * @param keyword     주소 검색어
     * @param currentPage 현재 페이지 번호
     * @return common(검색 정보), juso(검색 결과)
     */
    public JusoroPayload searchAddress(String keyword, int currentPage) throws JsonProcessingException {
        Mono<JusoroPayload> payloadMono = jusoroClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("keyword", keyword)
                        .queryParam("currentPage", currentPage)
                        .queryParam("countPerPage", 10)
                        .queryParam("resultType", "json")
                        .build())
                .retrieve()
                .bodyToMono(JusoroPayload.class);
        return payloadMono.block();
    }
}
