package example.src.test;

import example.src.test.model.GetMypageProfileRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class TestDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int checkUserExist(int userIdx){
        String checkEmailQuery = "select exists(select userIdx from User where userIdx = ? and userStatus = 'ACTIVE')";
        int checkEmailParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public GetMypageProfileRes getMypageProfile(int userId){
        String getProfileQuery = "select u.UserIdx, u.UserID, u.UserName, u.UserIntro, u.UserWebsite, u.UserProfileImgUrl,\n" +
                "       (select count(case when u.UserIdx = p.UserIdx then 1 end)) as postNum,\n" +
                "       (select count(case when u.UserIdx = f.followeeIdx then 1 end) from Follow f) as followerNum,\n" +
                "       (select count(case when u.UserIdx = f.followerIdx then 1 end) from Follow f) as followingNum\n" +
                "    from User u\n" +
                "    left join Post p on u.userIdx = p.UserIdx\n" +
                "    where u.UserStatus = 'ACTIVE' and u.UserIdx = ?";

        return this.jdbcTemplate.queryForObject(getProfileQuery,
                (rs, rowNum)-> new GetMypageProfileRes(
                        rs.getInt("userIdx"),
                        rs.getString("userID"),
                        rs.getString("userName"),
                        rs.getString("userIntro"),
                        rs.getString("userWebsite"),
                        rs.getString("userProfileImgUrl"),
                        rs.getInt("postNum"),
                        rs.getInt("followerNum"),
                        rs.getInt("followingNum")
                ), userId);
    }
}
