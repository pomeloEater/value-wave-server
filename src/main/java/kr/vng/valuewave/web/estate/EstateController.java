package kr.vng.valuewave.web.estate;

import kr.vng.valuewave.utils.ResultMapUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estate")
public class EstateController {

    private final EstateService estateService;
    private static final Logger LOGGER = LogManager.getLogger(EstateController.class);

    /**
     * 토지(임야) 대장 조회
     * @param pnu
     * @return
     */
    @GetMapping("/get-land-book/{pnu}")
    public Object getLandBook(@PathVariable String pnu) {
        return ResultMapUtil.success(estateService.getLandBook(pnu));
    }

    /**
     * 건축물 대장 조회
     * @param pnu
     * @return
     */
    @GetMapping("/get-building-book/{pnu}")
    public Object getBuildingBook(@PathVariable String pnu) {
        return ResultMapUtil.success(estateService.getBuldBookByPnu(pnu));
    }

    /**
     * 개별주택가격 조회
     * @param pnu
     * @return
     */
    @GetMapping("/get-individual-price/{pnu}")
    public Object getIndividualPrice(@PathVariable String pnu) {
        return ResultMapUtil.success(estateService.getIndividualHousePrice(pnu));
    }

    /**
     * 공동주택가격 조회
     * @param pnu
     * @return
     */
    @GetMapping("/get-apartment-price/{pnu}")
    public Object getApartmentPrice(@PathVariable String pnu) {
        return ResultMapUtil.success(estateService.getApartmentHousePrice(pnu));
    }

    /**
     * 공시지가 조회
     * @param pnu
     * @return
     */
    @GetMapping("/get-official-price/{pnu}")
    public Object getOfficialPrice(@PathVariable String pnu) {
        return ResultMapUtil.success(estateService.getOfficialLandPrice(pnu));
    }

    /**
     * 토지이용현황 조회(임시))
     * @param pnu
     * @return
     */
    @GetMapping("/get-land-use/{pnu}")
    public Object getLandUse(@PathVariable String pnu) {
        return ResultMapUtil.failed();
    }

}
