package example.src.restaurant;

import example.config.BaseException;
import example.src.restaurant.model.GetResReviewRes;
import example.src.restaurant.model.PostResReviewReq;
import example.src.restaurant.model.PostResReviewRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static example.config.BaseResponseStatus.*;

/**
 * Create Update Delete
 */
@Service
@Slf4j
public class RestaurantService {
    @Autowired
    private final RestaurantDao restaurantDao;

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }
    //========================================

    //식당리뷰 post
    public PostResReviewRes createReview(PostResReviewReq req) throws BaseException {
        try {
            int reviewIdx = restaurantDao.createReview(req);
            return new PostResReviewRes(reviewIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //식당리뷰삭제 patch
    public void deleteReview(int reviewId) throws BaseException {
        try {
            int result = restaurantDao.deleteReview(reviewId);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_REVIEW);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
