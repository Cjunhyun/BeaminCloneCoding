package example.src.test;

import example.config.BaseException;
import example.config.BaseResponse;
import example.src.test.model.GetMypageProfileRes;
import example.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static example.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/test")
public class TestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final TestProvider testProvider;
    @Autowired
    private final TestService testService;
    @Autowired
    private final JwtService jwtService;

    public TestController(TestProvider testProvider, TestService testService, JwtService jwtService){
        this.testProvider = testProvider;
        this.testService = testService;
        this.jwtService = jwtService;
    }

    /**
     * 1.1 마이페이지 회원 정보 조회 API
     * [GET] /test/info/{userIdx}
     * @param userIdx
     * @return GetMypageProfileRes
     * @throws BaseException
     */
    @GetMapping("/info/{userIdx}")
    public BaseResponse<GetMypageProfileRes> mypageProfile(@PathVariable ("userIdx") int userIdx) throws BaseException {
        // 헤더로 받은 JWT를 통해 유저의 인덱스를 추출합니다.
        int userIdxByJwt = jwtService.getUserIdx();
        // JWT로 추출한 유저 인덱스와, 파라미터로 받은 유저 인덱스가 일치하는지 확인합니다(validation 처리용 - 만약 일치하지 않는다면, 큰 문제가 되겠죠??)
        if(userIdx != userIdxByJwt){
            throw new BaseException(INVALID_JWT);
        }
        GetMypageProfileRes getMypageProfileRes = testService.mypageProfile(userIdx);
        return new BaseResponse<>(getMypageProfileRes);
    }

}