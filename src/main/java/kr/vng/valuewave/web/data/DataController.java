package kr.vng.valuewave.web.data;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.geojson.Geometry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.wfs.GML;
import org.geotools.wfs.WFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;

@RestController
@RequestMapping("/api/data")
public class DataController {

    public WebClient wfsClient;

    @Value("${data.dev.encoding}")
    private String DATA_API_KEY;

    @Autowired
    public DataController(WebClient wfsClient) {
        this.wfsClient = wfsClient;
    }

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static final Logger LOGGER = LogManager.getLogger(DataController.class);

    private static final String CADASTRAL_WFS = "/1611000/nsdi/map/ver/2/CtnlgsSpceService/wfs/getCtnlgsSpceWFS";

    @GetMapping("/get-shape/{pnuCode}")
    public Object getShape(@PathVariable String pnuCode,
                           @RequestParam String bbox) throws Exception {
        return null;
//        // 500 Internal Server Error from GET
//        Mono<String> dataMono = wfsClient.get()
//                .uri(CADASTRAL_WFS,
//                        uri -> uri.queryParam("serviceKey", DATA_API_KEY)
//                                .queryParam("typename", "F6")
//                                .queryParam("maxFeatures", 10)
//                                .queryParam("resultType", "results")
//                                .queryParam("srsName", "EPSG:4326")
//                                .queryParam("bbox", bbox)
//                                .build())
//                .retrieve()
//                .bodyToMono(String.class);
//        return dataMono.block();

//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1611000/nsdi/map/ver/2/CtnlgsSpceService/wfs/getCtnlgsSpceWFS"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + DATA_API_KEY); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("typename","UTF-8") + "=" + URLEncoder.encode("F6", "UTF-8")); /*질의 대상인 하나 이상의 피처 유형 이름의 리스트, 값은 쉼표로 구분*/
//        urlBuilder.append("&" + URLEncoder.encode("bbox","UTF-8") + "=" + URLEncoder.encode(bbox, "UTF-8")); /*좌표로 이루어진 사각형 안에 담겨 있는 (또는 부분적으로 걸쳐 있는) 피처를 검색. 좌표 순서는 사용되는 좌표 시스템을 따름. 일반적 표현은 하단좌표, 상단좌표, 좌표체계 순서입니다.(lc1,lc2,uc1,uc2,좌표체계)*/
//        urlBuilder.append("&" + URLEncoder.encode("maxFeatures","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*요청에 대한 응답으로 WFS가 반환해야하는 피처의 최대 값 (최대 허용값 : 100)*/
//        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("results", "UTF-8")); /*요청에 대하여 WFS가 어떻게 응답할 것인지 정의. results 값은 요청된 모든 피처를 포함하는 완전한 응답이 생성되어야 함을 나타내며, hits 값은 피처의 개수만이 반환되어야 함을 의미*/
//        urlBuilder.append("&" + URLEncoder.encode("srsName","UTF-8") + "=" + URLEncoder.encode("EPSG:4326", "UTF-8")); /*반환되어야 할 피처의 기하에 사용되어야 할 WFS가 지원하는 좌표체계*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "text/xml; subtype=\"gml/3.1.1\"; charset=\"UTF-8\"");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//        return sb.toString();
    }
}