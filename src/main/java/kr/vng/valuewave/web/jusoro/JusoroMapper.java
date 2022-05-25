package kr.vng.valuewave.web.jusoro;

import kr.vng.valuewave.web.jusoro.model.Juso;
import kr.vng.valuewave.web.jusoro.model.JusoroEntrc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JusoroMapper {

    List<JusoroEntrc> searchByPnuCode(List<Juso> jusoList);
}
