package kr.vng.valuewave.web.jusoro;

import kr.vng.valuewave.utils.ResultMapUtil;
import kr.vng.valuewave.web.jusoro.model.JusoroPayload;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 도로명주소 검색 API Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jusoro")
public class JusoroController {

    private final JusoroService jusoroService;
    private static final Logger LOGGER = LogManager.getLogger(JusoroController.class);

    /**
     * (도로명주소검색솔루션) 주소 검색하기
     *
     * @param keyword 주소 검색어
     * @param currentPage 현재 페이지번호(기본값: 1)
     * @return result(성공여부), common(검색 정보), juso(검색 결과)
     */
    @GetMapping("/search-address-solution/{keyword}")
    public Object searchAddressBySolution(@PathVariable String keyword,
                                @RequestParam(required = false)Optional<Integer> currentPage) {
        int pageNum = currentPage.orElse(1);
        LOGGER.info(keyword, pageNum);
        JusoroPayload jusoroPayload = jusoroService.searchAddressBySolution(keyword, pageNum);
        if (jusoroPayload == null || jusoroPayload.getResults().getJuso().size() == 0) {
            return ResultMapUtil.failed(jusoroPayload.getResults());
        }
        return ResultMapUtil.success(jusoroPayload.getResults());
    }

    /**
     * 주소 검색하기
     *
     * @param keyword 주소 검색어
     * @param currentPage 현재 페이지번호(기본값: 1)
     * @return result(성공여부), common(검색 정보), juso(검색 결과)
     */
    @GetMapping("/search-address/{keyword}")
    public Object searchAddress(@PathVariable String keyword,
                                @RequestParam(required = false)Optional<Integer> currentPage) {
        int pageNum = currentPage.orElse(1);
        LOGGER.info(keyword, pageNum);
        JusoroPayload jusoroPayload = jusoroService.searchAddressByOpen(keyword, pageNum);
        if (jusoroPayload == null || jusoroPayload.getResults().getJuso().size() == 0) {
            return ResultMapUtil.failed(jusoroPayload.getResults());
        }
        return ResultMapUtil.success(jusoroPayload.getResults());
    }

    /**
     * 주소로 좌표 검색
     * @param admCd 행정구역코드
     * @param rnMgtSn 도로명코드
     * @param udrtYn 지하여부
     * @param buldMnnm 건물본번
     * @param buldSlno 건물부번
     * @return result(성공여부), common(검색 정보), juso(검색 결과)
     */
    @GetMapping("/get-coords")
    public Object getCoordsByAddress(@RequestParam String admCd,
                                     @RequestParam String rnMgtSn,
                                     @RequestParam String udrtYn,
                                     @RequestParam int buldMnnm,
                                     @RequestParam int buldSlno) {
        JusoroPayload jusoroPayload = jusoroService.getCoordsByAddress(admCd, rnMgtSn, udrtYn, buldMnnm, buldSlno);
        if (jusoroPayload == null || jusoroPayload.getResults().getJuso().size() == 0) {
            return ResultMapUtil.failed(jusoroPayload.getResults());
        }
        return ResultMapUtil.success(jusoroPayload.getResults());
    }
}
