package kr.vng.valuewave.web.estate;

import kr.vng.valuewave.utils.ResultMapUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estate")
public class EstateController {

    private final EstateService estateService;
    private static final Logger LOGGER = LogManager.getLogger(EstateController.class);

    @GetMapping("/get-land-book/{pnu}")
    public Object getLandBook(@PathVariable String pnu) {
        return null;
    }

}
