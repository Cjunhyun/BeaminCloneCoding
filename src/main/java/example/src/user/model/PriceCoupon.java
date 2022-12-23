package example.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceCoupon {
    private int deductPrice;
    private int minPrice;
}
