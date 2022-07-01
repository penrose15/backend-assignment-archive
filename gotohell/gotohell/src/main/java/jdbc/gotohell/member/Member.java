package jdbc.gotohell.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    private Long memberId;
    private String email;
    private String name;
    private String phone;
}
