package kr.vng.valuewave.web.jusoro;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.vng.valuewave.utils.ResultMapUtil;
import kr.vng.valuewave.web.jusoro.model.JusoroPayload;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 도로명주소검색솔루션 주소검색 API Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jusoro")
public class JusoroController {

    private final JusoroService jusoroService;
    private static final Logger LOGGER = LogManager.getLogger(JusoroController.class);

    /**
     * 주소 검색하기
     *
     * @param keyword 주소 검색어
     * @param currentPage 현재 페이지번호(기본값: 1)
     * @return result(성공여부), common(검색 정보), juso(검색 결과)
     */
    @GetMapping("/search-address/{keyword}")
    public Object searchAddress(@PathVariable String keyword,
                                @RequestParam(required = false)Optional<Integer> currentPage) throws JsonProcessingException {
        int pageNum = currentPage.orElse(1);
        LOGGER.info(keyword, pageNum);
        JusoroPayload jusoroPayload = jusoroService.searchAddress(keyword, pageNum);
        if (jusoroPayload == null || jusoroPayload.getResults().getJuso().size() == 0) {
            return ResultMapUtil.failed();
        }
        return ResultMapUtil.success(jusoroPayload.getResults());
    }
}
