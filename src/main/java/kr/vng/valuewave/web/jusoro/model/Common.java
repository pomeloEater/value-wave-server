package kr.vng.valuewave.web.jusoro.model;

import lombok.Data;

/**
 * 도로명주소검색솔루션 주소검색 API 검색정보
 */
@Data
public class Common {

    private String totalCount; // 총 검색 데이터수
    private int currentPage; // 페이지 번호
    private int countPerPage; // 페이지당 출력할 결과 Row 수
    private String errorCode; // 에러 코드
    private String errorMessage; // 에러 메시지
}
