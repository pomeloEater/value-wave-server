package kr.vng.valuewave.web.jusoro.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 도로명주소검색솔루션 주소검색 API 검색결과
 */
@Data
public class Juso {
    private String roadAddr; // 전체 도로명주소
    private String roadAddrPart1; // 도로명주소(참고항목 제외)
    private String roadAddrPart2; // 도로명주소 참고항목(필수X)
    private String jibunAddr; // 지번 정보
    private String engAddr; // 도로명주소(영문)
    private String zipNo; // 우편번호
    private String admCd; // 행정구역코드
    private String rdMgtSn; // 도로명코드(SOLUTION)
    private String rnMgtSn; // 도로명코드(OPEN API)
    private String bdMgtSn; // 건물관리번호
    private String detBdNmList; // 상세건물명(필수X)
    private String bdNm; // 건물명
    private String bdKdcd; // 공동주택여부(1.공동주택, 0.비공동주택)
    private String siNm; // 시도명
    private String sggNm; // 시군구명
    private String emdNm; // 읍면동명
    private String liNm; // 법정리명(필수X)
    private String rn; // 도로명
    private String udrtYn; // 지하여부(0.지상, 1.지하)
    private int buldMnnm; // 건물본번
    private int buldSlno; // 건물부번
    private String mtYn; // 산여부(0.대지, 1.산)
    private int lnbrMnnm; // 지번본번(번지)
    private int lnbrSlno; // 지번부번(호)
    private String hstryYn; // 지난이력정보 여부(필수X)
    private String relJibun; // 관련지번(필수X)
    private String hemdNm; // 관할주민센터(필수X)

    private BigDecimal entX; // X좌표
    private BigDecimal entY; // Y좌표

    private String pnu; // pnu 코드드
}
