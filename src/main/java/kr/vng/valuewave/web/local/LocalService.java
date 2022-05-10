package kr.vng.valuewave.web.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.vng.valuewave.web.local.model.LocalPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * 카카오 로컬 API Service
 */
@Service
public class LocalService {

    private WebClient kakaoClient;

    @Autowired
    public LocalService(WebClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String SEARCH_ADDRESS = "/v2/local/search/address.json";
    private static final String GEO_COORD_REGION = "/v2/local/geo/coord2regioncode.json";
    private static final String GEO_COORD_ADDRESS = "/v2/local/geo/coord2address.json";
    private static final String SEARCH_KEYWORD = "/v2/local/search/keyword.json";

    /**
     * 주소 검색하기
     *
     * @param address 검색을 원하는 질의어
     * @param page    결과 페이지 번호
     * @return meta(검색 정보), documents(검색결과)
     */
    public LocalPayload getAddress(String address, int page) {
        Mono<LocalPayload> payloadMono = kakaoClient.get()
                .uri(SEARCH_ADDRESS,
                        uri -> uri.queryParam("query", address)
                                .queryParam("page", page)
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LocalPayload.class);
        LocalPayload localPayload = payloadMono.block();
        return localPayload;
    }

}
