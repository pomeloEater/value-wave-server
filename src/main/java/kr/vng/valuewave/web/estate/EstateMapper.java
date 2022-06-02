package kr.vng.valuewave.web.estate;

import kr.vng.valuewave.mvc.DefaultMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstateMapper {

    DefaultMap checkConnection();

    // 토지(임야)대장
    List<DefaultMap> getLandBook(String pnu);
    // 공시지가
    List<DefaultMap> getOfficialLandPrice(String pnu);
    // 표준지공시지가
    List<DefaultMap> getOfficialLandPriceForStandard(String pnu);
    // 공유지연명부
    List<DefaultMap> getShareLandOwnership(String pnu);
    // 개별주택가격
    List<DefaultMap> getIndividualHousePrice(String pnu);
    // 공동주택가격
    List<DefaultMap> getApartmentHousePrice(String pnu);

    // 건축물대장마스터
    List<DefaultMap> getBuldBookByPnu(String pnu);
    List<DefaultMap> getBuldBookByPk(String bldrgstPk);
    // 총괄표제부
    List<DefaultMap> getRecapTitle(String bldrgstPk);
    // 표제부
    List<DefaultMap> getTitle(String bldrgstPk);
    // 층별현황
    List<DefaultMap> getFloor(String bldrgstPk);
    // 전유부
    List<DefaultMap> getExposByPnu(String pnu);
    List<DefaultMap> getExposByPk(String bldrgstPk);
    // 부속지번현황
    List<DefaultMap> getAttachedJibun(String bldrgstPk);
    // 오수정화시설
    List<DefaultMap> getWclf(String bldrgstPk);
    // 지역지구구역현황
    List<DefaultMap> getJijigu(String bldrgstPk);
    // 소유자구분현황
    List<DefaultMap> getOwner(String bldrgstPk);
    // 주택가격
    List<DefaultMap> getHousePrice(String bldrgstPk);
}
