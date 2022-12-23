package example.src.restaurant;

import example.config.BaseException;
import example.src.restaurant.model.GetCategoryRes;
import example.src.restaurant.model.GetResMenuRes;
import example.src.restaurant.model.GetResReviewRes;
import example.src.user.model.GetUserAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static example.config.BaseResponseStatus.DATABASE_ERROR;


/**
 * Read
 */
@Service
@Slf4j
public class RestaurantProvider {
    @Autowired
    private final RestaurantDao restaurantDao;

    public RestaurantProvider(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }
    //========================================

    //식당리뷰 get
    public List<GetResReviewRes> getResReview(int resIdx) throws BaseException {
        try {
            List<GetResReviewRes> getResReview = restaurantDao.getResReview(resIdx);
            return getResReview;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //카테고리별 식당 반환 get
    public List<GetCategoryRes> getCategoryRes (String category) throws BaseException {
        try {
            List<GetCategoryRes> getCategoryRes = restaurantDao.getCategoryRes(category);
            return getCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //한 식당의 메뉴 get
    public List<GetResMenuRes> getResMenu (int resIdx) throws BaseException {
        try {
            List<GetResMenuRes> getResMenu = restaurantDao.getResMenu(resIdx);
            return getResMenu;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
