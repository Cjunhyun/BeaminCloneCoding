package example.src.restaurant;


import example.config.BaseException;
import example.config.BaseResponse;
import example.src.restaurant.model.*;
import example.src.user.model.GetUserRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static example.config.BaseResponseStatus.POST_REVIEW_NULL;

@RestController
@Slf4j
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private final RestaurantProvider restaurantProvider;
    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantProvider restaurantProvider, RestaurantService restaurantService) {
        this.restaurantProvider = restaurantProvider;
        this.restaurantService = restaurantService;
    }
    //========================================

    //식당리뷰 get
    @ResponseBody
    @GetMapping("/review/{resIdx}")
    public BaseResponse<List<GetResReviewRes>> getResReview(@PathVariable int resIdx) {
        try {
            List<GetResReviewRes> getResReviewRes = restaurantProvider.getResReview(resIdx);
            return new BaseResponse<>(getResReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //식당리뷰 post
    @PostMapping("/review")
    @ResponseBody
    public BaseResponse<PostResReviewRes> createReview(@RequestBody PostResReviewReq postResReviewReq) {
        //유저인덱스,식당인덱스,메뉴인덱스 null값 확인
        if (postResReviewReq.getUserIdx() == null || postResReviewReq.getResIdx() == null || postResReviewReq.getMenuIdx() == null) {
            return new BaseResponse<>(POST_REVIEW_NULL);
        }
        try {
            PostResReviewRes postResReviewRes = restaurantService.createReview(postResReviewReq);
            return new BaseResponse<>(postResReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //식당리뷰삭제 patch
    @ResponseBody
    @PatchMapping("/review/{reviewId}")
    public BaseResponse<String> deleteReview(@PathVariable int reviewId) {
        try {
            restaurantService.deleteReview(reviewId);
            String result = "식당리뷰삭제가 완료되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //카테고리별 식당 반환 get
    @ResponseBody
    @GetMapping("/{category}")
    public BaseResponse<List<GetCategoryRes>> getCategoryRes(@PathVariable String category) {
        try {
            List<GetCategoryRes> getCategoryRes = restaurantProvider.getCategoryRes(category);
            return new BaseResponse<>(getCategoryRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //한 식당의 메뉴 get
    @ResponseBody
    @GetMapping("/menu/{resIdx}")
    public BaseResponse<List<GetResMenuRes>> getResMenu(@PathVariable int resIdx) {
        try {
            List<GetResMenuRes> getResMenu = restaurantProvider.getResMenu(resIdx);
            return new BaseResponse<>(getResMenu);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
