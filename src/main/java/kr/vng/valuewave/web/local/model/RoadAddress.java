package kr.vng.valuewave.web.local.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RoadAddress {
    @SerializedName("address_name") public String address_name; // 전체 도로명 주소
    @SerializedName("region_1depth_name") public String region_1depth_name; // 지역명1
    @SerializedName("region_2depth_name") public String region_2depth_name; // 지역명2
    @SerializedName("region_3depth_name") public String region_3depth_name; // 지역명3
    @SerializedName("road_name") public String road_name; // 도로명
    @SerializedName("underground_yn") public String underground_yn; // 지하 여부, Y 또는 N
    @SerializedName("main_building_no") public String main_building_no; // 건물 본번
    @SerializedName("sub_building_no") public String sub_building_no; // 건물 부번, 없을 경우 빈 문자열("") 반환
    @SerializedName("building_name") public String building_name; // 건물 이름
    @SerializedName("zone_no") public String zone_no; // 우편번호(5자리)
    @SerializedName("x") public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    @SerializedName("y") public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
}
