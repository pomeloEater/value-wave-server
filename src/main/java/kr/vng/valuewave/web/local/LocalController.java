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
    @GetMapping("/search-address/{address}")
    public Object searchAddress(@PathVariable String address,
                             @RequestParam(required = false) Optional<Integer> page) {
        int pageNum = page.orElse(1);
        Map resultMap = new HashMap();
        resultMap.putAll(payloadToMap(localService.searchAddress(address, pageNum)));
        resultMap.put("result","success"); // TODO ResultMapUtil ?
        return resultMap;
    }

    /**
     * (임시) 좌표로 행정구역정보 받기
     * @param x X좌표값, 경위도인 경우 경도(longitude)
     * @param y Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-region/{x}/{y}")
    public Object getRegion(@PathVariable String x,
                             @PathVariable String y,
                             @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84");
        Map resultMap = new HashMap();
        resultMap.putAll(payloadToMap(localService.getRegion(x,y,coordSystem)));
        resultMap.put("result","success");
        return resultMap;
    }

    /**
     * (임시) 좌표로 주소 변환하기
     * @param x X좌표값, 경위도인 경우 경도(longitude)
     * @param y Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-address/{x}/{y}")
    public Object getAddress(@PathVariable String x,
                             @PathVariable String y,
                             @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84");
        Map resultMap = new HashMap();
        resultMap.putAll(payloadToMap(localService.getAddress(x,y,coordSystem)));
        resultMap.put("result","success");
        return resultMap;
    }

    /**
     * 좌표로 PNU코드 변환하기
     * @param x X좌표값, 경위도인 경우 경도(longitude)
     * @param y Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), pnu(PNU코드)
     */
    @GetMapping("/get-pnu/{x}/{y}")
    public Object getPnuCode(@PathVariable String x,
                             @PathVariable String y,
                             @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84");
        Map resultMap = new HashMap();
//        resultMap.put("pnu",localService.getPnuCode(x, y, coordSystem));
        resultMap.put("result","success");
        return resultMap;
    }

    public Map payloadToMap(LocalPayload localPayload) {
        return OBJECT_MAPPER.convertValue(localPayload, Map.class);
    }
}
