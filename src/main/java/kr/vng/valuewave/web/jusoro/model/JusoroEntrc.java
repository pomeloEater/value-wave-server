package kr.vng.valuewave.web.jusoro.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JusoroEntrc {
    private String sgg_cd; // 시군구코드
    private String ent_cd; // 출입구일련번호
    private String bjd_cd; // PK5 법정동코드: 시군구코드(5) + 읍면동코드(3) + 00
    private String si_nm; // 시도명
    private String sgg_nm; // 시군구명
    private String emd_nm; // 읍면동명
    private String rn_mgt_sn; // PK1 도로명코드: 시군구코드(5)+도로명번호(7)
    private String rn; // 도로명
    private String udrt_yn; // PK2 지하여부
    private int buld_mnnm; // PK3 건물본번
    private int buld_slno; // PK4 건물부번
    private String bd_nm; // 건물명
    private String zip_no; // 우편번호
    private String buld_use_ty; // 건물용도분류
    private String buld_gr_yn; // 건물군여부 (0:단독건물, 1:건물군)
    private String hemd_nm; // 관할행정동 주민센터에서 법정동을 나누어 관리하기위한 명칭(참고용)(예: xx1동, xx2동)
    private BigDecimal ent_x; // X좌표
    private BigDecimal ent_y; // Y좌표
}
