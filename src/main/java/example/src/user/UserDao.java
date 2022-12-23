package example.src.user;

import example.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //========================================

    //유저정보 get
    public GetUserRes getUser(int userIdx) {
        String getUserQuery = "select * from User where userIdx = ?";
        int getUserParams = userIdx;

        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("nickName"),
                        rs.getString("grade"),
                        rs.getString("address"),
                        rs.getString("status")),
                getUserParams);
    }

    //유저쿠폰 get
    public GetUserCouponRes getCoupon(int userIdx) {
        String getCouponQuery = "select * from Coupon where userIdx = ?";
        int getCouponParams = userIdx;

        return this.jdbcTemplate.queryForObject(getCouponQuery,
                (rs, rowNum) -> new GetUserCouponRes(
                        rs.getInt("couponIdx"),
                        rs.getString("status"),
                        rs.getString("couponName"),
                        rs.getString("couponContent"),
                        rs.getInt("deductPrice"),
                        rs.getInt("minPrice")),
                getCouponParams);
    }

    //유저주소목록 get
    public List<GetUserAddress> getAddress(int userIdx) {
        String getAddressQuery = "select * from UserAddress where userIdx = ?";
        int getAddressParams = userIdx;

        return this.jdbcTemplate.query(getAddressQuery,
                (rs, rowNum) -> new GetUserAddress(
                        rs.getInt("userIdx"),
                        rs.getString("status"),
                        rs.getString("address")),
                getAddressParams);
    }

    //유저탈퇴 patch
    public int deleteUser(int userIdx) {
        String deleteUserQuery = "update User set status ='D' where userIdx = ?";
        int deleteUserParams = userIdx;

        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }

    //유저 찜한 식당 목록 get
    public List<GetUserLikeRes> getUserLike(int userIdx) {
        String getUserLikeQuery = "select * from Liking where userIdx = ?";
        int getUserLikeParams = userIdx;

        return this.jdbcTemplate.query(getUserLikeQuery,
                (rs, rowNum) -> new GetUserLikeRes(
                        rs.getInt("resIdx"),
                        rs.getString("status")),
                getUserLikeParams);
    }

    //유저 주문내역 목록 get
    public List<GetUserOrderRes> getUserOrder(int userIdx) {
        String getUserOrderQuery = "select * from OrderT where userIdx = ?";
        int getUserOrderParams = userIdx;

        return this.jdbcTemplate.query(getUserOrderQuery,
                (rs, rowNum) -> new GetUserOrderRes(
                        rs.getInt("orderIdx"),
                        rs.getInt("resIdx"),
                        rs.getString("paymentMethod"),
                        rs.getInt("totalPrice"),
                        rs.getString("request")),
                getUserOrderParams);
    }

    //쿠폰 차감금액과 최소금액 반환
    public PriceCoupon getCouponPrice(int couponIdx) {
        String getCouponPriceQuery = "select * from Coupon where couponIdx = ?";
        int getCouponParams = couponIdx;

        return this.jdbcTemplate.queryForObject(getCouponPriceQuery,
                (rs, rowNum) -> new PriceCoupon(
                        rs.getInt("deductPrice"),
                        rs.getInt("minPrice")),
                getCouponParams);
    }

    //주문 전체금액 반환
    public OrderTotalPrice OrderTotalPrice(int orderIdx) {
        String getTotalPriceQuery = "select * from OrderT where orderIdx = ?";
        int getTotalPriceParams = orderIdx;

       return this.jdbcTemplate.queryForObject(getTotalPriceQuery,
               (rs,rowNum)-> new OrderTotalPrice(
                       rs.getInt("totalPrice")),
               getTotalPriceParams);
    }


}
