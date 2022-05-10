package kr.vng.valuewave.web.local.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class RoadAddress {
    public String addressName; // 전체 도로명 주소
    @JsonProperty("region_1depth_name") public String region1depthName; // 지역명1
    @JsonProperty("region_2depth_name") public String region2depthName; // 지역명2
    @JsonProperty("region_3depth_name") public String region3depthName; // 지역명3
    public String roadName; // 도로명
    public String undergroundYn; // 지하 여부, Y 또는 N
    public String mainBuildingNo; // 건물 본번
    public String subBuildingNo; // 건물 부번, 없을 경우 빈 문자열("") 반환
    public String buildingName; // 건물 이름
    public String zoneNo; // 우편번호(5자리)
    public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
}
