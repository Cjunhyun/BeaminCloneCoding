package example.src.user;


import example.config.BaseException;
import example.src.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static example.config.BaseResponseStatus.DATABASE_ERROR;
import static example.config.BaseResponseStatus.USER_PRICE;

/**
 * Read
 */
@Service
@Slf4j
public class UserProvider {
    @Autowired
    private final UserDao userDao;

    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }
    //========================================

    //유저정보 get
    public GetUserRes getUser(int userIdx) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(userIdx);
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //유저쿠폰 get
    public GetUserCouponRes getCoupon(int userIdx) throws BaseException {
        try {
            GetUserCouponRes getUserCouponRes = userDao.getCoupon(userIdx);
            return getUserCouponRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //유저주소목록 get
    public List<GetUserAddress> getAddress(int userIdx) throws BaseException {
        try {
            List<GetUserAddress> getAddress = userDao.getAddress(userIdx);
            return getAddress;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //유저 찜한 식당 목록 get
    public List<GetUserLikeRes> getUserLike(int userIdx) throws BaseException {
        try {
            List<GetUserLikeRes> getUserLike = userDao.getUserLike(userIdx);
            return getUserLike;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //유저 주문내역 목록 get
    public List<GetUserOrderRes> getUserOrder(int userIdx) throws BaseException {
        try {
            List<GetUserOrderRes> getUserOrder = userDao.getUserOrder(userIdx);
            return getUserOrder;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //쿠폰적용 후 주문금액 get
    public OrderTotalPrice getTotalPrice(GetCouponPriceReq req) throws BaseException {
        int userIdx = req.getUserIdx();
        int couponIdx = req.getCouponIdx();
        int orderIdx = req.getOrderIdx();

        //쿠폰 할인금액과 최소주문금액
        PriceCoupon couponPrice = userDao.getCouponPrice(couponIdx);
        int discountPrice = couponPrice.getDeductPrice();
        int minPrice = couponPrice.getMinPrice();

        //할인전 주문전체금액
        OrderTotalPrice orderTotalPrice = userDao.OrderTotalPrice(orderIdx);
        int beforeTotalPrice = orderTotalPrice.getTotalPrice();

        //최소주문금액 조건에 맞는지 확인
        if (minPrice <= beforeTotalPrice) {
            int newTotalPrice = beforeTotalPrice - discountPrice;
            return new OrderTotalPrice(newTotalPrice);
        } else {
            throw new BaseException(USER_PRICE);
        }
    }


}
