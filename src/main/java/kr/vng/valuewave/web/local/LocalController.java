package kr.vng.valuewave.web.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.vng.valuewave.web.local.model.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 카카오 로컬 API Controller
 */
@RestController
@RequestMapping("/api/local")
public class LocalController {

    public LocalService localService;

    @Autowired
    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 주소 검색하기
     * @param address 검색을 원하는 질의어
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-address/{address}")
    public Object getAddress(@PathVariable String address) {
        Map resultMap = new HashMap();
        resultMap.putAll(documentsToMap(localService.getAddress(address)));
        resultMap.put("result","success");
        return resultMap;
    }

    public Map documentsToMap(Documents documents) {
        return OBJECT_MAPPER.convertValue(documents, Map.class);
    }
}
