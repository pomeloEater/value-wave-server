package kr.vng.valuewave.mvc;

import com.google.common.base.CaseFormat;

import java.util.HashMap;

public class DefaultMap extends HashMap {

    public Object put(Object key, Object value) {
        return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, (String) key), value);
    }
}
