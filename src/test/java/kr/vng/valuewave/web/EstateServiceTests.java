package kr.vng.valuewave.web;

import kr.vng.valuewave.web.estate.EstateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EstateServiceTests {

    @Autowired
    private EstateService estateService;
    private final static Logger LOGGER = LogManager.getLogger(EstateServiceTests.class);

    @Test
    public void 토지대장_메서드_실행() {
        // given
        String pnu = "";

        // then
        estateService.getLandBook(pnu);
        estateService.getOfficialLandPrice(pnu);
        estateService.getOfficialLandPriceForStandard(pnu);
        estateService.getShareLandOwnership(pnu);
        estateService.getIndividualHousePrice(pnu);
        estateService.getApartmentHousePrice(pnu);
    }

    @Test
    public void 건축물대장_메서드_실행() {
//        // given
//        String pnu = "";
//
//        // set
//        String bldrgstPk = estateService.getBldrgstPk(pnu);
//        LOGGER.info(bldrgstPk);
//
//        // then
//        estateService.getBuldBook(pnu);
//        estateService.getRecapTitle(pnu);
//        estateService.getTitle(pnu);
//        estateService.getFloor(pnu);
//        estateService.getExpos(pnu);
//        estateService.getExposPublicArea(pnu);
//        estateService.getAttachedJibun(pnu);
//        estateService.getWclf(pnu);
//        estateService.getJijigu(pnu);
//        estateService.getOwner(pnu);
//        estateService.getHousePrice(pnu);
    }
}
