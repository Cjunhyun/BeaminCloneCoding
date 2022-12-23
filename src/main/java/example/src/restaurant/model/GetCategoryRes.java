package example.src.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCategoryRes {
    private int resIdx;
    private String name;
    private String address;
    private int phone;
    private float rating;
    private int minPrice;
}
