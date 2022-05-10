package kr.vng.valuewave.web.local.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Address {
    @SerializedName("address_name") public String address_name; // 전체 지번 주소
    @SerializedName("region_1depth_name") public String region_1depth_name; // 지역 1 Depth, 시도 단위
    @SerializedName("region_2depth_name") public String region_2depth_name; // 지역 2 Depth, 구 단위
    @SerializedName("region_3depth_name") public String region_3depth_name; // 지역 3 Depth, 동 단위
    @SerializedName("region_3depth_h_name") public String region_3depth_h_name; // 지역 3 Depth, 행정동 명칭
    @SerializedName("h_code") public String h_code; // 행정 코드
    @SerializedName("b_code") public String b_code; // 법정 코드
    @SerializedName("mountain_yn") public String mountain_yn; // 산 여부, Y 또는 N
    @SerializedName("main_address_no") public String main_address_no; // 지번 주번지
    @SerializedName("sub_address_no") public String sub_address_no; // 지번 부번지, 없을 경우 빈 문자열("") 반환
    @SerializedName("x") public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    @SerializedName("y") public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
}
