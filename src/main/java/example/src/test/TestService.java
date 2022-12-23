package example.src.test;

import example.config.BaseException;
import example.src.test.model.GetMypageProfileRes;
import example.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static example.config.BaseResponseStatus.*;
// Service Create, Update, Delete 의 로직 처리
@Service
public class TestService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TestDao testDao;
    private final TestProvider testProvider;
    private final JwtService jwtService;

    @Autowired
    public TestService(TestDao testDao, TestProvider testProvider, JwtService jwtService) {
        this.testDao = testDao;
        this.testProvider = testProvider;
        this.jwtService = jwtService;

    }

    public GetMypageProfileRes mypageProfile (int userIdx)throws BaseException {
        try{
            // 유효한 유저인지 확인
            if(testProvider.checkUserExist(userIdx) == 0){
                throw new BaseException(USER_INVALID);
            }
            GetMypageProfileRes getMypageProfileRes = testDao.getMypageProfile(userIdx);
            return getMypageProfileRes;
        }
        catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
