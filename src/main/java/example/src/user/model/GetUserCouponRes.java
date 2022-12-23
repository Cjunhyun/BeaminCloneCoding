package example.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserCouponRes {
    private int couponIdx;
    private String status;
    private String couponName;
    private String couponContent;
    private int deductPrice;
    private int minPrice;
}
