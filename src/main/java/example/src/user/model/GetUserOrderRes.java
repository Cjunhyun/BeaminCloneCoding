package example.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserOrderRes {
    private int orderIdx;
    private int resIdx;
    private String paymentMethod;
    private int totalPrice;
    private String request;
}
