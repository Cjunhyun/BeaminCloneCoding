package example.src.restaurant.model;

import lombok.*;

@Data
public class PostResReviewReq {
    private Integer userIdx;
    private Integer resIdx;
    private Integer menuIdx;
    private String reviewContent;
    private String reviewImg;
    private int rating;
}
