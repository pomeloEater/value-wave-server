package kr.vng.valuewave.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LocalControllerTest {

    @LocalServerPort
    @Value("${server.port}")
    private int port;

    private static final String SEARCH_ADDRESS = "/search-address/";
    private static final String GET_REGION = "/get-region/";
    private static final String GET_ADDRESS = "/get-address/";
    private static final String GET_PNU = "/get-pnu/";


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestRestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger(LocalControllerTest.class);


//    @DisplayName("@Value를 이용한 properties bind")
//    @Test
//    public void 카카오_애플리케이션키_설정_확인하기() {
//        /* LocalController의 KAKAO_REST_API에 @Getter 추가 후 확인 가능 */
//        assertThat(localController.getKAKAO_REST_API()).contains("dd3e");
//    }

    /**
     * URL 주소 반환
     * @param port
     * @return
     */
    public String getBaseUrl(int port) {
        return "http://localhost:" + port + "/api/local";
    }

    @DisplayName("특정 주소 검색하기")
    @Test
    public void 주소_검색하기() {
        // given
        String paramAddress = "남양주시";
        String url = String.format("%s%s%s", getBaseUrl(port), SEARCH_ADDRESS, paramAddress);

        // when
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info(result);

        // then
        assertThat(result).contains("success", paramAddress);
    }

    @DisplayName("좌표로 행정구역정보 받기")
    @Test
    public void 좌표로_행정구역정보_받기() {
        // given
        String x = "127.1086228";
        String y = "37.4012191";
        String url = String.format("%s%s%s/%s", getBaseUrl(port), GET_REGION, x, y);

        // when
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info(result);

        // then
        assertThat(result).contains("4113510900");
    }

    @DisplayName("좌표로 주소 변환하기")
    @Test
    public void 좌표로_주소_변환하기() {
        // given
        String x = "127.423084873712";
        String y = "37.0789561558879";
        String url = String.format("%s%s%s/%s", getBaseUrl(port), GET_ADDRESS, x, y);

        // when
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info(result);

        // then
        assertThat(result).contains("경기","안성시","죽산면");
    }

    @DisplayName("좌표로 PNU코드 변환하기")
    @Test
    public void 좌표로_PNU코드_변환하기() {
        // given
        String x = "127.1086228";
        String y = "37.4012191";
        String url = String.format("%s%s%s/%s", getBaseUrl(port), GET_PNU, x, y);

        // when
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info(result);

        // then
        assertThat(result).contains("4113510900");
    }
}
