package kr.vng.valuewave.web.local.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class Address {
    public String addressName; // 전체 지번 주소
    @JsonProperty("region_1depth_name") public String region1depthName; // 지역 1 Depth, 시도 단위
    @JsonProperty("region_2depth_name") public String region2depthName; // 지역 2 Depth, 구 단위
    @JsonProperty("region_3depth_name") public String region3depthName; // 지역 3 Depth, 동 단위
    @JsonProperty("region_3depth_h_name") public String region3depthHName; // 지역 3 Depth, 행정동 명칭
    public String hCode; // 행정 코드
    public String bCode; // 법정 코드
    public String mountainYn; // 산 여부, Y 또는 N
    public String mainAddressNo; // 지번 주번지
    public String subAddressNo; // 지번 부번지, 없을 경우 빈 문자열("") 반환
    public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
}
