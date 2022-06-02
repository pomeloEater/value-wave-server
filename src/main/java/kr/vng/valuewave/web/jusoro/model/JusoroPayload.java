package kr.vng.valuewave.web.jusoro.model;

import lombok.Data;

import java.util.List;

/**
 * 도로명주소검색솔루션 주소검색 API WebClient DTO
 */
@Data
public class JusoroPayload {

    public Results results;

    @Data
    public class Results {

        public Common common;
        public List<Juso> juso;
    }
}
