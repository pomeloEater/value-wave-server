package kr.vng.valuewave.utils;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 결과 전달 util
 */
@NoArgsConstructor
public class ResultMapUtil {

    private static final String RESULT_KEY = "_result_";
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";


    public static Map<String, Object> success() {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, SUCCESS);
        return map;
    }

    public static Map<String, Object> success(Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, SUCCESS);
        map.put("data", object);
        return map;
    }

    public static Map<String, Object> success(String key, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, SUCCESS);
        map.put(key, object);
        return map;
    }

    public static Map<String, Object> failed() {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, FAILED);
        return map;
    }

    public static Map<String, Object> failed(Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, FAILED);
        map.put("data", object);
        return map;
    }

    public static Map<String, Object> failed(String key, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESULT_KEY, FAILED);
        map.put(key, object);
        return map;
    }

}
