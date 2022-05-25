package kr.vng.valuewave.web;

import kr.vng.valuewave.web.jusoro.JusoroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JusoroControllerTests {

    @LocalServerPort
    @Value("${server.port}")
    private int port;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JusoroService jusoroService;

    private static final String SEARCH_SOLUTION = "/search-address-solution/";
    private static final String SEARCH_OPEN = "/search-address/";

    private static final Logger LOGGER = LogManager.getLogger(JusoroControllerTests.class);


    /**
     * URL 주소 반환
     * @param port 포트번호
     * @return TestRestTemplate 기본 주소
     */
    public String getBaseUrl(int port) {
        return "http://localhost:" + port + "/api/jusoro";
    }

    @Test
    public void 주소_검색_비교() {
        // given
        String keyword = "남양주시";
        String urlSolution = String.format("%s%s%s", getBaseUrl(port), SEARCH_SOLUTION, keyword);
        String urlOpen = String.format("%s%s%s", getBaseUrl(port), SEARCH_OPEN, keyword);

        // when
        Map resultSolution = restTemplate.getForObject(urlSolution, HashMap.class);
        Map resultOpen = restTemplate.getForObject(urlOpen, HashMap.class);
        LOGGER.info("SOLUTION  ", resultSolution.toString());
        LOGGER.info("OPEN  ", resultOpen.toString());

        // then
    }
    @Test
    public void 주소_검색_비교_with_JusoroService() {
        // given
        String keyword = "남양주시";
        int currentPage = 1;

        String resultSolution = jusoroService.searchAddressBySolution(keyword, currentPage).toString();
        String resultOpen = jusoroService.searchAddressByOpen(keyword, currentPage).toString();

        // when
        LOGGER.info("SOLUTION  ", resultSolution);
        LOGGER.info("OPEN  ", resultOpen);

        // then
    }
}
