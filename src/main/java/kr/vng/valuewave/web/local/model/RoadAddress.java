package kr.vng.valuewave.web.local.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RoadAddress {
    @SerializedName("address_name") public String addressName; // 전체 도로명 주소
    @SerializedName("region_1depth_name") public String region1depthName; // 지역명1
    @SerializedName("region_2depth_name") public String region2depthName; // 지역명2
    @SerializedName("region_3depth_name") public String region3depthName; // 지역명3
    @SerializedName("road_name") public String roadName; // 도로명
    @SerializedName("underground_yn") public String undergroundYn; // 지하 여부, Y 또는 N
    @SerializedName("main_building_no") public String mainBuildingNo; // 건물 본번
    @SerializedName("sub_building_no") public String subBuildingNo; // 건물 부번, 없을 경우 빈 문자열("") 반환
    @SerializedName("building_name") public String buildingName; // 건물 이름
    @SerializedName("zone_no") public String zoneNo; // 우편번호(5자리)
    @SerializedName("x") public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    @SerializedName("y") public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
}
