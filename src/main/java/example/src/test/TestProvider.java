package example.src.test;

import example.config.BaseException;
import example.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static example.config.BaseResponseStatus.*;
@Service
public class TestProvider {

    private final TestDao testDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestProvider(TestDao testDao, JwtService jwtService) {
        this.testDao = testDao;
        this.jwtService = jwtService;
    }

    /*
    유저 상태를 확인하는 함수
    1. 존재하는 유저인가?
    2. Active한 유저인가?
     */
    public int checkUserExist(int userIdx) throws BaseException {
        try{
            return testDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
