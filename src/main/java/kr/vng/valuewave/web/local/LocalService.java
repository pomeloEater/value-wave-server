package kr.vng.valuewave.web.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.vng.valuewave.web.local.model.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;


@Service
public class LocalService {

    private WebClient kakaoClient;

    @Autowired
    public LocalService(WebClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String ADDRESS_PATH = "/v2/local/search/address.json";

    public Documents getAddress(String address) {
        Mono<Documents> documentsMono = kakaoClient.get()
                        .uri(ADDRESS_PATH,
                                uri -> uri.queryParam("query", address)
                            .build())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Documents.class);
        Documents documents = documentsMono.block();
        return documents;
    }

    public String urlEncodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
