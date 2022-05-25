package kr.vng.valuewave.mvc;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DefaultDao extends SqlSessionDaoSupport {

    private static final Logger LOGGER = LogManager.getLogger(DefaultDao.class);

    @Resource(name = "sqlSessionFactory")
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public int insert(String queryId) {
        loggingQueryId(queryId);
        return super.getSqlSession().insert(queryId);
    }

    public int insert(String queryId, Object parameterObject) {
        loggingQueryId(queryId, parameterObject);
        return super.getSqlSession().insert(queryId, parameterObject);
    }

    public int update(String queryId) {
        loggingQueryId(queryId);
        return super.getSqlSession().update(queryId);
    }

    public int update(String queryId, Object parameterObject) {
        loggingQueryId(queryId, parameterObject);
        return super.getSqlSession().update(queryId, parameterObject);
    }

    public int delete(String queryId) {
        loggingQueryId(queryId);
        return super.getSqlSession().delete(queryId);
    }

    public int delete(String queryId, Object parameterObject) {
        loggingQueryId(queryId, parameterObject);
        return super.getSqlSession().delete(queryId, parameterObject);
    }

    public <T> T selectOne(String queryId) {
        loggingQueryId(queryId);
        return super.getSqlSession().selectOne(queryId);
    }

    public <T> T selectOne(String queryId, Object parameterObject) {
        loggingQueryId(queryId, parameterObject);
        return super.getSqlSession().selectOne(queryId, parameterObject);
    }

    public <E> List<E> selectList(String queryId) {
        loggingQueryId(queryId);
        return super.getSqlSession().selectList(queryId);
    }

    public <E> List<E> selectList(String queryId, Object parameterObject) {
        loggingQueryId(queryId, parameterObject);
        return super.getSqlSession().selectList(queryId, parameterObject);
    }

    /**
     * 로깅
     * @param queryId
     */
    private void loggingQueryId(String queryId) {
        LOGGER.info("Execute SQL - QUERY ID : {} ", queryId);
    }

    /**
     * 로깅
     * @param queryId
     * @param parameterObject
     */
    private void loggingQueryId(String queryId, Object parameterObject) {
        LOGGER.info("Execute SQL - QUERY ID : {} / PARAMETER : {}", queryId, parameterObject);
    }

    /**
     * 커밋
     */
    public void commit() {
        super.getSqlSession().commit();
    }

}
