package example.src.user;

import example.config.BaseException;
import example.config.BaseResponse;
import example.src.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;

    public UserController(UserProvider userProvider, UserService userService) {
        this.userProvider = userProvider;
        this.userService = userService;
    }
    //========================================

    //유저정보 get
    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<GetUserRes> getUsers(@PathVariable int userIdx) {
        try {
            GetUserRes getUserRes = userProvider.getUser(userIdx);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저쿠폰 get
    @ResponseBody
    @GetMapping("/coupon/{userIdx}")
    public BaseResponse<GetUserCouponRes> getCoupon(@PathVariable int userIdx) {
        try {
            GetUserCouponRes getUserCouponRes = userProvider.getCoupon(userIdx);
            return new BaseResponse<>(getUserCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저주소목록 get
    @ResponseBody
    @GetMapping("/address/{userIdx}")
    public BaseResponse<List<GetUserAddress>> getUserAddressList(@PathVariable int userIdx) {
        try {
            List<GetUserAddress> getUserAddress = userProvider.getAddress(userIdx);
            return new BaseResponse<>(getUserAddress);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저탈퇴 patch
    @ResponseBody
    @PatchMapping("/{userIdx}")
    public BaseResponse<String> deleteUser(@PathVariable int userIdx) {
        try {
            userService.deleteUser(userIdx);
            String result = "회원탈퇴가 완료되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 찜한 식당 목록 get
    @ResponseBody
    @GetMapping("/like/{userIdx}")
    public BaseResponse<List<GetUserLikeRes>> getUserLike(@PathVariable int userIdx) {
        try {
            List<GetUserLikeRes> getUserLikeRes = userProvider.getUserLike(userIdx);
            return new BaseResponse<>(getUserLikeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 주문내역 목록 get
    @ResponseBody
    @GetMapping("/order/{userIdx}")
    public BaseResponse<List<GetUserOrderRes>> getUserOrder(@PathVariable int userIdx) {
        try {
            List<GetUserOrderRes> getUserOrder = userProvider.getUserOrder(userIdx);
            return new BaseResponse<>(getUserOrder);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 쿠폰적용 주문전체금액 post
    @ResponseBody
    @PostMapping("/coupon")
    public BaseResponse<OrderTotalPrice> getCouponPrice(@RequestBody GetCouponPriceReq req) {
        try {
            OrderTotalPrice totalPrice = userProvider.getTotalPrice(req);
            return new BaseResponse<>(totalPrice);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
