package example.src.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetResMenuRes {
    private int menuIdx;
    private String status;
    private String menuName;
    private int price;
    private String menuImg;
    private String category;
    private int popular;
}
