package kr.vng.valuewave.web;

import kr.vng.valuewave.mvc.DefaultMap;
import kr.vng.valuewave.web.estate.EstateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EstateServiceTests {

    @Autowired
    private EstateService estateService;
    private final static Logger LOGGER = LogManager.getLogger(EstateServiceTests.class);

    @Test
    public void 토지대장_메서드_실행() {
        // given
        String pnu = "4219010100106620000";

        // then
        LOGGER.info(estateService.getLandBook(pnu));
        LOGGER.info(estateService.getOfficialLandPrice(pnu));
        LOGGER.info(estateService.getOfficialLandPriceForStandard(pnu));
        LOGGER.info(estateService.getShareLandOwnership(pnu));
        LOGGER.info(estateService.getIndividualHousePrice(pnu));
        LOGGER.info(estateService.getApartmentHousePrice(pnu));
    }

    @Test
    public void 건축물대장_메서드_실행() {
        // given
        String pnu = "4219010100106620000";

        // then
        List<DefaultMap> buldBookList = estateService.getBuldBookByPnu(pnu);
        LOGGER.info(buldBookList);
        String bldrgstPk = buldBookList.get(0).get("bldrgstPk").toString();
        LOGGER.info(bldrgstPk);
        LOGGER.info(estateService.getRecapTitle(bldrgstPk));
        LOGGER.info(estateService.getTitle(bldrgstPk));
        LOGGER.info(estateService.getFloor(bldrgstPk));
        LOGGER.info(estateService.getExposByPnu(pnu));
        LOGGER.info(estateService.getAttachedJibun(bldrgstPk));
        LOGGER.info(estateService.getWclf(bldrgstPk));
        LOGGER.info(estateService.getJijigu(bldrgstPk));
        LOGGER.info(estateService.getOwner(bldrgstPk));
        LOGGER.info(estateService.getHousePrice(bldrgstPk));
    }
}
