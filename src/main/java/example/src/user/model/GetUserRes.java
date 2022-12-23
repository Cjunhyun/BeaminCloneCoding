package example.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private String email;
    private String password;
    private String phone;
    private String nickName;
    private String grade;
    private String address;
    private String status;
}
