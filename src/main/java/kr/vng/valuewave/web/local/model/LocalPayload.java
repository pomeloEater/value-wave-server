package kr.vng.valuewave.web.local.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * 카카오 로컬 API WebClient DTO
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class LocalPayload {
    public List<Document> documents;
    public Meta meta;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Data
    public static class Meta {
        public Integer totalCount; // 검색어에 검색된 문서 수
        public Integer pageableCount; // total_count 중 노출 가능 문서 수 (최대: 45)
        public Boolean isEnd;
        // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 다음 요청 시 page 값을 증가시켜 다음 페이지 요청 가능
    }
}
