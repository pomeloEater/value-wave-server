package kr.vng.valuewave.web.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.vng.valuewave.web.local.model.LocalPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
     * @param page 결과 페이지 번호
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-address/{address}")
    public Object getAddress(@PathVariable String address,
                             @RequestParam(required = false) Optional<Integer> page) {
        int pageNum = page.orElse(1);
        Map resultMap = new HashMap();
        resultMap.putAll(payloadToMap(localService.getAddress(address, pageNum)));
        resultMap.put("result","success"); // TODO ResultMapUtil ?
        return resultMap;
    }

    public Map payloadToMap(LocalPayload localPayload) {
        return OBJECT_MAPPER.convertValue(localPayload, Map.class);
    }
}
