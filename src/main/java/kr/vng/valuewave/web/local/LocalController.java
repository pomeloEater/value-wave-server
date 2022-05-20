package kr.vng.valuewave.web.local;

import kr.vng.valuewave.utils.ResultMapUtil;
import kr.vng.valuewave.web.local.model.LocalPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 카카오 로컬 API Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/local")
public class LocalController {

    public final LocalService localService;

    /**
     * 주소 검색하기
     *
     * @param address 검색을 원하는 질의어
     * @param page    결과 페이지 번호
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/search-address/{address}")
    public Object searchAddress(@PathVariable String address,
                                @RequestParam(required = false) Optional<Integer> page) {
        int pageNum = page.orElse(1);
        LocalPayload result = localService.searchAddress(address, pageNum);
        if (result.getDocuments().size() == 0) {
            return ResultMapUtil.failed();
        }
        return ResultMapUtil.success(result);
    }

    /**
     * (임시) 좌표로 행정구역정보 받기
     *
     * @param x          X좌표값, 경위도인 경우 경도(longitude)
     * @param y          Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-region/{x}/{y}")
    public Object getRegion(@PathVariable String x,
                            @PathVariable String y,
                            @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84"); // 좌표계
        LocalPayload result = localService.getRegion(x, y, coordSystem);
        if (result.getDocuments().size() == 0) {
            return ResultMapUtil.failed();
        }
        return ResultMapUtil.success(result);
    }

    /**
     * (임시) 좌표로 주소 변환하기
     *
     * @param x          X좌표값, 경위도인 경우 경도(longitude)
     * @param y          Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), meta(검색 정보), documents(검색결과)
     */
    @GetMapping("/get-address/{x}/{y}")
    public Object getAddress(@PathVariable String x,
                             @PathVariable String y,
                             @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84"); // 좌표계
        LocalPayload address = localService.getAddress(x, y, coordSystem);
        if (address.getDocuments().size() == 0) {
            return ResultMapUtil.failed();
        }
        return ResultMapUtil.success(address);
    }

    /**
     * 좌표로 PNU코드 변환하기
     *
     * @param x          X좌표값, 경위도인 경우 경도(longitude)
     * @param y          Y좌표값, 경위도인 경우 위도(latitude)
     * @param inputCoord x, y로 입력되는 값에 대한 좌표계, 기본값은 WGS84
     * @return result(성공여부), pnu(PNU코드)
     */
    @GetMapping("/get-pnu/{x}/{y}")
    public Object getPnuCode(@PathVariable String x,
                             @PathVariable String y,
                             @RequestParam(required = false) Optional<String> inputCoord) {
        String coordSystem = inputCoord.orElse("WGS84"); // 좌표계
        String pnu = localService.getPnuCode(x, y, coordSystem); // PNU 코드
        if (pnu.length() != 19) {
            return ResultMapUtil.failed();
        }
        return ResultMapUtil.success(pnu);
    }
}
