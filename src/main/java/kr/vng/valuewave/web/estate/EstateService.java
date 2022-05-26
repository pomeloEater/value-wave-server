package kr.vng.valuewave.web.estate;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<HashMap<String, Object>> getLandBook(String pnu) {
        List<HashMap<String,Object>> landList = estateMapper.getLandBook(pnu);
        LOGGER.info(landList);
        return null;
    }

    /**
     * 공시지가
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getOfficialLandPrice(String pnu) {
        List<HashMap<String, Object>> priceList = estateMapper.getOfficialLandPrice(pnu);
        LOGGER.info(priceList);
        return null;
    }

    /**
     * 표준지공시지가
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getOfficialLandPriceForStandard(String pnu) {
        List<HashMap<String, Object>> priceList = estateMapper.getOfficialLandPriceForStandard(pnu);
        LOGGER.info(priceList);
        return null;
    }

    /**
     * 공유지연명부
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getShareLandOwnership(String pnu) {
        List<HashMap<String, Object>> ownershipList = estateMapper.getShareLandOwnership(pnu);
        LOGGER.info(ownershipList);
        return null;
    }

    /**
     * 개별주택가격
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getIndividualHousePrice(String pnu) {
        List<HashMap<String, Object>> priceList = estateMapper.getIndividualHousePrice(pnu);
        LOGGER.info(priceList);
        return null;
    }

    /**
     * 공동주택가격
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getApartmentHousePrice(String pnu) {
        List<HashMap<String, Object>> priceList = estateMapper.getApartmentHousePrice(pnu);
        LOGGER.info(priceList);
        return null;
    }


    /**
     * 건축물대장마스터 PK
     * @param pnu
     * @return
     */
    public String getBldrgstPk(String pnu) {
        String bldrgstPk = estateMapper.getBldrgstPk(pnu);
        return bldrgstPk;
    }

    /**
     * 건축물대장마스터
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getBuldBook(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getBuldBook(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 총괄표제부
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getRecapTitle(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getRecapTitle(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 표제부
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getTitle(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getTitle(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 층별현황
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getFloor(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getFloor(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 전유부
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getExpos(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getExpos(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 전유공용면적
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getExposPublicArea(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getExposPublicArea(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 부속지번현황
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getAttachedJibun(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getAttachedJibun(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 오수정화시설
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getWclf(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getWclf(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 지역지구구역현황
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getJijigu(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getJijigu(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 소유자구분현황
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getOwner(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getOwner(pnu);
        LOGGER.info(list);
        return null;
    }

    /**
     * 주택가격
     * @param pnu
     * @return
     */
    public List<HashMap<String, Object>> getHousePrice(String pnu) {
        String bldrgst = getBldrgstPk(pnu);
        List<HashMap<String, Object>> list = estateMapper.getHousePrice(pnu);
        LOGGER.info(list);
        return null;
    }
}
