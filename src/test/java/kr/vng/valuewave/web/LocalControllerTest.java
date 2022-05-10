package kr.vng.valuewave.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LocalControllerTest {

    @LocalServerPort
    @Value("${server.port}")
    private int port;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestRestTemplate restTemplate;


//    @DisplayName("@Value를 이용한 properties bind")
//    @Test
//    public void 카카오_애플리케이션키_설정_확인하기() {
//        /* LocalController의 KAKAO_REST_API에 @Getter 추가 후 확인 가능 */
//        assertThat(localController.getKAKAO_REST_API()).contains("dd3e");
//    }

    @DisplayName("특정 주소 검색하기")
    @Test
    public void 주소_검색하기() {
        // given
        String paramAddress = "금곡";
        String url = "http://localhost:" + port
                + "/api/local/get-address/" + paramAddress
                + "?page=2";
        String result = restTemplate.getForObject(url, String.class).toString();
        System.out.println(result);
    }
}
