package kr.vng.valuewave.web.estate;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EstateMapper {

    // 토지(임야)대장
    List<HashMap<String, Object>> getLandBook(String pnu);
    // 공시지가
    List<HashMap<String, Object>> getOfficialLandPrice(String pnu);
    // 표준지공시지가
    List<HashMap<String, Object>> getOfficialLandPriceForStandard(String pnu);
    // 공유지연명부
    List<HashMap<String, Object>> getShareLandOwnership(String pnu);
    // 개별주택가격
    List<HashMap<String, Object>> getIndividualHousePrice(String pnu);
    // 공동주택가격
    List<HashMap<String, Object>> getApartmentHousePrice(String pnu);

    // 건축물대장마스터 PK
    String getBldrgstPk(String pnu);
    // 건축물대장마스터
    List<HashMap<String, Object>> getBuldBook(String bldrgstPk);
    // 총괄표제부
    List<HashMap<String, Object>> getRecapTitle(String bldrgstPk);
    // 표제부
    List<HashMap<String, Object>> getTitle(String bldrgstPk);
    // 층별현황
    List<HashMap<String, Object>> getFloor(String bldrgstPk);
    // 전유부
    List<HashMap<String, Object>> getExpos(String bldrgstPk);
    // 전유공용면적
    List<HashMap<String, Object>> getExposPublicArea(String bldrgstPk);
    // 부속지번현황
    List<HashMap<String, Object>> getAttachedJibun(String bldrgstPk);
    // 오수정화시설
    List<HashMap<String, Object>> getWclf(String bldrgstPk);
    // 지역지구구역현황
    List<HashMap<String, Object>> getJijigu(String bldrgstPk);
    // 소유자구분현황
    List<HashMap<String, Object>> getOwner(String bldrgstPk);
    // 주택가격
    List<HashMap<String, Object>> getHousePrice(String bldrgstPk);
}
