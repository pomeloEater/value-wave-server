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
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DataControllerTest {

    @LocalServerPort
    @Value("${server.port}")
    private int port;
    private static final String GET_SHAPE = "/get-shape/";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestRestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger(DataControllerTest.class);


    /**
     * URL 주소 반환
     * @param port 포트번호
     * @return TestRestTemplate 기본 주소
     */
    public String getBaseUrl(int port) {
        return "http://localhost:" + port + "/api/data";
    }

    @DisplayName("검색범위 wfs 가져오기")
    @Test
    public void 검색범위_wfs_가져오기() throws IOException {
        // given
        String paramPnuCode = "4113510300101580002";
//        String paramBbox = "37.35875771663394,127.0894214286487,37.37173721573676,127.12411871245041,EPSG:4326";
        String paramBbox = "37.35875771663394,37.37173721573676,127.0894214286487,127.12411871245041";
        String urlMono = String.format("%s%s%s?bbox=%s", getBaseUrl(port), GET_SHAPE, paramPnuCode, paramBbox);
        LOGGER.info(urlMono);
        // when
        String resultMono = restTemplate.getForObject(urlMono, String.class);
        LOGGER.info(resultMono);
    }

    @Test
    public void parseMediaType_테스트() {
        // given
        String mimeTypeString = "text/xml; subtype=gml/3.1.1;charset=UTF-8";

        // when 1
        MediaType mediaType;
        List<MediaType> mediaTypeList;
        try {
            mediaType = MediaType.parseMediaType(mimeTypeString);
            LOGGER.info("SUCCESS >> FIRST : ", mediaType.toString());
        } catch (InvalidMediaTypeException e) {
            LOGGER.error("FIRST : ", e);
            // Invalid token character '/' in token "gml/3.1.1"
        }
        try {
            mediaType = MediaType.parseMediaType("text/xml; subtype=\"gml/3.1.1\"");
            LOGGER.info("SUCCESS >> SECOND : ", mediaType.toString());
        } catch (InvalidMediaTypeException e) {
            LOGGER.error("ERROR >> SECOND : ", e);
        }
        try {
            mediaTypeList = MediaType.parseMediaTypes(mimeTypeString);
            LOGGER.info("SUCCESS >> THIRD : ", mediaTypeList.toString());
        } catch (InvalidMediaTypeException e) {
            LOGGER.error("ERROR >> THIRD : ", e);
        }

    }
}
