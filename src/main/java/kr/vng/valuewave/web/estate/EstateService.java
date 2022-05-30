package kr.vng.valuewave.web.estate;

import kr.vng.valuewave.mvc.DefaultMap;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstateService {

    private final EstateMapper estateMapper;

    private static final Logger LOGGER = LogManager.getLogger(EstateService.class);

    /**
     * 토지(임야)대장
     * @param pnu
     * @return
     */
    public List<DefaultMap> getLandBook(String pnu) {
        List<DefaultMap> landList = estateMapper.getLandBook(pnu);
        return landList;
    }

    /**
     * 공시지가
     * @param pnu
     * @return
     */
    public List<DefaultMap> getOfficialLandPrice(String pnu) {
        List<DefaultMap> priceList = estateMapper.getOfficialLandPrice(pnu);
        return priceList;
    }

    /**
     * 표준지공시지가
     * @param pnu
     * @return
     */
    public List<DefaultMap> getOfficialLandPriceForStandard(String pnu) {
        List<DefaultMap> priceList = estateMapper.getOfficialLandPriceForStandard(pnu);
        return priceList;
    }

    /**
     * 공유지연명부
     * @param pnu
     * @return
     */
    public List<DefaultMap> getShareLandOwnership(String pnu) {
        List<DefaultMap> ownershipList = estateMapper.getShareLandOwnership(pnu);
        return ownershipList;
    }

    /**
     * 개별주택가격
     * @param pnu
     * @return
     */
    public List<DefaultMap> getIndividualHousePrice(String pnu) {
        List<DefaultMap> priceList = estateMapper.getIndividualHousePrice(pnu);
        return priceList;
    }

    /**
     * 공동주택가격
     * @param pnu
     * @return
     */
    public List<DefaultMap> getApartmentHousePrice(String pnu) {
        List<DefaultMap> priceList = estateMapper.getApartmentHousePrice(pnu);
        return priceList;
    }

    /**
     * 건축물대장마스터
     * @param pnu
     * @return
     */
    public List<DefaultMap> getBuldBookByPnu(String pnu) {
        List<DefaultMap> list = estateMapper.getBuldBookByPnu(pnu);
        return list;
    }

    /**
     * 건축물대장마스터
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getBuldBookByPk(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getBuldBookByPk(bldrgstPk);
        return list;
    }

    /**
     * 총괄표제부
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getRecapTitle(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getRecapTitle(bldrgstPk);
        return list;
    }

    /**
     * 표제부
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getTitle(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getTitle(bldrgstPk);
        return list;
    }

    /**
     * 층별현황
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getFloor(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getFloor(bldrgstPk);
        return list;
    }

    /**
     * 전유부
     * @param pnu
     * @return
     */
    public List<DefaultMap> getExposByPnu(String pnu) {
        List<DefaultMap> list = estateMapper.getExposByPnu(pnu);
        return list;
    }

    /**
     * 전유부
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getExposByPk(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getExposByPk(bldrgstPk);
        return list;
    }

    /**
     * 부속지번현황
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getAttachedJibun(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getAttachedJibun(bldrgstPk);
        return list;
    }

    /**
     * 오수정화시설
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getWclf(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getWclf(bldrgstPk);
        return list;
    }

    /**
     * 지역지구구역현황
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getJijigu(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getJijigu(bldrgstPk);
        return list;
    }

    /**
     * 소유자구분현황
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getOwner(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getOwner(bldrgstPk);
        return list;
    }

    /**
     * 주택가격
     * @param bldrgstPk
     * @return
     */
    public List<DefaultMap> getHousePrice(String bldrgstPk) {
        List<DefaultMap> list = estateMapper.getHousePrice(bldrgstPk);
        return list;
    }
}
