package example.src.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetResReviewRes {
    private int resIdx;
    private String status;
    private int rating;
    private String reviewContent;
    private String reviewImg;
}
