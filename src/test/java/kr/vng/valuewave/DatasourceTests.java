package kr.vng.valuewave;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DatasourceTests {
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    private static final Logger LOGGER = LogManager.getLogger(DatasourceTests.class);
    
    @Test
    public void 오라클_연결_테스트() {
        try(Connection con = sqlSessionFactory.openSession().getConnection()) {
            LOGGER.info("연결 성공");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
