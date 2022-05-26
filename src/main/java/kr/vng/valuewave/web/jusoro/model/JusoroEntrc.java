package kr.vng.valuewave.web.jusoro.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JusoroEntrc {
    private String sggCd; // 시군구코드
    private String entCd; // 출입구일련번호
    private String bjdCd; // PK5 법정동코드: 시군구코드(5) + 읍면동코드(3) + 00
    private String siNm; // 시도명
    private String sggNm; // 시군구명
    private String emdNm; // 읍면동명
    private String rnMgtSn; // PK1 도로명코드: 시군구코드(5)+도로명번호(7)
    private String rn; // 도로명
    private String udrtYn; // PK2 지하여부
    private int buldMnnm; // PK3 건물본번
    private int buldSlno; // PK4 건물부번
    private String bdNm; // 건물명
    private String zipNo; // 우편번호
    private String buldUseTy; // 건물용도분류
    private String buldGrYn; // 건물군여부 (0:단독건물, 1:건물군)
    private String hemdNm; // 관할행정동 주민센터에서 법정동을 나누어 관리하기위한 명칭(참고용)(예: xx1동, xx2동)
    private BigDecimal entX; // X좌표
    private BigDecimal entY; // Y좌표
}
