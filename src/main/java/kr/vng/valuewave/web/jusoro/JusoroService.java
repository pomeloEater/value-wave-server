package kr.vng.valuewave.web.jusoro;

import kr.vng.valuewave.config.prop.GlobalPropertySource;
import kr.vng.valuewave.web.jusoro.model.JusoroPayload;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 도로명주소 검색 API Service
 */
@RequiredArgsConstructor
@Service
@DependsOn(value = {"globalPropertySource"})
public class JusoroService {

    private final GlobalPropertySource globalPropertySource;
    private final WebClient jusoroSolutionClient;
    private final WebClient jusoroOpenClient;

    private static final Logger LOGGER = LogManager.getLogger(JusoroService.class);
    private static final String ROAD_ADDRESS = "/addrLinkApi.do";
    private static final String GEOCODING = "/addrCoordApi.do";

    /**
     * (도로명주소검색솔루션) 주소 검색
     * reference(https://www.juso.go.kr/addrlink/jusoSearchSolutionIntroduce.do)
     *
     * @param keyword     주소 검색어
     * @param currentPage 현재 페이지 번호
     * @return common(검색 정보), juso(검색 결과)
     */
    public JusoroPayload searchAddressBySolution(String keyword, int currentPage){
        Mono<JusoroPayload> payloadMono = jusoroSolutionClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("keyword", keyword)
                        .queryParam("currentPage", currentPage)
                        .queryParam("countPerPage", 10)
                        .queryParam("resultType", "json")
                        .build())
                .retrieve()
                .bodyToMono(JusoroPayload.class);
        return payloadMono.block();
    }

    /**
     * 주소 검색
     * reference(https://www.juso.go.kr/addrlink/devAddrLinkRequestGuide.do?menu=roadApi)
     *
     * @param keyword     주소 검색어
     * @param currentPage 현재 페이지 번호
     * @return common(검색 정보), juso(검색 결과)
     */
    public JusoroPayload searchAddressByOpen(String keyword, int currentPage) {
        Mono<JusoroPayload> payloadMono = jusoroOpenClient.get()
                .uri(ROAD_ADDRESS, uriBuilder -> uriBuilder.queryParam("keyword", keyword)
                        .queryParam("currentPage", currentPage)
                        .queryParam("countPerPage", 10)
                        .queryParam("resultType", "json")
                        .queryParam("confmKey", globalPropertySource.getJusoroRoadAddress())
                        .build())
                .retrieve()
                .bodyToMono(JusoroPayload.class);
        return payloadMono.block();
    }

    /**
     * 주소로 좌표 검색
     * reference(https://www.juso.go.kr/addrlink/devAddrLinkRequestGuide.do?menu=coordApi)
     *
     * @param admCd 행정구역코드
     * @param rnMgtSn 도로명코드
     * @param udrtYn 지하여부
     * @param buldMnnm 건물본번
     * @param buldSlno 건물부번
     * @return common(검색 정보), juso(검색 결과)
     */
    public JusoroPayload getCoordsByAddress(String admCd, String rnMgtSn, String udrtYn, int buldMnnm, int buldSlno) {
        Mono<JusoroPayload> payloadMono = jusoroOpenClient.get()
                .uri(GEOCODING, uriBuilder -> uriBuilder.queryParam("admCd", admCd)
                        .queryParam("rnMgtSn", rnMgtSn)
                        .queryParam("udrtYn", udrtYn)
                        .queryParam("buldMnnm", buldMnnm)
                        .queryParam("buldSlno", buldSlno)
                        .queryParam("resultType", "json")
                        .queryParam("confmKey", globalPropertySource.getJusoroGeocoding())
                        .build())
                .retrieve()
                .bodyToMono(JusoroPayload.class);
        return payloadMono.block();
    }

}
