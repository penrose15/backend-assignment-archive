package exalted.posset.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    private long memberId;
    private String name;
    private String email;
    private String phone;
}
