package kr.vng.valuewave.web.local.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Document {
    @SerializedName("address_name") public String addressName; // 전체 지번 주소 또는 전체 도로명 주소, 입력에 따라 결정됨
    /**
     * address_type
     * address_name의 값의 타입(Type)
     * REGION(지명) / ROAD(도로명) / REGION_ADDR(지번 주소) / ROAD_ADDR(도로명 주소)
     */
    @SerializedName("address_type") public String addressType;
    @SerializedName("x") public String x; // X 좌표값, 경위도인 경우 경도(longitude)
    @SerializedName("y") public String y; // Y 좌표값, 경위도인 경우 위도(latitude)
    @SerializedName("address") public Address address; // 지번 주소 상세 정보, 아래 Address 참고
    @SerializedName("road_address") public RoadAddress roadAddress; // 도로명 주소 상세 정보, 아래 RoadAaddress 참고

    /** 좌표로 행정구역정보 받기 */
    @SerializedName("region_type") public String regionType; // H(행정동) 또는 B(법정동)
    @SerializedName("region_1depth_name") public String region1depthName; // 지역 1Depth, 시도 단위, 바다 영역은 존재하지 않음
    @SerializedName("region_2depth_name") public String region2depthName; // 지역 2Depth, 구 단위, 바다 영역은 존재하지 않음
    @SerializedName("region_3depth_name") public String region3depthName; // 지역 3Depth, 동 단위, 바다 영역은 존재하지 않음
    @SerializedName("region_4depth_name") public String region4depthName; // 지역 4Depth, region_type이 법정동이며, 리 영역인 경우만 존재
    @SerializedName("code") public String code; // region 코드
}
