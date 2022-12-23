package example.src.restaurant;

import example.src.restaurant.model.*;
import example.src.user.model.GetUserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //========================================

    //식당리뷰 get
    public List<GetResReviewRes> getResReview(int resIdx) {
        String getResReviewQuery = "select * from Review where resIdx = ?";
        int getResReviewParams = resIdx;

        return this.jdbcTemplate.query(getResReviewQuery,
                (rs, rowNum) -> new GetResReviewRes(
                        rs.getInt("resIdx"),
                        rs.getString("status"),
                        rs.getInt("rating"),
                        rs.getString("reviewContent"),
                        rs.getString("reviewImg")),
                getResReviewParams);
    }

    //식당리뷰 post
    public int createReview(PostResReviewReq req) {
        String createReviewQuery = "insert into Review (userIdx,resIdx,menuIdx,reviewContent,reviewImg,rating) values(?,?,?,?,?,?)";
        Object[] createReviewParams = new Object[]{
                req.getUserIdx(), req.getResIdx(), req.getMenuIdx(), req.getReviewContent(), req.getReviewImg(), req.getRating()
        };
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    //식당리뷰삭제 patch
    public int deleteReview(int reviewIdx) {
        String deleteUserQuery = "update Review set status ='D' where reviewIdx = ?";
        int deleteUserParams = reviewIdx;
        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }

    //카테고리별 식당 반환 get
    public List<GetCategoryRes> getCategoryRes (String category) {
        String getCategoryResQuery = "select * from Restaurant where category = ?";
        String getCategoryResParams = category;

        return this.jdbcTemplate.query(getCategoryResQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("resIdx"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("phone"),
                        rs.getFloat("rating"),
                        rs.getInt("minPrice")),
                getCategoryResParams);
    }

    //한 식당의 메뉴 get
    public List<GetResMenuRes> getResMenu(int resIdx) {
        String getResMenuQuery = "select * from Menu where resIdx = ?";
        int getResMenuParams = resIdx;

        return this.jdbcTemplate.query(getResMenuQuery,
                (rs, rowNum) -> new GetResMenuRes(
                        rs.getInt("menuIdx"),
                        rs.getString("status"),
                        rs.getString("menuName"),
                        rs.getInt("price"),
                        rs.getString("menuImg"),
                        rs.getString("category"),
                        rs.getInt("popular")),
                getResMenuParams);
    }
}
