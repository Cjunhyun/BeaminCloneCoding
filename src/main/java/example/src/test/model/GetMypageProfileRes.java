package example.src.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMypageProfileRes {
    private int userIdx;
    private String userID;
    private String userName;
    private String userIntro;
    private String userWebsite;
    private String userProfileImg;
    private int postNum;
    private int followerNum;
    private int followingNum;
}
