package kr.vng.valuewave.web.local.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class LocalPayload {
    @SerializedName("documents") List<Document> documents;
    @SerializedName("meta") Meta meta;

    @Data
    public static class Meta {
        @SerializedName("total_count") public Integer totalCount; // 검색어에 검색된 문서 수
        @SerializedName("pageable_count") public Integer pageableCount; // total_count 중 노출 가능 문서 수 (최대: 45)
        @SerializedName("is_end") public Boolean isEnd;
        // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 다음 요청 시 page 값을 증가시켜 다음 페이지 요청 가능
    }
}
