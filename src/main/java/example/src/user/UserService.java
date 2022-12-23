package example.src.user;

import example.config.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static example.config.BaseResponseStatus.DATABASE_ERROR;
import static example.config.BaseResponseStatus.DELETE_FAIL_USER;

/**
 * Create Update Delete
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    //========================================

    //유저탈퇴 patch
    public void deleteUser(int userIdx) throws BaseException {
        try {
            int result = userDao.deleteUser(userIdx);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_USER);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
