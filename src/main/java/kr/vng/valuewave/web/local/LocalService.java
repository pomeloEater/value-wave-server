package kr.vng.valuewave.web.local;

import kr.vng.valuewave.web.local.model.Address;
import kr.vng.valuewave.web.local.model.Document;
import kr.vng.valuewave.web.local.model.LocalPayload;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 카카오 로컬 API Service
 */
@RequiredArgsConstructor
@Service
public class LocalService {

    private final WebClient kakaoClient;

    private static final String SEARCH_ADDRESS = "/v2/local/search/address.json";
    private static final String GEO_COORD_REGION = "/v2/local/geo/coord2regioncode.json";
    private static final String GEO_COORD_ADDRESS = "/v2/local/geo/coord2address.json";

    /**
     * 주소 검색하기
     * reference(https://developers.kakao.com/docs/latest/ko/local/dev-guide#address-coord)
     *
     * @param address 검색을 원하는 질의어
     * @param page    결과 페이지 번호
     * @return meta(검색 정보), documents(검색결과)
     */
    public LocalPayload searchAddress(String address, int page) {
        Mono<LocalPayload> payloadMono = kakaoClient.get()
                .uri(SEARCH_ADDRESS,
                        // uri에 파라미터 추가 ?query={address}&page={page}
                        uri -> uri.queryParam("query", address) // 질의어
                                .queryParam("page", page) // 결과 페이지 번호
//                                .queryParam("size", 10) // 한 페이지에 보여질 문서의 개수(min 1 max 30 default 10)
                                .build())
                .retrieve()
                .bodyToMono(LocalPayload.class);
        return payloadMono.block();
    }

    /**
     * 좌표로 행정구역정보 받기
     * reference(https://developers.kakao.com/docs/latest/ko/local/dev-guide#coord-to-district)
     *
     * @param x           X좌표값, 경위도인 경우 경도(longitude)
     * @param y           Y좌표값, 경위도인 경우 위도(latitude)
     * @param coordSystem x, y로 입력되는 값에 대한 좌표계
     * @return meta(검색 정보), documents(검색결과)
     */
    public LocalPayload getRegion(String x, String y, String coordSystem) {
        Mono<LocalPayload> payloadMono = kakaoClient.get()
                .uri(GEO_COORD_REGION,
                        // uri에 파라미터 추가 ?x={x}&y={y}&input_coord={coordSystem}
                        uri -> uri.queryParam("x", x)
                                .queryParam("y", y)
                                .queryParam("input_coord", coordSystem)
                                .build())
                .retrieve()
                .bodyToMono(LocalPayload.class);
        return payloadMono.block();
    }

    /**
     * 좌표로 주소 변환하기
     * reference(https://developers.kakao.com/docs/latest/ko/local/dev-guide#coord-to-address)
     *
     * @param x           X좌표값, 경위도인 경우 경도(longitude)
     * @param y           Y좌표값, 경위도인 경우 위도(latitude)
     * @param coordSystem x, y로 입력되는 값에 대한 좌표계
     * @return meta(검색 정보), documents(검색결과)
     */
    public LocalPayload getAddress(String x, String y, String coordSystem) {
        Mono<LocalPayload> payloadMono = kakaoClient.get()
                .uri(GEO_COORD_ADDRESS,
                        // uri에 파라미터 추가 ?x={x}&y={y}&input_coord={coordSystem}
                        uri -> uri.queryParam("x", x)
                                .queryParam("y", y)
                                .queryParam("input_coord", coordSystem)
                                .build())
                .retrieve()
                .bodyToMono(LocalPayload.class);
        return payloadMono.block();
    }

    /**
     * 좌표로 PNU코드 변환하기
     *
     * @param x           X좌표값, 경위도인 경우 경도(longitude)
     * @param y           Y좌표값, 경위도인 경우 위도(latitude)
     * @param coordSystem x, y로 입력되는 값에 대한 좌표계
     * @return pnu코드 값
     */
    public String getPnuCode(String x, String y, String coordSystem) {
        LocalPayload regionPayload = getRegion(x, y, coordSystem); // 행정구역 정보
        LocalPayload addressPayload = getAddress(x, y, coordSystem); // 주소 정보(필지/본번/부번
        String code = "", mountainCode = "", mainAddressCode = "", subAddressCode = "";
        // region 처리
        List<Document> regionDocuments = regionPayload.getDocuments();
        for (Document doc : regionDocuments) {
            if ("B".equals(doc.getRegionType())) { // 법정동인 경우에만
                code = doc.getCode(); // 시도-시군구-읍면동-리
            }
        }
        // address 처리
        if (addressPayload.getDocuments().size() != 0) {
            Address address = addressPayload.getDocuments().get(0).getAddress();
            mountainCode = address.getMountainYn().equals("Y") ? "2" : "1"; // 필지구분
            mainAddressCode = StringUtils.leftPad(address.getMainAddressNo(), 4, "0"); // 본번
            subAddressCode = StringUtils.leftPad(address.getSubAddressNo(), 4, "0"); // 부번
        }
        return code + mountainCode + mainAddressCode + subAddressCode;
    }

}
