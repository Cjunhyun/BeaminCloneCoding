package example.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GetCouponPriceReq {
    private int userIdx;
    private int couponIdx;
    private int orderIdx;
}
